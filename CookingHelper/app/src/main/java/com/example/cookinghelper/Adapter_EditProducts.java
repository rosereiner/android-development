package com.example.cookinghelper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_EditProducts extends RecyclerView.Adapter<Adapter_EditProducts.EditFoodViewHolder>{
    ArrayList<FoodDatabaseHelper.Food> editFoodList;
    private LayoutInflater mInflater3;
    FoodDatabaseHelper foodHelper;
    private Context mContext;

    public Adapter_EditProducts(Context context, ArrayList<FoodDatabaseHelper.Food> foodList) {
        super();
        editFoodList = foodList;
        mInflater3 = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public EditFoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView3 = mInflater3.inflate(R.layout.activity_adapter__edit_products,
                viewGroup, false);
        return new EditFoodViewHolder(mItemView3, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final EditFoodViewHolder editFoodViewHolder, final int i) {

       final TextView displayNamesOfAllProducts = editFoodViewHolder.itemView.findViewById(R.id.activityEdit_DisplayName);

       displayNamesOfAllProducts.setText(editFoodList.get(i).getName());

       Log.d("gg","displaying all products" + editFoodList.get(i).getName());


      //https://www.youtube.com/watch?v=ZXoGG2XTjzU

        TextView edit = editFoodViewHolder.itemView.findViewById(R.id.activityEdit_DisplayName);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("gg","item clicked on " + editFoodList.get(i).getName());
                Intent intent = new Intent(mContext, ProductToEdit.class);

                intent.putExtra("Name of Food", editFoodList.get(i).getName()); //put extra to know what is clicked on
                intent.putExtra("Weight of Food", Float.toString(editFoodList.get(i).getWeight())); //putting the weight as an extra and convert to string
                intent.putExtra("Price of Food", Float.toString(editFoodList.get(i).getPrice())); //convert float to string
                intent.putExtra("Description of Food", editFoodList.get(i).getDescription());

                intent.putExtra("In Kitchen", editFoodList.get(i).getInKitchen());

                intent.putExtra("id_AsInt", editFoodList.get(i).get_id());


                mContext.startActivity(intent); //need mContext because in an adapter and doesn't known context so have to reference it
            }
        });

    }

    @Override
    public int getItemCount() {
        return editFoodList.size();
    }

    public class EditFoodViewHolder extends RecyclerView.ViewHolder {
        Adapter_EditProducts fAdapter3;

        public EditFoodViewHolder(@NonNull View itemView, Adapter_EditProducts adapter) {
            super(itemView);
            this.fAdapter3 = adapter;
        }
    }


}
