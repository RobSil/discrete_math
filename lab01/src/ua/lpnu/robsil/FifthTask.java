package ua.lpnu.robsil;

import java.util.Arrays;
import java.util.List;

public class FifthTask {

    public static final int[] universe = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 4, 5, 6, 7};
        int[] b = new int[]{5, 6, 7, 8, 9, 10};
        int[] c = new int[]{1, 2, 3, 8, 9, 10};
        perform(a, b, c);
    }

    public static void perform(int[] a, int[] b, int[] c) {
        int[] ac = toComputer(a);
        int[] bc = toComputer(b);
        int[] cc = toComputer(c);
        firstOperation(ac, bc, cc);
        secondOperation(ac, bc, cc);
        thirdOperation(ac, bc, cc);
        long then = System.currentTimeMillis();
    }

    public static void thirdOperation(int[] a, int[] b, int[] c) {
        int[] notB = new int[10];

        for (int i = 0; i < b.length; i++) {
            if (b[i] == 0) {
                notB[i] = 1;
            }
        }

        int[] plus = new int[10];
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1 || notB[i] == 1) {
                plus[i] = 1;
            }
        }

        for (int i = 0; i < plus.length; i++) {
            if (c[i] == 1) {
                plus[i] = 0;
            }
        }

        System.out.println("result of third operations: " + Arrays.toString(plus));
    }

    public static void secondOperation(int[] a, int[] b, int[] c) {
        int[] together = new int[10];
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1 || b[i] == 1) {
                together[i] = 1;
            } else {
                together[i] = 0;
            }
        }

        int[] symSub = new int[10];
        for (int i = 0; i < together.length; i++) {
            if (together[i] == 1 && c[i] == 0) {
                symSub[i] = 1;
            } else if (together[i] == 0 && c[i] == 1) {
                symSub[i] = 1;
            } else {
                symSub[i] = 0;
            }
        }

        // not symSub
        for (int i = 0; i < symSub.length; i++) {
            if (symSub[i] == 1) {
                symSub[i] = 0;
            } else {
                symSub[i] = 1;
            }
        }

        System.out.println("result of second operations: " + Arrays.toString(symSub));
    }

    public static void firstOperation(int[] a, int[] b, int[] c) {
        // not b
        int[] notB = b;
        for (int i = 0; i < notB.length; i++) {
            if (notB[i] == 0) {
                notB[i] = 1;
            } else {
                notB[i] = 0;
            }
        }

        // a pereriz b
        int[] cut = new int[10];
        for (int i = 0; i < a.length; i++) {
            if (a[i] == notB[i]) {
                cut[i] = 1;
            } else {
                cut[i] = 0;
            }
        }

        // subtraction
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 1) {
                cut[i] = 0;
            }
        }

        System.out.println("first operation result: " + Arrays.toString(cut));
    }

    public static int[] toComputer(int[] arr) {
        int[] result = new int[universe.length];

        for (int i = 0; i < universe.length; i++) {
            if (contains(universe[i], arr) > -1) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }
        }

        return result;
    }

    // lookup in array, is item exists, if yes then return index, if not return -1
    public static int contains(int it, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == it) {
                return i;
            }
        }
        return -1;
    }

}
