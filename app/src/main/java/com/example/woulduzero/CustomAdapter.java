package com.example.woulduzero;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<OfflineShop> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<OfflineShop> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // list_offlineshop에 대한 View 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_offlineshop, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
        OfflineShop item = arrayList.get(position);

        Glide.with(holder.itemView)
                .load(item.getImg1())
                .into(holder.iv_img);

        holder.tv_storename.setText(item.getStore_name());
        holder.tv_address.setText(item.getAddress());
        holder.tv_storetype.setText(item.getStore_type());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), OfflineshopInfo.class);
                intent.putExtra("offlineshop", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public void filterList(ArrayList<OfflineShop> filteredList) {
        arrayList = filteredList;
        notifyDataSetChanged();
    }

    public void setArrayList(ArrayList<OfflineShop> arrayList) {
        this.arrayList = arrayList;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        TextView tv_storename;
        TextView tv_address;
        TextView tv_storetype;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.iv_img = itemView.findViewById(R.id.iv_img);

            this.tv_storename = itemView.findViewById(R.id.tv_storename);
            this.tv_address = itemView.findViewById(R.id.tv_address);
            this.tv_storetype = itemView.findViewById(R.id.tv_storetype);
        }
    }
}
