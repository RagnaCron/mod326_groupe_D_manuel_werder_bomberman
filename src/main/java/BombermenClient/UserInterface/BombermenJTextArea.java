package BombermenClient.UserInterface;

import javax.swing.*;
import java.awt.*;

public final class BombermenJTextArea extends JTextArea {

	public BombermenJTextArea() {
		super(5, 49);
		setWrapStyleWord(true);
		setLineWrap(true);
		setFont(new Font("DialogInput", Font.PLAIN, 18));
		setEditable(false);
		setTabSize(4);
	}

}
