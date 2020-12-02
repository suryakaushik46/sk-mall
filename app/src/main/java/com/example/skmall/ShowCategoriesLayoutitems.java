package com.example.skmall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.skmall.Models.GroceryItem;

import java.util.ArrayList;

public class ShowCategoriesLayoutitems extends AppCompatActivity {
    private static final String TAG = "ShowCategoriesLayoutite";
    private TextView txtCategory;
    private RecyclerView RecyclerView;

    private GroceryItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_categories_layoutitems);
        initViews();
        adapter=new GroceryItemAdapter(this);
        RecyclerView.setAdapter(adapter);
        RecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        try {
            Intent intent=getIntent();
            String category=intent.getStringExtra("category");
            Utils util=new Utils(this);
            ArrayList<GroceryItem> items=util.getItemsByCategoryName(category);
            if(items!=null){
                adapter.setItems(items);
            }
            txtCategory.setText(category);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private void initViews(){
        txtCategory=findViewById(R.id.txtCategory);

        RecyclerView=findViewById(R.id.RecyclerView);

    }
}
