package Old.BombermenClientServerInterfaces.Messaging;

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
			case "player_goodbye":
				code = CommandCode.PLAYER_GOODBYE;
				break;
			case "load_labyrinth":
				code = CommandCode.LOAD_LABYRINTH;
				break;
			case "start_game":
				code = CommandCode.START_GAME;
				break;
			case "error_code":
				code = CommandCode.ERROR_CODE;
				break;
			case "server_full":
				code = CommandCode.SERVER_FULL;
				break;
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

	public boolean contains(String value) {
		for (var str : parameters)
			if (str.toString().equals(value))
				return true;
		return false;
	}

	public String getPlayerName() {
		return parameters.get(1).toString();
	}

	public void removeName() {
		parameters.remove(1);
	}
}
