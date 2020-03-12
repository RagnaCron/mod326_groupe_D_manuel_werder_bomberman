package BomberMen.BomberClientServerInterfaces.Messaging;

public enum CommandCode {
	MOVE,
	DROP_BOMB,
	BOMB_EXPLODE,
	BOMB_COLLISION,
	PLAYER_LOGIN,
	PLAYER_LOGIN_SUCCESS,
	PLAYER_LOGIN_ERROR,
	PLAYER_EXIT,
	PLAYER_GOODBYE,
	PLAYER_POSITION,
	LOAD_LABYRINTH,
	START_GAME,
	ERROR_CODE,
	SERVER_FULL,
	SERVER_CONNECTION_ERROR,
}
