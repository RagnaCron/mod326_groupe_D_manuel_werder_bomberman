package BomberMen.BomberGame.ServerConnection;

import BomberMen.BomberClientServerInterfaces.Messaging.CommandCode;
import BomberMen.BomberClientServerInterfaces.Messaging.CustomJSONArray;
import BomberMen.BomberClientServerInterfaces.Messaging.JSONDecode;
import BomberMen.BomberClientServerInterfaces.Messaging.Message;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class BomberClientMulticastUDPListener extends Thread implements JSONDecode {

	private final int INPUT_PORT;
	private final String GROUP;
	private ConcurrentLinkedQueue<Message> inputQueue;
	private String playerName = "";

	public BomberClientMulticastUDPListener(int port, String group, ConcurrentLinkedQueue<Message> inputQueue) {
		this.INPUT_PORT = port;
		this.GROUP = group;
		this.inputQueue = inputQueue;
	}

	@Override
	public void run() {
		try {
			MulticastSocket inputSocket = new MulticastSocket(INPUT_PORT);
			InetAddress address = InetAddress.getByName("230.0.0.1");
			inputSocket.joinGroup(address);
			DatagramPacket packet;
			byte[] buffer;
			String received;
			Message serverMessage;
			boolean isRunning = true;
			while (isRunning) {
				buffer = new byte[1024];
				packet = new DatagramPacket(buffer, buffer.length);
				inputSocket.receive(packet);
				received = new String(packet.getData(), 0, packet.getLength());
				serverMessage = decode(new CustomJSONArray(received));
				isRunning = stillRunning(serverMessage);
				inputQueue.add(serverMessage);
				sleep(0, 1000);
			}
			System.out.format("%s: %s%n", Thread.currentThread().getName(), "Closing this Thread...");
			inputSocket.leaveGroup(address);
			inputSocket.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private boolean stillRunning(Message message) {
		if (message.getCode() == CommandCode.PLAYER_GOODBYE && (playerName.isEmpty() || playerName.equals(message.getPlayerName()))) {
			return false;
		}
		else if (message.getCode() == CommandCode.SERVER_FULL && (playerName.isEmpty())) {
			return false;
		}
		else if (message.getCode() == CommandCode.PLAYER_LOGIN_SUCCESS && !playerName.isEmpty()) {
			playerName = message.getPlayerName();
			return true;
		}
		return true;
	}
}
