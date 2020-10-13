import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) {

        int port = 9999;
        startServer(port);

    }

    private static void startServer(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            Socket socket = awaitConnection(server);

            System.out.println("Read message!");
            String message = readMessage(socket);
            System.out.println(message);

            writeMessage(socket, "Dies ist ein Test");

            System.out.println("Wait 5 seconds!");
            Thread.sleep(5000);
            System.out.println("Close connection!");
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
        return server.accept();
    }

    private static String readMessage(Socket socket) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        StringBuilder message = new StringBuilder();
        while (bufferedReader.ready()) {

            message.append(bufferedReader.readLine() + System.lineSeparator());
        }

        return message.toString();
    }

    private static void writeMessage(Socket socket, String message) throws IOException {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        printWriter.print(message);
        printWriter.flush();
    }
}