package sensordata;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SensorDataExchanger implements SensorDataSender, SensorDataReceiver {

    @Override
    public void sendSensorData(SensorData data, OutputStream stream) throws IOException {
        DataOutputStream dataStream = new DataOutputStream(stream);

        dataStream.writeLong(data.getTimestamp());
        dataStream.writeFloat(data.getValue());
        dataStream.writeUTF(data.getSensorName());

        dataStream.close();

    }

    @Override
    public SensorData receiveSensorData(InputStream stream) throws IOException {
        DataInputStream dataStream = new DataInputStream(stream);

        long milliseconds = dataStream.readLong();
        float temperatur = dataStream.readFloat();
        String name = dataStream.readUTF();

        return new SensorDataImpl(milliseconds, temperatur, name);
    }

}
