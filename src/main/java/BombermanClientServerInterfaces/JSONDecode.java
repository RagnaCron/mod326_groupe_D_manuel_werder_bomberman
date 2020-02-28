package BombermanClientServerInterfaces;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;

public interface JSONDecode {
	default Message decode(JSONArray jarray) {
		List<String> values = Arrays.asList(jarray.toString().split(" "));
		return new Message(values);
	}
}
