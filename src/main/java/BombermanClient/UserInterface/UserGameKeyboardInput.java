package BombermanClient.UserInterface;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserGameKeyboardInput extends KeyAdapter {


	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE:
				System.out.println("Exiting Bomberman Game...");
				System.exit(0);
				break;
			case KeyEvent.VK_W:
				System.out.println("Go up...");
				break;
			case KeyEvent.VK_A:
				System.out.println("Go left...");
				break;
			case KeyEvent.VK_D:
				System.out.println("Go right...");
				break;
			case KeyEvent.VK_S:
				System.out.println("Go down...");
				break;
			case KeyEvent.VK_SPACE:
				System.out.println("Drop bomb...");
				break;
			default:
				break;
		}
	}

//	@Override
//	public void keyTyped(KeyEvent e) {
//
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//		System.out.print("Released key: ");
//		System.out.println( e.getKeyCode());
//	}

}



//class txtInputListener implements ActionListener
//{
//
//	JTextField txtInput = new JTextField("");
//
//	public void actionPerformed(ActionEvent event)
//	{
//		String input = txtInput.getText();   //receive input from text field
//		System.out.println(input);
//	}
//}
