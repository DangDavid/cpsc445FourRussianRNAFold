package cpsc445;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 09/02/14
 * Time: 12:07 PM
 */
public class Main {

   final static String testString = "AUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCCGGGAGAUCC";
	//final static String testString = "GGGAAAUGGGAAAUCCAACGGCCAACGGGGGAAAUCCAACGGGGAAAGGGAAAUCCAACGGUCCAACGGGGGGAAAUCCAACGG";

    public static void main(String[] args) {

        //FastaParser fp = new FastaParser();
        //List<Sequence> result = fp.parse("p04403.fasta");

        Sequence test = new Sequence("test",testString+testString+testString+testString+testString+testString);

        long start = 0;

        start = System.nanoTime();
        //ScoreMatrix n = Nussinov.runNussinov(test);
        System.out.println(System.nanoTime() - start);


        start = System.nanoTime();
       ScoreMatrix a = AlternateNussinov.runAlternate(test);
        System.out.println(System.nanoTime() - start);
        start = System.nanoTime();
        ScoreMatrix r = FourRussian.runFourRussian(test, (int) (Math.log(test.getSize()) / Math.log(3)));
        System.out.println(System.nanoTime() - start);


        System.out.println(compareScores(a.scoreMatrix, r.scoreMatrix)); 
        System.out.println("Nussinov finished ");


    }

    /**
     * assumes both matrix are the same size
     *
     * @param m1
     * @param m2
     * @return
     */
    private static boolean compareScores(int[][] m1, int[][] m2) {
    	boolean result = true;
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                if (m1[i][j] != m2[i][j]) {
                    result = false;
                    System.out.println("i = " + i + " j = " + j + " alt = " +m1[i][j] + " 4r " + m2[i][j] );
                }

            }
        }
        System.out.println("i = " + 0 + " j = " + (m1.length-1) + " alt = " +m1[0][(m1.length-1)] + " 4r " + m2[0][(m1.length-1)] );
        return result;
    }
}
