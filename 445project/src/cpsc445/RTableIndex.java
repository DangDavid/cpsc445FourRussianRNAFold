package cpsc445;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 02/04/14
 * Time: 4:52 PM
 */
public class RTableIndex {

    final int i;
    final int g;
    final int[] v;

    public RTableIndex(int i, int g, int[] v) {
        this.i = i;
        this.g = g;
        this.v = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RTableIndex that = (RTableIndex) o;

        if (g != that.g) return false;
        if (i != that.i) return false;
        if (!Arrays.equals(v, that.v)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + g;
        result = 31 * result + Arrays.hashCode(v);
        return result;
    }
}
