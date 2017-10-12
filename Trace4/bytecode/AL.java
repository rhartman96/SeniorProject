import java.util.ArrayList;

public class AL extends ArrayList <Integer> {
   @java.lang.Override
   public Integer set (int i, Integer v) {
      assert 0 <= i && i < super.size():String.format ("0 <= %d < %d",i, super.size());
      final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
      System.out.printf ("%s/set: i=%d to %d; [before] %s%n", e, i, v, this);
      return super.set (i, v);
   }

   @java.lang.Override
   public Integer get (final int i) {
      assert 0 <= i && i < super.size():String.format ("0 <= %d < %d",i, super.size());
      final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
      final Integer v = super.get (i);
      System.out.printf ("%s/get: i=%d is %d; %s%n", e, i, v, this);
      return v;
   }
}

/*
 * ------------For GNU Emacs ------------
 * Local Variables:
 * compile-command: "javac AL.java"
 * End:
 */
