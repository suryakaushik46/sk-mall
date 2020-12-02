package com.example.skmall;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skmall.Models.GroceryItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainFragment extends Fragment {
    private Utils util;
    private static final String TAG = "MainFragment";
    private BottomNavigationView bottomNavigationView;
    private RecyclerView suggestedItemsRecyclerView, popularItemsRecyclerView, newItemsRecyclerView;
    private GroceryItemAdapter suggestedItemAdapter, newItemsAdapter, popularItemsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);
        initBottomNavigation();
        util = new Utils(getActivity());
        util.initDatabase();

        initRecViews();


        return view;
    }

    private void initViews(View view) {
        Log.d(TAG, "initViews: started");
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        suggestedItemsRecyclerView = view.findViewById(R.id.suggestedItemsRecyclerView);
        popularItemsRecyclerView = view.findViewById(R.id.popularItemsRecyclerView);
        newItemsRecyclerView = view.findViewById(R.id.newItemsRecyclerView);


    }

    private void initBottomNavigation() {
        Log.d(TAG, "initBottomNavigation: started");
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.search:
                        Toast.makeText(getActivity(), "search ", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getActivity(),SearchActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                    case R.id.home:
                        Toast.makeText(getActivity(), " your are in homepage ", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.cart:
                        Toast.makeText(getActivity(), "cart", Toast.LENGTH_SHORT).show();
                        Intent intent1=new Intent(getActivity(),CartActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                        break;
                    default:
                        break;

                }
                return true;
            }
        });

    }

    private void initRecViews() {
        suggestedItemAdapter = new GroceryItemAdapter(getActivity());
        newItemsAdapter = new GroceryItemAdapter(getActivity());
        popularItemsAdapter = new GroceryItemAdapter(getActivity());

        newItemsRecyclerView.setAdapter(newItemsAdapter);
        popularItemsRecyclerView.setAdapter(popularItemsAdapter);
        suggestedItemsRecyclerView.setAdapter(suggestedItemAdapter);

        newItemsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        popularItemsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        suggestedItemsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
//all items

        UpdateREcViews();
    }

    private void UpdateREcViews() {
        ArrayList<GroceryItem> newItems = util.getAllItems();


        Comparator<GroceryItem> newItemComprator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem o1, GroceryItem o2) {
                return o1.getId() - o2.getId();
            }
        };

        Comparator<GroceryItem> reverseComparator1 = Collections.reverseOrder(newItemComprator);
        Collections.sort(newItems, reverseComparator1);
        if (newItems != null) {
            newItemsAdapter.setItems(newItems);
        }

        ArrayList<GroceryItem> popularItems = util.getAllItems();

        //to get popularItems
        Comparator<GroceryItem> popularityComparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem o1, GroceryItem o2) {
                return compareByPopularity(o1, o2);
            }
        };
        Comparator<GroceryItem> reverseComparator = Collections.reverseOrder(popularityComparator);
        Collections.sort(popularItems, reverseComparator);

        popularItemsAdapter.setItems(popularItems);

        ArrayList<GroceryItem> suggestedItems = util.getAllItems();
        Comparator<GroceryItem> suggestedItemComparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem o1, GroceryItem o2) {
                return o1.getUserPoint() - o2.getUserPoint();
            }
        };

        Comparator<GroceryItem> reverse2 = Collections.reverseOrder(suggestedItemComparator);
        Collections.sort(suggestedItems, suggestedItemComparator);

        suggestedItemAdapter.setItems(suggestedItems);

    }

    @Override
    public void onResume() {
        UpdateREcViews();
        super.onResume();
    }

    private int compareByPopularity(GroceryItem g1, GroceryItem g2) {
        Log.d(TAG, "compareByPopularity: started");

        if (g1.getPopularityPoint() > g2.getPopularityPoint()) {
            return 1;
        } else if (g1.getPopularityPoint() < g2.getPopularityPoint()) {
            return -1;
        } else {
            return 0;
        }
    }
}