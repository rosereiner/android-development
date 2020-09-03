package com.example.flag;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Advanced_Level extends AppCompatActivity {
    JSONObject jsonObject;
    String fullName1;
    String fullName2;
    String fullName3;
    Random random = new Random();
    TextView textToDisplay1;
    TextView textToDisplay2;
    TextView textToDisplay3;
    int numberOfIncorrectGuesses = 0;
    boolean submitToNext = true;
    int keepingScore = 0;
    android.os.Handler customHandler;
    int timeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);


        ImageView image1 = (ImageView) findViewById(R.id.image1_left);
        ImageView image2 = (ImageView) findViewById(R.id.image2_middle);
        ImageView image3 = (ImageView) findViewById(R.id.image3_right);

        String resource1 = "ad";
        String resource2 = "us";
        String resource3 = "hk";

        int resource_id1 = getResources().getIdentifier(resource1, "drawable", "com.example.flag");
        int resource_id2 = getResources().getIdentifier(resource2, "drawable", "com.example.flag");
        int resource_id3 = getResources().getIdentifier(resource3, "drawable", "com.example.flag");


        image1.setImageResource(resource_id1);
        image2.setImageResource(resource_id2);
        image3.setImageResource(resource_id3);

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
            TextView tvTimer = (TextView) findViewById(R.id.tv_Timer3);
            tvTimer.setText("Seconds " + timeCount++);

            customHandler.postDelayed(this, 1000); //1000 millis = 1 second
        }
    };

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

    public void changeRandomFlags(View v) {
        try {

            int randomNumber1 = random.nextInt(257); //generates a random number between 0 and 256
            int randomNumber2 = random.nextInt(257);
            int randomNumber3 = random.nextInt(257);

            String jsonName1 = (String) jsonObject.names().get(randomNumber1); //getting the two letter flag with random number
            String jsonName2 = (String) jsonObject.names().get(randomNumber2);
            String jsonName3 = (String) jsonObject.names().get(randomNumber3);

            fullName1 = jsonObject.getString(jsonName1);
            fullName2 = jsonObject.getString(jsonName2);
            fullName3 = jsonObject.getString(jsonName3);

            Log.d("gg", "name1: " + fullName1 +
                    " name2: " + fullName2 +
                    " name3: " + fullName3);


            ImageView v1 = findViewById(R.id.image1_left);
            ImageView v2 = findViewById(R.id.image2_middle);
            ImageView v3 = findViewById(R.id.image3_right);

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

    public void advSubmit(View view) {
        TextView scoreCount = (TextView) findViewById(R.id.scoreCount);

        //Setting the texts to be decided correct answer
        String flag1_guess = ((EditText) findViewById(R.id.editText_image1)).getText().toString();
        String flag2_guess = ((EditText) findViewById(R.id.editText_image2)).getText().toString();
        String flag3_guess = ((EditText) findViewById(R.id.editText_image3)).getText().toString();


        if (flag1_guess.toLowerCase().equals(fullName1.toLowerCase())) { //compares guess to the flag1 name
            Log.d("gg", "correct!");

            keepingScore++;
            scoreCount.setText("Score: " + keepingScore);

            textToDisplay1 = (TextView) findViewById(R.id.img1_correctText);
            textToDisplay1.setText("Correct");
            textToDisplay1.setTextColor(Color.GREEN);

        } else {
            Log.d("gg", "full name is: " + fullName1 + " guessed name: " + flag1_guess);
            textToDisplay1 = (TextView) findViewById(R.id.img1_correctText);
            textToDisplay1.setText("Incorrect");
            textToDisplay1.setTextColor(Color.RED);

            numberOfIncorrectGuesses++;
            Log.d("gg", "number of incorrect guesses is: " + numberOfIncorrectGuesses);

        }
        if (flag2_guess.toLowerCase().equals(fullName2.toLowerCase())) { //compares guess to flag2 name
            Log.d("gg", "correct!");

            keepingScore++;
            scoreCount.setText("Score: " + keepingScore);

            textToDisplay2 = (TextView) findViewById(R.id.img2_correctText);
            textToDisplay2.setText("Correct");
            textToDisplay2.setTextColor(Color.GREEN);
        } else {
            Log.d("gg", "full name is: " + fullName2 + " guessed name: " + flag2_guess);
            textToDisplay2 = (TextView) findViewById(R.id.img2_correctText);
            textToDisplay2.setText("Incorrect");
            textToDisplay2.setTextColor(Color.RED);

            numberOfIncorrectGuesses++;
            Log.d("gg", "number of incorrect guesses is: " + numberOfIncorrectGuesses);

        }
        if (flag3_guess.toLowerCase().equals(fullName3.toLowerCase())) { //compares guess to flag3 name
            Log.d("gg", "correct!");

            keepingScore++;
            scoreCount.setText("Score: " + keepingScore);

            textToDisplay3 = (TextView) findViewById(R.id.img3_correctText);
            textToDisplay3.setText("Correct");
            textToDisplay3.setTextColor(Color.GREEN);
        } else {
            Log.d("gg", "full name is: " + fullName3 + " guessed name: " + flag3_guess);
            textToDisplay3 = (TextView) findViewById(R.id.img3_correctText);
            textToDisplay3.setText("Incorrect");
            textToDisplay3.setTextColor(Color.RED);

            numberOfIncorrectGuesses++;
            Log.d("gg", "number of incorrect guesses is: " + numberOfIncorrectGuesses);
        }

        //Seeing if the number of guesses is higher than 3 then display answers
        if (numberOfIncorrectGuesses >= 3) {
            Log.d("gg", "incorrect guesses is above 3: " + numberOfIncorrectGuesses);
            numberOfIncorrectGuesses = 0;

            //if the guess is not equal to the flag name, then display the correct flag name
            if (!flag1_guess.toLowerCase().equals(fullName1.toLowerCase())) {
                TextView flag1CorrectAnswer = (TextView) findViewById(R.id.flag1CorrectAnswer);
                flag1CorrectAnswer.setText(fullName1);
                flag1CorrectAnswer.setTextColor(Color.BLUE);
            }
            if (!flag2_guess.toLowerCase().equals(fullName2.toLowerCase())) {
                TextView flag2CorrectAnswer = (TextView) findViewById(R.id.flag2CorrectAnswer);
                flag2CorrectAnswer.setText(fullName2);
                flag2CorrectAnswer.setTextColor(Color.BLUE);
            }
            if (!flag3_guess.toLowerCase().equals(fullName3.toLowerCase())) {
                TextView flag3CorrectAnswer = (TextView) findViewById(R.id.flag3CorrectAnswer);
                flag3CorrectAnswer.setText(fullName3);
                flag3CorrectAnswer.setTextColor(Color.BLUE);
            }


            TextView nextButton = (TextView) findViewById(R.id.Btn_Submit);

            if (submitToNext == true) {
                nextButton.setText("Next");
                submitToNext = false;
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { //going to the next round
                        TextView nextButton = (TextView) findViewById(R.id.Btn_Submit);
                        changeRandomFlags(null);
                        nextButton.setText("Submit");
                        submitToNext = false;
                    }
                });

                // changeRandomFlags(null);
            } else {
                nextButton.setText("Submit");
                submitToNext = true;
                // changeRandomFlags();
            }

            // changeRandomFlags(null);
        }
    }
}
