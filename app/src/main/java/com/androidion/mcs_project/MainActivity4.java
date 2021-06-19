package com.androidion.mcs_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity4 extends AppCompatActivity {

    TextView timerTxt;
    Button btnStartStop;
    Timer timer;
    TimerTask timerTask;
    String date = "";
    Double time = 0.0;
    Double hasil = 0.0;
    Double hasil2;
    int inputHere1;

    String timee;
    String hasill;
    String hasill2;
    String inputHere11;



    TextView judul;
    EditText inputHere;
    Boolean timerStarted = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        timerTxt = (TextView) findViewById(R.id.timerTxt);
        btnStartStop = (Button) findViewById(R.id.btnStartStop);

        timer = new Timer();

//        ImageButton backbutton = (ImageButton) findViewById(R.id.BackButton);
//        backbutton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity4.this, MainActivity.class);
//                startActivity(i);
//            }
//        });

        judul = findViewById(R.id.judultv2);
        String nama = "";
        Intent intent =getIntent();


        nama = intent.getStringExtra("Judul");
        judul.setText(nama);
    }
    public void Reset(View view) {
        AlertDialog.Builder resetAlert = new AlertDialog.Builder(this);
        resetAlert.setTitle("Reset Timer");
        resetAlert.setMessage("Are you want to reset the timer ?");
        resetAlert.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(timerTask != null) {
                    timerTask.cancel();
                    setButton("START", R.color.green);
                    time = 0.0;
                    timerStarted = false;
                    timerTxt.setText(formatTime(0,0,0));
                }

            }
        });

        resetAlert.setNeutralButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        resetAlert.show();

    }

    public void StartStop(View view) {
        if(timerStarted == false) {
            timerStarted = true;
            setButton("STOP", R.color.red);

            startTimer();
        } else {
            timerStarted = false;
            setButton("START", R.color.green);

            timerTask.cancel();
        }
    }

    private void setButton(String start, int color) {
        btnStartStop.setText(start);
        btnStartStop.setTextColor(ContextCompat.getColor(this, color));
    }

    private void startTimer() {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timerTxt.setText(getTimerTxt());
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);

    }

    private String getTimerTxt() {
        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);

        return formatTime(seconds, minutes, hours);
    }

    private String formatTime(int seconds, int minutes, int hours) {
        timee = String.format("%02d", hours) + " : " + String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
        return timee;
    }

    boolean isEmpty (EditText text)
    {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void Finish(View view) {

        inputHere = (EditText) findViewById(R.id.inputHere);

        if(isEmpty(inputHere)) {
            Toast toast = Toast.makeText(getApplicationContext(), "This field can't be empty", Toast.LENGTH_SHORT);
            toast.show();
        } else {

            Intent i = new Intent(MainActivity4.this, Calculation.class);
            Calendar c = Calendar.getInstance();
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);
            date = day + "/" + (month+1) + "/" + year;

            hasilCal();
            hasill = String.valueOf(hasil);
            hasill2 = String.valueOf(hasil2);
            inputHere11 = String.valueOf(inputHere1);
            Database database = new Database(MainActivity4.this);
            database.insertSpendingItem(new Data(-1,date, judul.getText().toString(), timee, inputHere11, hasill, hasill2));
            startActivity(i);
        }

    }
    public void hasilCal() {
        if(judul.getText().toString().equals("Push Up")) {
            inputHere1 = Integer.parseInt(inputHere.getText().toString());

            hasil = inputHere1*0.45;


        } else if(judul.getText().toString().equals("Sit Up")) {
            inputHere1 = Integer.parseInt(inputHere.getText().toString());
            hasil = inputHere1*0.2;

        } else if(judul.getText().toString().equals("Hip Raise")) {
            inputHere1 = Integer.parseInt(inputHere.getText().toString());
            hasil = inputHere1*0.5;

        } else if(judul.getText().toString().equals("Leg raise")) {
            inputHere1 = Integer.parseInt(inputHere.getText().toString());
            hasil = inputHere1*0.5;

        } else if(judul.getText().toString().equals("Burpees")) {
            inputHere1 = Integer.parseInt(inputHere.getText().toString());
            hasil = inputHere1*0.5;

        } else if(judul.getText().toString().equals("Jumping Jack")) {
            inputHere1 = Integer.parseInt(inputHere.getText().toString());
            hasil = inputHere1*0.2;

        } else if(judul.getText().toString().equals("Squats")) {
            inputHere1 = Integer.parseInt(inputHere.getText().toString());
            hasil = inputHere1*0.2;

        } else if(judul.getText().toString().equals("Lunges")) {
            inputHere1 = Integer.parseInt(inputHere.getText().toString());
            hasil = inputHere1*0.2;

        }

//        hasil2 = hasil2 + hasil;

        Toast toast = Toast.makeText(getApplicationContext(),"" + hasil,Toast.LENGTH_SHORT);
        toast.show();
    }
}