package instrument;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.CannotCompileException;
import javassist.expr.MethodCall;
import javassist.expr.ExprEditor;
import javassist.bytecode.MethodInfo;

public class ClassTransformation {

   public static void main (final String[] args) throws Exception {
      for (final String arg: args) main (arg);
   }

   public static void main (final String arg) throws Exception {
      final CtClass clas = ClassPool.getDefault().get(arg);
      if (clas == null) {
         System.err.printf ("Class '%s' not found.%n", arg);
      } else {
         System.out.printf ("Modifing class '%s' ...%n", arg);
         modify (clas);
         clas.writeFile();   // Overwrite existing class file!
         System.out.printf ("Modified class '%s'.%n", arg);
      }
   }

   private static void modify (final CtClass c) throws CannotCompileException {
      final CtMethod[] methods = c.getDeclaredMethods();
      for (final CtMethod method: methods) modify (method);
   }

   private static void modify (final CtMethod m) throws CannotCompileException {
      final MethodInfo methodInfo = m.getMethodInfo();
      final String name = methodInfo.getName();
      CallEditor.instrument (m);
      System.out.printf ("Modified method %s.%n", name);
   }
}