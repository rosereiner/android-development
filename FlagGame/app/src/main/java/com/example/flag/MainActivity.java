package com.example.flag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    static boolean showTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declaring countryButton to add an activity
        Button countryButton = (Button)findViewById(R.id.guessing_country_button);
        //declaring guessHintsButton to add an activity
        Button guessHintsButton = (Button)findViewById(R.id.Guessed_Hints_Button);
        //declaring guessFlagsButton to add an activity
        Button guessFlagsButton = (Button)findViewById(R.id.Guess_The_Flag_Button);
        //declaring advancedLevelButton to add an activity
        Button advancedLevelButton = (Button)findViewById(R.id.Advanced_Level_Button);


       //Create an onClick Listener to make a new intent for the activity
        countryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("gg", "clicked country button");

                Intent intent = new Intent(getApplicationContext(), CountryActivity.class);
                startActivity(intent);
            }
        });

        guessHintsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("gg", "clicked guess hints button");

                Intent intent = new Intent(getApplicationContext(), Guess_Hints2.class);
                startActivity(intent);
            }
        });

        guessFlagsButton.setOnClickListener(new View.OnClickListener(){
                @Override
            public void onClick(View v) {
                    Log.d("gg", "clicked guess flags button");

                    Intent intent = new Intent(getApplicationContext(), Guessing_Flags.class);
                    startActivity(intent);
                }
        });

        advancedLevelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("gg", "clicked advanced level button");

                Intent intent = new Intent(getApplicationContext(), Advanced_Level.class);
                startActivity(intent);
            }
        });


        //https://stackoverflow.com/questions/11278507/android-widget-switch-on-off-event-listener
        Switch timerSwitch = (Switch)findViewById(R.id.switch1);
        timerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showTimer = isChecked;
                Log.d("gg", "show timer is: " + isChecked);
            }
        });
    }

    public void guessHints(View view){

    }

    public void guessFlag(View view) {
    }

    public void advancedLevel(View view) {
    }

    public void guessCountry(View view) {
    }
}
