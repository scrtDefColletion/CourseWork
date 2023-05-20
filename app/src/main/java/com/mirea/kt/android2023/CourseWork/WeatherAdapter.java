package com.mirea.kt.android2023.CourseWork;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private ArrayList<Weather> weathers;

    public WeatherAdapter(ArrayList<Weather> weathers) {
        this.weathers = weathers;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView degreeView;
        private final TextView weatherStatusView;
        private final ImageView weatherImageView;
        private final TextView regionView;

        public ViewHolder(View view) {
            super(view);
            degreeView = view.findViewById(R.id.tvDegrees);
            weatherStatusView= view.findViewById(R.id.tvWeatherStatus);
            weatherImageView = view.findViewById(R.id.ivWeatherImage);
            regionView = view.findViewById(R.id.tvRegion);
        }
    }

    @NonNull
    @Override
    public WeatherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
