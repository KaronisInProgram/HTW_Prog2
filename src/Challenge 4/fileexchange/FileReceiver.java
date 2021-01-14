package fileexchange;

import java.io.IOException;
import java.io.InputStream;

/**
 * Contract to be able to receive a File
 */
public interface FileReceiver {

    /**
     * Receive a File from another process and writes it to a local destination
     * 
     * @param fileName local name of the file
     * @param stream   connection/stream from remote entity
     * @throws IOException
     */
    void receiveFile(String fileName, InputStream stream) throws IOException;
}
