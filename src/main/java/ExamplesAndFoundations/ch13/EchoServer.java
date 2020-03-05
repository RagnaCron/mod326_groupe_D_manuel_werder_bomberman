//package ExamplesAndFoundations.ch13;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//@SuppressWarnings("InfiniteLoopStatement")
//public class EchoServer extends Thread {
//
//    private static final int PORT = 8765;
//
//    private Socket client;
//
//    public EchoServer(Socket client) {
//        this.client = client;
//    }
//
//    public void run() {
//        try (BufferedReader in =
//                     new BufferedReader(
//                             new InputStreamReader(client.getInputStream()));
//             PrintWriter out =
//                     new PrintWriter(
//                             client.getOutputStream(), true)) {
//
//            out.println("Hallo, ich bin der EchoServer");
//
//            String input;
//            while ((input = in.readLine()) != null) {
//                out.println(input);
//            }
//        } catch (IOException e) {
//            System.err.println(e);
//        }
//    }
//
//    public static void main(String[] args) {
//        int port = PORT;
//
//        try (ServerSocket server = new ServerSocket(port)) {
//            System.out.println("EchoServer auf " + port + " gestartet ...");
//            while (true) {
//                Socket client = server.accept();
//                new EchoServer(client).start();
//            }
//        } catch (IOException e) {
//            System.err.println(e);
//        }
//    }
//}
