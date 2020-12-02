package com.example.skmall;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skmall.Models.Order;

import java.util.ArrayList;

public class SucessfulPaymentFragment extends Fragment {
    private static final String TAG = "SucessfulPaymentFragment";
    private Button btnGoBack;
    @SuppressLint("LongLogTag")
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: started");
        View view=inflater.inflate(R.layout.fragment_sucess,container,false);
        btnGoBack=view.findViewById(R.id.btnGoBack);
        Utils util=new Utils(getActivity());
        util.RemoveCartItems();
        Bundle bundle=getArguments();
        try{
            Order order=bundle.getParcelable("order");
            ArrayList<Integer> itemIds=order.getItems();
            util.addPopularityPoint(itemIds);
        }catch (NullPointerException e){

        }

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        return view;
    }
}
