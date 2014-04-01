package cpsc445;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 19/01/14
 * Time: 6:30 PM
 */
public class Nussinov extends AbstractAlg {





    public static ScoreMatrix runNussinov(Sequence sequence){

        ScoreMatrix result = new ScoreMatrix(sequence);
        int[][] score = result.getScoreMatrix();


        for(int i = sequence.getSize()-1; i >=0; i--){

            for(int j = i+1; j < sequence.getSize(); j++){


                // calculate running max

                int currMax =  score[i+1][j];
                currMax = compareMax(score[i][j - 1], currMax);
                currMax = compareMax(score[i+1][j - 1]+ getScore(sequence.get(i), sequence.get(j)), currMax);
                currMax =compareMax(score[i][j - 1], currMax);

                for (int k = i+1; k < j; k++) {
                    currMax =   compareMax(score[i][k]+score[k+1][j], currMax);
                }

                score[i][j] = currMax;

            }
        }

       // printScore(score);

        return result;
    }



}
