package com.example.skmall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skmall.Models.GroceryItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements ShowAllActivitiesDailog.SelectCatergory {
    private static final String TAG = "SearchActivity";

    private EditText EdtSearchBar;
    private TextView FirstCategory, SecondCategory, ThirdCategory, btnAllCategories;
    private RecyclerView recycleView;
    private BottomNavigationView bottomNavigationView;
    private ImageView BtnSearch;
    private Utils util;

    private GroceryItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        util = new Utils(this);
        initViews();
        initBottomNavigation();
        initThreeTextViews();
         adapter=new GroceryItemAdapter(this);
         recycleView.setAdapter(adapter);
         recycleView.setLayoutManager(new LinearLayoutManager(this));
        BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: started");
                initSearch();
            }
        });
       btnAllCategories.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              ShowAllActivitiesDailog showAllActivitiesDailog=new ShowAllActivitiesDailog();
              showAllActivitiesDailog.show(getSupportFragmentManager(),"all dailog");
           }
       });

       EdtSearchBar.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<GroceryItem> items=util.searchForItem(String.valueOf(s));
               adapter.setItems(items);
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
    }

    private void initThreeTextViews(){
        Log.d(TAG, "initThreeTextViews: started");
        ArrayList<String> categories=util.getThreeCategories();
        switch (categories.size()){
            case 1:
                FirstCategory.setText(categories.get(0));
                SecondCategory.setVisibility(View.GONE);
                ThirdCategory.setVisibility(View.GONE);

                break;
            case 2:
                FirstCategory.setText(categories.get(0));
                SecondCategory.setText(categories.get(1));
                ThirdCategory.setVisibility(View.GONE);

                break;
            case 3:
                FirstCategory.setText(categories.get(0));
                SecondCategory.setText(categories.get(1));
                ThirdCategory.setText(categories.get(2));

                break;
            default:
                SecondCategory.setVisibility(View.GONE);
                ThirdCategory.setVisibility(View.GONE);
                FirstCategory.setVisibility(View.GONE);
                break;
        }

        FirstCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,ShowCategoriesLayoutitems.class);
                intent.putExtra("category",FirstCategory.getText().toString());
                startActivity(intent);
            }
        });
        SecondCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,ShowCategoriesLayoutitems.class);
                intent.putExtra("category",SecondCategory.getText().toString());
                startActivity(intent);
            }
        });
        ThirdCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,ShowCategoriesLayoutitems.class);
                intent.putExtra("category",ThirdCategory.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void initSearch() {
        Log.d(TAG, "initSearch: started");
        String text = EdtSearchBar.getText().toString();
        ArrayList<GroceryItem> items=util.searchForItem(text);
        adapter.setItems(items);
    }

    private void initViews() {
        Log.d(TAG, "initViews: started");
        EdtSearchBar = findViewById(R.id.EdtSearchBar);

        FirstCategory = findViewById(R.id.FirstCategory);
        SecondCategory = findViewById(R.id.SecondCategory);
        ThirdCategory = findViewById(R.id.ThirdCategory);
        btnAllCategories = findViewById(R.id.btnAllCategories);

        recycleView = findViewById(R.id.recycleView);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        BtnSearch = findViewById(R.id.BtnSearch);


    }
    private void initBottomNavigation() {
        Log.d(TAG, "initBottomNavigation: started");
        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.search:
                        Toast.makeText(SearchActivity.this, "search", Toast.LENGTH_SHORT).show();
                        //todo
                        break;
                    case R.id.home:
                        Toast.makeText(SearchActivity.this, "home", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(SearchActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                    case R.id.cart:
                        Intent intent2=new Intent(SearchActivity.this,CartActivity.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent2);
                        break;
                    default:
                        break;

                }
                return true;
            }
        });

    }

    @Override
    public void onSelectedCategory(String categoryy) {
        Log.d(TAG, "onSelectedCategory: started");
        Intent intent=new Intent(SearchActivity.this,ShowCategoriesLayoutitems.class);
        intent.putExtra("category",categoryy);
        startActivity(intent);
    }


}
