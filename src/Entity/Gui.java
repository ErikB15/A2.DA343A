package Entity;

import se.mau.DA343A.VT26.assignment2.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Main.*;

public class Gui extends GUI{

    private IPauseButtonPressedCallback iPauseButtonPressedCallback;
    private IPauseButtonPressedCallback callback;
    private ArrayList<IPauseButtonPressedCallback> callbacks = new ArrayList<>();
    private WeatherServer weatherServer;

    public Gui(BufferedImage mapImage, WeatherServer weatherServer){
        super(mapImage);
        this.weatherServer = weatherServer;
        startGUIOnNewThread();
        addPlayPauseButtonCallback(iPauseButtonPressedCallback);
    }

    @Override
    public synchronized void addPlayPauseButtonCallback(IPauseButtonPressedCallback playPauseButtonPressedCallback) {
        IPauseButtonPressedCallback callback = new Callback(playPauseButtonPressedCallback, weatherServer);
        this.callback = callback;
        callbacks.add(callback);
    }

    @Override
    public void removePlayPauseButtonCallback(IPauseButtonPressedCallback iPauseButtonPressedCallback) {
       // Callback.removeCallback(this.iPauseButtonPressedCallback);
        this.iPauseButtonPressedCallback=null;
    }

    @Override
    protected void invokePlayPauseButtonCallbacks(){
        for (IPauseButtonPressedCallback callback : callbacks){
            callback.playPauseButtonPressed();
            System.out.println("hej");
        }
    }

    @Override
    protected void onExiting() {
    }
}
