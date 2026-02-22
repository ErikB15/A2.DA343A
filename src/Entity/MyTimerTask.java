package Entity;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

    private Callback callback;
    private Gui gui;

    public MyTimerTask(Callback callback, Gui gui) {
        this.callback = callback;
        this.gui = gui;
    }

    @Override
    public void run() {
        if (!gui.isPaused()) {
            callback.gatherTemperatures();
        }
    }
}
