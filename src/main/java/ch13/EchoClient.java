//package ch13;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class EchoClient {
//
//    private static final int PORT = 8765;
//
//    public static void main(String[] args) {
//        String host = "127.0.0.1";
//        int port = PORT;
//
//        try (Socket socket = new Socket(host, port);
//             BufferedReader in = new BufferedReader(
//                     new InputStreamReader(socket.getInputStream()));
//             PrintWriter out = new PrintWriter(
//                     socket.getOutputStream(), true);
//             Scanner sc = new Scanner(System.in)) {
//
//            System.out.println(in.readLine());
//
//            while (true) {
//                System.out.print("> ");
//                String line = sc.nextLine();
//                if (line.length() == 0)
//                    break;
//                out.println(line);
//                System.out.println("Antwort vom Server:");
//                System.out.println(in.readLine());
//            }
//        } catch (Exception e) {
//            System.err.println(e);
//        }
//    }
//}
