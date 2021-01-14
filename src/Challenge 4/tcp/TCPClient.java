package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {

    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    public String receiveMessage() throws IOException {
        System.out.println("TCPClient.receiveMessage()");

        return in.readUTF();
    }

    public void sendMessage(String msg) throws IOException {
        System.out.println("TCPClient.sendMessage()");

        out.writeUTF(msg);
    }

    public void sendFile(String filename) throws IOException {
        System.out.println("TCPClient.sendFile()");

        out.writeUTF(filename);

        FileInputStream fis = new FileInputStream(filename);

        int read = 0;
        do {
            read = fis.read();
            if (read != -1) {
                out.write(read);
            }

        } while (read != -1);

        fis.close();
    }

    public void sendSensorData() throws IOException {
        System.out.println("TCPClient.sendSensorData()");

        out.writeLong(System.currentTimeMillis());
        out.writeFloat(13.4f);
        out.writeUTF("HomeSensor 1000");
    }

    public void startConnection(String ip, int port) throws IOException {
        System.out.println("TCPClient.startConnection() - Start connection!");

        clientSocket = new Socket(ip, port);
        out = new DataOutputStream(clientSocket.getOutputStream());
        in = new DataInputStream(clientSocket.getInputStream());
    }

    public void stopConnection() throws IOException {
        System.out.println("TCPClient.stopConnection() - Close connection!");
        in.close();
        out.close();
        clientSocket.close();
    }
}