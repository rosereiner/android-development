package com.example.cookinghelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductToEdit extends AppCompatActivity {
    FoodDatabaseHelper foodHelper;
    ArrayList<FoodDatabaseHelper.Food> displayFoodItems;
    EditText user_editName;
    EditText user_editWeight;
    EditText user_editPrice;
    EditText user_editDescription;
    CheckBox user_editInKitchen;
    int idFromIntent;
    int idFromIntentAsInteger;
    FoodDatabaseHelper.Food checkingItemForKitchen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_to_edit);

        foodHelper = new FoodDatabaseHelper(getApplicationContext());
        displayFoodItems = foodHelper.getFoodItems();

        getNamesClickedOn(); //calling method to get the intents


        user_editName = (EditText)findViewById(R.id.nameToEdit);
        user_editWeight = (EditText)findViewById(R.id.weightToEdit);
        user_editPrice= (EditText)findViewById(R.id.priceToEdit);
        user_editDescription = (EditText)findViewById(R.id.descriptionToEdit);
        user_editInKitchen = (CheckBox)findViewById(R.id.checkboxInKitchen);

        CheckBox changedCheckBox = user_editInKitchen;
        changedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked() == false){
                    //displayFoodItems.get(idFromIntentAsInteger).setInKitchen(false);
                    checkingItemForKitchen = displayFoodItems.get(idFromIntentAsInteger);
                    checkingItemForKitchen.setInKitchen(false);
                    user_editInKitchen.setChecked(false);
                   // foodHelper.addInKitchen(displayFoodItems.get(idFromIntentAsInteger));
                    Log.d("gg","option 1 checkbox suppose to be false, it is: " + displayFoodItems.get(idFromIntentAsInteger).getInKitchen());
                }else if(buttonView.isChecked() == true){
                   // displayFoodItems.get(idFromIntentAsInteger).setInKitchen(true);
                    checkingItemForKitchen = displayFoodItems.get(idFromIntentAsInteger);
                    checkingItemForKitchen.setInKitchen(true);
                    user_editInKitchen.setChecked(true);
                  //  foodHelper.addInKitchen(displayFoodItems.get(idFromIntentAsInteger));
                    Log.d("gg","option 2 checkbox suppose to be true, it is: " + displayFoodItems.get(idFromIntentAsInteger).getInKitchen());
                }
            }
        });


    }

    public void submitChanges(View view){

        String sChangedName = user_editName.getText().toString();
        String sChangedDescription = user_editDescription.getText().toString();

        String sChangedWeight = user_editWeight.getText().toString();
        Float changedWeightAsFloat = Float.parseFloat(sChangedWeight);

        String sChangedPrice = user_editPrice.getText().toString();
        Float changedPriceAsFloat = Float.parseFloat(sChangedPrice);

        idFromIntentAsInteger = idFromIntent;

        foodHelper.updateFoodItem(new FoodDatabaseHelper.Food(idFromIntentAsInteger, sChangedName, changedWeightAsFloat, changedPriceAsFloat, sChangedDescription));

        //  CheckBox changedCheckBox = user_editInKitchen.isChecked();



        // displayFoodItems.get(idFromIntentAsInteger).setInKitchen(user_editInKitchen.);

/*
            if(user_editInKitchen.isChecked()){
                user_editInKitchen.setChecked(true);
                foodHelper.addInKitchen(displayFoodItems.get(i));
                Log.d("gg", "food now updated in the kitchen is: " + displayFoodItems.get(i).getName() +
                        "food in kitchen is set to: " + displayFoodItems.get(i).getInKitchen());
            }else{
                user_editInKitchen.setChecked(false);
            }*/





            //displayFoodItems.get(i).getInKitchen();
            //foodHelper.addInKitchen(displayFoodItems.get(i));




            //Display message that the options picked are saved
         //   Toast toast = Toast.makeText(this, R.string.saveData, Toast.LENGTH_SHORT);
           // toast.show();

           // Log.d("gg", "food now updated in the kitchen is: " + displayFoodItems.get(i).getName() +
               //     "food in kitchen is set to: " + displayFoodItems.get(i).getInKitchen());








    }

    //https://www.youtube.com/watch?v=ZXoGG2XTjzU
    private void getNamesClickedOn(){
        Log.d("gg", "checking for the names clicked on");
      //  if(getIntent().hasExtra("Name of Food") && getIntent().hasExtra("Weight of Food")){
            Log.d("gg", "found intent extras");

            //Getting the intents
            String name = getIntent().getStringExtra("Name of Food");
            String weight = getIntent().getStringExtra("Weight of Food");
            String price = getIntent().getStringExtra("Price of Food");
            String description = getIntent().getStringExtra("Description of Food");

            //String inKitchen = getIntent().getStringExtra("In Kitchen");
            Boolean inKitchen = getIntent().getBooleanExtra("In Kitchen",false);


            //Getting the id as an int
            idFromIntent = getIntent().getIntExtra("id_AsInt", -1);

            setNameToText(name, weight, price, description, inKitchen); //calling other method to set the name to text box
      //  }
    }

    private void setNameToText(String name, String weight, String price, String description, Boolean inKitchen){
        Log.d("gg","setting name gotten from intent to TextViews");

       // TextView nameToEdit = (TextView)findViewById(R.id.nameToEdit);
        EditText nameToEdit = (EditText)findViewById(R.id.nameToEdit);
        nameToEdit.setText(name);

        EditText weightToEdit = (EditText)findViewById(R.id.weightToEdit);
        weightToEdit.setText(weight);

        EditText priceToEdit = (EditText)findViewById(R.id.priceToEdit);
        priceToEdit.setText(price);

        EditText descriptionToEdit = (EditText)findViewById(R.id.descriptionToEdit);
        descriptionToEdit.setText(description);

        CheckBox changeInKitchen = (CheckBox)findViewById(R.id.checkboxInKitchen);
        changeInKitchen.setChecked(inKitchen);

    }

}
