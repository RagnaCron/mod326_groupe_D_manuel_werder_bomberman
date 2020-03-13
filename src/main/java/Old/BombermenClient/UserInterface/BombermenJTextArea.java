package Old.BombermenClient.UserInterface;

import javax.swing.*;
import java.awt.*;

public final class BombermenJTextArea extends JTextArea {

	public BombermenJTextArea() {
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
