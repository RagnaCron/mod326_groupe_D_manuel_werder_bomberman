package BombermenServer;

import BombermenClientServerInterfaces.Messaging.JSONEncode;
import BombermenClientServerInterfaces.Messaging.Message;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerMulticastUDPSender extends Thread implements JSONEncode {

	private final InetAddress GROUP;
	private final int PORT;
	private ConcurrentLinkedQueue<Message> queue;

	public ServerMulticastUDPSender(InetAddress group, int port, ConcurrentLinkedQueue<Message> queue) {
		this.GROUP = group;
		this.PORT = port;
		this.queue = queue;
	}

	@SuppressWarnings("InfiniteLoopStatement")
	@Override
	public void run() {
		byte[] buffer;
		DatagramPacket packet;
			try {
				DatagramSocket socket = new DatagramSocket(PORT - 1);
				while (true) {
					if (!queue.isEmpty()) {
						buffer = encode(queue.poll()).toString().getBytes(StandardCharsets.UTF_8);
						packet = new DatagramPacket(buffer, buffer.length, GROUP, PORT);
						socket.send(packet);
						System.out.println(new String(packet.getData(), 0, packet.getLength()));
					}
						sleep(0, 1000);
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
	}
}
