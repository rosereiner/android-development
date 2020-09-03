package com.example.cookinghelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class EditProducts extends AppCompatActivity {
    Adapter_EditProducts mAdapter;
    FoodDatabaseHelper foodHelper;
    ArrayList<FoodDatabaseHelper.Food> displayFoodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_products);


        foodHelper = new FoodDatabaseHelper(getApplicationContext());
       // foodHelper = new Adapter_EditProducts(getApplicationContext());
        displayFoodItems = foodHelper.getFoodItems();


        //Got code from tutorial...
        //Make a new recyclerview
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activityEdit_RecyclerView);
        //Create an adapter
        mAdapter = new Adapter_EditProducts(getApplicationContext(), displayFoodItems);
        //Connect adapter with RecyclerView
        recyclerView.setAdapter(mAdapter);
        //Give the RecyclerView a default layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}
