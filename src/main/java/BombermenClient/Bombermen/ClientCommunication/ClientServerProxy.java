package BombermenClient.Bombermen.ClientCommunication;

import BombermenClientServerInterfaces.Messaging.CommandCode;
import BombermenClientServerInterfaces.Messaging.Message;

import javax.swing.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientServerProxy extends SwingWorker<Message, Message> {
//	private String HOST;
	private static final int INPUT_PORT = 8768;
	private static final int OUTPUT_PORT = 8764;

	private ConcurrentLinkedQueue<Message> inputQueue;
	private ConcurrentLinkedQueue<Message> outputQueue;


//	private Socket inputSocket;
	private Socket outputSocket;

//	private Labyrinth labyrinth;
	private JTextArea textArea;

	public ClientServerProxy(ConcurrentLinkedQueue<Message> inputQueue,
	                         ConcurrentLinkedQueue<Message> outputQueue,
	                         JTextArea textArea)
	{
		this.inputQueue = inputQueue;
		this.outputQueue = outputQueue;
//		this.labyrinth = labyrinth;
		this.textArea = textArea;
		try {
			String host = Inet4Address.getLocalHost().getHostName();
//			inputSocket = new Socket(host, INPUT_PORT);
			outputSocket = new Socket(host, OUTPUT_PORT);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	protected Message doInBackground() {
//		(new Thread(new ClientSocketListener(inputSocket, inputQueue), "Client Input Thread")).start();
		(new Thread(new ClientMulticastUDPListener(INPUT_PORT, inputQueue), "Client Input Thread")).start();
		(new Thread(new ClientSocketSender(outputSocket, outputQueue), "Client Output Thread")).start();
		Message message;
		while (!isCancelled()) {
			if(!inputQueue.isEmpty()) {
				message = inputQueue.poll();
				System.err.format("Ready to process Messages... %s%n", message.PARAMETERS[0]);
//				publish(message);
				switch (message.CODE){
					case MOVE:
						break;
					case DROP_BOMB:
						break;
					case BOMB_COLLISION:
						break;
					case BOMB_EXPLODE:
						break;
					case SERVER_LOGGING_MESSAGES:
						break;
//					case PLAYER_LOGIN:
//						break;
					case PLAYER_LOGIN_SUCCESS:
						System.out.println(message.PARAMETERS.toString());
						break;
					case PLAYER_LOGIN_ERROR:
						System.out.println(message.PARAMETERS.toString());
						break;
					case PLAYER_EXIT:
						break;
					case LOAD_LABYRINTH:
						break;
					case ERROR_CODE:
						break;
				}
				try {
					wait(0, 10000);
				} catch (InterruptedException ignored) {}
			}
		}
		return new Message(
				CommandCode.ERROR_CODE,
				new String[]{"error_code", "Restart the game...you lost the server connection!"});
	}

//	@Override
//	protected void process(List<Message> chunks) {
//		for (Message message : chunks) {
//
//		}
//	}

}
