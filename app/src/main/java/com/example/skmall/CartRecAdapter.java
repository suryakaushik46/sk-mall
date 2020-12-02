package com.example.skmall;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skmall.Models.GroceryItem;

import java.util.ArrayList;

public class CartRecAdapter extends RecyclerView.Adapter<CartRecAdapter.ViewHolder> {
    private ArrayList<GroceryItem> CartItem = new ArrayList<>();
    private static final String TAG = "CartRecAdapter";
    private Context context;

    public interface GetTotalPrice {
        void OnGettingTotalPrice(double price);
    }

    public interface DeleteCartItem {
        void onDeletingResult(GroceryItem item);
    }

    private DeleteCartItem deleteCartItem;
    private GetTotalPrice getTotalPrice;
    private Fragment fragment;

    public CartRecAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public CartRecAdapter(Context context) {
        this.context = context;
    }

    public CartRecAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_rec_view_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder cart: started");
        holder.txtName.setText(CartItem.get(position).getName());
        holder.txtPrice.setText(String.valueOf(CartItem.get(position).getPrice()));
        try {
            deleteCartItem=(DeleteCartItem)fragment;
        }catch (ClassCastException e){
            e.printStackTrace();
        }
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo Dialog for Removing item
                AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
                builder.setTitle("DeleteItem");
                builder.setMessage("Do you remove Item from CArt");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteCartItem.onDeletingResult(CartItem.get(position));
                    }
                });

                builder.create().show();

            }
        });




    }

    @Override
    public int getItemCount() {
        return CartItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, btnDelete, txtPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            txtPrice = itemView.findViewById(R.id.txtPrice);

        }
    }

    private void CalculatePrice() {
        Log.d(TAG, "CalculatePrice:started ");
        try {
            getTotalPrice = (GetTotalPrice) fragment;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        double totalPrice = 0;
        for (GroceryItem item : CartItem) {
            totalPrice = totalPrice + item.getPrice();
        }
        getTotalPrice.OnGettingTotalPrice(totalPrice);
    }

    public void setCartItem(ArrayList<GroceryItem> cartItem) {
        CartItem = cartItem;
        CalculatePrice();
        notifyDataSetChanged();
    }
}
