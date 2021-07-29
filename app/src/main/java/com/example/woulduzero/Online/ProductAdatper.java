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

import java.util.ArrayList;

public class ProductAdatper extends RecyclerView.Adapter<ProductAdatper.ProductViewHolder> {

    private ArrayList<Product> arrayList;
    private Context context;

    public ProductAdatper(ArrayList<Product> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_online_product, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        //product link를 어떻게 가져올지..
        Product item = arrayList.get(position);

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImg())
                .into(holder.img_view);
        holder.product_name.setText(arrayList.get(position).getName());
        holder.product_brand.setText(arrayList.get(position).getSiteName());
        holder.product_price.setText(arrayList.get(position).getPrice());
        holder.product_uppercategory.setText(arrayList.get(position).getMain_category());
        holder.product_lowercategory.setText(arrayList.get(position).getSub_category());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView img_view;
        TextView product_name;
        TextView product_brand;
        TextView product_price;
        TextView product_uppercategory;
        TextView product_lowercategory;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            this.img_view = itemView.findViewById(R.id.img_online_product);
            this.product_name = itemView.findViewById(R.id.text_product_name);
            this.product_brand = itemView.findViewById(R.id.text_product_brand);
            this.product_price = itemView.findViewById(R.id.text_product_price);
            this.product_lowercategory = itemView.findViewById(R.id.product_subcategory);
            this.product_uppercategory = itemView.findViewById(R.id.product_maincategory);


        }
    }
}
