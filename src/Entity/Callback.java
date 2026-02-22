package Entity;

import se.mau.DA343A.VT26.assignment2.*;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;

public class Callback implements IPauseButtonPressedCallback {

    private WeatherServer weatherServer;
    private ArrayList<Integer> sensorIDs = new ArrayList<>();
    private Gui gui;
    private boolean initialized = false;

    public Callback(WeatherServer weatherServer, Gui gui) {
        this.weatherServer = weatherServer;
        this.gui = gui;
    }

    @Override
    public void playPauseButtonPressed() {
        gui.togglePaused();
        if (initialized == false) {
            initialized = true;
            querySensors();
        }
    }

    private void querySensors() {
        try {
            DataInput inStream = weatherServer.getInput();
            DataOutput outStream = weatherServer.getOutput();

            outStream.writeInt(2);
            outStream.writeInt(1);

            int version = inStream.readInt();
            int messageType = inStream.readInt();

            if (version != 2){
                throw new IOException("Wrong version");
            }

            if (messageType == 2) {
                int numberOfSensors = inStream.readInt();
                for (int i = 0; i < numberOfSensors; i++) {
                    int sensorID = inStream.readInt();
                    String sensorName = inStream.readUTF();
                    sensorIDs.add(sensorID);

                    System.out.println("Sensor ID: " + sensorID + ", Sensor name: " + sensorName);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gatherTemperatures() {
        try {
            DataInput in = weatherServer.getInput();
            DataOutput out = weatherServer.getOutput();

            for (int sensorID : sensorIDs) {

                out.writeInt(2);
                out.writeInt(3);
                out.writeInt(sensorID);

                int version = in.readInt();
                if(version !=2){
                    throw new IOException("Wrong version");
                }
                int messageType = in.readInt();
                int id = in.readInt();
                int row = in.readInt();
                int col = in.readInt();
                double temperature = in.readDouble();

                gui.setTemperature(new GridTemperature(row, col, temperature));
            }
            gui.repaint();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
