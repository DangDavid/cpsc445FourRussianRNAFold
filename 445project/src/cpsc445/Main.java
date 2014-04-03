package cpsc445;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 09/02/14
 * Time: 12:07 PM
 */
public class Main {

    static String  testString = "AUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCC";


    public static void main(String [] args){

        //FastaParser fp = new FastaParser();
        //List<Sequence> result = fp.parse("p04403.fasta");

        Sequence test = new Sequence("test", testString+testString+testString+testString+testString+testString);

        long start = 0;

        start = System.nanoTime();
       //ScoreMatrix n = Nussinov.runNussinov(test);
        System.out.println(System.nanoTime() - start);


        start = System.nanoTime();
        //ScoreMatrix a = AlternateNussinov.runAlternate(test);
        System.out.println(System.nanoTime() -start);
        start = System.nanoTime();
       ScoreMatrix r = FourRussian.runFourRussian(test, (int) (Math.log(test.getSize()) / Math.log(3)));
        System.out.println(System.nanoTime() -start);






        System.out.println("Nussinov finished ");








    }

    /**
     * assumes both matrix are the same size
     * @param m1
     * @param m2
     * @return
     */
    private static boolean compareScores(int[][] m1, int[][] m2) {

        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++){
                if (m1[i][j] != m2[i][j]) {
                    return false;
                }

            }
        }

        return true;
    }
}
