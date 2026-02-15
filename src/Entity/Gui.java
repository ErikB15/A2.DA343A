package Entity;

import se.mau.DA343A.VT26.assignment2.*;
import java.awt.image.BufferedImage;

public class Gui extends GUI implements IPauseButtonPressedCallback {

    private GridTemperature gridTemprature;

    public Gui(BufferedImage mapImage){
        super(mapImage);
        startGUIOnNewThread();
    }
    @Override
    public void addPlayPauseButtonCallback(IPauseButtonPressedCallback iPauseButtonPressedCallback) {

    }

    @Override
    public void removePlayPauseButtonCallback(IPauseButtonPressedCallback iPauseButtonPressedCallback) {

    }

    @Override
    protected void invokePlayPauseButtonCallbacks() {

    }

    @Override
    protected void onExiting() {

    }

    @Override
    public void playPauseButtonPressed() {

    }
}
