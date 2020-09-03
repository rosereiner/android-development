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
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Guess_Hints2 extends AppCompatActivity {
    JSONObject jsonObject;
    String idName;
    String fullName;
    TextView textViewCountryName;
    int randomNumber;
    int count1 = 0;
    boolean submitOrNext = true;
    int badGuess = 0;
    android.os.Handler customHandler;
    int timeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_hints2);

        textViewCountryName = (TextView) findViewById(R.id.dashes_country_name);


        ImageView imageToDisplay = (ImageView) findViewById(R.id.Guess_Image);
        String resource = "us";
        int resource_id = getResources().getIdentifier(resource, "drawable", "com.example.flag");
        imageToDisplay.setImageResource(resource_id);

        //Now read the json file
        String jsonString = readTextFileFromRawResource3(getApplicationContext(), R.raw.countries);
        doJson3(jsonString);
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
            TextView tvTimer = (TextView) findViewById(R.id.tv_Timer4);
            tvTimer.setText("Seconds " + timeCount++);

            customHandler.postDelayed(this, 1000); //1000 millis = 1 second
        }
    };

    void doJson3(String jsonStr) {

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
    public static String readTextFileFromRawResource3(Context context, int id) {
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

            // jsonName = (String)jsonObject.names().get(idx);
            //  String jsonName = (String) jsonObject.names().get(3);

            randomNumber = random.nextInt(257); //generates a random number between 0 and 256
            String jsonName = (String) jsonObject.names().get(randomNumber);
            fullName = jsonObject.getString(jsonName);

            //nameOfFlagShown = jsonName;
            Log.d("gg", "jsonName is: " + jsonName + " full name is: " + fullName);


            ImageView v1 = findViewById(R.id.Guess_Image);
            //v1.setImageResource(R.drawable.ad);
            String resource = jsonName.toLowerCase();
            int resource_id = getResources().getIdentifier(resource, "drawable", "com.example.flag");
            v1.setImageResource(resource_id);


            String guessedLetters = ((EditText) findViewById(R.id.guessingLetters)).getText().toString();
            String dashedString = makeDashedString(fullName, guessedLetters);

            textViewCountryName.setText(dashedString); //setting it to the textview to display
            // textViewCountryName.setText("zzz");

        } catch (Exception e) {
            //  Log.d("gg", "exception: " + e);
            Log.getStackTraceString(e);
        }
    }

    public void showFullName() {
        try {
            randomNumber = random.nextInt(257); //generates a random number between 0 and 256
            String jsonName = (String) jsonObject.names().get(randomNumber);
            //nameOfFlagShown = jsonName;

            //full name of the flag displayed
            Log.d("gg", "id of the flag shown is: " + idName + " the full name is: " + fullName);
            idName = (String) jsonObject.names().get(randomNumber);
            fullName = jsonObject.getString(idName);
            textViewCountryName.setText(fullName);
            textViewCountryName.setTextColor(Color.BLUE);
        } catch (Exception e) {

        }
    }

    public String makeDashedString(String countryName, String lettersGuessed) {
        int count = 0;
        String dashes = "";
        //String guessedString = "";
        Log.d("gg", "letters guessed: " + lettersGuessed + " country name is: " + countryName);

        badGuess = 0;

        //goes through the country's name and sees if the character is equal make a new string rep. of dashes
        for (int i = 0; i < countryName.length(); i++) {
            char character = countryName.charAt(i); //gets one individual  letter in the country name
            character = Character.toLowerCase(character); //sets that to a lowercase version for comparison
            if (character == ' ') {
                dashes += " ";
            } else if (character == ',') {
                dashes += ',';
            } else if (character == '(') {
                dashes += '(';
            } else if (character == ')') {
                dashes += ')';
            } else if (lettersGuessed.toLowerCase().indexOf(character) == -1) { //if the character is not contained in the guessed letters, make a dash
                dashes += "-";

            } else { //making the starting words in the flag name uppercase
                if (i == 0) {
                    dashes += Character.toUpperCase(character);
                } else if (countryName.toLowerCase().charAt(i - 1) == ' ') {
                    dashes += Character.toUpperCase(character);
                } else if (countryName.toLowerCase().charAt(i - 1) == '(') {
                    dashes += Character.toUpperCase(character);
                } else if (countryName.toLowerCase().charAt(i - 1) == '\'') {
                    dashes += Character.toUpperCase(character);
                } else {
                    dashes += character;
                }
            }
        }

        //goes through the string of the guessed letters and see if they exist in the country name
        //to see how many incorrect character guesses there are
        for (int i = 0; i < lettersGuessed.length(); i++) {
            // char character = countryName.charAt(i);
            char letterInLettersGuessed = lettersGuessed.charAt(i);
            //character = Character.toLowerCase(character);

            if (countryName.toLowerCase().indexOf(letterInLettersGuessed) == -1) {
                badGuess++;
                Log.d("gg", "bad guess is: " + badGuess);
            }
        }
        return dashes;
    }


    public void advLevelSubmitButton(View view) {
        //  changeRandomFlags(view);
        int count = 0;

        TextView nextButton = (TextView) findViewById(R.id.submit_button2);

       /*     if (submitOrNext == true) {
                nextButton.setText("Next");
                submitOrNext = false;
            } else {
                nextButton.setText("Submit");
                submitOrNext = true;
            }*/
        nextButton = (TextView) findViewById(R.id.submit_button2);

        String guessedLetters = ((EditText) findViewById(R.id.guessingLetters)).getText().toString();
        String dashedString = makeDashedString(fullName, guessedLetters);
        textViewCountryName.setText(dashedString); //setting it to the textview to display
        if (dashedString.indexOf('-') == -1) {
            count = 0;
            Log.d("gg", "congrats!");
            correctMessage("Correct");

            if (submitOrNext == true) {
                nextButton.setText("Next");
                changeRandomFlags(null);
                correctMessage("");
                submitOrNext = false;
            } else {
                nextButton.setText("Submit");
                submitOrNext = true;
               // changeRandomFlags(null);
            }

            // changeRandomFlags(view);
        } else if (dashedString.indexOf('-') == 1) {
            Log.d("gg", "guess again" + " count is: " + count);
            //  count++;
            if (badGuess >= 3) {
                Log.d("gg", "3 guesses are up!" + " count is: " + count);
                if (submitOrNext == true) {
                    nextButton.setText("Next");
                    changeRandomFlags(null);
                    correctMessage("");
                    submitOrNext = false;
                } else {
                    nextButton.setText("Submit");
                    submitOrNext = true;
                }
            }
        }
    }

    public void correctMessage(String message) {
        TextView correctMessage = (TextView) findViewById(R.id.correctMessage);
        TextView incorrectMessage = (TextView) findViewById(R.id.incorrectMessage);
        correctMessage.setText(message);
        incorrectMessage.setText("");
        correctMessage.setTextColor(Color.GREEN);
    }

    public void wrongMessage(String message) {
        TextView incorrectMessage = (TextView) findViewById(R.id.incorrectMessage);
        TextView correctMessage = (TextView) findViewById(R.id.correctMessage);
        incorrectMessage.setText(message);
        correctMessage.setText("");
        incorrectMessage.setTextColor(Color.RED);

    }

    public void displayCorrectCountryName() {
        TextView correctCountryName = (TextView) findViewById(R.id.correctCountryName);
        try {
            correctCountryName.setText(jsonObject.getString(fullName));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        correctCountryName.setTextColor(Color.BLUE);

    }
}

