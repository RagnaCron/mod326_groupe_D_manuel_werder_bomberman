package BombermenClientServerInterfaces.Messaging;

public final class Message {
	public CommandCode CODE;
	public String[] PARAMETERS;

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
			case "player_login":
				CODE = CommandCode.PLAYER_LOGIN;
				break;
			case "player_login_error":
				CODE = CommandCode.PLAYER_LOGIN_ERROR;
				break;
			case "load_labyrinth":
				CODE = CommandCode.LOAD_LABYRINTH;
				break;
			case "player_exit":
				CODE = CommandCode.PLAYER_EXIT;
				break;
			case "error_code":
			default:
				CODE = CommandCode.ERROR_CODE;
		}
		this.PARAMETERS = values;
	}

	public Message(CommandCode code, String[] message) {
		this.CODE = code;
		this.PARAMETERS = message;
	}
}
