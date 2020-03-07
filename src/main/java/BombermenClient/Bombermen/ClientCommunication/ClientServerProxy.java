package BombermenClient.Bombermen.ClientCommunication;

import BombermenClient.UserInterface.BombermenJTextArea;
import BombermenClient.UserInterface.BombermenJTextField;
import BombermenClientServerInterfaces.Messaging.CommandCode;
import BombermenClientServerInterfaces.Messaging.CustomJSONArray;
import BombermenClientServerInterfaces.Messaging.Message;

import javax.swing.*;
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
	private BombermenJTextField textField;
	private JButton button;
	private String playerName = "";

	public ClientServerProxy(ConcurrentLinkedQueue<Message> inputQueue,
	                         ConcurrentLinkedQueue<Message> outputQueue,
	                         BombermenJTextArea textArea,
	                         BombermenJTextField textField,
	                         JButton button)
	{
		this.inputQueue = inputQueue;
		this.outputQueue = outputQueue;
//		this.labyrinth = labyrinth;
		this.textArea = textArea;
		this.textField = textField;
		this.button = button;

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
						case PLAYER_LOGIN_SUCCESS:
							loginSuccess(message);
							break;
						case PLAYER_LOGIN_ERROR:
							append(message);
							break;
						case PLAYER_EXIT:
							message.getParameters().put(1, playerName);
							outputQueue.add(message);
							break;
						case PLAYER_GOODBYE:
							isRunning = isMyGoodbye(message);
							break;
						case LOAD_LABYRINTH:
							break;
						case ERROR_CODE:
							break;
					}
					sleep(0, 1000);
				}
			}
			outputSocket.close();
			join();
		} catch (Exception ignored) {}
	}

	private void loginSuccess(Message message) {
		playerName = message.getPlayerName();
		append(message);
//		textField.setVisible(false);
		button.setVisible(false);
		textField.setEditable(false);
	}

	private boolean isMyGoodbye(Message message) {
		message.removeName();
		append(message);
		return !message.getPlayerName().equals(playerName);
	}

	private void append(Message message) {
		if (message.getCode() == CommandCode.PLAYER_LOGIN_SUCCESS)
			message.removeName();
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
