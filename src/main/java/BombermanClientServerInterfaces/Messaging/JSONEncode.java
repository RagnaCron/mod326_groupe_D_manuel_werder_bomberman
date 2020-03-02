package BombermanClientServerInterfaces.Messaging;

public interface JSONEncode {
	default CustomJSONArray encode(Message message) {
		return new CustomJSONArray(message.PARAMETERS);
	}
}
