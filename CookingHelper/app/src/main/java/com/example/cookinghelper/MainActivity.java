package com.example.cookinghelper;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRegister = (Button)findViewById(R.id.registerProductButton);
        Button btnDisplay = (Button)findViewById(R.id.displayProductsButton);
        Button btnAvailability = (Button)findViewById(R.id.availabilityButton);
        Button btnEdit = (Button)findViewById(R.id.editProductsButton);
        Button btnSearch = (Button)findViewById(R.id.searchButton);
        Button btnRecipes = (Button)findViewById(R.id.recipesButto);

        //New intent for the activity Register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterProducts.class);
                startActivity(intent);
            }
        });

        //New intent for the activity Display
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayProducts.class);
                startActivity(intent);
            }
        });

        //New intent for the activity Availability
        btnAvailability.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Availability.class);
                startActivity(intent);
            }
        });

        //New intent for the activity Edit
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditProducts.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);
            }
        });




        //adding food items
        FoodDatabaseHelper foodHelper = new FoodDatabaseHelper(getApplicationContext());
        //deleting all items in the table
      //  foodHelper.deleteAllItems();

       // foodHelper.addFoodItem(new FoodDatabaseHelper.Food(0, "Gummy Bears", 0.5f, 2.00f, "chewy gummys in shape of bears"));
        //foodHelper.addFoodItem(new FoodDatabaseHelper.Food(1, "Herseys Kisses", 1.0f, 2.50f, "chocolate kiss"));


       /* ArrayList<FoodDatabaseHelper.Food> displayFoodItems = foodHelper.getFoodItems();
        foodHelper.deleteFoodItem(displayFoodItems.get(0));
        Log.d("gg", "deleted" + " item: " + displayFoodItems.get(0).getName());*/


    }

    public void displayProducts(View view) {
    }


    public interface Constants extends BaseColumns {
        public static final String TABLE_NAME = "food";
        //Columns in FoodDatabase
        public static final String NAME = "name";
    }
}
