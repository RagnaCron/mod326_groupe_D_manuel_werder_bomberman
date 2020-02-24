package BombermanServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings("InfiniteLoopStatement")
public class Main {
	private static final int PORT = 8765;

	public static void main(String[] args) {
//		int port = 8765;

		try (ServerSocket server = new ServerSocket(PORT)) {
			System.out.println("EchoServer auf " + PORT + " gestartet ...");
			while (true) {
				Socket client = server.accept();
				new BombermanServer(client).start();
			}
		} catch (IOException e) {
			//noinspection ThrowablePrintedToSystemOut
			System.err.println(e);
		}
	}
}
