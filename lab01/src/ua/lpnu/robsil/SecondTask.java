package ua.lpnu.robsil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.lpnu.robsil.LogicalUtil.implicit;

public class SecondTask {

    public static void pseudoSecondTask() {
//        List<boolean[]> booleanVariations = getBooleanVariations(3);
        List<boolean[]> booleanVariations = BooleanVariationUtil.BOOLEAN_LIST_VARIATIONS;
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
        boolean foundFalse = BooleanVariationUtil.getBooleanVariations(3)
                .stream()
                .map(b -> secondTask(b[0], b[1], b[2]))
                .anyMatch(b -> !b);

        System.out.printf("found false: %b, isTop: %b\n", foundFalse, !foundFalse);
    }

}
