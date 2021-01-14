package sensordata;

import java.io.IOException;
import java.io.InputStream;

public interface SensorDataReceiver {
    /**
     * 
     * @param stream
     * @return
     * @throws IOException
     */
    SensorData receiveSensorData(InputStream stream) throws IOException;
}
