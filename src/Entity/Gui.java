package Entity;

import se.mau.DA343A.VT26.assignment2.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Main.*;

public class Gui extends GUI{

    private IPauseButtonPressedCallback iPauseButtonPressedCallback;
    private IPauseButtonPressedCallback callback;
    private ArrayList<IPauseButtonPressedCallback> callbacks = new ArrayList<>();
    private StreamHandler streamHandler;


    public Gui(BufferedImage mapImage, StreamHandler streamHandler){
        super(mapImage);
        this.streamHandler = streamHandler;
        startGUIOnNewThread();
        addPlayPauseButtonCallback(iPauseButtonPressedCallback);
    }

    @Override
    public synchronized void addPlayPauseButtonCallback(IPauseButtonPressedCallback playPauseButtonPressedCallback) {
        IPauseButtonPressedCallback callback = new Callback(playPauseButtonPressedCallback);
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
            streamHandler.stream();
            System.out.println("hej");
        }
    }

    @Override
    protected void onExiting() {
    }
}
