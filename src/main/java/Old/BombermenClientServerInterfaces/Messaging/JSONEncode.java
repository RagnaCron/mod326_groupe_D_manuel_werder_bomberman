package Old.BombermenClientServerInterfaces.Messaging;

public interface JSONEncode {
	default CustomJSONArray encode(Message message) {
		return message.getParameters();
	}
}
