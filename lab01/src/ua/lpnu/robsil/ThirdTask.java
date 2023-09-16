package ua.lpnu.robsil;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static ua.lpnu.robsil.LogicalUtil.implicit;

public class ThirdTask {

    private static final Map<Character, Map<Character, Boolean>> pPredicate = Map.of(
            'a', Map.of('a', false, 'b', true),
            'b', Map.of('a', false, 'b', true)
    );
    private static final Map<Character, Map<Character, Boolean>> qPredicate = Map.of(
        'a', Map.of('a', true, 'b', false),
        'b', Map.of('a', false, 'b', true)
    );


    public static void main(String[] args) {
//        manualFirstFormula();
//        runFirstFormula();
//        runSecondFormula();
//        manualSecondFormula();
    }

    public static void manualFirstFormula() {
        Scanner scanner = new Scanner(System.in);
        String x = scanner.next();
        if (x.isBlank() || x.length() > 1 || (!x.equals("a") && !x.equals("b"))) {
            System.out.println("execution failed, x is not good");
            return;
        }
        String y = scanner.next();
        if (y.isBlank() || y.length() > 1 || (!y.equals("a") && !y.equals("b"))) {
            System.out.println("execution failed, y is not good");
            return;
        }
        System.out.printf("result of first formula: %b\n", performFirstFormula(x.toCharArray()[0]));
    }

    public static boolean getPPredicate(char x, char y) {
        return pPredicate.get(x).get(y);
    }

    public static boolean getQPredicate(char x, char y) {
        return qPredicate.get(x).get(y);
    }

    // for all x: Q(x, x)
    public static void runFirstFormula() {
        boolean anyFalse = false;
        for (Map.Entry<Character, Map<Character, Boolean>> entry: qPredicate.entrySet()) {
            if (!qPredicate.get(entry.getKey()).get(entry.getKey())) {
                anyFalse = true;
            }
        }

        System.out.printf("anyFalse: %b, forAllTrue: %s\n", anyFalse, anyFalse ? "there is some false" : "there is no false, true!");
    }

    public static boolean performFirstFormula(char x) {
        return getPPredicate(x, x);
    }

    public static void manualSecondFormula() {
        Scanner scanner = new Scanner(System.in);
        String x = scanner.next();
        if (x.isBlank() || x.length() > 1 || (!x.equals("a") && !x.equals("b"))) {
            System.out.println("execution failed, x is not good");
            return;
        }
        String y = scanner.next();
        if (y.isBlank() || y.length() > 1 || (!y.equals("a") && !y.equals("b"))) {
            System.out.println("execution failed, y is not good");
            return;
        }

        System.out.printf("result of second formula: %b", performSecondFormula(x.toCharArray()[0], y.toCharArray()[0]));
    }

    public static void runSecondFormula() {
        char[] vars = {'a', 'b'};

        Map<Character, Boolean> result = new HashMap<>(Map.of('a', false, 'b', false));

        for (char i: vars) {
            for (char j: vars) {
                if (performSecondFormula(i, j)) {
                    result.put(i, true);
                }
            }
        }

        boolean notExists = false;
        for (Map.Entry<Character, Boolean> entry: result.entrySet()) {
            if (!entry.getValue()) {
                notExists = true;
            }
        }

        System.out.printf("not exists: %b, for all x exists y: %s\n", notExists, notExists ? "not exists" : "exists!");
    }

    // for all x, exists y: P(x, y) -> !Q(x, y)
    public static boolean performSecondFormula(char x, char y) {
        return implicit(pPredicate.get(x).get(y), !qPredicate.get(x).get(y));
    }

}
