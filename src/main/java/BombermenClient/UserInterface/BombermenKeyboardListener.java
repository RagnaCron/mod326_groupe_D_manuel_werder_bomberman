package BombermenClient.UserInterface;

import BombermenClientServerInterfaces.Messaging.CommandCode;
import BombermenClientServerInterfaces.Messaging.Message;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentLinkedQueue;

import static BombermenClient.Bombermen.Bombermen.GoodbyePlayer;

public final class BombermenKeyboardListener extends KeyAdapter {
	private static BombermenKeyboardListener keyboardListener;
	private static ConcurrentLinkedQueue<Message> queue;
	private BombermenKeyboardListener() {
		super();
	}
	public synchronized static BombermenKeyboardListener CreateBombermenKeyboardListener(ConcurrentLinkedQueue<Message> outputQueue) {
		if (keyboardListener == null) {
			keyboardListener = new BombermenKeyboardListener();
			queue = outputQueue;
		}
		return keyboardListener;
	}
	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
			queue.add(new Message(CommandCode.PLAYER_EXIT, "player_exit playerID socketID saveGamePoints".split(" ")));
			GoodbyePlayer();
		}
	}
}
