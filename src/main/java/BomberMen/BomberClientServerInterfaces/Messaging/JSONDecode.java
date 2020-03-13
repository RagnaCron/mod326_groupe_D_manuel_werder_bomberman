package BomberMen.BomberClientServerInterfaces.Messaging;

public interface JSONDecode {
	default Message decode(CustomJSONArray array) {
		return new Message(array);
	}
}
