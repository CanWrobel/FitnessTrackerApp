

package com.example.androidjava.FitnessTracker.Viewmodels;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidjava.FitnessTracker.Models.Datenbank.DayDataMessage;
import com.example.androidjava.FitnessTracker.Models.Room.DayData;
import com.example.androidjava.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

/*
 * History recycler view adapter implementation
 * based on tutorial by Practical Coding - https://www.youtube.com/watch?v=Mc0XT58A1Z4
 *
 */
public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<DayData> dayDataList;

    //ArrayList<DayDataMessage> dayDataList;

    public HistoryRecyclerViewAdapter(Context context, ArrayList<DayData> dayDataList) {
    //public HistoryRecyclerViewAdapter(Context context, ArrayList<DayDataMessage> dayDataList) {
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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(dayDataList.get(position).getDatum());

        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        double distance = dayDataList.get(position).getDistance();
        distance = Double.parseDouble(decimalFormat.format(distance));

        holder.tvDate.setText(dateString);
        holder.tvSteps.setText("Steps: " + dayDataList.get(position).getSteps());
        holder.tvDistance.setText("Distance: " + distance +" km");
        holder.tvCalories.setText("Calories burned: " + dayDataList.get(position).getCalories());
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