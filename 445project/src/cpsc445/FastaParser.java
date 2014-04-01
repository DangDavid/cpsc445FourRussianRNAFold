package cpsc445;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 19/01/14
 * Time: 8:36 PM
 */
public class FastaParser {

    public FastaParser(){

    }

    public List<Sequence> parse(String filename) {



        String pattern = "^>(.*)[|](.*)[|](\\S*)\\s(.+)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        List<Sequence> result = new ArrayList<Sequence>();
        try {

            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();

            Sequence currSeq = null;


            while ( line != null) {

                Matcher m = r.matcher(line);
                if (m.find()) {



                    currSeq = new Sequence(m.group(3), "");
                    result.add(currSeq);
                    line = br.readLine();
                    continue;
                }

                if (currSeq!= null){
                    currSeq.appendSeq(line.trim());
                }




                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }



        return result;
    }
}
