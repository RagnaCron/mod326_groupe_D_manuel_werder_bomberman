package BombermanClientServerInterfaces.Messaging;

public final class Message {
	public final CommandCode CODE;
	public final String[] PARAMETERS;

	public Message(String[] values) {
		switch (values[0]) {
			case "move":
				CODE = CommandCode.MOVE;
				break;
			case "drop_bomb":
				CODE = CommandCode.DROP_BOMB;
				break;
			case "bomb_explode":
				CODE = CommandCode.BOMB_EXPLODE;
				break;
			case "bomb_collision":
				CODE = CommandCode.BOMB_COLLISION;
				break;
			case "logging":
				CODE = CommandCode.SERVER_LOGGING_MESSAGES;
				break;
			default:
				CODE = CommandCode.ERROR_CODE;
				break;
		}
		this.PARAMETERS = values;
	}

	public Message() {
		this.CODE = CommandCode.ERROR_CODE;
		this.PARAMETERS = new String[]{"Something went wrong!"};
	}
}
