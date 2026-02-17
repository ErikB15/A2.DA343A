package Entity;

import se.mau.DA343A.VT26.assignment2.*;
import java.awt.image.BufferedImage;

public class Gui extends GUI{
    private IPauseButtonPressedCallback iPauseButtonPressedCallback;

    public Gui(BufferedImage mapImage){
        super(mapImage);
        startGUIOnNewThread();
        addPlayPauseButtonCallback(iPauseButtonPressedCallback);
    }

    @Override
    public void addPlayPauseButtonCallback(IPauseButtonPressedCallback iPauseButtonPressedCallback) {
        Callback callback = new Callback(iPauseButtonPressedCallback);
        this.iPauseButtonPressedCallback = iPauseButtonPressedCallback;
        invokePlayPauseButtonCallbacks();
    }

    @Override
    public void removePlayPauseButtonCallback(IPauseButtonPressedCallback iPauseButtonPressedCallback) {
        this.iPauseButtonPressedCallback=null;
    }

    @Override
    protected void invokePlayPauseButtonCallbacks(){
    }

    @Override
    protected void onExiting() {
    }
}
