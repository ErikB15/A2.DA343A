package Entity;

public class MyTimerTask extends java.util.TimerTask {

    private Callback callback;

    public MyTimerTask(Callback callback) {
        this.callback=callback;
    }

    @Override
    public void run() {
        callback.fetchTemperatures();
    }
}
