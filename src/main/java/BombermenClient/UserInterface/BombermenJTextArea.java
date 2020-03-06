package BombermenClient.UserInterface;

import javax.swing.*;
import java.awt.*;

public final class BombermenJTextArea extends JTextArea {

	public BombermenJTextArea() {
		super(5, 49);
		setWrapStyleWord(true);
		setLineWrap(true);
		setFont(new Font("DialogInput", Font.PLAIN, 16));
		setEditable(false);
		setTabSize(4);
	}

	@Override
	public void append(String str) {
		setCaretColor(Color.BLACK);
		super.append(str);
	}

	public void appendSuccess(String message) {
		setCaretColor(Color.GREEN);
		append(message);
	}

	public void appendError(String message) {
		setCaretColor(Color.RED);
		append(message);
	}
}
