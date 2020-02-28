package BombermanClientServerInterfaces;

import java.util.List;

public final class Message {
	public final Code CODE;
	public final List<String> PARAMETERS;

	public Message(List<String> values) {
		switch (values.get(0)) {
			case "move":
				CODE = Code.MOVE;
				break;
			case "drop_bomb":
				CODE = Code.DROP_BOMB;
				break;
			case "bomb_collision":
				CODE = Code.BOMB_COLLISION;
				break;
			case "logging":
				CODE = Code.SERVER_LOGGING_MESSAGES;
				break;
			default:
				CODE = Code.ERROR_CODE;
				break;
		}
		this.PARAMETERS = values;
	}
}
