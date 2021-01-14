package fileexchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Contract to be able to send a File
 */
public interface FileSender {

    /**
     * Send a File to another process
     * 
     * @param fileName local name of the file
     * @param stream   connection/stream to remote entity
     * @throws IOException
     */
    void sendFile(String fileName, OutputStream stream) throws IOException;

}
