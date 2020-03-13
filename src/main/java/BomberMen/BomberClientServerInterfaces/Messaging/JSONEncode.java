package BomberMen.BomberClientServerInterfaces.Messaging;

public interface JSONEncode {
	default CustomJSONArray encode(Message message) {
		return message.getParameters();
	}
}
