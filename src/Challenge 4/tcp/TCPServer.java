package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    public String receiveMessage() throws IOException {
        System.out.println("TCPServer.receiveMessage()");

        return in.readUTF();
    }

    public void sendMessage(String msg) throws IOException {
        System.out.println("TCPServer.sendMessage()");

        out.writeUTF(msg);
    }

    public void receiveFile() throws IOException {
        System.out.println("TCPServer.receiveFile()");

        String filename = in.readUTF();
        String fileNameEnding = filename.substring(filename.lastIndexOf("."), filename.length());
        filename = filename.replace(fileNameEnding, "-copy" + fileNameEnding);
        FileOutputStream fos = new FileOutputStream(filename);

        int read = 0;
        do {
            read = in.read();
            if (read != -1) {
                fos.write(read);
            }

        } while (read != -1);

        fos.close();
    }

    public String receiveSensorData() throws IOException {
        System.out.println("TCPServer.receiveSensorData()");

        long milliseconds = in.readLong();
        float temperatur = in.readFloat();
        String name = in.readUTF();

        String response = "Milliseconds since midnight, January 1, 1970 UTC: " + milliseconds + System.lineSeparator();
        response += "Temperatur: " + temperatur + " °C" + System.lineSeparator();
        response += "Sensorname: " + name;

        return response;
    }

    public void start(int port) throws IOException {
        System.out.println("TCPServer.start() - Start connection!");

        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new DataOutputStream(clientSocket.getOutputStream());
        in = new DataInputStream(clientSocket.getInputStream());
    }

    public void stop() throws IOException {
        System.out.println("TCPServer.stop() - Close connection!");

        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

}