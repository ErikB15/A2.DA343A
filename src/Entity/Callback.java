package Entity;

import se.mau.DA343A.VT26.assignment2.*;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;
import java.util.Timer;

public class  Callback implements IPauseButtonPressedCallback{
    private IPauseButtonPressedCallback callback;
    private WeatherServer weatherServer;
    private int sensorID;
    private ArrayList<Integer> sensorIDs = new ArrayList<>();
    private Gui gui;

    public Callback (IPauseButtonPressedCallback callback, WeatherServer weatherServer, Gui gui){
        this.callback=callback;
        this.weatherServer=weatherServer;
        this.gui=gui;
    }

    @Override
    public void playPauseButtonPressed() {
        try{
            DataInput inStream = weatherServer.getInput();
            DataOutput outStream = weatherServer.getOutput();

            outStream.writeInt( 2);
            outStream.writeInt(1);

            int version = inStream.readInt();
            int messageType = inStream.readInt();

            if(messageType == 2){
                int numberOfSensers = inStream.readInt();
                for (int i = 0; i < numberOfSensers; i++){
                    sensorID = inStream.readInt();
                    String sensorName = inStream.readUTF();

                    System.out.println("Sensor ID: " + sensorID + ", Sensor name: " + sensorName);
                    sensorIDs.add(sensorID);
                }
            }

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new MyTimerTask(this), 500, 500);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void fetchTemperatures() {
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
