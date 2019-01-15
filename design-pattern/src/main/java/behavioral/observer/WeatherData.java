/**
 * @description:
 * @author: fancying
 * @create: 2019-01-14 23:00
 **/
package behavioral.observer;

import java.util.ArrayList;

public class WeatherData implements Subject {
    // 记录观察者
    private ArrayList<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList<>();
    }
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObserver() {

    }
}