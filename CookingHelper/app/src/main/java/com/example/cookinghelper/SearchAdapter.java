package com.example.cookinghelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.NameViewHolder>{
    ArrayList<FoodDatabaseHelper.Food> fList;
    private LayoutInflater mInflater;
    TextView nameFound;

    public SearchAdapter(Context context, ArrayList<FoodDatabaseHelper.Food> foodList) {
        super();
        fList = foodList;
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.activity_search_adapter,
                viewGroup, false);
        return new NameViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder nameViewHolder, int i) {

        nameFound = nameViewHolder.itemView.findViewById(R.id.foodNameFound);

        nameFound.setText(fList.get(i).getName());



    }

    @Override
    public int getItemCount() {
        return fList.size();
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {
        SearchAdapter fAdapter;

        public NameViewHolder(@NonNull View itemView, SearchAdapter adapter) {
            super(itemView);
            this.fAdapter = adapter;
        }
    }
}
