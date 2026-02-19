package Entity;

import se.mau.DA343A.VT26.assignment2.*;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;


public class  Callback implements IPauseButtonPressedCallback{
    private IPauseButtonPressedCallback cacllback;
    private WeatherServer weatherServer;
    private int sensorID;
    private ArrayList<Integer> sensorIDs = new ArrayList<>();




    public Callback (IPauseButtonPressedCallback callback, WeatherServer weatherServer){
        this.cacllback=callback;
        this.weatherServer=weatherServer;
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

            for (int sensorid : sensorIDs){

                outStream.writeInt(2);
                outStream.writeInt(3);
                outStream.writeInt(sensorID);

                version = inStream.readInt();
                messageType = inStream.readInt();
                sensorID = inStream.readInt();
                int Row = inStream.readInt();
                int Col = inStream.readInt();
                double Temprature = inStream.readDouble();

                //tbd sätt tempratur. skapåa timern uppdatera gui
            }



        } catch (Exception e){

        }
    }
}
