package fileexchange;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileExchanger implements FileSender, FileReceiver {

    @Override
    public void sendFile(String fileName, OutputStream stream) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        streamData(fis, stream);
        fis.close();
    }

    @Override
    public void receiveFile(String fileName, InputStream stream) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        streamData(stream, fos);
        fos.close();
    }

    private void streamData(InputStream inStream, OutputStream outStream) throws IOException {
        int read = 0;
        do {
            read = inStream.read();
            if (read != -1) {
                outStream.write(read);
            }

        } while (read != -1);
    }

}
