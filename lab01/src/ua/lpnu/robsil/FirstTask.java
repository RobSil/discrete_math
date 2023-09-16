package ua.lpnu.robsil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ua.lpnu.robsil.LogicalUtil.implicit;

public class FirstTask {
    public static void firstTask() {
        BooleanVariationUtil.getBooleanVariations(3)
                .forEach(arr -> firstTask(arr[0], arr[1], arr[2]));
    }

    public static void pseudoFirstTask() {
//        List<boolean[]> booleanVariations = getBooleanVariations(3);
        List<boolean[]> booleanVariations = BooleanVariationUtil.BOOLEAN_LIST_VARIATIONS;

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
        toPrint.put("!q", notQ);
        toPrint.put("p -> q", pImplQ);
        toPrint.put("p && !q", pAndNotQ);
        toPrint.put("p && !q -> r", pAndNotQImplR);
        toPrint.put("(p -> q) && (p && !q -> r)", impl1ImplImpl2);

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

}
