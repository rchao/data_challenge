import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Rick
 * Date: 4/9/14
 * Time: 6:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class KeepFirstFewLines {
    public static void main(String[] arg) {
        final int FROM = 1;
        final int TO = 10000;
        final String CATEGORY = "review";
        final String FILENAME = "yelp_academic_dataset_" + CATEGORY + ".json";
//        final String OUTPUTFILENAME = "line"+FROM+"ToLine"+TO+".txt";
        final String OUTPUTFILENAME = CATEGORY + TO + ".txt";
        try {
            Scanner in = new Scanner(new FileInputStream(FILENAME));
            PrintStream out = new PrintStream(new FileOutputStream(OUTPUTFILENAME));
            for (int i = 1; i <= TO; i++) {
                if (!in.hasNextLine()) {
                    break;
                }
                if (i >= FROM) {
                    out.println(in.nextLine());
                } else {
                    in.nextLine();
                }
            }
        } catch (FileNotFoundException e) {

        }
    }
}
