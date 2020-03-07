package BombermenClient.Bombermen.ClientCommunication;

import BombermenClient.UserInterface.BombermenJTextArea;
import BombermenClientServerInterfaces.Messaging.CustomJSONArray;
import BombermenClientServerInterfaces.Messaging.Message;

import java.net.Inet4Address;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientServerProxy extends Thread {
	private static final int INPUT_PORT = 8768;
	private static final int OUTPUT_PORT = 8764;

	private ConcurrentLinkedQueue<Message> inputQueue;
	private ConcurrentLinkedQueue<Message> outputQueue;

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
			outputSocket = new Socket(host, OUTPUT_PORT);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void run() {
		(new Thread(new ClientMulticastUDPListener(INPUT_PORT, inputQueue), "Client Input Thread")).start();
		(new Thread(new ClientSocketSender(outputSocket, outputQueue), "Client Output Thread")).start();

		Message message;

		boolean isRunning = true;
		try {
			while (isRunning) {
				if(!inputQueue.isEmpty()) {
					message = inputQueue.poll();
					System.err.format("Ready to process Messages... %s%n", message.readFirst());
//				CustomJSONArray array = message.getParameters();
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
						case PLAYER_LOGIN_ERROR:
							append(message);
							break;
						case PLAYER_EXIT:
							isRunning = false;
							break;
						case LOAD_LABYRINTH:
							break;
						case ERROR_CODE:
							break;
					}
					sleep(0, 1000);
				}
			}
		} catch (Exception ignored) {}
	}

	private void append(Message message) {
		textArea.append(parse(message.getParameters()));
	}

	private String parse(CustomJSONArray array) {
		array.remove(0);
		return array.toJSONString() + '\n';
	}

//	private void process(List<Message> chunks) {
//		for (var message : chunks) {
//			if (message.getCode() == CommandCode.PLAYER_LOGIN_SUCCESS) {
//				textArea.append(parse(message.getParameters()));
//			}
//			else if (message.getCode() == CommandCode.PLAYER_LOGIN_ERROR) {
//				textArea.append(parse(message.getParameters()));
//			}
//		}
//	}




}
