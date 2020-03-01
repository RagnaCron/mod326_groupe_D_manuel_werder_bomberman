package BombermanClientServerInterfaces;

import org.json.JSONArray;

public interface JSONDecode {
	default Message decode(JSONArray array) {
		String[] values = new String[array.length()];
		for (int i = 0; i < array.length(); i++) {
			values[i] = array.get(i).toString();
		}
		return new Message(values);
	}
}
