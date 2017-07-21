package com.example.kareem.dontdisturb.Control;

/**
 * Created by PC - MeiR on 7/20/2017.
 */
public class ArduinoData {
    private boolean lightState;
    private boolean motion;


    public void parse(String request){
        request = request.substring(request.indexOf('?')+1);
        request = request.substring(0,request.indexOf(' '));
        request+="&";
        while (request.contains("&")){
            String attr= request.substring(0,request.indexOf('='));
            String var = request.substring(request.indexOf('=')+1,request.indexOf('&'));
            request=request.substring(request.indexOf('&')+1);
            if (attr.contains("LightState"))
             this.lightState= (var.contains("true"));
            else if (attr.contains("MotionState"))
                this.motion= (var.contains("true"));
        }
    }

    public boolean isLightState() {
        return lightState;
    }

    public void setLightState(boolean lightState) {
        this.lightState = lightState;
    }

    public boolean isMotion() {
        return motion;
    }

    public void setMotion(boolean motion) {
        this.motion = motion;
    }
}
