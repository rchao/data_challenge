import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Rick
 * Date: 3/14/14
 * Time: 4:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class MostCommonKeywordWithinARestaurant {
    public static void main(String[] arg) {
        final int NTHMOSTREVIEW = 2;
        Scanner getRestaurantID = null;
        Scanner getReview = null;
        PrintStream writeMostCommon = null;
        try {
            getRestaurantID = new Scanner(new FileInputStream("mostReviewRestaurants.txt"));
            getReview = new Scanner(new FileInputStream("yelp_academic_dataset_review.json"));
            writeMostCommon = new PrintStream(new FileOutputStream("mostCommonKeywordWithinARestaurant.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            System.exit(1);
        }

        for (int i = 1; i < NTHMOSTREVIEW; i++) {
            getRestaurantID.nextLine();
        }
        String restaurant_id = getRestaurantID.next();

        HashMap<String, Integer> count = new HashMap<String, Integer>();
        int reviewCount = 0;

        while (getReview.hasNextLine()) {
            JSONObject json = (JSONObject)JSONValue.parse(getReview.nextLine());
            if (!json.get("business_id").equals(restaurant_id)) {
                continue;
            }
            reviewCount++;
            String review = (String)json.get("text");
            Scanner strScanner = new Scanner(review);
            while (strScanner.hasNext()) {
                String word = strScanner.next().toLowerCase();
                if (count.containsKey(word)) {
                    count.put(word, count.get(word) + 1);
                } else {
                    count.put(word, 1);
                }
            }
        }
        ArrayList<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>();
        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            entryList.add(entry);
        }
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });
        for (int i = 0; i < 1000; i++) {
            writeMostCommon.println(entryList.get(i).getKey() + " " +entryList.get(i).getValue());
        }
        System.out.println("ReviewCount = "+reviewCount);
    }
//    private static class StringIgnoreCase {
//        String str;
//        StringIgnoreCase(String str) {
//            this.str = str;
//        }
//
//        @Override
//        public boolean equals(Object sic) {
//            return sic instanceof StringIgnoreCase && str.compareToIgnoreCase(((StringIgnoreCase)sic).toString()) == 0;
////            return sic instanceof StringIgnoreCase && str.compareTo(((StringIgnoreCase)sic).toString()) == 0;
//        }
//
//        @Override
//        public String toString() {
//            return str;
//        }
//
//        @Override
//        public int hashCode() {
//            int h = 0;
//            for (int i = 0; i < str.length(); i++) {
//                h = 31 * h + Character.toLowerCase(str.charAt(i));
////                h = 31 * h + str.charAt(i);
//            }
//            return h;
//        }
//    }
}
