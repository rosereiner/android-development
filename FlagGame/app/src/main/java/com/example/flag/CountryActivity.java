package com.example.flag;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CountryActivity extends AppCompatActivity {
    JSONObject jsonObject;
    String nameOfFlagShown;
    String nameOfFlagChosenFromList;
    String name;
    TextView displayCorrect;
    TextView displayIncorrect;
    TextView displayCountryName;
    boolean submitOrNext;
    android.os.Handler customHandler;
    int timeCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        displayCorrect = (TextView) findViewById(R.id.correctText);
        displayIncorrect = (TextView) findViewById(R.id.incorrectText);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        ImageView v1 = findViewById(R.id.imageView2);
        // v1.setImageResource(R.drawable.ad);
        String resource = "ad";
        nameOfFlagShown = resource;
        //changeRandomFlags(v1);
        int resource_id = getResources().getIdentifier(resource, "drawable", "com.example.flag");
        v1.setImageResource(resource_id);


        //Now read the json file
        String jsonStr = readTextFileFromRawResource(getApplicationContext(), R.raw.countries);
        //Log.d("gg", "reading json file" + jsonStr);

        doJson(jsonStr);
        Spinner();
        //loadListWithJson();
        changeRandomFlags(null); //added this in so it would be a different random flag every time the activity opens


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
            TextView tvTimer = (TextView) findViewById(R.id.tv_Timer2);
            tvTimer.setText("Seconds " + timeCount++);

            customHandler.postDelayed(this, 1000); //1000 millis = 1 second
        }
    };

    // do country spinner
    // from https://www.mkyong.com/android/android-spinner-drop-down-list-example/
    public void Spinner() {
        Spinner countrySpinner = (Spinner) findViewById(R.id.drop_down_spinner);
        List<String> list = new ArrayList<String>();
        loadListWithJson(list, jsonObject); //load the dropdown list with the countries in the json file
        //create an ArrayAdapter
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(dataAdapter);

        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                try {
                    String spinnerCountry = parent.getItemAtPosition(i).toString();
                    name = (String) jsonObject.names().get(i);
                    nameOfFlagChosenFromList = name;
                    Log.d("gg", "guessed: " + name +
                            " full name: " + spinnerCountry);
                } catch (Exception e) {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        })
        ;
    }

    // loads list with json values
    void loadListWithJson(List<String> listToLoad, JSONObject json) {
        try {
            for (int i = 0; i < json.length(); i++) {
                // get name of json at idx
                String jsonName = (String) json.names().get(i);
                // get value from name at idx
                listToLoad.add(json.getString(jsonName));
                Log.d("gg", "going through names in json file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //
    void doJson(String jsonStr) {

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

    // Got code from
    // from https://stackoverflow.com/questions/4087674/android-read-text-raw-resource-file/4087865
    public static String readTextFileFromRawResource(Context context, int id) {
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

    //Getting new random flags
    public void changeRandomFlags(View v) {
        try {

            int randomNumber = random.nextInt(257); //generates a random number between 0 and 256
            String jsonName = (String) jsonObject.names().get(randomNumber);
            nameOfFlagShown = jsonName;

            ImageView v1 = findViewById(R.id.imageView2);
            //v1.setImageResource(R.drawable.ad);
            String resource = jsonName.toLowerCase();
            int resource_id = getResources().getIdentifier(resource, "drawable", "com.example.flag");
            v1.setImageResource(resource_id);

        } catch (Exception e) {
        }
    }

    //Submit Button
    public void compareFlagAndGuess(View view) {
        // make an if statement to see if right or wrong
        try {
            Log.d("gg", "at compareFlagAndGuess button - nameOfFlagShown : " + nameOfFlagShown.toLowerCase() + " name of f" +
                    " flag chosen: " + nameOfFlagChosenFromList.toLowerCase() + "full name: " + jsonObject.getString(nameOfFlagShown));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Button nextButton = (Button) findViewById(R.id.Submit_Button);

        if (submitOrNext == true) { //if the boolean is true
            nextButton.setText("Next"); //make the text of the button say next
            submitOrNext = false; // change the boolean set to false for next time
        } else {
            nextButton.setText("Submit");
            submitOrNext = true;
        }

        if (nameOfFlagShown.toLowerCase().equals(nameOfFlagChosenFromList.toLowerCase())) {
            displayTextCorrect("Correct!");

        } else {
            displayTextWrong("Incorrect");
            displayCorrectCountryName(); //display correct name if its incorrect
        }
        if (submitOrNext == true) {
            changeRandomFlags(null); //if the boolean is true and the button is set to next, change the flags
            displayCountryName.setText(""); //make the names disappear for next round
            displayTextWrong("");

        }
    }

    //Displays the characteristics for the message for correct
    public void displayTextCorrect(String message) {
        displayCorrect = (TextView) findViewById(R.id.correctText);
        displayIncorrect = (TextView) findViewById(R.id.incorrectText);
        displayCorrect.setText(message);
        displayIncorrect.setText("");
        displayCorrect.setTextColor(Color.GREEN);

    }

    //Displays the characteristics for the message incorrect
    public void displayTextWrong(String message) {
        displayCorrect = (TextView) findViewById(R.id.correctText);
        displayIncorrect = (TextView) findViewById(R.id.incorrectText);
        displayIncorrect.setText(message);
        displayCorrect.setText("");
        displayIncorrect.setTextColor(Color.RED);

    }

    //Displays the characteristics for the message of the correct country name
    public void displayCorrectCountryName() {
        displayCountryName = (TextView) findViewById(R.id.correctAnswer);
        try {
            displayCountryName.setText(jsonObject.getString(nameOfFlagShown));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        displayCountryName.setTextColor(Color.BLUE);
    }

}



