package Controller;
import Entity.Gui;
import Entity.IsLand;
import se.mau.DA343A.VT26.assignment2.*;

import java.awt.image.BufferedImage;

public class Controller  {

    private Gui gui;
    private IIsLand island;
    private WeatherServer weatherServer;

    public Controller(BufferedImage mapImage){
        island = new IsLand();
        weatherServer = new WeatherServer(island);
        gui = new Gui(mapImage);
    }
}
