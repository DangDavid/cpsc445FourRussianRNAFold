package cpsc445;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 26/03/14
 * Time: 12:59 AM
 */
public class FourRussian extends AbstractAlg {

    final static List<int[]> binaryV = new ArrayList<int[]>();
    final static HashMap<Integer, int[]> vgHash = new HashMap<Integer, int[]>();

    public static ScoreMatrix runFourRussian(Sequence sequence, int q) {
        genBinaryVectors(binaryV, q);
        ScoreMatrix result = new ScoreMatrix(sequence);
        int[][] score = result.getScoreMatrix();

        // BUILD
        //http://csiflabs.cs.ucdavis.edu/~gusfield/rnafoldlncs.pdf


        for (int j = 1; j < sequence.getSize(); j++) {
            // [Independent] Calculations below don't depend on the current column j
            // Should be the same as alternate
            for (int i = 0; i < j; i++) {
                // do {rules a and b}
                score[i][j] = Math.max(score[i + 1][j - 1] + getScore(sequence.get(i), sequence.get(j)), score[i][j - 1]);
            }


            //   [Dependent] Calculations below depend on the current column j

            for (int i = j - 1; i >= 0; i--) {

                for (int g = (j - 1) / q; g >= (i + 1) / q; g--) {


                    // TODO Figure out

                    if (i >= g * q) {   //(i >k), k ∈ Rgroup g. {
                        //{this statement runs at most once, for the smallest g}

                        //find k*(i, g, j) by directly computing and comparing S(i, k-1)+S(k, j) where k ∈ L = {g * q to i}
                        //System.out.println("j " + j + " i " + i + " g " + g + "  True");
                        //|L| <q

                        //if ((g + 1) * q - (i + 1) >= q) {
                        //    System.out.println("Error not withing q " + ((g + 1) * q - (i + 1)));
                        //}
                        for (int k = i + 1; k < (g + 1) * q && k < j; k++) {
                            score[i][j] = Math.max(score[i][j], score[i][k - 1] + score[k][j]);
                        }

                        // TODO TRUE case is done


                    } else {
                       // System.out.println("j " + j + " i " + i + " g " + g + "  false");
                        //retrieve vg given g
                        int[] vg = getLittleVG(g, q);
                        int k = getKFromRTable(i, g, vg);
                        score[i][j] = Math.max(score[i][j], score[i][k - 1] + score[k][j]);

                    }


                }
                if (i % q == 0) { // IE i is the last row in the r group
                    //  , Compute vg for group g and store it
                    setLittleVg(i / q, q);
                }


            }

            if ((j+2) %q == 0){
                //[Table] once Cgroup g = ⌊j/q⌋ is complete

                // generate all binary vectors of q

                //genBinaryVectors(binaryV, q);

                for (int[] bv : binaryV){
                    int[] vPrime = decodeBinaryV(bv);
                    for (int i = 0; i<j-1; i++) {
                        fillRTable(i,i/q,bv);
                    }
                }

            }
        }

        //printScore(score);
        return result;

    }

    private static void fillRTable(int i, int g, int[] bv) {
        // Set the indices of the r table to the index of the max k
        // TODO Sophia last 3 lines
    }

    private static int[] decodeBinaryV(int[] bv) {
        //TODO  sohpia looking into z
        return new int[0];
    }

    private static void genBinaryVectors(List<int[]> binaryV, int q) {
         // can be done once and reuse vectors


        int total = (int) Math.pow(2,q-1) ;
     //   System.out.println("Generating values from 0 to " + total);

        for (int i = 0; i < total; i++) {
            int[] vector = new int[q-1];
            int curr = i;
            for (int b = 0; b < q-1; b++) {
                vector[b] = curr & 1;
                curr= curr>>1;
            }

           // System.out.println(Arrays.toString(vector));
            binaryV.add(vector);

        }




    }

    private static void setLittleVg(int g, int q) {

        // the int array should be based on score
        // watch out for out of bounds
        // TODO   David H Given S int[][] int g int q int j
        vgHash.put(g, new int[q - 1]);

    }

    private static int getKFromRTable(int i, int g, int[] vg) {
        // Makee sure vg is indexable
        // ie change to binary representation

        // TODO
        return i + 1;
    }

    private static int[] getLittleVG(int g, int q) {

         // Done
        //System.out.println("Do we no vg g" + g + " " + vgHash.containsKey(g));
        return vgHash.get(g);
    }
}
