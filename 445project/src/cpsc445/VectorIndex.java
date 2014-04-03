package cpsc445;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 02/04/14
 * Time: 6:28 PM
 */
public class VectorIndex {

    private final int hash;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VectorIndex that = (VectorIndex) o;

        if (!Arrays.equals(v, that.v)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    final int[] v;
    public VectorIndex(int[] v) {
        this.v = v;
        this.hash = Arrays.hashCode(v);
    }

}
