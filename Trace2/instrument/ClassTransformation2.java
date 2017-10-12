package instrument;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import javassist.expr.MethodCall;
import javassist.expr.ExprEditor;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Bytecode;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.Mnemonic;
import javassist.bytecode.BadBytecode;

import java.util.*;

public class ClassTransformation2 {

   private static final String BCI = "iinc";

   private static boolean instrumentIfCmp = System.getProperty ("instrument.cmp")!=null;
   private static boolean instrumentInc   = System.getProperty ("instrument.inc")!=null;
   private static boolean instrumentGo    = System.getProperty ("instrument.go")!=null;

   private static final String HELPER_METHOD_NAME = "javassist$pi";
   private static final String HELPER_METHOD_SIGNATURE = "()V";
   //private static final String CODE_METHOD = "static void "+HELPER_METHOD_NAME+"(){System.out.print(Thread.currentThread().getStackTrace()[2].toString());System.out.println (\"/"+BCI+"; index=?\");}";

   //private static final String CODE_METHOD = "static void "+HELPER_METHOD_NAME+"(){System.out.print(Thread.currentThread().getStackTrace()[2].toString());System.out.print (\"/"+BCI+"; \"); System.out.println (javassist$ARG);}";

   private static final String makeCode (final String b, final String arg) {
      final StringBuffer sb = new StringBuffer ();
      sb.append ("static void ");
      sb.append (HELPER_METHOD_NAME);
      sb.append ("(){System.out.print(Thread.currentThread().getStackTrace()[2].toString());System.out.println (\"/");
      sb.append (b);
      sb.append ("; ");
      sb.append (arg);
      sb.append ("\");}");
      return sb.toString();
   }

   private static final String makeCode3 () {
      final StringBuffer sb = new StringBuffer ();
      sb.append ("static void javassist$pi3(int i, int j, int k) {");
      sb.append ("System.out.print(Thread.currentThread().getStackTrace()[2].toString());");
      sb.append ("System.out.print(\"; \");");
      sb.append ("if(i==132)System.out.print(\"iinc\");else if(i==167)System.out.print(\"go \");else System.out.print(i);");
      sb.append ("System.out.print(\"; \");");
      sb.append ("if(k!=0)System.out.print((char)('A'+j));else System.out.print(j);");
      sb.append ("System.out.print(\" \");");
      sb.append ("System.out.print(k);");
      sb.append ("System.out.println();");
      sb.append ("}");
      return sb.toString();
   }

   private static final boolean instrumentMain = false;

   private static final boolean IsToBeInstrumented (final String name) {
      if (name.startsWith ("javassist$")) return false;
      if (!instrumentMain && name.equals("main")) return false;
      return (true);
   }


   public static void main (final String[] args) throws Exception {
      for (final String arg: args) main (arg);
   }

   public static void main (final String arg) throws Exception {
      count=0;
      map.clear();
      final CtClass clas = ClassPool.getDefault().get(arg);
      if (clas == null) {
         System.err.printf ("Class '%s' not found.%n", arg);
      } else {
         System.out.printf ("%nModifing CLASS '%s' ...%n", arg);

         final ClassPool cp = clas.getClassPool();

         /*
         //  Add a static field to the class in which to pass arguments
         final CtClass stringClazz = cp.get("java.lang.String");
         final CtField f = new CtField (stringClazz, "javassist$ARG", clas);
         f.setModifiers (javassist.Modifier.STATIC);
         clas.addField(f, CtField.Initializer.constant ("arg"));
         */

         
         if (instrumentInc || instrumentIfCmp || instrumentGo) {
            try {
               clas.getDeclaredMethod (HELPER_METHOD_NAME);
            } catch (NotFoundException ex) {
               final CtMethod m = CtNewMethod.make(makeCode3(),clas);
               clas.addMethod(m);  // DuplicateMemberException
            }
         }

         modify (clas);
         clas.writeFile();   // Overwrite existing class file!
         System.out.printf ("Modified CLASS '%s'.%n", arg);
      }
   }

   private static void modify (final CtClass c) throws CannotCompileException, BadBytecode {
      final CtMethod[] methods = c.getDeclaredMethods();
      for (final CtMethod method: methods) modify (c, method);
   }


   private static String CODE_STACK = "System.out.print (Thread.currentThread().getStackTrace()[1].toString());";
   private static String CODE_SET = "System.out.print (\"/set; index=\");";
   private static String CODE_GET = "System.out.print (\"/get; index=\");";
   private static String CODE_INDEX= "System.out.print ($1);";
   private static String CODE_SEP1 = "System.out.print (\"; array (AFTER) =\");";
   private static String CODE_SEP2 = "System.out.print (\"; array=\");";
   private static String CODE_THIS= "System.out.print ($0.toString());";
   private static String CODE_END = "System.out.println ();";


   private static void modify (final CtClass c, final CtMethod m) throws CannotCompileException, BadBytecode {
      final MethodInfo methodInfo = m.getMethodInfo();
      final String name = methodInfo.getName();
      if (! IsToBeInstrumented (name)) return;

      System.out.printf ("Modifing method '%s'...%n", name);


      /* 1.
         Instrument some call sites.
      */
      CallEditor.instrument (m);


      /* 2.
         Walk the byte code again differently
      */

      final CodeAttribute ca = methodInfo.getCodeAttribute();
      final CodeIterator ci = ca.iterator();
      while (ci.hasNext()) {
         final int index = ci.next();
         final int op = ci.byteAt(index);
         final String opcode =Mnemonic.OPCODE[op];
         if (instrumentGo && opcode.startsWith ("go")) {
            System.out.printf("Found byte code instruction '%s'%n", Mnemonic.OPCODE[op]);
            final Bytecode code = new Bytecode(methodInfo.getConstPool());
            code.addIconst (op);
            code.addIconst (0);
            code.addIconst (0);
            code.addInvokestatic (c, "javassist$pi3", "(III)V");
            ci.insert(index, code.get());
         } else if (instrumentInc && opcode.equals ("iinc")) {
            // Danger  iinc could be modified with byte code 'wide'!!!!
            final int l = ci.byteAt(index+1);
            final int v = ci.byteAt(index+2);
            final int arg = get (name, l);
            //System.out.println (makeCode (opcode, String.format ("%s %d", arg,v)));
            System.out.printf("Found byte code instruction '%s %d[%c] %d'%n", Mnemonic.OPCODE[op], l, (char)('A'+arg), v);
            
            final Bytecode code = new Bytecode(methodInfo.getConstPool());
            //code.addInvokestatic (c, HELPER_METHOD_NAME, HELPER_METHOD_SIGNATURE);
            code.addIconst (op);
            code.addIconst (arg); // change to value using map
            code.addIconst (v);
            code.addInvokestatic (c, "javassist$pi3", "(III)V");
            ci.insert(index, code.get());


         } else if (instrumentIfCmp && opcode.startsWith ("if_icmp")) {
            System.out.printf("Found byte code instruction '%s'%n", Mnemonic.OPCODE[op]);
            final Bytecode code = new Bytecode(methodInfo.getConstPool());
            code.addIconst (op);
            code.addIconst (0);
            code.addIconst (0);
            code.addInvokestatic (c, "javassist$pi3", "(III)V");
            ci.insert(index, code.get());
         }
      }
      ca.computeMaxStack();

      System.out.printf ("Modified method %s.%n", name);
   }

   private static final Map<String,Integer> map = new HashMap<>();
   private static String mangle (final String name, final int i) {
      return String.format ("%s;%d", name, i);
   }
   private static int get (final String name, final int i) {
      return get (mangle (name, i));
   }
   private static int count=0;
   private static int get (final String x) {
      if (map.containsKey (x)) return map.get (x);
      //final char c = (char) ('A' + count);
      map.put (x, count);
      count++;
      return map.get(x);
   }
}