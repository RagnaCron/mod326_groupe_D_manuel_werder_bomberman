package BombermanClient.Bomberman;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BombermanServerProxy implements Runnable {
	private static final String HOST = "127.0.0.1";
	private static final int INPUT_PORT = 8768;
	private static final int OUTPUT_PORT = 8764;

	@Override
	public void run() {
		try (Socket socket = new Socket(HOST, INPUT_PORT);
		     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		     Scanner sc = new Scanner(System.in)) {

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
}
