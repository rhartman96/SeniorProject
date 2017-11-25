/*
 * Author:  Brandon Sawyer, bsawyer2016@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: where
 *
 */
import java.io.*;

public final class Where {
    private Where(){}

    public static void main (final String[] args) throws IOException {
        final BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        String[] input = buff.readLine().split(" ");
        final int nValue = Integer.parseInt(input[0]);
        final int kValue = Integer.parseInt(input[1]);
        final short qValue = Short.parseShort(input[2]);
        final int[] aValue = new int[nValue];
        input = buff.readLine().split(" ");
        for (int i = 0; i < nValue; ++i) {
            aValue[i] = Integer.parseInt(input[i]);
        }
        final int offset = nValue - kValue % nValue;
        final StringBuffer sBuffer = new StringBuffer();
        for (short q = 0; q < qValue; ++q) {
            final int newValue = Integer.parseInt(buff.readLine());
            sBuffer.append(aValue[(newValue + offset) % nValue] + "\n");
        }
        System.out.print(sBuffer);
    }
}
