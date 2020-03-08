package BombermenClient.Bombermen.ClientCommunication;

import BombermenClientServerInterfaces.Messaging.CommandCode;
import BombermenClientServerInterfaces.Messaging.CustomJSONArray;
import BombermenClientServerInterfaces.Messaging.JSONDecode;
import BombermenClientServerInterfaces.Messaging.Message;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientMulticastUDPListener extends Thread implements JSONDecode {

	private final int INPUT_PORT;
	private ConcurrentLinkedQueue<Message> inputQueue;
	private String playerName = "";

	public ClientMulticastUDPListener(int port, ConcurrentLinkedQueue<Message> queue) {
		this.INPUT_PORT = port;
		this.inputQueue = queue;
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
