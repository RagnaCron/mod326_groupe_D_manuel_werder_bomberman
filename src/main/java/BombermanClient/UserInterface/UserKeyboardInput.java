package BombermanClient.UserInterface;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserKeyboardInput extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.out.println("Exiting Bomberman Game...");
				System.exit(0);
		}
	}

}
