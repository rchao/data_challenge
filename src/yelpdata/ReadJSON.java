package yelpdata;

import org.json.simple.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
public class ReadJSON {
    public static void main(String[] arg) {
        System.out.println("=======decode=======");
//        String s="{\"0\": {\"1\": 2, \"3\": 4, \"5\": 6}, \"7\": \"8\", \"9\": \"10\", \"11\": 12, \"13\": \"14\", \"15\": \"16\", \"17\": \"18\", \"19\": \"20\"}";
        try {
            System.setIn(new FileInputStream(new File("sample.txt")));
        } catch (FileNotFoundException e) { }
        Scanner in = new Scanner(System.in);
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        while (in.hasNextLine()) {
            JSONObject json = (JSONObject)JSONValue.parse(in.nextLine());
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
        for (int i = 0; i < 100; i++) {
            System.out.print(entryList.get(i).getKey() + " " +entryList.get(i).getValue() + " ");
        }
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
