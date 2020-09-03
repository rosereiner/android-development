package com.example.cookinghelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;


public class DisplayProducts extends AppCompatActivity {
    FoodListAdapter mAdapter;
    CheckBox checkBox;
    boolean check;
    FoodDatabaseHelper foodHelper;
    ArrayList<FoodDatabaseHelper.Food> displayFoodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);


        foodHelper = new FoodDatabaseHelper(getApplicationContext());
        if(getIntent().hasExtra("name_to_search")) {
            String nameSearched = getIntent().getStringExtra("name_to_search");
            displayFoodItems = foodHelper.searchingForFoodNameItemByName(nameSearched);
        }else{
            displayFoodItems = foodHelper.getFoodItems();
        }


        //Got code from tutorial...
        //Make a new recyclerview
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recy_showFoods);
        //Create an adapter
        mAdapter = new FoodListAdapter(getApplicationContext(), displayFoodItems);
        //Connect adapter with RecyclerView
        recyclerView.setAdapter(mAdapter);
        //Give the RecyclerView a default layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void addToKitchen(View view) {

        //go through all the elements in the arraylist of displayFoodItems to see what is set to be in kitchen or not
        for(int i = 0; i < displayFoodItems.size(); i++){
            //displayFoodItems.get(i).getInKitchen();
            foodHelper.addInKitchen(displayFoodItems.get(i));

            //Display message that the products are added into the kitchen
            Toast toast = Toast.makeText(this, R.string.addInKitchen, Toast.LENGTH_LONG);
            toast.show();

            Log.d("gg", "food now updated in the kitchen is: " + displayFoodItems.get(i).getName() +
                    "food in kitchen is set to: " + displayFoodItems.get(i).getInKitchen());
        }

    }
}
