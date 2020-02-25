package BombermanClient.Bomberman;

import BombermanClient.GameConstants;
import BombermanClient.Labyrinth.Labyrinth;
import BombermanClient.UserInterface.UserGameKeyboardInput;
import BombermanClient.UserInterface.UserKeyboardInput;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Bomberman extends JFrame implements GameConstants {
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 8765;

	UserKeyboardInput keyboardInput = new UserKeyboardInput();

	public Bomberman() {
		super("Bomberman");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(BOMBERMAN_FRAME_SIZE);

		addKeyListener(keyboardInput);

		loadServerLogin();
		loadServerLoggingTextArea();
		loadLabyrinth();

		pack();
		setVisible(true);
	}

	public void runClient() {
		try (Socket socket = new Socket(HOST, PORT);
		     BufferedReader in = new BufferedReader(
		     		new InputStreamReader(socket.getInputStream()));
		     PrintWriter out = new PrintWriter(
		     		socket.getOutputStream(), true);
		     Scanner sc = new Scanner(System.in))
		{

			System.out.println(in.readLine());

			System.out.print("> ");
			String line = sc.nextLine();
			if (line.length() == 0)
				return;
			out.println(line);
			System.out.println("Antwort vom Server:");
			System.out.println(in.readLine());

			repaint();

		} catch (Exception e) {
			//noinspection ThrowablePrintedToSystemOut
			System.err.println(e);
		}
	}

	private void loadServerLogin() {
		JTextField textInput = new JTextField("");
		textInput.setBounds(TEXT_INPUT_POSITION);
		textInput.addKeyListener(keyboardInput);
		add(textInput);
		JButton signInButton = new JButton("Sign In");
		signInButton.setBounds(SING_IN_BUTTON_POSITION);
		signInButton.addKeyListener(keyboardInput);
		add(signInButton);
	}

	private void loadServerLoggingTextArea() {
		JTextArea area = new JTextArea(5, 49);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		area.setFont(new Font("DialogInput", Font.PLAIN, 18));
		area.setEditable(false);
		area.setTabSize(4);
		JScrollPane pane  = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBounds(SERVER_LOGGING_TEXTAREA_POSITION);
		add(pane);
	}

	private void loadLabyrinth() {
		Labyrinth labyrinth = new Labyrinth(LABYRINTH_WIDTH, LABYRINTH_HEIGHT);
		labyrinth.setBounds(LABYRINTH_POSITION);
		labyrinth.addKeyListener(new UserGameKeyboardInput());
		setLayout(null);
		add(labyrinth);
	}
}
