package Entity;
import se.mau.DA343A.VT26.assignment2.*;

import javax.management.Query;
import java.io.DataInput;
import java.io.DataOutput;

public class StreamHandler {
    private WeatherServer weatherServer;

    public StreamHandler(WeatherServer weatherServer){
        this.weatherServer=weatherServer;
    }

    public void stream(){
        try{
            DataInput inStream = weatherServer.getInput();
            DataOutput outStream = weatherServer.getOutput();

            outStream.writeInt( 2);
            outStream.writeInt(1);
            ((java.io.DataOutputStream) outStream).flush();

            int version = inStream.readInt();
           int messageType = inStream.readInt();

           if(messageType == 2){
               int numberOfSensers = inStream.readInt();
               for (int i = 0; i < numberOfSensers-1; i++){
                   int sensorID = inStream.readInt();
                   String sensorName = inStream.readUTF();

                   System.out.println("Sensor ID: " + sensorID + ", Sensor name: " + sensorName);
               }
           }


        } catch (Exception e){

        }

    }
}
