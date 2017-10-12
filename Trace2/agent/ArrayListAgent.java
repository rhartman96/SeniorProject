import java.lang.instrument.Instrumentation;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;




public class ArrayListAgent {


   private static final String CLASS_NAME = "java/util/ArrayList";
   private static final String CLASS_NAME2 = "java.util.ArrayList";

   private static class Transformer implements ClassFileTransformer {

      public byte[] transform (final ClassLoader loader, final String className,
         Class classBeingRedefined, ProtectionDomain protectionDomain,
         final byte[] classfileBuffer) throws IllegalClassFormatException {
         byte[] byteCode = classfileBuffer;
         System.out.println("transform......");
         System.out.println (className);
         System.out.println (System.getProperty ("java.class.path"));

         // since this transformer will be called when all the classes are
         // loaded by the classloader, we are restricting the instrumentation
         // using if block only for the Lion class
         if (className.equals(CLASS_NAME)) {
            System.out.println("Instrumenting......");
            try {
               final ClassPool classPool = ClassPool.getDefault();
               final CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
               final CtMethod[] methods = ctClass.getDeclaredMethods();
               for (final CtMethod method : methods) {
                  final javassist.bytecode.MethodInfo methodInfo = method.getMethodInfo();
                  final String name = methodInfo.getName();
                  System.out.println (method);
                  if (name.startsWith ("set")) {
                     method.insertBefore("Modifications.set2(this,0,0);");
                  } else if (name.startsWith ("get")) {
                     method.insertBefore("Modifications.get2(this,0);");
                  }
               }
               byteCode = ctClass.toBytecode();
               ctClass.detach();
               System.out.println("Instrumentation complete.");
            } catch (Throwable ex) {
               System.out.println("Exception: " + ex);
               ex.printStackTrace();
            }
         }
         return byteCode;
      }
   }

   private static Transformer T = new Transformer ();

   // 
   public static void premain (final String agentArgs, final Instrumentation inst) {
      System.out.println("Executing premain.........");

      inst.addTransformer(T, true);

      // by the time we are cassed, the classes to be
      // instrumented may have been loaded already. So, check
      // for candidates in the loaded classes.

      // If instrumenting system classes, we must make sure javassist
      // is in the boot class path.

      final Class[] classes = inst.getAllLoadedClasses();
      for (final Class c: classes) {
         //System.out.printf ("%s %B %B%n", c, inst.isModifiableClass(c),inst.isRetransformClassesSupported());
         if (inst.isModifiableClass(c) && inst.isRetransformClassesSupported()){
         }
         if (c.getName().equals (CLASS_NAME2)) {
            System.out.printf ("%s %B %B%n", c, inst.isModifiableClass(c),inst.isRetransformClassesSupported());
            try {
               inst.retransformClasses(c);
            } catch (final UnmodifiableClassException ex) {
               System.out.println (ex);
            }
         }
      }

      inst.removeTransformer(T);

   }
}
