package com.example.skmall;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.skmall.Models.GroceryItem;

import java.util.ArrayList;

public class GroceryItemAdapter  extends RecyclerView.Adapter<GroceryItemAdapter.ViewHolder> {
    private static final String TAG = "GroceryItemAdapter";
    private Context context;
    ArrayList<GroceryItem> items=new ArrayList<>();

    public GroceryItemAdapter() {
    }

    public GroceryItemAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_rec_list_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: started");
           holder.itemName.setText(items.get(position).getName());
           holder.itemPrice.setText("Rs"+String.valueOf(items.get(position).getPrice()));
           Glide.with(context).asBitmap().load(items.get(position).getImageUrl()).into(holder.itemImage);

           holder.parent.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent=new Intent(context,GroceryItemActivity.class);
                   intent.putExtra("item",items.get(position));
                   context.startActivity(intent);
               }
           });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView itemImage;
        private TextView itemPrice,itemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            itemImage=itemView.findViewById(R.id.itemImage);
            itemName=itemView.findViewById(R.id.itemName);
            itemPrice=itemView.findViewById(R.id.itemPrice);

        }
    }

    public void setItems(ArrayList<GroceryItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
