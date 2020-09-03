package com.example.flag;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Timer;

public class Guessing_Flags extends AppCompatActivity {
    JSONObject jsonObject;
    String fullName;
    int randomNumberOneToThree;
    TextView textToDisplay;
    TextView textToDisplayWrong;
    android.os.Handler customHandler;
    int timeCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing__flags);
        textToDisplay = (TextView) findViewById(R.id.Displays_Correct);
        textToDisplayWrong = (TextView) findViewById(R.id.Displays_Wrong);

        //Setting the images to a variable to be able to use
        ImageView imageOnLeft = findViewById(R.id.imageView_left);
        ImageView imageInMiddle = findViewById(R.id.imageView_middle);
        ImageView imageOnRight = findViewById(R.id.imageView_right);

        String resource = "ad";
        String resource2 = "us";
        String resource3 = "hk";

        int resource_id = getResources().getIdentifier(resource, "drawable", "com.example.flag");
        int resource_id2 = getResources().getIdentifier(resource2, "drawable", "com.example.flag");
        int resource_id3 = getResources().getIdentifier(resource3, "drawable", "com.example.flag");

        imageOnLeft.setImageResource(resource_id);
        imageInMiddle.setImageResource(resource_id2);
        imageOnRight.setImageResource(resource_id3);

        //Now read the json file
        String jsonString = readTextFileFromRawResource2(getApplicationContext(), R.raw.countries);
        doJson2(jsonString);
        changeRandomFlags(null);

        //Starting the timer
        if (MainActivity.showTimer == true) {
            customHandler = new android.os.Handler();
            customHandler.postDelayed(updateTimerThread, 0);
        }
    }

    // Copied from
    // https://stackoverflow.com/questions/14292749/android-how-to-repeat-a-function-every-contant-time
    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            TextView tvTimer = (TextView) findViewById(R.id.tv_Timer);
            tvTimer.setText("Seconds " + timeCount++);

            customHandler.postDelayed(this, 1000); //1000 millis = 1 second
        }
    };

    //
    void doJson2(String jsonStr) {

        // from https://www.tutorialspoint.com/android/android_json_parser.htm
        // https://www.programcreek.com/java-api-examples/?class=org.json.JSONObject&method=names
        try {
            jsonObject = new JSONObject(jsonStr); //makes the string jsonStr into an object (an array)
            Log.d("gg", "json length " + jsonObject.length()); //this should be 256 bc the amount of countries
            String jsonName;
            String jsonValue;

            //goes through the jsonObject
            for (int idx = 0; idx < jsonObject.length(); idx++) {
                jsonName = (String) jsonObject.names().get(idx);
                jsonValue = jsonObject.getString(jsonName);
                //  Log.d("gg", 2 + " Name " + jsonName + " value " + jsonValue);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("gg", Log.getStackTraceString(e));
        }
    }

    //
    // from https://stackoverflow.com/questions/4087674/android-read-text-raw-resource-file/4087865
    public static String readTextFileFromRawResource2(Context context, int id) {
        InputStream inputStream = context.getResources().openRawResource(id);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buffer[] = new byte[1024];
        int size;
        try {
            while ((size = inputStream.read(buffer)) != -1) { //reading and putting into buffer; if returns -1 then there's nothing left in the file to read
                outputStream.write(buffer, 0, size); //takes buffer and gives it to the output stream that uses byte arrays
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            Log.d("gg", "error reading file ");
        }
        return outputStream.toString(); //makes the output into a string
    }


    //get random number for a random flag
    Random random = new Random();

    public void changeRandomFlags(View v) {
        try {

            int randomNumber1 = random.nextInt(257); //generates a random number between 0 and 256
            int randomNumber2 = random.nextInt(257);
            int randomNumber3 = random.nextInt(257);

            String jsonName1 = (String) jsonObject.names().get(randomNumber1);
            String jsonName2 = (String) jsonObject.names().get(randomNumber2);
            String jsonName3 = (String) jsonObject.names().get(randomNumber3);

            randomNumberOneToThree = random.nextInt(3);

            //String fullName1 = jsonObject.getString(jsonName1);
            //  Log.d("gg", "what is jsonName" + jsonName);
            //randomNumberOneToThree = randomOneToThree;
            if (randomNumberOneToThree == 0) {
                fullName = jsonObject.getString(jsonName1);
            } else if (randomNumberOneToThree == 1) {
                fullName = jsonObject.getString(jsonName2);
            } else if (randomNumberOneToThree == 2) {
                fullName = jsonObject.getString(jsonName3);
            } else {
                Log.d("gg", "problem with random number");
            }

            //fullName1 = jsonObject.getString(jsonName1);
            Log.d("gg", "jSonNames" + jsonName1 + " " + jsonName2 + " " + jsonName3 + " full name: " + fullName
                    + "random number is : " + randomNumberOneToThree);

            TextView flagToMatch = (TextView) findViewById(R.id.Flag_To_Match);
            flagToMatch.setText(fullName);


            ImageView v1 = findViewById(R.id.imageView_left);
            ImageView v2 = findViewById(R.id.imageView_middle);
            ImageView v3 = findViewById(R.id.imageView_right);
            //v1.setImageResource(R.drawable.ad);
            String resource1 = jsonName1.toLowerCase();
            String resource2 = jsonName2.toLowerCase();
            String resource3 = jsonName3.toLowerCase();

            int resource_id1 = getResources().getIdentifier(resource1, "drawable", "com.example.flag");
            int resource_id2 = getResources().getIdentifier(resource2, "drawable", "com.example.flag");
            int resource_id3 = getResources().getIdentifier(resource3, "drawable", "com.example.flag");

            v1.setImageResource(resource_id1);
            v2.setImageResource(resource_id2);
            v3.setImageResource(resource_id3);

        } catch (Exception e) {
        }
    }

    public void buttonNext(View view) {
        changeRandomFlags(view);
        displayTextCorrect(R.string.nothing);
        displayTextWrong(R.string.nothing);
    }

    public void clickedOnLeftImage(View view) {
        if (randomNumberOneToThree == 0) {
            displayTextCorrect(R.string.makeToast_Submit_Right);
        } else {
            displayTextWrong(R.string.makeToast_Submit_Wrong);
        }
    }

    public void clickedMiddleImage(View view) {

        if (randomNumberOneToThree == 1) {
            displayTextCorrect(R.string.makeToast_Submit_Right);
        } else {
            displayTextWrong(R.string.makeToast_Submit_Wrong);
        }
    }

    public void clickedRightImage(View view) {
        if (randomNumberOneToThree == 2) {
            displayTextCorrect(R.string.makeToast_Submit_Right);
        } else {
            displayTextWrong(R.string.makeToast_Submit_Wrong);
        }
    }


    public void displayTextCorrect(int message) {
        textToDisplay = (TextView) findViewById(R.id.Displays_Correct);
        textToDisplay.setText(message);
        textToDisplayWrong.setText("");
        textToDisplay.setTextColor(Color.GREEN);

    }

    public void displayTextWrong(int message) {
        textToDisplayWrong = (TextView) findViewById(R.id.Displays_Wrong);
        textToDisplayWrong.setText(message);
        textToDisplay.setText("");
        textToDisplayWrong.setTextColor(Color.RED);
    }
}
