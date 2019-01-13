package behavioral.observer;

public interface Subject {
    // 这两个方法都需要一个观察者作为变量，该观察者是用来注册或者被删除的
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);

    public void notifyObserver();
}
