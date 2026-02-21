package Entity;

import se.mau.DA343A.VT26.assignment2.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Gui extends GUI{

    private IPauseButtonPressedCallback playPauseButtonPressedCallback;
    private ArrayList<IPauseButtonPressedCallback> callbacks = new ArrayList<>();
    private WeatherServer weatherServer;

    public Gui(BufferedImage mapImage, WeatherServer weatherServer){
        super(mapImage);
        this.weatherServer = weatherServer;
        startGUIOnNewThread();
        addPlayPauseButtonCallback(playPauseButtonPressedCallback);
    }

    @Override
    public synchronized void addPlayPauseButtonCallback(IPauseButtonPressedCallback playPauseButtonPressedCallback) {
        Callback callback = new Callback(playPauseButtonPressedCallback, weatherServer, this);
        callbacks.add(callback);
    }

    @Override
    public void removePlayPauseButtonCallback(IPauseButtonPressedCallback iPauseButtonPressedCallback) {
       callbacks.remove(this.playPauseButtonPressedCallback);
        this.playPauseButtonPressedCallback=null;
    }

    @Override
    protected void invokePlayPauseButtonCallbacks(){
        for (IPauseButtonPressedCallback callback : callbacks){
            callback.playPauseButtonPressed();
        }
    }

    @Override
    protected void onExiting() {
    }

    @Override
    public void setTemperature(GridTemperature gridTemperature) {
        super.setTemperature(gridTemperature);
    }
}
