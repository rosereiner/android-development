package com.example.cookinghelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
//import static com.example.cookinghelper.Constants.*;

public class FoodDatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;

    private static final String DATABASE_NAME = "food.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "foodTable";

    /* Helper object for food database*/
    public FoodDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("gg", "helper object created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String foodTableCreateSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, weight INTEGER, price DECIMAL, description VARCHAR, inKitchen BOOLEAN)";

        Log.d("gg", "food table: " + foodTableCreateSQL);
        db.execSQL(foodTableCreateSQL);

       // db.execSQL("CREATE TABLE" + DATABASE_NAME + " (" + _ID + "name";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //http://mrbool.com/how-to-insert-data-into-a-sqlite-database-in-android/28895
    public void addFoodItem(Food foodItem){
       // SQLiteDatabase db = this.getWritableDatabase();
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //don't need id to add food item because it's autoincrement
        values.put("name", foodItem.getName());
        values.put("weight", foodItem.getWeight());
        values.put("price", foodItem.getPrice());
        values.put("description", foodItem.getDescription());

        db.insertOrThrow(TABLE_NAME, null, values); //from lecture notes

    }
    public void addInKitchen(Food foodItem){
        db = this.getWritableDatabase();

        String where = "_id =?"; //the columns the update
       // String[] makeInKitchen = new String[]{Boolean.toString(kitchen)};
        String[] makeInKitchen = new String[]{Integer.toString(foodItem._id)};
        ContentValues values = new ContentValues();

       /* values.put("name", foodItem.getName());
        values.put("weight", foodItem.getWeight());
        values.put("price", foodItem.getPrice());
        values.put("description", foodItem.getDescription());*/
        values.put("inKitchen", foodItem.getInKitchen());

        db.update(TABLE_NAME, values, where, makeInKitchen);
        //  db.replace(TABLE_NAME, null, values);

    }

    public void updateFoodItem(Food foodItem){
        db = this.getWritableDatabase();

        String where = "_id =?"; //the columns the update
        // String[] makeInKitchen = new String[]{Boolean.toString(kitchen)};
        String[] infoThatReplacesQuestionMark = new String[]{Integer.toString(foodItem._id)};
        ContentValues values = new ContentValues();

        values.put("name", foodItem.getName());
        values.put("weight", foodItem.getWeight());
        values.put("price", foodItem.getPrice());
        values.put("description", foodItem.getDescription());
        values.put("inKitchen", foodItem.getInKitchen());

        // Update tablename set name = potatos,weight=5etc. where id = foodItem.get_Id() --writing it out in sql
        db.update(TABLE_NAME, values, where, infoThatReplacesQuestionMark);

    }

    //https://stackoverflow.com/questions/7510219/deleting-row-in-sqlite-in-android
    public void deleteFoodItem(Food foodItem){
        String whereClaus = "_id =?";
        //String whereArgs = foodItem._id;
        //String deleteName = "Gummy Bears";
        SQLiteDatabase db = this.getWritableDatabase();
        String[] foodItemToString = new String[]{Integer.toString(foodItem._id)};
        db.delete(TABLE_NAME, whereClaus, foodItemToString);
      //  db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE "+ "='"+deleteName+"'");
        db.close();
    }
    public void deleteAllItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null, null);
    }

    public ArrayList<Food> getFoodItems() {
        ArrayList<Food> foodList = new ArrayList<Food>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                Food newFoodItem = new Food(cursor.getInt(0),  cursor.getString(1),
                        cursor.getFloat(2),  cursor.getFloat(3),  cursor.getString(4));

                if(cursor.getInt(5) == 0){
                    newFoodItem.setInKitchen(false);
                }else{
                    newFoodItem.setInKitchen(true);
                }

             //   newFoodItem.setInKitchen(cursor.getInt());
                Log.d("gg", "seeing the column inkitchen is " + Integer.toString(cursor.getInt(5)));

                foodList.add(newFoodItem);
                Log.d("gg", "get id: " + newFoodItem.get_id() +" new food item: " + newFoodItem.getName());
            }
            while (cursor.moveToNext());
        } // return contact list return wordList; }

        return foodList;
    }

    public ArrayList<Food> getInKitchenItems(){
        ArrayList<Food> foodList = new ArrayList<Food>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE inKitchen = 1";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                Food newFoodItem = new Food(cursor.getInt(0),  cursor.getString(1),
                        cursor.getFloat(2),  cursor.getFloat(3),  cursor.getString(4));

                if(cursor.getInt(5) == 0){
                    newFoodItem.setInKitchen(false);
                }else{
                    newFoodItem.setInKitchen(true);
                }

                //   newFoodItem.setInKitchen(cursor.getInt());
                Log.d("gg", "seeing the column inkitchen is " + Integer.toString(cursor.getInt(5)));

                foodList.add(newFoodItem);
                Log.d("gg", "get id: " + newFoodItem.get_id() +" new food item: " + newFoodItem.getName());
            }
            while (cursor.moveToNext());
        } // return contact list return wordList; }

        return foodList;
    }
    public ArrayList<Food> getFoodNames(){
        ArrayList<Food> foodList = new ArrayList<Food>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE name =?" ;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                Food newFoodItem = new Food(cursor.getInt(0),  cursor.getString(1),
                        cursor.getFloat(2),  cursor.getFloat(3),  cursor.getString(4));

                if(cursor.getInt(5) == 0){
                    newFoodItem.setInKitchen(false);
                }else{
                    newFoodItem.setInKitchen(true);
                }

                //   newFoodItem.setInKitchen(cursor.getInt());
                Log.d("gg", "seeing the column inkitchen is " + Integer.toString(cursor.getInt(5)));

                foodList.add(newFoodItem);
                Log.d("gg", "get id: " + newFoodItem.get_id() +" new food item: " + newFoodItem.getName());
            }
            while (cursor.moveToNext());
        } // return contact list return wordList; }

        return foodList;
    }
    public ArrayList<Food> searchingForFoodNameItemByName(String foodName){
        ArrayList<Food> foodList = new ArrayList<Food>();
        String[] foodNameArgument = new String[]{foodName.toString()};
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE name =?";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, foodNameArgument);
        //String[] foodItemToString = new String[]{Integer.toString(foodItem._id)}
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                Food newFoodItem = new Food(cursor.getInt(0),  cursor.getString(1),
                        cursor.getFloat(2),  cursor.getFloat(3),  cursor.getString(4));

                if(cursor.getInt(5) == 0){
                    newFoodItem.setInKitchen(false);
                }else{
                    newFoodItem.setInKitchen(true);
                }

                //   newFoodItem.setInKitchen(cursor.getInt());
                Log.d("gg", "seeing the name " + newFoodItem.getName());

                foodList.add(newFoodItem);
                Log.d("gg", "get id: " + newFoodItem.get_id() +" new food item: " + newFoodItem.getName());
            }
            while (cursor.moveToNext());
        } // return contact list return wordList; }

        return foodList;
    }

    public static class Food{
        private String name;
        private float weight;
        private float price;
        private String description;
        private int _id;
        private boolean inKitchen;

        public Food(int _id, String name, float weight, float price, String description){
            this.set_id(_id);
            this.setName(name);
            this.setWeight(weight);
            this.setPrice(price);
            this.setDescription(description);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public boolean getInKitchen(){
            return inKitchen;
        }
        public void setInKitchen(boolean inKitchen){
            this.inKitchen = inKitchen;
        }

    }
}
