package com.example.cookinghelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>  {
    ArrayList<FoodDatabaseHelper.Food> fList;
    private LayoutInflater mInflater;

    public FoodListAdapter(Context context, ArrayList<FoodDatabaseHelper.Food> foodList) {
        super();
        fList = foodList;
        mInflater = LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.foodlist_item_view,
                viewGroup, false);
        return new FoodViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder foodViewHolder, final int position) {
        final TextView foodName;
        TextView foodWeight;
        TextView foodPrice;
        TextView foodDescription;
        final CheckBox checkBox;

        foodName = foodViewHolder.itemView.findViewById(R.id.textView_FoodName);
        foodWeight = foodViewHolder.itemView.findViewById(R.id.textView_FoodWeight);
        foodPrice = foodViewHolder.itemView.findViewById(R.id.textView_FoodPrice);
        foodDescription = foodViewHolder.itemView.findViewById(R.id.textView_FoodDescription);
        checkBox = foodViewHolder.itemView.findViewById(R.id.checkBox);


        //Set the text to the ArrayList made
        foodName.setText(fList.get(position).getName());
      //  foodWeight.setText(((Integer.toString(position))(fList.get(position).getWeight()));
        foodWeight.setText((Float.toString(fList.get(position).getWeight()))); //make this toString from a float
        foodPrice.setText((Float.toString(fList.get(position).getPrice()))); //casting the float to a string
        foodDescription.setText(fList.get(position).getDescription());


        if(fList.get(position).getInKitchen()){
            checkBox.setChecked(true);

        }
        //https://www.youtube.com/watch?v=PA4A9IesyCg
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("gg", "id is: " + fList.get(position).get_id() + "food name is: " + foodName.getText().toString()
                + "is it checked" + fList.get(position).getInKitchen());

                if(buttonView.isChecked()){
                    fList.get(position).setInKitchen(true); //set in kitchen to be true if the checkbox is checked
                    Log.d("gg", "in kitchen is suppose to be true. It is actually set to: " + fList.get(position).getInKitchen());
                }else{
                    fList.get(position).setInKitchen(false); //set in kitchen to be false if the checkbox is unchecked
                    Log.d("gg", "in kitchen is set to: " + fList.get(position).getInKitchen());
                    Log.d("gg", "in kitchen is suppose to be false. It is actually set to: " + fList.get(position).getInKitchen());
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return fList.size();
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder {
        FoodListAdapter fAdapter;

        public FoodViewHolder(@NonNull View itemView, FoodListAdapter adapter) {
            super(itemView);
            this.fAdapter = adapter;
        }
    }

}


