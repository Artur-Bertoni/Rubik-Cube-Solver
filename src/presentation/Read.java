package presentation;

import java.util.Scanner;

public class Read {

// nextDouble() nextLine() leer.next();

    private static final Scanner keyboard = new Scanner(System.in);

    public static String chain() {
        return keyboard.next();
    }

    public static char character() {
        return character("");
    }

    public static char character(String s) {
        System.out.println(s);
        return keyboard.next().charAt(0);
    }

    public static int whole() {
        return whole("");
    }

    public static int whole(String s) {
        int result = 0;
        boolean ok;
        do {
            ok = true;
            System.out.println(s);
            try {
                result = keyboard.nextInt();
            } catch (Exception e) {
                ok = false;
                keyboard.next();
            }
        } while (!ok);
        keyboard.nextLine();
        return result;
    }

    public static int whole(String s, int min, int max) {
        int result = min - 1;
        while (result < min || result > max)
            result = whole(s + "[" + min + "," + max + "]");
        return result;
    }

    public static double continue_() {
        return continue_("");
    }

    public static double continue_(String s) {
        double result = 0;
        boolean ok;
        do {
            ok = true;
            System.out.println(s);
            try {
                result = keyboard.nextDouble();
            } catch (Exception e) {
                ok = false;
                keyboard.next();
            }
        } while (!ok);
        keyboard.nextLine();
        return result;
    }

    public static long large() {
        return large("");
    }

    public static long large(String s) {
        long result = 0;
        boolean ok;
        do {
            ok = true;
            System.out.println(s);
            try {
                result = keyboard.nextLong();
            } catch (Exception e) {
                ok = false;
                keyboard.next();
            }
        } while (!ok);
        keyboard.nextLine();
        return result;
    }
}