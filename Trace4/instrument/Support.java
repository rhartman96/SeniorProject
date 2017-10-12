package instrument;

/*
  If the instrumentation uses these utility methods, then the instrucmented program
  needs to have this class "instrument.Support" in the class path.
*/

import java.util.*;
public final class Support {

   final static String FORMAT = "%s; %s; %s%n";

   private static void print (final String s1, final String s2) {
      final String location = Thread.currentThread().getStackTrace()[2].toString();
      System.out.printf (FORMAT, location, s1, s2);
   }

   public static void print0 () {
      Support.print ("null","null");
   }

   public static void print1 (final String s1) {
      Support.print (s1,"null");
   }

   public static void print2 (final String s1, final String s2) {
      Support.print (s1,s2);
   }

}