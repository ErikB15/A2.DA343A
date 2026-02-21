package Entity;

import se.mau.DA343A.VT26.assignment2.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

public class Gui extends GUI {

    private ArrayList<IPauseButtonPressedCallback> callbacks = new ArrayList<>();
    private WeatherServer weatherServer;

    private boolean paused = true;
    private Timer timer;
    private Callback callback;

    public Gui(BufferedImage mapImage, WeatherServer weatherServer) {
        super(mapImage);

        this.weatherServer = weatherServer;

        startGUIOnNewThread();

        callback = new Callback(weatherServer, this);
        addPlayPauseButtonCallback(callback);

        timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(callback,this), 500, 500);
    }

    @Override
    public synchronized void addPlayPauseButtonCallback(IPauseButtonPressedCallback cb) {
        callbacks.add(cb);
    }

    @Override
    public synchronized void removePlayPauseButtonCallback(IPauseButtonPressedCallback cb) {
        callbacks.remove(cb);
    }

    @Override
    protected void invokePlayPauseButtonCallbacks() {
        for (IPauseButtonPressedCallback callback : callbacks) {
            callback.playPauseButtonPressed();
        }
    }

    public synchronized void togglePaused() {
        paused = !paused;
    }

    public synchronized boolean isPaused() {
        return paused;
    }

    @Override
    protected void onExiting() {
        try {
            weatherServer.close();
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setTemperature(GridTemperature gridTemperature) {
        super.setTemperature(gridTemperature);
    }
}
