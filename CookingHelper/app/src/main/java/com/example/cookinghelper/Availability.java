package com.example.cookinghelper;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Availability extends AppCompatActivity {
    ArrayList<FoodDatabaseHelper.Food> displayFoodItems;
    FoodDatabaseHelper foodHelper;
    ShowAvailabilityAdapter mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);


        foodHelper = new FoodDatabaseHelper(getApplicationContext());
        displayFoodItems = foodHelper.getInKitchenItems();


        //Make new recycler view
        RecyclerView recyclerView2 = (RecyclerView)findViewById(R.id.activityAvailability_RecyclerView);
        //Create an adapter
        mAdapter2 = new ShowAvailabilityAdapter(getApplicationContext(), displayFoodItems);
        //Connect adapter with recycler view
        recyclerView2.setAdapter(mAdapter2);
        //Give the RecyclerView a default layout manager
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

    }

    public void Save(View view){
        //go through all the elements in the arraylist of displayFoodItems to see what is set to be in kitchen or not
        for(int i = 0; i < displayFoodItems.size(); i++){
            //displayFoodItems.get(i).getInKitchen();
            foodHelper.addInKitchen(displayFoodItems.get(i));

            //Display message that the options picked are saved
            Toast toast = Toast.makeText(this, R.string.saveData, Toast.LENGTH_SHORT);
            toast.show();

            Log.d("gg", "food now updated in the kitchen is: " + displayFoodItems.get(i).getName() +
                    "food in kitchen is set to: " + displayFoodItems.get(i).getInKitchen());
        }
    }
}
