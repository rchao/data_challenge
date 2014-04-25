package yelpdata;

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
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import static yelpdata.Constants.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rick
 * Date: 3/14/14
 * Time: 4:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class MostCommonKeyword {
    public static ArrayList<Map.Entry<String, Integer>> mostCommonKeywordsInTopNRestaurants(
            ArrayList<Map.Entry<String, Long>> restaurantList, int n, int[] reviewCount) {
        Scanner getReview = null;
        try {
            getReview = new Scanner(new FileInputStream(REVIEW));
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            System.exit(1);
        }

        HashMap<String, Integer> count = new HashMap<String, Integer>();
        HashSet<String> restaurants = new HashSet<String>();
        for (int i = 0; i < n; i++) {
            String restaurant_id = restaurantList.get(i).getKey();
            restaurants.add(restaurant_id);
        }

        while (getReview.hasNextLine()) {
            JSONObject json = (JSONObject)JSONValue.parse(getReview.nextLine());
            if (!restaurants.contains(json.get("business_id"))) {
                continue;
            }
            reviewCount[0]++;
            String review = (String)json.get("text");
            Scanner strScanner = new Scanner(review);
//            strScanner.useDelimiter("[\\W]+");
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
        return entryList;
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
