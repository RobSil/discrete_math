package ua.lpnu.robsil;

import java.util.BitSet;
import java.util.List;
import java.util.stream.IntStream;

public class BooleanVariationUtil {

    public static final List<boolean[]> BOOLEAN_LIST_VARIATIONS = List.of(
            new boolean[]{ true, true, true },
            new boolean[]{ true, true, false },
            new boolean[]{ true, false, true },
            new boolean[]{ true, false, false },
            new boolean[]{ false, true, true },
            new boolean[]{ false, true, false },
            new boolean[]{ false, false, true },
            new boolean[]{ false, false, false }
    );

    public static final boolean[][] BOOLEAN_ARRAY_VARIATIONS = new boolean[][] {
            new boolean[]{ true, true, true },
            new boolean[]{ true, true, false },
            new boolean[]{ true, false, true },
            new boolean[]{ true, false, false },
            new boolean[]{ false, true, true },
            new boolean[]{ false, true, false },
            new boolean[]{ false, false, true },
            new boolean[]{ false, false, false }
    };

    public static List<boolean[]> getBooleanVariations(int n) {
        return IntStream.range(0, pow(2, n))
                .mapToObj(i -> BitSet.valueOf(new long[] {i}))
                .map(bs -> bitSetToArray(bs, n))
                .toList();
    }
    public static boolean[] bitSetToArray(BitSet bs, int width) {
        boolean[] result = new boolean[width]; // all false
        bs.stream().forEach(i -> result[i] = true);
        return result;
    }

    public static int pow(int number, int n) {
        int result = number;
        for (int i = 1; i < n; i++) {
            result *= number;
        }
        return result;
    }
}
