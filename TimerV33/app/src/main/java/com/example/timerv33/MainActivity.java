package com.example.timerv33;
/*
Heber Cooke  9/28/2019
Android Studio final project
TIMER

 */
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;

import static com.example.timerv33.Edit.n1;
import static com.example.timerv33.Edit.n2;
import static com.example.timerv33.Edit.n3;
import static com.example.timerv33.Edit.n4;
import static com.example.timerv33.Edit.ch1;
import static com.example.timerv33.Edit.ch2;
import static com.example.timerv33.Edit.ch3;
import static com.example.timerv33.Edit.ch4;

public class MainActivity extends AppCompatActivity {

    //-------------TIME----------------------
     Chronometer chronometer1,chronometer2,chronometer3,chronometer4;
     long chronometerTimeStopped1,chronometerTimeStopped2,chronometerTimeStopped3,chronometerTimeStopped4;

    private EditText textInput1,textInput2,textInput3,textInput4;
    private TextView textViewTimer1,textViewTimer2,textViewTimer3,textViewTimer4;
    public  TextView tvName1,tvName2,tvName3,tvName4;
    Button btnEdit;
    private Button btnSetTimer1,btnSetTimer2,btnSetTimer3,btnSetTimer4;
    private Button btnStartStop1,btnStartStop2,btnStartStop3,btnStartStop4;
    private Button btnReset1,btnReset2,btnReset3,btnReset4;

    private CountDownTimer countDownTimer1,countDownTimer2,countDownTimer3,countDownTimer4;

    private long startTimeMilliseconds1,startTimeMilliseconds2,startTimeMilliseconds3,startTimeMilliseconds4;
    private boolean timerIsRunning1,timerIsRunning2,timerIsRunning3,timerIsRunning4;
    private long timeLeftMilliseconds1,timeLeftMilliseconds2,timeLeftMilliseconds3,timeLeftMilliseconds4;
    private long endTime1,endTime2,endTime3,endTime4;

    String name1,name2,name3,name4 = "";
    String start = "Start";
    String pause = "Pause";
    String namePassed;
    int nameSelected;
   // boolean ch1,ch2,ch3,ch4 =false; //checkboxes to reset total time timer

     long chrono1Time;
     long chrono2Time;
     long chrono3Time;
     long chrono4Time;

    //this disables the back button
    @Override
    public void onBackPressed(){
        moveTaskToBack(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //--------TIME----Chronometer---------------
        chronometer1 = findViewById(R.id.chronometer1);
        chronometer2 = findViewById(R.id.chronometer2);
        chronometer3 = findViewById(R.id.chronometer3);
        chronometer4 = findViewById(R.id.chronometer4);
        chronometer1.setBase(SystemClock.elapsedRealtime());
        chronometer2.setBase(SystemClock.elapsedRealtime());
        chronometer3.setBase(SystemClock.elapsedRealtime());
        chronometer4.setBase(SystemClock.elapsedRealtime());

        textInput1 = findViewById(R.id.textInput1);
        textInput2 = findViewById(R.id.textInput2);
        textInput3 = findViewById(R.id.textInput3);
        textInput4 = findViewById(R.id.textInput4);

        textViewTimer1 = findViewById(R.id.textViewCountDown1);
        textViewTimer2 = findViewById(R.id.textViewCountDown2);
        textViewTimer3 = findViewById(R.id.textViewCountDown3);
        textViewTimer4 = findViewById(R.id.textViewCountDown4);

        tvName1 = findViewById(R.id.tvName1);
        tvName2 = findViewById(R.id.tvName2);
        tvName3 = findViewById(R.id.tvName3);
        tvName4 = findViewById(R.id.tvName4);

        btnStartStop1 = findViewById(R.id.btnStartStop1);
        btnStartStop2 = findViewById(R.id.btnStartStop2);
        btnStartStop3 = findViewById(R.id.btnStartStop3);
        btnStartStop4 = findViewById(R.id.btnStartStop4);

        btnReset1 = findViewById(R.id.btnReset1);
        btnReset2 = findViewById(R.id.btnReset2);
        btnReset3 = findViewById(R.id.btnReset3);
        btnReset4 = findViewById(R.id.btnReset4);

        btnSetTimer1 = findViewById(R.id.btnSetTimer1);
        btnSetTimer2 = findViewById(R.id.btnSetTimer2);
        btnSetTimer3 = findViewById(R.id.btnSetTimer3);
        btnSetTimer4 = findViewById(R.id.btnSetTimer4);

        btnEdit = findViewById(R.id.btnEdit1);

        //This loads names into the main activity when starting
        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        name1 = prefs.getString("name1","");
        name2 =prefs.getString("name2","");
        name3 =prefs.getString("name3","");
        name4 =prefs.getString("name4","");
        tvName1.setText(name1);
        tvName2.setText(name2);
        tvName3.setText(name3);
        tvName4.setText(name4);

        // This intent gets the name from the Edit Class and sets the name when changing a name or editing a name
        Intent intent = getIntent();
       namePassed = intent.getStringExtra("name");
       nameSelected = intent.getIntExtra("rbSelected",0);
       if(nameSelected ==1){
           tvName1.setText(n1);
       }
        if(nameSelected ==2){
            tvName2.setText(n2);
        }
       if(nameSelected ==3 ){
           tvName3.setText(n3);
       }
        if(nameSelected ==4){
            tvName4.setText(n4);
        }
        // names are set to n1
        name1 = n1;
        name2 = n2;
        name3 = n3;
        name4= n4;

        //Edit button goes to the Edit activity
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pausing the chronometer during edit
                chronometer1.stop();
                chronometer2.stop();
                chronometer3.stop();
                chronometer4.stop();
                chronometerTimeStopped1 = chronometer1.getBase() - SystemClock.elapsedRealtime();
                chronometerTimeStopped2 = chronometer2.getBase() - SystemClock.elapsedRealtime();
                chronometerTimeStopped3 = chronometer3.getBase() - SystemClock.elapsedRealtime();
                chronometerTimeStopped4 = chronometer4.getBase() - SystemClock.elapsedRealtime();
                //pausing timer during edit
                if(timerIsRunning1){
                    pauseTimer1();
                }
                if(timerIsRunning2) {
                    pauseTimer2();
                }
                if(timerIsRunning3) {
                    pauseTimer3();
                }
                if(timerIsRunning4) {
                    pauseTimer4();
                }

                startActivity(new Intent(MainActivity.this,Edit.class));
            }
        });

//=========================================================
        btnSetTimer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = textInput1.getText().toString();
                if(input.length() == 0){
                    Toast.makeText(MainActivity.this,"Cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if(millisInput == 0){
                    Toast.makeText(MainActivity.this,"Enter a positive number",Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime1(millisInput);
                textInput1.setText("");
            }
        });
        btnSetTimer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = textInput2.getText().toString();
                if(input.length() == 0){
                    Toast.makeText(MainActivity.this,"Cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if(millisInput == 0){
                    Toast.makeText(MainActivity.this,"Enter a positive number",Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime2(millisInput);
                textInput2.setText("");
            }
        });
        btnSetTimer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = textInput3.getText().toString();
                if(input.length() == 0){
                    Toast.makeText(MainActivity.this,"Cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if(millisInput == 0){
                    Toast.makeText(MainActivity.this,"Enter a positive number",Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime3(millisInput);
                textInput3.setText("");
            }
        });
        btnSetTimer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = textInput4.getText().toString();
                if(input.length() == 0){
                    Toast.makeText(MainActivity.this,"Cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if(millisInput == 0){
                    Toast.makeText(MainActivity.this,"Enter a positive number",Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime4(millisInput);
                textInput4.setText("");
            }
        });
//=========================================================
        btnStartStop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerIsRunning1){
                    chronometer1.stop();
                    chrono1Time = chronometer1.getBase() - SystemClock.elapsedRealtime();
                   chronometerTimeStopped1 = chronometer1.getBase() - SystemClock.elapsedRealtime();
                    pauseTimer1();
                }
                else{
                    chronometer1.setBase(chrono1Time + SystemClock.elapsedRealtime());
                    chronometer1.start();
                    startTimer1();
                }
            }
        });
        btnStartStop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerIsRunning2){
                    chronometer2.stop();
                    chrono2Time = chronometer2.getBase() - SystemClock.elapsedRealtime();
                    chronometerTimeStopped2 = chronometer2.getBase() - SystemClock.elapsedRealtime();
                    pauseTimer2();
                }
                else{
                    chronometer2.setBase(chrono2Time + SystemClock.elapsedRealtime());
                    chronometer2.start();
                    startTimer2();
                }
            }
        });
        btnStartStop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerIsRunning3){
                    chronometer3.stop();
                    chrono3Time = chronometer3.getBase() -SystemClock.elapsedRealtime();
                    chronometerTimeStopped3 = chronometer3.getBase() - SystemClock.elapsedRealtime();
                    pauseTimer3();
                }
                else{
                    chronometer3.setBase(chrono3Time + SystemClock.elapsedRealtime());
                    chronometer3.start();
                    startTimer3();
                }
            }
        });
        btnStartStop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerIsRunning4){
                    chronometer4.stop();
                    chrono4Time = chronometer4.getBase() - SystemClock.elapsedRealtime();
                    chronometerTimeStopped4 = chronometer4.getBase() - SystemClock.elapsedRealtime();
                    pauseTimer4();
                }
                else{
                    chronometer4.setBase(chrono4Time + SystemClock.elapsedRealtime());
                    chronometer4.start();
                    startTimer4();
                }
            }
        });

        btnReset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer1();
            }
        });
        btnReset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer2();
            }
        });
        btnReset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer3();
            }
        });
        btnReset4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer4();
            }
        });
    } // end on create  method ------------------------------------------------------------

    private void setTime1(long millis){
        startTimeMilliseconds1 = millis;
        resetTimer1();
        closeKeyboard();
    }
    private void setTime2(long millis){
        startTimeMilliseconds2 = millis;
        resetTimer2();
        closeKeyboard();
    }
    private void setTime3(long millis){
        startTimeMilliseconds3 = millis;
        resetTimer3();
        closeKeyboard();
    }
    private void setTime4(long millis){
        startTimeMilliseconds4 = millis;
        resetTimer4();
        closeKeyboard();
    }
    //==================================================================
    private  void startTimer1(){
        endTime1 = System.currentTimeMillis() + timeLeftMilliseconds1;
        countDownTimer1 = new CountDownTimer(timeLeftMilliseconds1,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftMilliseconds1 = millisUntilFinished;
                updateCountDownText1();
            }
            @Override
            public void onFinish() {
                timerIsRunning1 = false;
                updateWatchInterface1();
                chrono1Time = chronometer1.getBase() - SystemClock.elapsedRealtime();
                chronometer1.stop();
            }
        }.start();
        timerIsRunning1 = true;
        updateWatchInterface1();
    }
    private  void startTimer2(){
        endTime2 = System.currentTimeMillis() + timeLeftMilliseconds2;
        countDownTimer2 = new CountDownTimer(timeLeftMilliseconds2,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftMilliseconds2 = millisUntilFinished;
                updateCountDownText2();
            }
            @Override
            public void onFinish() {
                timerIsRunning2 = false;
                updateWatchInterface2();
                chrono2Time = chronometer2.getBase() - SystemClock.elapsedRealtime();
                chronometer2.stop();
            }
        }.start();
        timerIsRunning2 = true;
        updateWatchInterface2();
    }
    private  void startTimer3(){
        endTime3 = System.currentTimeMillis() + timeLeftMilliseconds3;
        countDownTimer3 = new CountDownTimer(timeLeftMilliseconds3,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftMilliseconds3 = millisUntilFinished;
                updateCountDownText3();
            }
            @Override
            public void onFinish() {
                timerIsRunning3 = false;
                updateWatchInterface3();
               chrono3Time = chronometer3.getBase() - SystemClock.elapsedRealtime();
                chronometer3.stop();
            }
        }.start();
        timerIsRunning3 = true;
        updateWatchInterface3();
    }
    private  void startTimer4(){
        endTime4 = System.currentTimeMillis() + timeLeftMilliseconds4;
        countDownTimer4 = new CountDownTimer(timeLeftMilliseconds4,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftMilliseconds4 = millisUntilFinished;
                updateCountDownText4();
            }
            @Override
            public void onFinish() {
                timerIsRunning4 = false;
                updateWatchInterface4();
                chrono4Time = chronometer4.getBase() - SystemClock.elapsedRealtime();
                chronometer4.stop();
            }
        }.start();
        timerIsRunning4 = true;
        updateWatchInterface4();
    }
    //================================================================================
    private void pauseTimer1(){
        countDownTimer1.cancel();
        timerIsRunning1 = false;
        updateWatchInterface1();
    }
    private void pauseTimer2(){
        countDownTimer2.cancel();
        timerIsRunning2 = false;
        updateWatchInterface2();
    }
    private void pauseTimer3(){
        countDownTimer3.cancel();
        timerIsRunning3 = false;
        updateWatchInterface3();
    }
    private void pauseTimer4(){
        countDownTimer4.cancel();
        timerIsRunning4 = false;
        updateWatchInterface4();
    }
    //==============================================================================
    private void resetTimer1(){
        timeLeftMilliseconds1 = startTimeMilliseconds1;
        updateCountDownText1();
        updateWatchInterface1();
    }
    private void resetTimer2(){
        timeLeftMilliseconds2 = startTimeMilliseconds2;
        updateCountDownText2();
        updateWatchInterface2();
    }
    private void resetTimer3(){
        timeLeftMilliseconds3 = startTimeMilliseconds3;
        updateCountDownText3();
        updateWatchInterface3();
    }
    private void resetTimer4(){
        timeLeftMilliseconds4 = startTimeMilliseconds4;
        updateCountDownText4();
        updateWatchInterface4();
    }
    //==========================================================
    private void updateCountDownText1(){
        int hrs = (int)(timeLeftMilliseconds1 / 1000) / 3600;
        int min = (int) ((timeLeftMilliseconds1 / 1000)%3600) /60;
        int sec = (int)(timeLeftMilliseconds1 / 1000) % 60;
        String timeLeftFormatted;
        if(hrs > 0){
            timeLeftFormatted = String.format(Locale.getDefault(),"%d:%02d:%02d",hrs,min,sec);
        }
        else{
            timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",min,sec);
        }
        textViewTimer1.setText(timeLeftFormatted);
    }
    private void updateCountDownText2(){
        int hrs = (int)(timeLeftMilliseconds2 / 1000) / 3600;
        int min = (int) ((timeLeftMilliseconds2 / 1000)%3600) /60;
        int sec = (int)(timeLeftMilliseconds2 / 1000) % 60;
        String timeLeftFormatted;
        if(hrs > 0){
            timeLeftFormatted = String.format(Locale.getDefault(),"%d:%02d:%02d",hrs,min,sec);
        }
        else{
            timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",min,sec);
        }
        textViewTimer2.setText(timeLeftFormatted);
    }
    private void updateCountDownText3(){
        int hrs = (int)(timeLeftMilliseconds3 / 1000) / 3600;
        int min = (int) ((timeLeftMilliseconds3 / 1000)%3600) /60;
        int sec = (int)(timeLeftMilliseconds3 / 1000) % 60;
        String timeLeftFormatted;
        if(hrs > 0){
            timeLeftFormatted = String.format(Locale.getDefault(),"%d:%02d:%02d",hrs,min,sec);
        }
        else{
            timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",min,sec);
        }
        textViewTimer3.setText(timeLeftFormatted);
    }
    private void updateCountDownText4(){
        int hrs = (int)(timeLeftMilliseconds4 / 1000) / 3600;
        int min = (int) ((timeLeftMilliseconds4 / 1000)%3600) /60;
        int sec = (int)(timeLeftMilliseconds4 / 1000) % 60;
        String timeLeftFormatted;
        if(hrs > 0){
            timeLeftFormatted = String.format(Locale.getDefault(),"%d:%02d:%02d",hrs,min,sec);
        }
        else{
            timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",min,sec);
        }
        textViewTimer4.setText(timeLeftFormatted);
    }
    //=================================================================
    private void updateWatchInterface1(){
        if(timerIsRunning1){
            textInput1.setVisibility(View.INVISIBLE);
            btnSetTimer1.setVisibility(View.INVISIBLE);
            btnReset1.setVisibility(View.INVISIBLE);
            btnStartStop1.setText(pause);
        }
        else{
            textInput1.setVisibility(View.VISIBLE);
            btnSetTimer1.setVisibility(View.VISIBLE);
            btnStartStop1.setText(start);
            if(timeLeftMilliseconds1 < 1000){
                btnStartStop1.setVisibility(View.INVISIBLE);

            }
            else{
                btnStartStop1.setVisibility(View.VISIBLE);
            }
            if(timeLeftMilliseconds1 < startTimeMilliseconds1){
                btnReset1.setVisibility(View.VISIBLE);
            }
            else {
                btnReset1.setVisibility(View.INVISIBLE);
            }
        }
    }
    private void updateWatchInterface2(){
        if(timerIsRunning2){
            textInput2.setVisibility(View.INVISIBLE);
            btnSetTimer2.setVisibility(View.INVISIBLE);
            btnReset2.setVisibility(View.INVISIBLE);
            btnStartStop2.setText(pause);
        }
        else{
            textInput2.setVisibility(View.VISIBLE);
            btnSetTimer2.setVisibility(View.VISIBLE);
            btnStartStop2.setText(start);
            if(timeLeftMilliseconds2 < 1000){
                btnStartStop2.setVisibility(View.INVISIBLE);
            }
            else{
                btnStartStop2.setVisibility(View.VISIBLE);
            }
            if(timeLeftMilliseconds2 < startTimeMilliseconds1){
                btnReset2.setVisibility(View.VISIBLE);
            }
            else {
                btnReset2.setVisibility(View.INVISIBLE);
            }
        }
    }
    private void updateWatchInterface3(){
        if(timerIsRunning3){
            textInput3.setVisibility(View.INVISIBLE);
            btnSetTimer3.setVisibility(View.INVISIBLE);
            btnReset3.setVisibility(View.INVISIBLE);
            btnStartStop3.setText(pause);
        }
        else{
            textInput3.setVisibility(View.VISIBLE);
            btnSetTimer3.setVisibility(View.VISIBLE);
            btnStartStop3.setText(start);
            if(timeLeftMilliseconds3 < 1000){
                btnStartStop3.setVisibility(View.INVISIBLE);
            }
            else{
                btnStartStop3.setVisibility(View.VISIBLE);
            }
            if(timeLeftMilliseconds3 < startTimeMilliseconds1){
                btnReset3.setVisibility(View.VISIBLE);
            }
            else {
                btnReset3.setVisibility(View.INVISIBLE);
            }
        }
    }
    private void updateWatchInterface4(){
        if(timerIsRunning4){
            textInput4.setVisibility(View.INVISIBLE);
            btnSetTimer4.setVisibility(View.INVISIBLE);
            btnReset4.setVisibility(View.INVISIBLE);
            btnStartStop4.setText(pause);
        }
        else{
            textInput4.setVisibility(View.VISIBLE);
            btnSetTimer4.setVisibility(View.VISIBLE);
            btnStartStop4.setText(start);
            if(timeLeftMilliseconds4 < 1000){
                btnStartStop4.setVisibility(View.INVISIBLE);
            }
            else{
                btnStartStop4.setVisibility(View.VISIBLE);
            }
            if(timeLeftMilliseconds4 < startTimeMilliseconds1){
                btnReset4.setVisibility(View.VISIBLE);
            }
            else {
                btnReset4.setVisibility(View.INVISIBLE);
            }
        }
    }
    //================================================================
    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(),0);
            }
        }
    }
    //===================================================================
    /*
    @Override
    protected void onSaveInstanceState( @NonNull  Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putLong("chrono1",chrono1Time + SystemClock.elapsedRealtime());
        bundle.putLong("chrono2",chrono2Time + SystemClock.elapsedRealtime());
        bundle.putLong("chrono3",chrono3Time + SystemClock.elapsedRealtime());
        bundle.putLong("chrono4",chrono4Time + SystemClock.elapsedRealtime());

    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle b){
        super.onRestoreInstanceState(b);
        chronometer1.setBase(b.getLong("chrono1",0) - SystemClock.elapsedRealtime());
        chronometer2.setBase(b.getLong("chrono2",0) - SystemClock.elapsedRealtime());
        chronometer3.setBase(b.getLong("chrono3",0) - SystemClock.elapsedRealtime());
        chronometer4.setBase(b.getLong("chrono4",0) - SystemClock.elapsedRealtime());
    }
    */
    //=====================================================================

    @Override
    protected void onStop(){
       super.onStop();

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("name1",name1);
        editor.putString("name2",name2);
        editor.putString("name3",name3);
        editor.putString("name4",name4);

        editor.putLong("startTimeInMillis1", startTimeMilliseconds1);
        editor.putLong("millisLeft1", timeLeftMilliseconds1);
        editor.putBoolean("timerRunning1", timerIsRunning1);
        editor.putLong("endTime1", endTime1);
        editor.putLong("chro1",chrono1Time);
        //--------------------------------------
        editor.putLong("startTimeInMillis2", startTimeMilliseconds2);
        editor.putLong("millisLeft2", timeLeftMilliseconds2);
        editor.putBoolean("timerRunning2", timerIsRunning2);
        editor.putLong("endTime2", endTime2);
        editor.putLong("chro2",chrono2Time);
        //--------------------------------------
        editor.putLong("startTimeInMillis3", startTimeMilliseconds3);
        editor.putLong("millisLeft3", timeLeftMilliseconds3);
        editor.putBoolean("timerRunning3", timerIsRunning3);
        editor.putLong("endTime3", endTime3);
        editor.putLong("chro3",chrono3Time);
        //---------------------------------------
        editor.putLong("startTimeInMillis4", startTimeMilliseconds4);
        editor.putLong("millisLeft4", timeLeftMilliseconds4);
        editor.putBoolean("timerRunning4", timerIsRunning4);
        editor.putLong("endTime4", endTime4);
        editor.putLong("chro4",chrono4Time);
        //------------------------------------------

        editor.apply();
        if(countDownTimer1 != null){
            countDownTimer1.cancel();
        }
        if(countDownTimer2 != null){
            countDownTimer2.cancel();
        }
        if(countDownTimer3 != null){
            countDownTimer3.cancel();
        }
        if(countDownTimer4 != null){
            countDownTimer4.cancel();
        }
    }
    //====================================================================
    @Override
    protected void onStart(){
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);

        startTimeMilliseconds1 = prefs.getLong("startTimeInMillis1",600000);
        timeLeftMilliseconds1 = prefs.getLong("millisLeft1", startTimeMilliseconds1);
        timerIsRunning1 = prefs.getBoolean("timerRunning1",false);
        chrono1Time = prefs.getLong("chro1",0);
        //This resets total time if checkbox in edit is checked

        if(ch1){
            chronometer1.setBase(SystemClock.elapsedRealtime());
            chrono1Time = 0;
        }
        else{
            chronometer1.setBase(SystemClock.elapsedRealtime() + chrono1Time );
        }
        updateCountDownText1();
        updateWatchInterface1();
        if(timerIsRunning1){
            endTime1 = prefs.getLong("endTime",0);
            timeLeftMilliseconds1 = endTime1 - System.currentTimeMillis();
            if(timeLeftMilliseconds1 < 0){
                timeLeftMilliseconds1 = 0;
                timerIsRunning1 = false;
                updateCountDownText1();
                updateWatchInterface1();
            }
            else{
              startTimer1();
            }
        }

        //===============================================
        startTimeMilliseconds2 = prefs.getLong("startTimeInMillis2",600000);
        timeLeftMilliseconds2 = prefs.getLong("millisLeft2", startTimeMilliseconds2);
        timerIsRunning2 = prefs.getBoolean("timerRunning2",false);
        chrono2Time = prefs.getLong("chro2",0);
        if(ch2){
            chronometer2.setBase(SystemClock.elapsedRealtime());
            chrono2Time =0;
        }
        else {
            chronometer2.setBase(SystemClock.elapsedRealtime() + chrono2Time);
        }
        updateCountDownText2();
        updateWatchInterface2();
        if(timerIsRunning2){
            endTime2 = prefs.getLong("endTime2",0);
            timeLeftMilliseconds2 = endTime2 - System.currentTimeMillis();
            if(timeLeftMilliseconds2 < 0){
                timeLeftMilliseconds2 = 0;
                timerIsRunning2 = false;
                updateCountDownText2();
                updateWatchInterface2();
            }
            else{
               startTimer2();
            }
        }

        //===================================
        startTimeMilliseconds3 = prefs.getLong("startTimeInMillis3",600000);
        timeLeftMilliseconds3 = prefs.getLong("millisLeft3", startTimeMilliseconds3);
        timerIsRunning3 = prefs.getBoolean("timerRunning3",false);
        chrono3Time = prefs.getLong("chro3",0);
        if(ch3){
            chronometer3.setBase(SystemClock.elapsedRealtime());
            chrono3Time = 0;
        }
        else {
            chronometer3.setBase(SystemClock.elapsedRealtime() + chrono3Time);
        }
        updateCountDownText3();
        updateWatchInterface3();
        if(timerIsRunning3){
            endTime3 = prefs.getLong("endTime3",0);
            timeLeftMilliseconds3 = endTime3 - System.currentTimeMillis();
            if(timeLeftMilliseconds3 < 0){
                timeLeftMilliseconds3 = 0;
                timerIsRunning3 = false;
                updateCountDownText3();
                updateWatchInterface3();
            }
            else{
                startTimer3();
            }
        }

        //=========================================
        startTimeMilliseconds4 = prefs.getLong("startTimeInMillis4",600000);
        timeLeftMilliseconds4 = prefs.getLong("millisLeft4", startTimeMilliseconds4);
        timerIsRunning4 = prefs.getBoolean("timerRunning4",false);
        chrono4Time = prefs.getLong("chro4",0);
        if(ch4){
            chronometer4.setBase(SystemClock.elapsedRealtime());
            chrono4Time = 0;
        }
        else {
            chronometer4.setBase(SystemClock.elapsedRealtime() + chrono4Time);
        }
        updateCountDownText4();
        updateWatchInterface4();
        if(timerIsRunning4){
            endTime4 = prefs.getLong("endTime4",0);
            timeLeftMilliseconds4 = endTime4 - System.currentTimeMillis();
            if(timeLeftMilliseconds4 < 0){
                timeLeftMilliseconds4 = 0;
                timerIsRunning4 = false;
                updateCountDownText4();
                updateWatchInterface4();
            }
            else{
               startTimer4();
            }
        }
    }

}
