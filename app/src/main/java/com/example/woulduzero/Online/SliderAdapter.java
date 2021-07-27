package com.example.woulduzero.Online;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.woulduzero.R;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private static final String TAG = "SliderAdapter";

    private List<ImageSlide> sliderItems;
    private Context context;

    public SliderAdapter(List<ImageSlide> sliderItems, Context context) {
        this.sliderItems = sliderItems;
        this.context = context;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_slide, parent, false);
        SliderViewHolder sHolder = new SliderViewHolder(rView);

        return sHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder sHolder, int position) {
        Glide.with(sHolder.itemView)
                .load(sliderItems.get(position).getImg_url())
                .into(sHolder.img_slide);
    }

    @Override
    public int getItemCount() {
        return (sliderItems != null ? sliderItems.size() : 0);
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        ImageView img_slide;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img_slide = itemView.findViewById(R.id.img_online_slide);
        }

    }

}
