package BombermanClientServerInterfaces.Messaging;

public interface JSONDecode {
	default Message decode(CustomJSONArray array) {
		String[] values = new String[array.length()];
		for (int i = 0; i < array.length(); i++) {
			values[i] = array.get(i).toString();
		}
		return new Message(values);
	}
}
