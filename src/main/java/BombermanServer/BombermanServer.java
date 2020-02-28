package BombermanServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BombermanServer extends Thread {

	private Socket client;

	public BombermanServer(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try (BufferedReader in =
				     new BufferedReader(
						     new InputStreamReader(client.getInputStream()));
		     PrintWriter out =
				     new PrintWriter(
						     client.getOutputStream(), true)) {

			out.println("Hallo, ich bin der EchoServer");

			String input;
			while ((input = in.readLine()) != null) {
				System.out.println("Meldung vom Client: "+ input);
				out.println(input);
			}
		} catch (IOException e) {
			//noinspection ThrowablePrintedToSystemOut
			System.err.println(e);
		}
	}
}
