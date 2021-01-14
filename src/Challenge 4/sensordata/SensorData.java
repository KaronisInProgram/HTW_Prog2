package sensordata;

public interface SensorData {

    /**
     * 
     * @return timestamp of measurement
     */
    long getTimestamp();

    /**
     * 
     * @return value of measurement
     */
    float getValue();

    /**
     * 
     * @return name of the sensor
     */
    String getSensorName();
}
