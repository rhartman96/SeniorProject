package analyze;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.io.*;

public final class Difference {

   private static final int MAX_LINES = 250;

   private static final int DELETE_COST = 2;
   private static final int INSERT_COST = 2;

   static class Element {
      final String info;
      final String element;
      final String arg;
      final String arg2;
      public Element (final String[] parts) {
         info=parts[0].trim();
         element=parts[1].trim();
         arg=parts[2].trim();
         arg2 = (parts.length>3) ?  parts[3].trim() : "";
         
      }
      public String toString() {
         return String.format ("%s; %s; %s; '%s'", info, element, arg, arg2);
      }

      int costSubst (final Element other) {
         //System.out.printf ("%s COMPARE %s%n", this, other);
         if (this.element.startsWith("1") && other.element.startsWith("1")) {
            return 0; 
         } else if (this.element.equals("iinc")) {
            if (other.element.equals("iinc") && this.arg.equals(other.arg)) return 0; else return 1;
         } else if (this.element.equals("set")) {
            // same state
            if (other.element.equals("set") && this.arg2.equals(other.arg2)) {
               //System.out.printf ("EQUAL STATE. %s ==  %s%n", this.arg2, other.arg2);
               return 0;
            } else if (other.element.equals("set")) {
               return 1;
            } else {
               return 2;
            }
         } else if (this.element.equals(other.element)) {
            return this.arg.equals(other.arg) ? 0 : 1;
         }  else {
            return 1;
         }
      }
      
      int costDelete () {
         return DELETE_COST;
      }

      int costInsert () {
         return INSERT_COST;
      }
   }

   public static void main (final String[] args) throws IOException {
      ArrayList<Element> trace1 = new ArrayList<>();
      trace1.add(null); // start at 1

      // The "good" trace
      try (final Scanner t1 = new Scanner (new FileInputStream ((args[0])))) {
            int lines=0;
            while (t1.hasNextLine() & lines++ < MAX_LINES) {
               final String[] line = t1.nextLine().split ("[/;] *");
               if (line.length>=3) {
                  trace1.add (new Element (line));
               }
            }
         }

      // The "bad" grace
      ArrayList<Element> trace2 = new ArrayList<>();
      trace2.add(null); // start at 1
      try (final Scanner t2 = new Scanner (new FileInputStream ((args[1])))) {
            int lines=0;
            while (t2.hasNextLine() & lines++ < MAX_LINES) {
               final String[] line = t2.nextLine().split ("[/;]");
               if (line.length>=3) {
                  trace2.add (new Element (line));
               }
            }
         }

      final int[][] d = new int[trace2.size()][trace1.size()];
      final int[][] path = new int[trace2.size()][trace1.size()];

      for (int i=1; i<trace2.size(); i++) d[i][0]=i;
      for (int j=1; j<trace1.size(); j++) d[0][j]=j;

      for (int j=1; j<trace1.size(); j++) {
         for (int i=1; i<trace2.size(); i++) {
            int min,prev;
            // Cost of substitution
            final int subst = d[i-1][j-1] + trace1.get(j).costSubst (trace2.get(i));
            final int del = d[i-1][j] + 1;
            min = (subst<del)?subst:del;
            prev = (subst<del)?0:+1;
            final int ins = d[i][j-1] + 1;
            if (ins<=min) min=ins;
            if (ins<=min) prev=-1;
            d[i][j]=min;
            path[i][j]= prev;
         }
      }

      /*
      for (int i=1; i<trace2.size(); i++) {
         System.out.printf ("%s/%s : %s%n", trace2.get(i).info, trace2.get(i).element, Arrays.toString(d[i]));
      }
      */

      int i=trace2.size()-1;
      int j=trace1.size()-1;
      int min = d[i][j];
      System.out.printf ("%d%n%n", min);


      ArrayList<String> edits = new ArrayList<>();

      int ms = 0;
      StringBuilder sb=new StringBuilder ();
      
      while (i>0 && j>0) {
         //System.out.printf ("%d,%d : %d; %d%n", i,j,min,d[i][j]);
         if (path[i][j]==0) {
            if (d[i][j]==d[i-1][j-1]) {
               ms++;
               sb.append('=');
               //System.out.printf ("%s == %s%n", trace2.get(i).element, trace1.get(j).element);
               //edits.add (String.format ("Matching %s with %s", trace2.get(i), trace1.get(j)));
            } else {
               //System.out.printf ("Substitute %s with %s%n", trace2.get(i).element, trace1.get(j).element);
               if (ms>0)  edits.add (String.format ("EQUAL %d %s", ms, sb));
               ms=0; sb.setLength(0);
               edits.add (String.format ("SUBSTITUTE %s WITH %s", trace2.get(i), trace1.get(j)));
            }
            i--; j--;
         } else if (path[i][j]==+1) {
            //System.out.printf ("Delete %s from the bad trace.%n", trace2.get(i).element);
               if (ms>0)  edits.add (String.format ("EQUAL %d %s", ms, sb));
               ms=0; sb.setLength(0);
            edits.add (String.format ("DELETE %s from bad trace", trace2.get(i)));
            i--;
         } else {
            //System.out.printf ("Insert %s into the bad trace.%n", trace1.get(j).element);
               if (ms>0)  edits.add (String.format ("EQUAL %d %s", ms, sb));
               ms=0; sb.setLength(0);
            edits.add (String.format ("INSERT %s into bad trace", trace1.get(j).element));
            j--;
         }
      }
      if (ms>0)  edits.add (String.format ("EQUAL %d %s", ms, sb));
      ms=0; sb.setLength(0);

      Collections.reverse (edits);

      // for (String line: edits) {
      //    System.out.println (line);
      // }

   }
}

