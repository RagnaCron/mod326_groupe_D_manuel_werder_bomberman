package ExamplesAndFoundations.ch13;

import BombermenClientServerInterfaces.Messaging.CustomJSONArray;
import BombermenClientServerInterfaces.Messaging.JSONEncode;
import BombermenClientServerInterfaces.Messaging.Message;

import java.util.Arrays;

/**
 * @see <a href="https://stleary.github.io/JSON-java/index.html">JSON-java</a>
 */
public class JsonExample implements JSONEncode {

    private final static String[][] DefaultBoard = {
            {"1","2","2","2","2","2","2","2","2","2","2","2","2","2","2","1"},
            {"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
            {"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
            {"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
            {"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
            {"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
            {"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
            {"1","3","3","3","3","3","3","3","3","3","3","3","3","3","3","1"},
            {"1","3","3","3","3","3","3","3","3","3","3","3","3","3","3","1"},
            {"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
            {"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
            {"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
            {"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
            {"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
            {"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
            {"2","2","2","2","2","2","2","2","2","2","2","2","2","2","2","2"},
    };


    public static void main(String[] args) {

        Message message = new Message(new String[]{"load_labyrinth", Arrays.deepToString(DefaultBoard)});
        CustomJSONArray array = new CustomJSONArray(message.getParameters().get(1).toString());
        System.err.println(array.toString());
        for (int i = 0; i < array.length(); i++) {
            for (int j = 0; j < array.getJSONArray(i).length(); j++) {
                    System.err.print(array.getJSONArray(i).getInt(j));
            }
            System.err.println();
        }

//        Map<String, String> map = new HashMap<>();
//        map.put("name", "jon doe");
//        map.put("age", "22");
//        map.put("city", "chicago");
//        JSONObject jo = new JSONObject(map);
//        System.out.println(jo);
//
//        JsonExample example = new JsonExample();
//        CustomJSONArray array = example.encode(new Message(new String[]{"error_code", "goodbye"}));
//        System.out.println(array);
//        System.out.println(array.toJSONString());
//        System.out.println(new CustomJSONArray(array.toString()));
//        System.out.println(array.toString());
//
//        array = new CustomJSONArray(array.toString());
//        System.out.println(array);
//        JSONArray arr = new JSONArray(array.toString());
//        arr.put(1, "playerName");
//        System.out.println(arr);

//        map = jo.toMap();
//        System.out.println(map);
    }
}
