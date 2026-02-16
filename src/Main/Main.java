package Main;

import se.mau.DA343A.VT26.assignment2.*;
import Controller.Controller;


public class Main {
    public static void main(String[] args) {

        ImageResources imageResources = new ImageResources();
        Controller controller = new Controller(imageResources.getMapImage());

        }
    }