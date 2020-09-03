package com.example.cookinghelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class RegisterProducts extends AppCompatActivity {
    TextView userInput_Name;
    TextView userInput_Weight;
    TextView userInput_Price;
    TextView userInput_Description;

    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_products);


        userInput_Name = (TextView) findViewById(R.id.edit_foodName);
        userInput_Weight = (TextView) findViewById(R.id.edit_Weight);
        userInput_Price = (TextView) findViewById(R.id.edit_Price);
        userInput_Description = (TextView) findViewById(R.id.edit_description);

    }

    public void saveProduct(View view) {

        FoodDatabaseHelper foodHelper = new FoodDatabaseHelper(getApplicationContext());
        //https://stackoverflow.com/questions/10735679/how-to-convert-string-into-float-value-in-android/10735754
        String sInputedName = userInput_Name.getText().toString();
        String sInputedDescription = userInput_Description.getText().toString();

        String sInputedWeight = userInput_Weight.getText().toString();
        Float fInputedWeight = Float.parseFloat(sInputedWeight);

        String sInputedPrice = userInput_Price.getText().toString();
        Float fInputedPrice = Float.parseFloat(sInputedPrice);


        // foodHelper.addFoodItem(userInput_Name, userInput_Weight,userInput_Price, userInput_Description);
        // int _id = -1;
        //id is -1 because it's autoincrement
        foodHelper.addFoodItem(new FoodDatabaseHelper.Food(-1, sInputedName, fInputedWeight, fInputedPrice, sInputedDescription));

        Toast toast = Toast.makeText(this, R.string.registerProducts, Toast.LENGTH_SHORT);
        toast.show();

        userInput_Name.setText("");
        userInput_Weight.setText("");
        userInput_Price.setText("");
        userInput_Description.setText("");




    }

}

