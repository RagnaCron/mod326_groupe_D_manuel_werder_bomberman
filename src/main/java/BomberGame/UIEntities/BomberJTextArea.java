package BomberGame.UIEntities;

import javax.swing.*;
import java.awt.*;

public final class BomberJTextArea extends JTextArea {

	public BomberJTextArea() {
		super(5, 49);
		setWrapStyleWord(true);
		setLineWrap(true);
		setFont(new Font("DialogInput", Font.PLAIN, 14));
		setEditable(false);
		setTabSize(4);

	}

	@Override
	public void append(String message) {
		super.append(message);
		setCaretPosition(getDocument().getLength() -1);
	}

}
