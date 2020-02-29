package BombermanClientServerInterfaces;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;

public interface JSONDecode {
	default Message decode(JSONArray array) {
		List<String> values = Arrays.asList(array.toString().split(" "));
		return new Message(values);
	}
}
