package ch13;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

	private static final int INPUT_PORT = 8764;
	private static final int OUTPUT_PORT = 8768;

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

	        JSONArray array = new JSONArray("[drop_bomb, hello, world!]");

            while (true) {
                System.out.print("> ");
                String line = sc.nextLine();
                if (line.length() == 0)
                    break;
                out.println(array);
                System.out.println("Antwort vom Server:");
                System.out.println(in.readLine());
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
