/**
 * https://www.youtube.com/watch?v=Mc0XT58A1Z4
 *
 * Recycler view item click - https://www.youtube.com/watch?v=7GPUpvcU1FE&t=2s
 */

package com.example.androidjava.FitnessTracker.Views;

import android.content.Context;
import com.example.androidjava.FitnessTracker.Models.*;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidjava.R;

import java.util.ArrayList;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<DayDataMessage> dayDataList;

    public HistoryRecyclerViewAdapter(Context context, ArrayList<DayDataMessage> dayDataList) {
        this.context = context;
        this.dayDataList = dayDataList;
    }

    @NonNull
    @Override
    public HistoryRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout (Give a look to the rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_day, parent, false);
        return new HistoryRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryRecyclerViewAdapter.MyViewHolder holder, int position) {
        // assigning values to the views in the item_day.xml
        // based on the position of the recycler view

        holder.tvDate.setText(dayDataList.get(position).getDatum().toString());
        holder.tvSteps.setText(dayDataList.get(position).getSteps());
        holder.tvDistance.setText((int) dayDataList.get(position).getDistance());
        holder.tvCalories.setText((int) dayDataList.get(position).getCalories());
    }

    @Override
    public int getItemCount() {
        // Get number of items to be displayed
        return dayDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // Grab views from item_day.xml
        // Like onCreate

        TextView tvDate, tvSteps, tvDistance, tvCalories;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.rvDayDate);
            tvSteps = itemView.findViewById(R.id.rvDaySteps);
            tvDistance = itemView.findViewById(R.id.rvDayDistance);
            tvCalories = itemView.findViewById(R.id.rvDayCalories);


        }
    }
}
