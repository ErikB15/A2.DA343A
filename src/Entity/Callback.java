package Entity;

import se.mau.DA343A.VT26.assignment2.*;


public class Callback implements IPauseButtonPressedCallback{
    private IPauseButtonPressedCallback cacllback;

    public Callback (IPauseButtonPressedCallback callback){
        this.cacllback=callback;
    }

    @Override
    public void playPauseButtonPressed() {
    }
}
