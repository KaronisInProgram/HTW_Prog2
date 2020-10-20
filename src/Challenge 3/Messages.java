import java.io.IOException;
import java.net.UnknownHostException;

import client.TCPClient;
import server.TCPServer;

public class Messages {
    public static void main(String[] args) throws UnknownHostException, IOException {

        System.out.println("Implementation of Messages");
        if (args.length == 1) {
            TCPServer server = new TCPServer();
            server.start(readPortArg(args));

            String response = server.receiveMessage();
            System.out.println(response);
            server.sendMessage("hello client - from server");

            server.stop();

        } else if (args.length == 2) {
            TCPClient client = new TCPClient();
            client.startConnection(readHostArg(args), readPortArg(args));

            client.sendMessage("hello server - from client");
            String response = client.receiveMessage();
            System.out.println(response);

            client.stopConnection();

        } else {
            System.err.println("No Entry!");
        }

    }

    private static int readPortArg(String[] args) {
        String portArg = "";
        try {
            portArg = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Please set the Argument 0 [required] - Port (example 12345)");
            System.exit(0);
        }

        int port = Integer.parseInt(portArg);
        return port;
    }

    private static String readHostArg(String[] args) {
        String hostArg = "";
        try {
            hostArg = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err
                    .println("Please set the Argument 1 [required for client] - Host (example localhost, htw-berlin)");
            System.exit(0);
        }
        return hostArg;
    }
}
