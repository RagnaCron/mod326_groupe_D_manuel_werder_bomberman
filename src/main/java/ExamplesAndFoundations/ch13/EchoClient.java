package ExamplesAndFoundations.ch13;

import Old.BombermenClientServerInterfaces.Messaging.CustomJSONArray;

import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

@SuppressWarnings("all")
public class EchoClient {

	private static final int INPUT_PORT = 8768;
	private static final int OUTPUT_PORT = 8764;

    public static void main(String[] args) throws UnknownHostException {
        String host = "127.0.0.1";

        try {
            MulticastSocket inputSocket = new MulticastSocket(INPUT_PORT);
            InetAddress address = InetAddress.getByName("230.0.0.1");
            inputSocket.joinGroup(address);
            DatagramPacket packet;

            Socket outputSocket = new Socket(host, OUTPUT_PORT);
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(inputSocket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    outputSocket.getOutputStream(), true);
            Scanner sc = new Scanner(System.in);
//            System.out.println(in.readLine());

            String[] message = new String[]{"drop_bomb", "hello", "world!"};
            CustomJSONArray array = new CustomJSONArray(message);

            while (true) {
//                if (in.ready()) {

//                    System.out.println(in.readLine());
//                }
                System.out.print("> ");
                String line = sc.nextLine();
                if (line.length() == 0)
                    break;
                out.println(array);
                System.out.println("Antwort vom Server:");

                byte[] buffer = new byte[256];
                packet = new DatagramPacket(buffer, buffer.length);
                inputSocket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);
            }
            inputSocket.leaveGroup(address);
            inputSocket.close();
            message[0] = "player_exit";
            out.println(new CustomJSONArray(message));
            outputSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
