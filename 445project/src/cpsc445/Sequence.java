package cpsc445;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 19/01/14
 * Time: 7:26 PM
 */
public class Sequence {
    final String name;
    String sequence;


    public Sequence(String name, String seq) {
        this.name = name;
        sequence = seq;
    }

    public int getSize() {
        return sequence.length();
    }

    public String getSeq() {
        return sequence;
    }


    public void appendSeq(String line) {
        sequence = sequence.concat(line);
    }

    public String getName() {
        return name;
    }

    public String get(int currIndex) {
        return String.valueOf(sequence.charAt(currIndex));
    }
}
