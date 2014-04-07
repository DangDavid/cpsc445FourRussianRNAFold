package cpsc445;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 19/01/14
 * Time: 6:50 PM
 */
public class ScoreMatrix {

    private final Sequence sequence;         // Top as in top of the matrix
    private final int[][] scoreMatrix;


    public ScoreMatrix(Sequence sequence) {
        this.sequence = sequence;
        int topLength = sequence.getSize();

        scoreMatrix = new int[topLength][topLength];
    }

    public int[][] getScoreMatrix() {
        return scoreMatrix;
    }


}
