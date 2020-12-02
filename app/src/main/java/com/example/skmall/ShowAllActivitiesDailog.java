package com.example.skmall;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class ShowAllActivitiesDailog extends DialogFragment {
    private static final String TAG = "ShowAllActivitiesDailog";
    public interface SelectCatergory{
        void onSelectedCategory(String categoryy);


    }
    private SelectCatergory selectCatergory;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

      View view=getActivity().getLayoutInflater().inflate(R.layout.show_all_categories_dailog,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setTitle("All Categories");
        ListView categories=view.findViewById(R.id.categories);
        Utils util=new Utils(getActivity());
        final ArrayList<String> category=util.getAllCategories();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,category);
        categories.setAdapter(adapter);
        try {
            selectCatergory=(SelectCatergory)getActivity();
        }catch (ClassCastException e){
            e.printStackTrace();
        }

        categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              selectCatergory.onSelectedCategory(category.get(position));
            }
        });
        return builder.create();
    }
}
