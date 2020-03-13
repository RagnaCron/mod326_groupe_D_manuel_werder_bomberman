package BomberMen.BomberServer;

import BomberMen.BomberClientServerInterfaces.Messaging.JSONEncode;
import BomberMen.BomberClientServerInterfaces.Messaging.Message;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BomberServerMulticastUDPSender extends Thread implements JSONEncode {

	private final InetAddress GROUP;
	private final int PORT;
	private ConcurrentLinkedQueue<Message> outputQueue;

	public BomberServerMulticastUDPSender(InetAddress group, int port, ConcurrentLinkedQueue<Message> outputQueue) {
		this.GROUP = group;
		this.PORT = port;
		this.outputQueue = outputQueue;
	}

	@SuppressWarnings("InfiniteLoopStatement")
	@Override
	public void run() {
		byte[] buffer;
		DatagramPacket packet;
			try {
				DatagramSocket socket = new DatagramSocket(PORT - 1);
				while (true) {
					if (!outputQueue.isEmpty()) {
						buffer = encode(outputQueue.poll()).toString().getBytes(StandardCharsets.UTF_8);
						packet = new DatagramPacket(buffer, buffer.length, GROUP, PORT);
						socket.send(packet);
					}
					sleep(0, 1000);
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
	}
}
