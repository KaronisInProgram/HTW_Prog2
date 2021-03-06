import java.io.IOException;

import client.TCPClient;
import server.TCPServer;

public class SensorData {
    public static void main(String[] args) throws IOException {

        System.out.println("Implementation of SensorData");
        if (args.length == 1) {
            TCPServer server = new TCPServer();
            server.start(readPortArg(args));
            String data = server.receiveSensorData();
            System.out.println(data);
            server.stop();

        } else if (args.length == 2) {
            TCPClient client = new TCPClient();
            client.startConnection(readHostArg(args), readPortArg(args));
            client.sendSensorData();
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
            System.err.println("Please set the Argument 1 [required] - Port (example 12345)");
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
                    .println("Please set the Argument 2 [required for client] - Host (example localhost, htw-berlin)");
            System.exit(0);
        }
        return hostArg;
    }

}
