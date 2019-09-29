package com.example.timerv33;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class Edit extends AppCompatActivity {

    private EditText nameTimer;
    public RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private CheckBox cbReset1, cbReset2, cbReset3, cbReset4;
    private Button btnSave;
    private Button btnReturn;
    private Button btnNotes;
    public static String n1, n2, n3, n4 = "Name";
    public static boolean ch1,ch2,ch3,ch4 =false;
    private String name;

    //This disables the back button
    @Override
    public void onBackPressed(){
        moveTaskToBack(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        nameTimer = findViewById(R.id.nameTimer);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        btnReturn = findViewById(R.id.btnReturn);
        btnSave = findViewById(R.id.btnSave);
        btnNotes = findViewById(R.id.btnNotes);
        cbReset1 = findViewById(R.id.cbReset1);
        cbReset2 = findViewById(R.id.cbReset2);
        cbReset3 = findViewById(R.id.cbReset3);
        cbReset4 = findViewById(R.id.cbReset4);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Edit.this, MainActivity.class);

                name = nameTimer.getText().toString();
                if(!name.equals("")) {
                    if (rb1.isChecked()) {
                        intent.putExtra("rbSelected", 1);
                        n1 = name;
                    }
                    if (rb2.isChecked()) {
                        intent.putExtra("rbSelected", 2);
                        n2 = name;
                    }
                    if (rb3.isChecked()) {
                        intent.putExtra("rbSelected", 3);
                        n3 = name;
                    }
                    if (rb4.isChecked()) {
                        intent.putExtra("rbSelected", 4);
                        n4 = name;
                    }
                    intent.putExtra("name", name);
                }
                //================================
                if (cbReset1.isChecked()) {
                    intent.putExtra("reset", 1);
                    ch1 = true;
                }
                if (cbReset2.isChecked()) {
                    intent.putExtra("reset", 2);
                    ch2 = true;
                }
                if (cbReset3.isChecked()) {
                    intent.putExtra("reset", 3);
                    ch3 = true;
                }
                if (cbReset4.isChecked()) {
                    intent.putExtra("reset", 4);
                    ch4 = true;
                }
                startActivity(intent);
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit.this, MainActivity.class);
                startActivity(intent);
               // finish();
            }
        });

        btnNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Edit.this,Notes.class));
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();

        //resetting checkbox values
        ch1= false;
        ch2 =false;
        ch3= false;
        ch4 =false;

        SharedPreferences prefs = getSharedPreferences("prefs2",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("n1",n1);
        editor.putString("n2",n2);
        editor.putString("n3",n3);
        editor.putString("n4",n4);
        editor.apply();

    }
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs3 = getSharedPreferences("prefs2",MODE_PRIVATE);
        n1 = prefs3.getString("n1","");
        n2 = prefs3.getString("n2","");
        n3 = prefs3.getString("n3","");
        n4 = prefs3.getString("n4","");

        ch1= false; //resetting the checkbox values
        ch2 =false;
        ch3= false;
        ch4 =false;
    }
}
