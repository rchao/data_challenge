package yelpdata;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Rick
 * Date: 4/24/14
 * Time: 9:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] arg) {
        int[] reviewCount = {0};
        ArrayList<Map.Entry<String, Long>> restaurantList
                = MostReviewRestaurants.mostReviewRestaurants();
        ArrayList<Map.Entry<String, Integer>> keywordCount
                = MostCommonKeyword.mostCommonKeywordsInTopNRestaurants(restaurantList, 1, reviewCount);
//        for (int i = 0; i < restaurantList.size(); i++) {
//            System.out.println(restaurantList.get(i).getKey() + " " + restaurantList.get(i).getValue());
//        }
//        System.out.println("Review Count: " + reviewCount[0]);
        PrintStream out = null;
        try {
            out = new PrintStream(new FileOutputStream("mostCommonKeywordWithSymbols.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            System.exit(1);
        }
        for (int i = 0; i < keywordCount.size(); i++) {
            out.println(keywordCount.get(i).getKey() + " " + keywordCount.get(i).getValue());
        }
    }
}
