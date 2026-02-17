package Main;

import Entity.Gui;
import Entity.StreamHandler;
import se.mau.DA343A.VT26.assignment2.*;

public class Main {

    public static void main(String[] args) {

        ImageResources imageResources = new ImageResources();
        IsLandFromMaskImage isLandFromMaskImage = new IsLandFromMaskImage(imageResources.getMapIsLandMaskImage());
        WeatherServer weatherServer = new WeatherServer(isLandFromMaskImage);
        StreamHandler streamHandler = new StreamHandler(weatherServer);

        Thread weatherThread = new Thread(weatherServer);
        weatherThread.start();

        Gui gui = new Gui(imageResources.getMapImage(), streamHandler);
        }
    }