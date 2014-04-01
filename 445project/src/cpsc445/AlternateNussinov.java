package cpsc445;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 26/03/14
 * Time: 12:59 AM
 */
public class AlternateNussinov extends AbstractAlg {
    public static ScoreMatrix runAlternate(Sequence sequence) {
        ScoreMatrix result = new ScoreMatrix(sequence);
        int[][] score = result.getScoreMatrix();




        for( int j = 1 ; j < sequence.getSize(); j++ ) {
            // [Independent] Calculations below don't depend on the current column j

            for (int i = 0; i < j ; i++ ){
                // do {rules a and b}
                score[i][ j] = Math.max( score[i+1][j - 1]+ getScore(sequence.get(i), sequence.get(j)), score[i][j - 1]);
            }



            //   [Dependent] Calculations below depend on the current column j

            for (int i = j - 1; i>=0 ; i--){

                score[i][ j] = Math.max( score[i+1][j], score[i][j]);


                for (int k = j - 1; k >= i+1; k--){

                    score[i][ j] = Math.max(  score[i][j],  score[i][ k - 1]+score[k] [j]);
                }


            }

        }


        //printScore(score);

        return result;

    }
}
