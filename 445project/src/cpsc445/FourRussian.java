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
    static VectorResult[] vgArray;
    private static int[][][] rTable;

    public static ScoreMatrix runFourRussian(Sequence sequence, int q) {

        genBinaryVectors(q);
        initRTable(sequence.getSize(), q);
        vgArray = new VectorResult[(sequence.getSize() / q) + 1];
        ScoreMatrix result = new ScoreMatrix(sequence);
        int[][] score = result.getScoreMatrix();

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

                    if (i >= g * q) {
                        for (int k = i + 1; k < (g + 1) * q && k < j; k++) {
                            score[i][j] = Math.max(score[i][j], score[i][k - 1] + score[k][j]);
                        }

                        // TODO TRUE case is done might be missing the left cells within the cube
                        // David

                    } else {

                        VectorResult vg = getLittleVG(g);
                        int k = getKFromRTable(i, g, vg.getNum());
                        // TODO uncomment when k is non 0
                        // score[i][j] = Math.max(score[i][j], score[i][k - 1] + score[k][j]);

                    }


                }
                if (i % q == 0) { // IE i is the last row in the r group
                    setLittleVg(score, i / q, q, j);
                }


            }

            if ((j + 2) % q == 0) {
                for (VectorResult bv : binaryV) {
                    int[] vPrime = decodeBinaryV(bv.getV());
                    for (int i = 0; i < j - 2; i++) {
                        // This should be g from column group
                        fillRTable(i, ((j + 2) / q) - 1, vPrime, bv.getNum(), q, score);
                    }
                }

            }
        }
        return result;

    }

    private static void fillRTable(int i, int g, int[] vPrime, int vNum, int q, int[][] score) {

        int k = -1;
        int maxValue = -1;
        int x = q*(g+1) -2;
        for (int index = 0; index < q; index++) {
        	// Iterate through k
        	
        	int tempValue =-1;
        	if ( g== 0 && index == q-1) {
        		 tempValue = 0 + vPrime[index];
        	}else {
        		 tempValue = score[i][x-index] + vPrime[index];
        	}
   
			if (tempValue > maxValue) {
        		
        		k = index;
        		maxValue = tempValue;
        	}
        }
        
        setKFromRTable(i, g, vNum, k);


    }

    private static int[] decodeBinaryV(int[] bv) {

    	
    	int z = bv.length;
    	int[] vPrime = new int[z+1];
    	
    	for (int k = 0; k < z+1; k++) {
    		int sum = 0;
    		for (int p = 0; p < z-k; p++) {
    			sum += bv[p];
    		}
    		vPrime[k] = sum;
    	}
        return vPrime;

    }

    private static void genBinaryVectors(int q) {
        // can be done once and reuse vectors
        int total = (int) Math.pow(2, q - 1);
        for (int i = 0; i < total; i++) {
            int[] vector = new int[q - 1];
            int curr = i;
            for (int b = 0; b < q - 1; b++) {
                vector[b] = curr & 1;
                curr = curr >> 1;
            }
            binaryV.add(new VectorResult(i, vector));
        }


    }

    private static void setLittleVg(int[][] s, int g, int q, int j) {

        // the int array should be based on score
        // watch out for out of bounds
        VectorResult v = encode(s, g, q, j);
        vgArray[g] = v;

    }

    private static VectorResult encode(int[][] s, int g, int q, int j) {

        int[] v = new int[q - 1];
        int start = q * g;
        int num = 0;

        for (int i = 0; i < q - 1; i++) {

            int val = 0;
            if (start + i + 1 < s[0].length) {
                val = s[start + i][j] - s[start + i + 1][j];
            }

            if (val > 0) {
                // TODO figure out what we hsould do here
                //System.out.println("ERROR negative value in v");
            }

            v[q - 2 - i] = val;
            if (val == 1) {
                num += 1 << (q - 2 - i);
            }

        }
        return new VectorResult(num, v);
    }

    private static int getKFromRTable(int i, int g, int v) {
        return rTable[i][g][v];
    }


    private static void setKFromRTable(int i, int g, int vg, int k) {
        rTable[i][g][vg] = k;
    }

    private static VectorResult getLittleVG(int g) {
        return vgArray[g];
    }


    private static void initRTable(int seqLength, int q) {
        rTable = new int[seqLength][seqLength / q + 1][(int) Math.pow(2, q - 1)];
    }


}
