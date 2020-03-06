package BombermenClient.Bombermen.ClientCommunication;

import BombermenClient.UserInterface.BombermenJTextArea;
import BombermenClientServerInterfaces.Messaging.CustomJSONArray;
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
	private BombermenJTextArea textArea;

	public ClientServerProxy(ConcurrentLinkedQueue<Message> inputQueue,
	                         ConcurrentLinkedQueue<Message> outputQueue,
	                         BombermenJTextArea textArea)
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
		boolean isRunning = true;
		while (isRunning) {
			if(!inputQueue.isEmpty()) {
				message = inputQueue.poll();
				System.err.format("Ready to process Messages... %s%n", message.readFirst());
				CustomJSONArray array = message.getParameters();
//				publish(message);
				switch (message.getCode()){
					case MOVE:
					case DROP_BOMB:
					case BOMB_COLLISION:
					case BOMB_EXPLODE:
						break;
					case SERVER_LOGGING_MESSAGES:
						break;
//					case PLAYER_LOGIN:
//						break;
					case PLAYER_LOGIN_SUCCESS:
						array.remove(0);
						textArea.appendSuccess(array.toJSONString());
						break;
					case PLAYER_LOGIN_ERROR:
						array.remove(0);
						textArea.appendError(array.toJSONString());
						break;
					case PLAYER_EXIT:
						isRunning = false;
						break;
					case LOAD_LABYRINTH:
						break;
					case ERROR_CODE:
						break;
				}
				try {
					wait(0, 1000);
				} catch (InterruptedException ignored) {}
			}
		}
		return new Message(new String[]{"error_code", "Restart the game...you lost the server connection!"});
	}

//	@Override
//	protected void process(List<Message> chunks) {
//		for (Message message : chunks) {
//
//		}
//	}

}
