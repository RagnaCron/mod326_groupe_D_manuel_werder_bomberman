package BombermenClient.Bombermen;

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
//	private Labyrinth labyrinth;

	private Socket inputSocket;
	private Socket outputSocket;

	public ClientServerProxy(ConcurrentLinkedQueue<Message> outputQueue
	                         )
	{
		this.inputQueue = new ConcurrentLinkedQueue<>();
		this.outputQueue = outputQueue;
//		this.labyrinth = labyrinth;
		try {
			String host = Inet4Address.getLocalHost().getHostAddress();
			inputSocket = new Socket(host, INPUT_PORT);
			outputSocket = new Socket(host, OUTPUT_PORT);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	protected Message doInBackground() {
		(new Thread(new ClientSocketListener(inputSocket, inputQueue), "Client Input Thread")).start();
		(new Thread(new ClientSocketSender(outputSocket, outputQueue), "Client Output Thread")).start();
		Message message;
		while (!isCancelled()) {
			if(!inputQueue.isEmpty()) {
				message = inputQueue.poll();
//				System.err.format("Ready to process Messages... %s%n", message.PARAMETERS[0]);
//				publish(message);
//				switch (message.CODE){
//					case MOVE:
//						break;
//					case DROP_BOMB:
//						break;
//					case BOMB_COLLISION:
//						break;
//					case BOMB_EXPLODE:
//						break;
//					case SERVER_LOGGING_MESSAGES:
//						break;
//					case PLAYER_LOGIN:
//
//						break;
//					case LOAD_LABYRINTH:
//						break;
//					case ERROR_CODE:
//						break;
//				}
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

	//	@Override
//	public void runasdf() {
//		try (Socket inputSocket = new Socket(HOST, INPUT_PORT);
//		     Socket outputSocket = new Socket(HOST, OUTPUT_PORT);
//		     BufferedReader in = new BufferedReader(new InputStreamReader(inputSocket.getInputStream()));
//		     PrintWriter out = new PrintWriter(outputSocket.getOutputStream(), true);
//		     Scanner sc = new Scanner(System.in))
//		{
//			System.out.println(in.readLine());
//
//			while (true) {
//				System.out.print("> ");
//				String line = sc.nextLine();
//				if (line.length() == 0)
//					break;
//				out.println(line);
//				System.out.println("Antwort vom Server:");
//				System.out.println(in.readLine());
//			}
//		} catch (Exception e) {
//			//noinspection ThrowablePrintedToSystemOut
//			System.err.println(e);
//		}
//	}
}
