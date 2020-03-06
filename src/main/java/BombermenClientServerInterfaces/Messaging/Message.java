package BombermenClientServerInterfaces.Messaging;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public final class Message {
	private CommandCode code;
	private CustomJSONArray parameters;

	public Message(CustomJSONArray array) {
		this.parameters = array;
		switch (array.getString(0)){
			case "move":
				code = CommandCode.MOVE;
				break;
			case "drop_bomb":
				code = CommandCode.DROP_BOMB;
				break;
			case "bomb_explode":
				code = CommandCode.BOMB_EXPLODE;
				break;
			case "bomb_collision":
				code = CommandCode.BOMB_COLLISION;
				break;
			case "logging":
				code = CommandCode.SERVER_LOGGING_MESSAGES;
				break;
			case "player_login":
				code = CommandCode.PLAYER_LOGIN;
				break;
			case "player_login_success":
				code = CommandCode.PLAYER_LOGIN_SUCCESS;
				break;
			case "player_login_error":
				code = CommandCode.PLAYER_LOGIN_ERROR;
				break;
			case "player_exit":
				code = CommandCode.PLAYER_EXIT;
				break;
			case "load_labyrinth":
				code = CommandCode.LOAD_LABYRINTH;
				break;
			case "error_code":
			default:
				code = CommandCode.ERROR_CODE;
		}
	}

	public Message(String[] array) {
		this(new CustomJSONArray(array));
	}

	public Message(String message) {
		this(message.split(" "));
	}

	public String readFirst() {
		return parameters.getString(0);
	}

	public boolean setValue(int index, String value) {
		try {
			parameters.put(index, value);
			return true;
		} catch (Exception ignored) {
			return false;
		}
	}

	public String getValue(int index) {
		return parameters.getString(index);
	}

}
