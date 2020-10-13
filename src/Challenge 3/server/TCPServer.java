package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) {

        int port = readPortArg(args);

        System.out.println("Port: " + port);
        System.out.println("Start Server...");

        startServer(port);

    }

    private static int readPortArg(String[] args) {
        String portArg = "";
        try {
            portArg = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Please set the Argument 0 - Port (example 12345)");
            System.exit(0);
        }

        int port = Integer.parseInt(portArg);
        return port;
    }

    private static void startServer(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            Socket socket = awaitConnection(server);

            readMessage(socket);
            writeMessage(socket, "Message from Server, Hey! ;-)");

            System.out.println("Wait for 5 seconds!");
            Thread.sleep(5000);

            System.out.println("Close connection!");
            socket.close();
            server.close();

        } catch (IOException e) {
            System.err.println("Can't write IO");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted");
            e.printStackTrace();
        }
    }

    private static Socket awaitConnection(ServerSocket server) throws IOException {
        System.out.println("Wait for TCP-Connection!");
        Socket socket = server.accept();
        System.out.println("TCP-Connection established!");

        return socket;
    }

    private static void readMessage(Socket socket) throws IOException {
        System.out.println("Read message - Start!");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        StringBuilder message = new StringBuilder();
        while (bufferedReader.ready()) {

            message.append(bufferedReader.readLine() + System.lineSeparator());
        }

        System.out.println(message.toString());
        System.out.println("Read message - End!");
    }

    private static void writeMessage(Socket socket, String message) throws IOException {
        System.out.println("Write message - Start!");

        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        printWriter.print(message);
        printWriter.flush();

        System.out.println("Write message - End!");
    }
}