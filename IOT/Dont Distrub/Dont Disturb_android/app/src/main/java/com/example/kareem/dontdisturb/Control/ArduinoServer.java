package com.example.kareem.dontdisturb.Control;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kareem on 7/20/17.
 */
public abstract class ArduinoServer implements Runnable {
    private final int port=4444;
    private  ArduinoData arduinoData=new ArduinoData();
    @SuppressWarnings("FieldCanBeLocal")
    private ArduinoData lastArduinoData;
    public ArduinoServer(){}

 //   public   void getRequest(String request);
    boolean last;
    public ArduinoData getRequest ()throws Exception{
        ServerSocket serverSocket = new ServerSocket(port);
        Log.e("Connection", serverSocket.getInetAddress().toString());
        while (true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String request;
            while ((request = in.readLine()) != null) {
                Log.e("serverData", "Connect :" + request);
                if (request.isEmpty()) {
                    break;
                }
                if(request.contains("Host")) continue;
                last = arduinoData.isLightState();
                    arduinoData.parse(request);
                if (arduinoData.isLightState() != last)
                //only trigger when light state changed
                onDataTransfer(arduinoData);
            }
            in.close();
            clientSocket.close();
        }
    }


    @Override
    public void run() {
        try {
            getRequest();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("e", "run: ", e );
        }
    }
    public ArduinoData GetArduinoState (){
        return this.arduinoData;
    }
    public abstract void onDataTransfer(ArduinoData arduinoData);
}
