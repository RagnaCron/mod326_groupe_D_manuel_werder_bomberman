package BombermanClientServerInterfaces;

import BombermanClientServerInterfaces.Messaging.JSONDecode;
import BombermanClientServerInterfaces.Messaging.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractSocketListener extends Thread implements JSONDecode {
	protected Socket client;
	protected ConcurrentLinkedQueue<Message> queue;
	protected BufferedReader in;

	public AbstractSocketListener(Socket client, ConcurrentLinkedQueue<Message> queue) {
		this.client = client;
		this.queue = queue;
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
