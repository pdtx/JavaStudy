/**
 * @description:
 * @author: fancying
 * @create: 2019-01-14 23:00
 **/
package behavioral.observer;

import java.util.Observable;

// Observable 违反了我们的OO设计原则：针对接口编程，而非针对实现编程
public class WeatherData extends Observable {

    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() { }

    private void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}