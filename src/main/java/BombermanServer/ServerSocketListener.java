package BombermanServer;

import java.net.Socket;

public class ServerSocketListener implements Runnable {
	private Socket client;

	public ServerSocketListener(Socket client) {
		this.client = client;
	}


	@Override
	public void run() {

	}

}
