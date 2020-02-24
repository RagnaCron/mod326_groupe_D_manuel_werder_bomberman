package ch13;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://stleary.github.io/JSON-java/index.html">JSON-java</a>
 */
public class JsonExample {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("name", "jon doe");
        map.put("age", "22");
        map.put("city", "chicago");
        JSONObject jo = new JSONObject(map);;
        
        System.out.println(jo);
//        map = jo.toMap();
//        System.out.println(map);
    }
}
