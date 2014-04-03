package cpsc445;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 26/03/14
 * Time: 12:59 AM
 */
public class FourRussian extends AbstractAlg {

    final static List<VectorResult> binaryV = new ArrayList<VectorResult>();
    final static HashMap<Integer, VectorResult> vgHash = new HashMap<Integer, VectorResult>();
    private static int[][][] rTable;
    //private static HashMap<VectorIndex, Integer> binDecTable;

    public static ScoreMatrix runFourRussian(Sequence sequence, int q) {

        genBinaryVectors(binaryV, q);
        initRTable(sequence.getSize(), q);
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

                        // TODO TRUE case is done might be missing the left cells within the cube


                    } else {
                       // System.out.println("j " + j + " i " + i + " g " + g + "  false");
                        //retrieve vg given g
                        VectorResult vg = getLittleVG(g);

                        // carefull can be null in cases where the par
                        int k = getKFromRTable(i, g, vg.getNum());

                       // TODO uncomment when k is non 0
                       // score[i][j] = Math.max(score[i][j], score[i][k - 1] + score[k][j]);

                    }


                }
                if (i % q == 0) { // IE i is the last row in the r group
                    //  , Compute vg for group g and store it
                    setLittleVg(score, i / q, q, j);
                }


            }

            if ((j+2) %q == 0){
                //[Table] once Cgroup g = ⌊j/q⌋ is complete

                // generate all binary vectors of q

                //genBinaryVectors(binaryV, q);

                for (VectorResult bv : binaryV){
                    int[] vPrime = decodeBinaryV(bv.getV());
                    for (int i = 0; i<j-1; i++) {
                        // This should be g from column group
                        fillRTable(i,((j+2)/q)-1, vPrime,bv.getNum());
                    }
                }

            }
        }

        //printScore(score);
        return result;

    }

    private static void fillRTable(int i, int g, int[] vPrime, int vNum) {
        // Set the indices of the r table to the index of the max k
        // TODO Sophia last 3 lines

        int k = 1;
        setKFromRTable(i,g,vNum, k);


    }

    private static int[] decodeBinaryV(int[] bv) {
        //TODO  sohpia looking into z
        return new int[0];
    }

    private static void genBinaryVectors(List<VectorResult> binaryV, int q) {
         // can be done once and reuse vectors
        int total = (int) Math.pow(2,q-1) ;


        //binDecTable = new HashMap<VectorIndex, Integer>(total*2);
     //   System.out.println("Generating values from 0 to " + total);

        for (int i = 0; i < total; i++) {
            int[] vector = new int[q-1];
            int curr = i;
            for (int b = 0; b < q-1; b++) {
                vector[b] = curr & 1;
                curr= curr>>1;
            }

           // System.out.println(Arrays.toString(vector));
            binaryV.add(new VectorResult(i,vector));
           // binDecTable.put(new VectorIndex(vector), i);

        }




    }

    private static void setLittleVg(int[][] s, int g, int q, int j) {

        // the int array should be based on score
        // watch out for out of bounds
        VectorResult v = encode(s, g, q, j);
        vgHash.put(g, v);

    }
    
    private static VectorResult encode(int[][] s, int g, int q, int j) {
    	
    	int[] v = new int[q-1];
    	int start = q*g;
        int num = 0 ;
    	
    	for (int i = 0; i < q-1; i++) {
    	
    		int val = 0;
    		if (start+i+1 < s[0].length) {
    			val = s[start+i][j]-s[start+i+1][j];
    		}
    		
    		if (val > 0) {
    			//System.out.println("ERROR negative value in v");
    		}
    		
    		v[q-2-i] = val;
            if (val == 1){
                num += 1 <<  ( q-2-i);
            }

    	}
    	return new VectorResult(num,v);
    }

    private static int getKFromRTable(int i, int g, int v) {
        // Makee sure vg is indexable
        // ie change to binary representation
       // System.out.println( "Get " + i +" "+ g);

        return rTable[i][g][v];
      //  return rTableHash.get(new RTableIndex(i,g,vg));
    }


    private static void setKFromRTable(int i, int g, int vg, int k) {
        // Makee sure vg is indexable
        // ie change to binary representation


      rTable[i][g][vg] = k;
       // System.out.println( "Put " + i +" "+ g);
        //rTableHash.put(new RTableIndex(i, g, vg), k);
    }

    private static VectorResult getLittleVG(int g) {



         // Done
        //System.out.println("Do we no vg g" + g + " " + vgHash.containsKey(g));
        return vgHash.get(g);

    }



    private static void initRTable(int seqLength, int q){
        rTable = new int[seqLength][seqLength/q +1][(int) Math.pow(2,q-1)];
    }


}
