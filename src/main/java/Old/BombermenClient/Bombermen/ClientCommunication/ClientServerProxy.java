package Old.BombermenClient.Bombermen.ClientCommunication;

import Old.BombermenClient.Bombermen.Bombermen;
import Old.BombermenClient.Labyrinth.Labyrinth;
import Old.BombermenClient.UserInterface.BombermenJTextArea;
import Old.BombermenClient.UserInterface.BombermenJTextField;
import Old.BombermenClientServerInterfaces.Messaging.CommandCode;
import Old.BombermenClientServerInterfaces.Messaging.CustomJSONArray;
import Old.BombermenClientServerInterfaces.Messaging.Message;

import javax.swing.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientServerProxy extends Thread {
	private static final int INPUT_PORT = 8768;
	private static final int OUTPUT_PORT = 8764;

	private ConcurrentLinkedQueue<Message> inputQueue;
//	private ConcurrentLinkedQueue<Message> deliveryQueue;
	private ConcurrentLinkedQueue<Message> outputQueue;

	private Socket outputSocket;

	private Labyrinth labyrinth;
	private BombermenJTextArea textArea;
	private BombermenJTextField textField;
	private JButton button;
	private String playerName = "";

	private Bombermen bombermen;

	public ClientServerProxy(Bombermen bombermen,
	                         ConcurrentLinkedQueue<Message> inputQueue,
//	                         ConcurrentLinkedQueue<Message> deliveryQueue,
                             ConcurrentLinkedQueue<Message> outputQueue,
                             Labyrinth labyrinth,
                             BombermenJTextArea textArea,
                             BombermenJTextField textField,
                             JButton button)
	{
		this.bombermen = bombermen;
		this.inputQueue = inputQueue;
//		this.deliveryQueue = deliveryQueue;
		this.outputQueue = outputQueue;
		this.labyrinth = labyrinth;
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
					System.err.format("Ready to process Messages... %s%n", message.getParameters().toJSONString());
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
							System.out.println("Time to load the labyrinth...");
							loadBoard(message);
							break;
						case START_GAME:
							labyrinth.loadPlayers(message);
							append(message);
							break;
						case ERROR_CODE:
							break;
						case SERVER_FULL:
							isRunning = serverIsFull(message);
							break;
					}
					sleep(0, 10000);
				}
			}
			outputSocket.close();
			inputQueue = null;
			outputQueue = null;
			join();
		} catch (Exception ignored) {}
	}

	private void loadBoard(Message message) {
//		SwingWorker<CustomJSONArray, Message> worker = new SwingWorker<>() {
//			@Override
//			protected CustomJSONArray doInBackground() {
//				return (CustomJSONArray) message.getParameters().getJSONArray(1);
//			}
//
//
//		};
//
//		worker.execute();
//		worker.addPropertyChangeListener(new Labyrinth.ChangeLister(worker.get()));
//		worker.firePropertyChange();
//		worker.execute();

//		(new Thread(() -> {
//			try {
//				labyrinth.loadPlayingBoard(new CustomJSONArray(message.getParameters().getInt(1)));
////				labyrinth = new Labyrinth(new CustomJSONArray(message.getParameters().getInt(1)));
//				labyrinth.setVisible(true);
//				labyrinth.updateUI();
//				sleep(1000);
//				join();
//				System.err.println("Should have meade update to labyrinth");
//			} catch (Exception ignored) {}
//		})).start();
	}

	private boolean serverIsFull(Message message) {
//		button.setEnabled(true);
//		textField.setEnabled(true);
		outputQueue.add(new Message("server_full"));
		append(message);
		return false;
	}

	private void loginSuccess(Message message) {
		playerName = message.getPlayerName();
		append(message);
		button.setEnabled(false);
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
