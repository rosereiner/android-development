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

public class ShowAvailabilityAdapter extends RecyclerView.Adapter<ShowAvailabilityAdapter.AvailableFoodViewHolder> {
    ArrayList<FoodDatabaseHelper.Food> availabilityFoodList;
    private LayoutInflater mInflater2;
    FoodDatabaseHelper foodHelper;

    public ShowAvailabilityAdapter(Context context, ArrayList<FoodDatabaseHelper.Food> foodList) {
        super();
        availabilityFoodList = foodList;
        mInflater2 = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AvailableFoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView2 = mInflater2.inflate(R.layout.available_foodlist_item_view,
                viewGroup, false);
        return new AvailableFoodViewHolder(mItemView2, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final AvailableFoodViewHolder availableFoodViewHolder, final int i) {
        final TextView availableFoodName = availableFoodViewHolder.itemView.findViewById(R.id.activityAvail_ShowName);
        TextView availableFoodWeight = availableFoodViewHolder.itemView.findViewById(R.id.activityAvail_showWeight);
        TextView availableFoodPrice = availableFoodViewHolder.itemView.findViewById(R.id.activityAvail_showPrice);
        TextView availableFoodDescription = availableFoodViewHolder.itemView.findViewById(R.id.activityAvail_showDescription);

        final CheckBox availabilityCheckBox = availableFoodViewHolder.itemView.findViewById(R.id.activityAvailability_checkbox);


        //if (availabilityFoodList.get(i).getInKitchen() == true) {
        availableFoodName.setText(availabilityFoodList.get(i).getName());
        availableFoodWeight.setText(Float.toString(availabilityFoodList.get(i).getWeight()));
        availableFoodPrice.setText(Float.toString(availabilityFoodList.get(i).getPrice()));
        availableFoodDescription.setText(availabilityFoodList.get(i).getDescription());
        availabilityCheckBox.setChecked(true);
        // }
        Log.d("gg", "displaying the available item " + availabilityFoodList.get(i).getName());


        if (availabilityFoodList.get(i).getInKitchen()) {
            availabilityCheckBox.setChecked(true);
        }
        availabilityCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //    Log.d("gg", "id is: " + EditFoodList.get(i).get_id() + "food name is: " + availableFoodName.getText().toString()
                //           + "is it checked" + availabilityCheckBox.get(i).getInKitchen());

                if (buttonView.isChecked()) {
                    availabilityFoodList.get(i).setInKitchen(true); //set in kitchen to be true if the checkbox is checked
                    Log.d("gg", "in kitchen is suppose to be true. It is actually set to: " + availabilityFoodList.get(i).getInKitchen());
                } else {
                    availabilityFoodList.get(i).setInKitchen(false); //set in kitchen to be false if the checkbox is unchecked
                    Log.d("gg", "in kitchen is set to: " + availabilityFoodList.get(i).getInKitchen());
                    Log.d("gg", "in kitchen is suppose to be false. It is actually set to: " + availabilityFoodList.get(i).getInKitchen());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return availabilityFoodList.size();
    }


    public class AvailableFoodViewHolder extends RecyclerView.ViewHolder {
        ShowAvailabilityAdapter fAdapter2;

        public AvailableFoodViewHolder(@NonNull View itemView, ShowAvailabilityAdapter adapter) {
            super(itemView);
            this.fAdapter2 = adapter;
        }
    }

}
