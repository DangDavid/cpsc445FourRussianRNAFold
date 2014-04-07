package cpsc445;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 26/03/14
 * Time: 12:57 AM
 */
public class AbstractAlg {
    private static final int NOT_FOUND_MATCH = 0;
    private static final HashMap<String, Integer> SCORE_TABLE;

    static {
        SCORE_TABLE = new HashMap<String, Integer>();
        SCORE_TABLE.put("AU", 1);
        SCORE_TABLE.put("UA", 1);
        SCORE_TABLE.put("GC", 1);
        SCORE_TABLE.put("CG", 1);
        // UG MAY BE ALLOWED
        //SCORE_TABLE.put("UG", 1);
        //SCORE_TABLE.put("GU", 1);

    }

    protected static void printScore(int[][] score) {
        System.out.println();
        for (int i = 0; i < score.length; i++) {

            for (int j = 0; j < score.length; j++) {


                System.out.print(score[i][j] + " ");


            }
            System.out.println();
        }

    }

    protected static int compareMax(int newInt, int currMax) {
        if (currMax < newInt) {
            return newInt;
        }
        return currMax;
    }

    protected static int getScore(String s1, String s2) {
        if (!SCORE_TABLE.containsKey(s1 + s2)) {
            return NOT_FOUND_MATCH;
        }

        return SCORE_TABLE.get(s1 + s2);


    }
}
