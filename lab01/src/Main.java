
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
//        pseudoFirstTask();
        pseudoSecondTask();
//        firstTask();
//        secondTask();
    }

    public static void pseudoFirstTask() {
        List<boolean[]> booleanVariations = getBooleanVariations(3);

        Map<String, boolean[]> toPrint = new HashMap<>();

        boolean[] p = new boolean[8];
        boolean[] q = new boolean[8];
        boolean[] r = new boolean[8];
        boolean[] notQ = new boolean[8];
        boolean[] pImplQ = new boolean[8];
        boolean[] pAndNotQ = new boolean[8];
        boolean[] pAndNotQImplR = new boolean[8];
        boolean[] impl1ImplImpl2 = new boolean[8];
        for (int i = 0; i < booleanVariations.size(); i++) {
            boolean[] vars = booleanVariations.get(i);
            p[i] = vars[0];
            q[i] = vars[1];
            r[i] = vars[2];
            notQ[i] = !vars[1];
            pImplQ[i] = implicit(vars[0], vars[1]);
            pAndNotQ[i] = vars[0] && !vars[1];
            pAndNotQImplR[i] = implicit(vars[0] && !vars[1], vars[2]);
            impl1ImplImpl2[i] = implicit(implicit(vars[0], vars[1]), implicit(vars[0] && !vars[1], vars[2]));
        }

        toPrint.put("p", p);
        toPrint.put("q", q);
        toPrint.put("r", r);
        toPrint.put("!p", notQ);
        toPrint.put("p -> q", pImplQ);
        toPrint.put("p && !q", pAndNotQ);
        toPrint.put("p && !q -> r", pAndNotQImplR);
        toPrint.put("(p -> q) && (p && !q -> r", impl1ImplImpl2);

        for (Map.Entry<String, boolean[]> entry: toPrint.entrySet()) {
            System.out.println("-------");
            System.out.println(entry.getKey());
            System.out.println(Arrays.toString(entry.getValue()));
            System.out.println("-------");
        }

        boolean isNotTautology = false;

        for (boolean bool: impl1ImplImpl2) {
            if (!bool) {
                isNotTautology = true;
            }
        }

        if (isNotTautology) {
            System.out.println("not tautology!");
        } else {
            System.out.println("tautology");
        }

    }

    public static void firstTask(boolean p, boolean q, boolean r) {
        boolean impl1 = implicit(p, q);
        boolean impl2 = implicit(p && !q, r);
        boolean result = implicit(impl1, impl2);

        System.out.printf("result is: %b\n", result);
    }

    public static void pseudoSecondTask() {
        List<boolean[]> booleanVariations = getBooleanVariations(3);
        Map<String, boolean[]> toPrint = new HashMap<>();
        boolean[] p = new boolean[8];
        boolean[] q = new boolean[8];
        boolean[] r = new boolean[8];
        boolean[] pImplQ = new boolean[8];
        boolean[] qImplR = new boolean[8];
        boolean[] impl1OrImpl2 = new boolean[8];
//        boolean impl2 = implicit(q, r);
//        boolean result = impl1 || impl2
        for (int i = 0; i < booleanVariations.size(); i++) {
            boolean[] vars = booleanVariations.get(i);
            p[i] = vars[0];
            q[i] = vars[1];
            r[i] = vars[2];
            pImplQ[i] = implicit(vars[0], vars[1]);
            qImplR[i] = implicit(vars[1], vars[2]);
            impl1OrImpl2[i] = implicit(vars[0], vars[1]) || implicit(vars[1], vars[2]);
        }

        toPrint.put("p", p);
        toPrint.put("q", q);
        toPrint.put("r", r);
        toPrint.put("p -> q", pImplQ);
        toPrint.put("q -> r", qImplR);
        toPrint.put("(p -> q) || (q -> r)", impl1OrImpl2);

        for (Map.Entry<String, boolean[]> entry: toPrint.entrySet()) {
            System.out.println("-------");
            System.out.println(entry.getKey());
            System.out.println(Arrays.toString(entry.getValue()));
            System.out.println("-------");
        }

        System.out.print("counter example: ");
        boolean isNotTautology = false;
        for (boolean bool: impl1OrImpl2) {
            if (bool == false) {
                isNotTautology = true;
            }
        }

        if (isNotTautology) {
            System.out.println("not tautology!");
        } else {
            System.out.println("tautology!");
        }

//        System.out.println("implicit: p -> q");
    }

    public static boolean secondTask(boolean p, boolean q, boolean r) {
        boolean impl1 = implicit(p, q);
        boolean impl2 = implicit(q, r);

        boolean result = impl1 || impl2;
        System.out.printf("result is: %b\n", result);

        return result;
    }

    public static void secondTask() {
        boolean foundFalse = getBooleanVariations(3)
                .stream()
                .map(b -> secondTask(b[0], b[1], b[2]))
                .anyMatch(b -> !b);

        System.out.printf("found false: %b, isTop: %b\n", foundFalse, !foundFalse);
    }

    public static void firstTask() {
        getBooleanVariations(3)
                .forEach(arr -> firstTask(arr[0], arr[1], arr[2]));
    }



    public static List<boolean[]> getBooleanVariations(int n) {
        return IntStream.range(0, pow(2, n))
                .mapToObj(i -> BitSet.valueOf(new long[] {i}))
                .map(bs -> bitSetToArray(bs, n))
                .toList();
    }

    public static int pow(int number, int n) {
        int result = number;
        for (int i = 1; i < n; i++) {
            result *= number;
        }
        return result;
    }

    public static boolean[] bitSetToArray(BitSet bs, int width) {
        boolean[] result = new boolean[width]; // all false
        bs.stream().forEach(i -> result[i] = true);
        return result;
    }

    public static boolean implicit(boolean a, boolean b) {
        return !a || b;
    }
}



// a ? b : true
// !a || b
// false || (true, false) -> true, false
// true || (true, false) -> true, true
