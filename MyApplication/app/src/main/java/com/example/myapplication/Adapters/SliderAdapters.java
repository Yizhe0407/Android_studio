package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.List;

import com.example.myapplication.Domian.Sliderltems;
import com.example.myapplication.R;
import com.google.android.material.slider.Slider;


public class SliderAdapters extends RecyclerView.Adapter<SliderAdapters.SliderViewHolder> {
    private  List<Sliderltems> sliderItemsList;
    private ViewPager2 viewPager2;
    private Context context;

    public SliderAdapters(List<Sliderltems> sliderItemsList, ViewPager2 viewPager2) {
        this.sliderItemsList = sliderItemsList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderAdapters.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapters.SliderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SliderViewHolder {
    }
}
