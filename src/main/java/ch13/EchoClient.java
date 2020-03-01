package ch13;

import BombermanClientServerInterfaces.CustomJSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

	private static final int INPUT_PORT = 8768;
	private static final int OUTPUT_PORT = 8764;

    public static void main(String[] args) {
        String host = "127.0.0.1";

        try (Socket inputSocket = new Socket(host, INPUT_PORT);
             Socket outputSocket = new Socket(host, OUTPUT_PORT);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(inputSocket.getInputStream()));
             PrintWriter out = new PrintWriter(
		             outputSocket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in)) {

            System.out.println(in.readLine());

            String[] message = new String[]{"drop_bomb", "hello", "world!"};

            CustomJSONArray array = new CustomJSONArray(message);

            while (true) {
                if (in.ready()) {
                    System.out.println("Antwort vom Server:");
                    System.out.println(in.readLine());
                }
                System.out.print("> ");
                String line = sc.nextLine();
                if (line.length() == 0)
                    break;
                out.println(array);
                if (in.ready()) {
                    System.out.println("Antwort vom Server:");
                    String input = in.readLine();
                    System.out.println(input);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
