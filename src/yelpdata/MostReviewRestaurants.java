

import org.json.simple.JSONArray;
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

import static Constants.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rick
 * Date: 3/14/14
 * Time: 4:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class MostReviewRestaurants {
    public static void main(String[] arg) {
        try {
            System.setIn(new FileInputStream(BUSINESS));
            System.setOut(new PrintStream(new FileOutputStream("mostReviewRestaurants.txt")));
        } catch (FileNotFoundException e) { }
        Scanner in = new Scanner(System.in);
        HashMap<String, Long> count = new HashMap<String, Long>();
        while (in.hasNextLine()) {
            JSONObject json = (JSONObject)JSONValue.parse(in.nextLine());
            if (!((JSONArray)json.get("categories")).contains("Restaurants")) {
                continue;
            }
            String business_id = (String)json.get("business_id");
            Long review_count = (Long)json.get("review_count");

            count.put(business_id, review_count);
        }
        ArrayList<Map.Entry<String, Long>> entryList = new ArrayList<Map.Entry<String, Long>>();
        for (Map.Entry<String, Long> entry : count.entrySet()) {
            entryList.add(entry);
        }
        Collections.sort(entryList, new Comparator<Map.Entry<String, Long>>() {
            public int compare(Map.Entry<String, Long> entry1, Map.Entry<String, Long> entry2) {
                return entry2.getValue() > entry1.getValue() ? 1 : -1;
            }
        });
        for (int i = 0; i < 100; i++) {
            System.out.println(entryList.get(i).getKey() + " " + entryList.get(i).getValue());
        }
    }
}
