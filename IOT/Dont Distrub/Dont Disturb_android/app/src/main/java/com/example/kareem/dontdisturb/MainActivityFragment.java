package com.example.kareem.dontdisturb;

import android.app.TimePickerDialog;
import android.content.Context;
import android.media.AudioManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kareem.dontdisturb.Control.ArduinoData;
import com.example.kareem.dontdisturb.Control.ArduinoServer;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    private TextView activeClock;
    public static ArduinoServer arduinoServer = null;
    private static int f_hour = 11;
    private static int f_min = 59;
    private static int t_hour = 7;
    private static int t_min = 0;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        TextView fromClock = (TextView) view.findViewById(R.id.from_clock);
        setClock(fromClock);
        setClock((TextView) view.findViewById(R.id.to_clock));
        return view;
    }
    private void setClock(TextView textView)
    {
       textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        activeClock = (TextView) v;
        new TimePickerDialog(getActivity(), this, 11, 00,false).show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        String AM_PM;
        if(hourOfDay < 12) {
            AM_PM = "AM";
        } else {
            hourOfDay = hourOfDay -12;
            AM_PM = "PM";
        }
        activeClock.setText(hourOfDay + ":" + minute + AM_PM);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (arduinoServer == null) {
            arduinoServer = new ArduinoServer() {
                @Override
                public void onDataTransfer(final ArduinoData arduinoData) {
                    //TODO
                    //      only do it in specific clock range
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                         updateState(arduinoData.isLightState());
                        }
                    });
                }
            };
            new Thread(arduinoServer).start();
        }
    }
    private boolean lastState = false;
    private void updateState(boolean lightState){
        final AudioManager audioManager = (AudioManager) getActivity().getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        if (!lightState) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            createAToast("Normal Mode Activated");
        } else {
            createAToast("Don't Disturb Mode Activated");
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }
    }
    private void createAToast(String content)
    {
        Toast.makeText(getActivity(),content,Toast.LENGTH_SHORT).show();
    }
}
