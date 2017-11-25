/*
* Author: Justyn Diaz, diazj2016@my.fit.edu
* Course: CSE 1002, Section 03, Fall 2017
* Project: Extra Assignment - Where is Watson?
*/

import java.util.Scanner;
import java.util.LinkedList;

public final class Where {
    private Where () {}

    public static void main (final String[] args) {
        final Scanner keyboard = new Scanner(System.in);
        final LinkedList<Integer> rotatedArray = new LinkedList<Integer>();

        final int n = keyboard.nextInt();
        final int k = keyboard.nextInt();
        int q = keyboard.nextInt();

        for (int i = 0; i < n; i++) {
            final int element = keyboard.nextInt();
            rotatedArray.add(element);
        }

        for (int i = 0; i < k; i++) {
            rotatedArray.addFirst(rotatedArray.getLast());
            rotatedArray.removeLast();
        }

        for (int i = 0; q > i; q--) {
            final int postion = keyboard.nextInt();
            System.out.println(rotatedArray.get(postion));
        }
    }
}
