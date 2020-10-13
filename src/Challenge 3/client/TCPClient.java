package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) {

        String hostArg = readHostArg(args);
        int port = readPortArg(args);

        System.out.println("Host: " + hostArg);
        System.out.println("Port: " + port);
        System.out.println("Start Client...");

        startClient(hostArg, port);
    }

    private static int readPortArg(String[] args) {
        String portArg = "";
        try {
            portArg = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Please set the Argument 1 - Port (example 12345)");
            System.exit(0);
        }

        int port = Integer.parseInt(portArg);
        return port;
    }

    private static String readHostArg(String[] args) {
        String hostArg = "";
        try {
            hostArg = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Please set the Argument 0 - Host (example localhost, htw-berlin)");
            System.exit(0);
        }
        return hostArg;
    }

    private static void startClient(String host, int port) {
        try {
            Socket client = new Socket(host, port);

            writeMessage(client, "Message from Client, Hello! ;-)" + System.lineSeparator() + "How are you?");
            readMessage(client);

            System.out.println("Wait for 5 seconds!");
            Thread.sleep(5000);

            System.out.println("Close connection!");
            client.close();

        } catch (IOException e) {
            System.err.println("Can't write IO");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted");
            e.printStackTrace();
        }
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