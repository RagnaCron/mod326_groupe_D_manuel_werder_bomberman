package BomberMen.BomberClientServerInterfaces;

import BomberMen.BomberClientServerInterfaces.Messaging.CommandCode;
import BomberMen.BomberClientServerInterfaces.Messaging.CustomJSONArray;
import BomberMen.BomberClientServerInterfaces.Messaging.JSONDecode;
import BomberMen.BomberClientServerInterfaces.Messaging.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractSocketListener extends Thread implements JSONDecode {
	protected Socket client;
	protected ConcurrentLinkedQueue<Message> inputQueue;
	protected BufferedReader in;

	public AbstractSocketListener(Socket client, ConcurrentLinkedQueue<Message> inputQueue) {
		this.client = client;
		this.inputQueue = inputQueue;
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException exception) {
			inputQueue.add(new Message(new String[]{"server_connection_error", "Ups... you cannot connect to the Server."}));
		}
	}

	@Override
	public void run() {
		Message message;
		String input;
		while (true) {
			try {
				if (in.ready()) {
					input = in.readLine();
					message = decode(new CustomJSONArray(input));
					inputQueue.add(message);
					if (message.getCode() == CommandCode.PLAYER_EXIT)
						break;
				}
				sleep(0, 1000);
			} catch (Exception ignored) {}
		}
	}

}
