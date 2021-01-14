package sensordata;

import java.io.IOException;
import java.io.OutputStream;

public interface SensorDataSender {

    /**
     * 
     * @param data
     * @throws IOException
     */
    void sendSensorData(SensorData data, OutputStream stream) throws IOException;
}
