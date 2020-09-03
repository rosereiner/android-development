package com.example.cookinghelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    SearchAdapter mAdapter;
    FoodDatabaseHelper foodHelper;
    ArrayList<FoodDatabaseHelper.Food> displayFoodNames;
    String userInputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



     //   userInputSearch = "Hereseys Kisses";
        Button btnSearch = (Button) findViewById(R.id.Btn_Search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // foodHelper = new FoodDatabaseHelper(getApplicationContext());
                //displayFoodNames = foodHelper.searchingForFoodNameItemByName(userInputSearch);
                EditText search = (EditText) findViewById(R.id.textView_search);
                userInputSearch = search.getText().toString();

                Intent intent = new Intent(getApplicationContext(), DisplayProducts.class);
                intent.putExtra("name_to_search", userInputSearch);
                startActivity(intent);





             /*   //Got code from tutorial...
                //Make a new recyclerview
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Search);
                //Create an adapter
                mAdapter = new SearchAdapter(getApplicationContext(), displayFoodNames);
                //Connect adapter with RecyclerView
                recyclerView.setAdapter(mAdapter);
                //Give the RecyclerView a default layout manager
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));*/
            }
        });

        /*foodHelper = new FoodDatabaseHelper(getApplicationContext());
        displayFoodNames = foodHelper.searchingForFoodNameItemByName(userInputSearch);


        //Got code from tutorial...
        //Make a new recyclerview
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView_Search);
        //Create an adapter
        mAdapter = new SearchAdapter(getApplicationContext(), displayFoodNames);
        //Connect adapter with RecyclerView
        recyclerView.setAdapter(mAdapter);
        //Give the RecyclerView a default layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/

    }

    public void searchForFood(View view) {



    }
}
