package Controller;
import Entity.Gui;
import se.mau.DA343A.VT26.assignment2.*;

import java.awt.image.BufferedImage;

public class Controller {

    private Gui gui;

    public Controller(BufferedImage mapImage){
        gui = new Gui(mapImage);
    }


}
