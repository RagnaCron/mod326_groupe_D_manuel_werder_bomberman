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
	private ConcurrentLinkedQueue<Message> queue;

	public ClientMulticastUDPListener(int port, ConcurrentLinkedQueue<Message> queue) {
		this.INPUT_PORT = port;
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			MulticastSocket inputSocket = new MulticastSocket(INPUT_PORT);
			InetAddress address = InetAddress.getByName("230.0.0.1");
			inputSocket.joinGroup(address);
			DatagramPacket packet;

			byte[] buffer = new byte[256];
			String received;
			Message serverMessage;
			boolean isRunning = true;
			while (isRunning) {

//				if (true) {
//				if (in.ready()) {
//					String input = in.readLine();
//					System.err.println(input);
//					System.out.format("%s: %s%n", Thread.currentThread().getName(), input);
//					serverMessage = decode(new CustomJSONArray(input));
					packet = new DatagramPacket(buffer, buffer.length);
					inputSocket.receive(packet);
					received = new String(packet.getData(), 0, packet.getLength());

					System.out.println(received);

					serverMessage = decode(new CustomJSONArray(received));
					if (serverMessage.getCode() == CommandCode.PLAYER_EXIT) // TODO: ADD PLAYER NAME TO CONFIRM
						isRunning = false;

					queue.add(serverMessage);
					sleep(0, 10000);
//				} else {
////					System.out.format("%s: %s%n", Thread.currentThread().getName(), "Sleeps for 1 milliseconds...");
//					sleep(1);
//				}
			}
			System.out.format("%s: %s%n", Thread.currentThread().getName(), "Closing this Thread...");
			inputSocket.leaveGroup(address);
			inputSocket.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
