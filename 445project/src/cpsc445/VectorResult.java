package cpsc445;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 02/04/14
 * Time: 8:29 PM
 */
public class VectorResult {


    private final int[] v;
    private final int num;

    public int[] getV() {
        return v;
    }

    public int getNum() {
        return num;
    }

    public VectorResult(int num, int[] v) {
        this.num = num;
        this.v = v;
    }
}
