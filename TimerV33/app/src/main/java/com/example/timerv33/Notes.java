package com.example.timerv33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Notes extends AppCompatActivity {

    private EditText textView1,textView2,textView3,textView4;
    private Button btnS1,btnS2,btnS3,btnS4;
    private Button btnBack;
    String S1,S2,S3,S4;

    //this disables the back button
    @Override
    public void onBackPressed(){
        moveTaskToBack(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4= findViewById(R.id.textView4);
        btnS1 =findViewById(R.id.btnSave1);
        btnS2 =findViewById(R.id.btnSave2);
        btnS3 =findViewById(R.id.btnSave3);
        btnS4 =findViewById(R.id.btnSave4);
        btnBack = findViewById(R.id.btnBack);


      btnS1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              S1 = textView1.getText().toString();
              finish();
          }
      });
        btnS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                S2 = textView2.getText().toString();
                finish();
            }
        });
        btnS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                S3 = textView3.getText().toString();
                finish();
            }
        });
        btnS4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                S4 = textView4.getText().toString();
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    } //end on create

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("notes",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("S1", S1);
        editor.putString("S2", S2);
        editor.putString("S3", S3);
        editor.putString("S4", S4);
        editor.apply();
    }

    @Override
    protected void onStart(){
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("notes",MODE_PRIVATE);

        S1 = prefs.getString("S1","");
        textView1.setText(S1);
        S2 = prefs.getString("S2","");
        textView2.setText(S2);
        S3 = prefs.getString("S3","");
        textView3.setText(S3);
        S4 = prefs.getString("S4","");
        textView4.setText(S4);
    }
}
