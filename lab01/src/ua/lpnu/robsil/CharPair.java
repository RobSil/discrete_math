package ua.lpnu.robsil;

import java.util.Objects;

public class CharPair {

    char x;
    char y;

    public CharPair(char x, char y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharPair charPair = (CharPair) o;
        return x == charPair.x && y == charPair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
