package com.example.woulduzero.Online;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        return new SliderViewHolder(rView);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        ImageSlide item = sliderItems.get(position);

        Glide.with(holder.itemView)
                .load(sliderItems.get(position).getImg())
                .into(holder.img_slide);
        holder.text_name.setText(sliderItems.get(position).getName());
        holder.text_siteName.setText(sliderItems.get(position).getSiteName());
        holder.text_price.setText(sliderItems.get(position).getPrice());
        holder.text_maincategory.setText(sliderItems.get(position).getMain_category());
        holder.text_subcategory.setText(sliderItems.get(position).getSub_category());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return (sliderItems != null ? sliderItems.size() : 0);
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        ImageView img_slide;
        TextView text_siteName;
        TextView text_name;
        TextView text_price;
        TextView text_maincategory;
        TextView text_subcategory;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img_slide = itemView.findViewById(R.id.img_online_slide);
            this.text_name = itemView.findViewById(R.id.text_slide_name);
            this.text_siteName = itemView.findViewById(R.id.text_slide_siteName);
            this.text_price = itemView.findViewById(R.id.text_slide_price);
            this.text_maincategory = itemView.findViewById(R.id.text_maincategory);
            this.text_subcategory = itemView.findViewById(R.id.text_subcategory);
        }

    }

}
