package Entity;

import se.mau.DA343A.VT26.assignment2.*;

import java.io.DataInput;
import java.io.DataOutput;
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

        if (!initialized) {
            initialized = true;
            querySensorList();
        }
    }

    private void querySensorList() {

        try {

            DataInput inStream = weatherServer.getInput();
            DataOutput outStream = weatherServer.getOutput();

            outStream.writeInt(2);
            outStream.writeInt(1);

            int version = inStream.readInt();
            int messageType = inStream.readInt();

            if (messageType == 2) {

                int numberOfSensors = inStream.readInt();

                for (int i = 0; i < numberOfSensors; i++) {

                    int sensorID = inStream.readInt();
                    String sensorName = inStream.readUTF();

                    sensorIDs.add(sensorID);

                    System.out.println("Sensor ID: " + sensorID + ", Sensor name: " + sensorName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchTemperatures() {

        if (gui.isPaused()) {
            return;
        }

        try {

            DataInput in = weatherServer.getInput();
            DataOutput out = weatherServer.getOutput();

            for (int sensorID : sensorIDs) {

                out.writeInt(2);
                out.writeInt(3);
                out.writeInt(sensorID);

                int version = in.readInt();
                int messageType = in.readInt();
                int id = in.readInt();
                int row = in.readInt();
                int col = in.readInt();
                double temperature = in.readDouble();

                gui.setTemperature(new GridTemperature(row, col, temperature));
            }

            gui.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
