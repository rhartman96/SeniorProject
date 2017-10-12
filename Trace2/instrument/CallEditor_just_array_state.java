package instrument;

import javassist.CannotCompileException;
import javassist.expr.MethodCall;
import javassist.expr.ExprEditor;
import javassist.bytecode.MethodInfo;

public final class CallEditor extends ExprEditor {

   private CallEditor () { };  // singleton pattern

   private static final ExprEditor EDITOR = new CallEditor ();
   
   public static void instrument (final javassist.CtMethod m) throws CannotCompileException {
      m.instrument (EDITOR);
   }

   private static String CODE_STACK = "System.out.print (Thread.currentThread().getStackTrace()[1].toString());";
   private static String CODE_SET = "System.out.print (\"; set; index=\");";
   private static String CODE_GET = "System.out.print (\"; get; index=\");";
   private static String CODE_REMOVE = "System.out.print (\"; remove; index=\");";
   private static String CODE_ADD = "System.out.print (\"; add\");";
   private static String CODE_INDEX= "System.out.print ($1);";
   private static String CODE_SEP1 = "System.out.print (\"; array (AFTER) =\");";
   private static String CODE_SEP2 = "System.out.print (\"; array=\");";
   private static String CODE_THIS= "System.out.print ($0.toString());";
   private static String CODE_GET_NUM= "System.out.print ($0.get($1));";
   private static String CODE_END = "System.out.println ();";
   // System.out.flush() ??
   /*
   private static String CODE1 = CODE_STACK + CODE_SET + CODE_INDEX + CODE_SEP1 + CODE_THIS + CODE_END;
   private static String CODE2 = CODE_STACK + CODE_GET + CODE_INDEX + CODE_SEP2 + CODE_THIS + CODE_END;
   private static String CODE3 = CODE_STACK + CODE_REMOVE + CODE_INDEX + CODE_SEP2 + CODE_THIS + CODE_END;
   private static String CODE4 = CODE_STACK + CODE_ADD + CODE_SEP2 + CODE_THIS + CODE_END;
   */

   private static String CODE1 = CODE_THIS + CODE_END;
   private static String CODE2 = CODE_GET_NUM + CODE_END;
   private static String CODE3 = CODE_THIS + CODE_END;
   private static String CODE4 = CODE_THIS + CODE_END;


      @java.lang.Override
      public void edit (final MethodCall call) throws CannotCompileException {
         if (call.getMethodName().equals("set")) {
            System.out.println ("Found call to 'set'");
            call.replace("{ $_ = $proceed($$); "+ CODE1 +"}");
         } else if  (call.getMethodName().equals("get")) {
            System.out.println ("Found call to 'get'");
            call.replace("{ "+CODE2+" $_ = $proceed($$); }");
         } else if  (call.getMethodName().equals("remove")) {
            System.out.println ("Found call to 'remove'");
            call.replace("{ "+CODE3+" $_ = $proceed($$); }");
         } else if  (call.getMethodName().equals("add")) {
            System.out.println ("Found call to 'add'");
            call.replace("{ "+CODE4+" $_ = $proceed($$); }");
         }
      }
}


