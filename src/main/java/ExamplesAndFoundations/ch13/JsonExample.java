package ExamplesAndFoundations.ch13;

import BombermenClientServerInterfaces.Messaging.CustomJSONArray;
import BombermenClientServerInterfaces.Messaging.JSONEncode;
import BombermenClientServerInterfaces.Messaging.Message;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://stleary.github.io/JSON-java/index.html">JSON-java</a>
 */
public class JsonExample implements JSONEncode {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("name", "jon doe");
        map.put("age", "22");
        map.put("city", "chicago");
        JSONObject jo = new JSONObject(map);
        System.out.println(jo);

        JsonExample example = new JsonExample();
        CustomJSONArray array = example.encode(new Message(new String[]{"error_code", "goodbye"}));
        System.out.println(array);
        System.out.println(array.toJSONString());
        System.out.println(new CustomJSONArray(array.toString()));
        System.out.println(array.toString());

        array = new CustomJSONArray(array.toString());
        System.out.println(array);

//        map = jo.toMap();
//        System.out.println(map);
    }
}
