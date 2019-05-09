package sample.Observer;

public interface Publisher {
    void addObserver (Observer o);
    void removeObserver (Observer o);
    void notifyObservers();
}
