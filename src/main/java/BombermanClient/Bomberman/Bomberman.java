package BombermanClient.Bomberman;

import BombermanClient.GameConstants;
import BombermanClient.Labyrinth.Labyrinth;
import BombermanClient.UserInterface.UserGameKeyboardInput;
import BombermanClient.UserInterface.UserKeyboardInput;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Bomberman extends JFrame implements GameConstants {
	private static final int PORT = 8765;

	UserKeyboardInput keyboardInput = new UserKeyboardInput();

	public Bomberman() {
		super("Bomberman");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(BOMBERMAN_FRAME_SIZE);

		addKeyListener(keyboardInput);

		loadServerLogin();
		loadLabyrinth();

		pack();
		setVisible(true);
	}

	public void runClient() {
		String HOST = "127.0.0.1";
		try (Socket socket = new Socket(HOST, PORT);
		     BufferedReader in = new BufferedReader(
		     		new InputStreamReader(socket.getInputStream()));
		     PrintWriter out = new PrintWriter(
		     		socket.getOutputStream(), true);
		     Scanner sc = new Scanner(System.in))
		{

			System.out.println(in.readLine());

			while (true) {
				System.out.print("> ");
				String line = sc.nextLine();
				if (line.length() == 0)
					break;
				out.println(line);
				System.out.println("Antwort vom Server:");
				System.out.println(in.readLine());
			}
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

	private void loadLabyrinth() {
		Labyrinth labyrinth = new Labyrinth(LABYRINTH_WIDTH, LABYRINTH_HEIGHT);
		labyrinth.setBounds(LABYRINTH_POSITION);
		labyrinth.addKeyListener(new UserGameKeyboardInput());
		setLayout(null);
		add(labyrinth);
	}

}
