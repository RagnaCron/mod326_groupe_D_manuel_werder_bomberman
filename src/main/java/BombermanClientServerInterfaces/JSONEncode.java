package BombermanClientServerInterfaces;

import org.json.JSONArray;

public interface JSONEncode {
	default JSONArray encode(Message message) {
		return new JSONArray(message.PARAMETERS);
	}
}
