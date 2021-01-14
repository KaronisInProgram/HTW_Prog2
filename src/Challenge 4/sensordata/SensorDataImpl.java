package sensordata;

public class SensorDataImpl implements SensorData {

    private long timestamp;
    private float value;
    private String sensorName;

    public SensorDataImpl(long timestamp, float value, String sensorName) {
        this.timestamp = timestamp;
        this.value = value;
        this.sensorName = sensorName;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public float getValue() {
        return this.value;
    }

    public String getSensorName() {
        return this.sensorName;
    }

}