package com.example.skmall;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skmall.Models.GroceryItem;
import com.example.skmall.Models.Order;

import java.util.ArrayList;


public class CartFirstFragment extends Fragment implements CartRecAdapter.GetTotalPrice, CartRecAdapter.DeleteCartItem {
    private static final String TAG = "CartFirstFragment";
    private RecyclerView RecyclerView;
    private Button btnNext;
    private TextView txtSum, txtNoItem;
    private CartRecAdapter adapter;
    private Utils util;

    private double totalPrice=0;
    private ArrayList<Integer> items;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_cart_layout, container, false);
        initView(view);
        items=new ArrayList<>();
        adapter = new CartRecAdapter(this);
        RecyclerView.setAdapter(adapter);
        RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        util = new Utils(getActivity());

        ArrayList<Integer> cartItemsIds = util.getCartItems();
        if (cartItemsIds != null) {
            ArrayList<GroceryItem> cartItems = util.getItemsByID(cartItemsIds);
            if (cartItems.size() == 0) {
                btnNext.setVisibility(View.GONE);
                btnNext.setEnabled(false);
                RecyclerView.setVisibility(View.GONE);
                txtNoItem.setVisibility(View.VISIBLE);
            } else {
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setEnabled(true);
                RecyclerView.setVisibility(View.VISIBLE);
                txtNoItem.setVisibility(View.GONE);
            }
            items=cartItemsIds;
            adapter.setCartItem(cartItems);
        }


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order order=new Order();
                order.setTotalPrice(totalPrice);
                order.setItems(items);
                Bundle bundle=new Bundle();
                bundle.putParcelable("order",order);
                Cart2fragment cart2fragment=new Cart2fragment();
                cart2fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.in,R.anim.out).replace(R.id.fragment_container,cart2fragment).commit();

            }
        });
        return view;
    }

    private void initView(View view) {
        Log.d(TAG, "initView: started");
        RecyclerView = view.findViewById(R.id.RecyclerView);
        btnNext = view.findViewById(R.id.btnNext);
        txtSum = view.findViewById(R.id.txtSum);
        txtNoItem = view.findViewById(R.id.txtNoItem);

    }

    @Override
    public void OnGettingTotalPrice(double price) {
        Log.d(TAG, "OnGettingTotalPrice: started");
        txtSum.setText(String.valueOf(price));
        this.totalPrice=price;
    }

    @Override
    public void onDeletingResult(GroceryItem item) {
        Log.d(TAG, "onDeletingResult: started");
        ArrayList<Integer> itemids = new ArrayList<>();
        itemids.add(item.getId());
        ArrayList<GroceryItem> newItems = util.getItemsByID(itemids);
        if (newItems.size() > 0) {
            ArrayList<Integer> newitemsids = util.deleteCartItem(newItems.get(0));

            if (newitemsids.size() == 0) {
                btnNext.setVisibility(View.GONE);
                btnNext.setEnabled(false);
                RecyclerView.setVisibility(View.GONE);
                txtNoItem.setVisibility(View.VISIBLE);
            } else {
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setEnabled(true);
                RecyclerView.setVisibility(View.VISIBLE);
                txtNoItem.setVisibility(View.GONE);
            }
           ArrayList<GroceryItem> newItems1=util.getItemsByID(itemids);
            this.items=newitemsids;
            adapter.setCartItem(newItems1);
        }

    }
}
