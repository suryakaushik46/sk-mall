package com.example.skmall;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skmall.Models.Order;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cart3Fragment extends Fragment {
    private static final String TAG = "Cart3Fragment";
    private Button btnBack, btnNext;
    private TextView txtPrice, TxtShippingDetails;
    private RadioGroup rgPaymentMethod;
    private Order incoming_order;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgment_3_layout, container, false);
        initViews(view);
        Bundle bundle = getArguments();
        try {
            incoming_order = bundle.getParcelable("order");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (incoming_order != null) {
            txtPrice.setText(String.valueOf(incoming_order.getTotalPrice()));
            String finalString = "Items :\n\tAddress : " + incoming_order.getAddress() + "\n\t" + incoming_order.getPhoneNo();
            TxtShippingDetails.setText(finalString);

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoPayment();
                }
            });
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goback();
                }
            });

        }
        return view;
    }

    private void initViews(View view) {
        Log.d(TAG, "initViews: started");
        rgPaymentMethod = view.findViewById(R.id.rgPaymentMethod);

        btnBack = view.findViewById(R.id.btnBack);
        btnNext = view.findViewById(R.id.btnNext);

        txtPrice = view.findViewById(R.id.txtPrice);
        TxtShippingDetails = view.findViewById(R.id.TxtShippingDetails);

    }

    private void gotoPayment() {
        RadioButton button = getActivity().findViewById(rgPaymentMethod.getCheckedRadioButtonId());
        incoming_order.setPaymentMethod(button.getText().toString());

        incoming_order.setSuccess(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
        Call<Order> call = retrofitClient.gotoFakePayment(incoming_order);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Log.d(TAG, "onResponse: started" + response.code());
                if (!response.isSuccessful()) {
                    return;
                }

                goTopaymentResult(response.body());
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.d(TAG, "onFailure: started" + t.getMessage());
            }
        });


    }

    private void goTopaymentResult(Order order) {
        Log.d(TAG, "goTopaymentResult: started");
        if (order.isSuccess()) {
            SucessfulPaymentFragment sucessfulPaymentFragment = new SucessfulPaymentFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("order", order);
            sucessfulPaymentFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.in,R.anim.out).replace(R.id.fragment_container, sucessfulPaymentFragment).commit();
        } else {
            FailueFragment failueFragment = new FailueFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("order", order);
            failueFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.in,R.anim.out).setCustomAnimations(R.anim.in,R.anim.out).replace(R.id.fragment_container, failueFragment).commit();
        }
    }

    private void goback() {
        Log.d(TAG, "goback: started");
        Order order = new Order();
        order.setTotalPrice(incoming_order.getTotalPrice());
        order.setItems(incoming_order.getItems());
        Bundle newBundle = new Bundle();
        newBundle.putParcelable("order", order);
        Cart2fragment cart2fragment = new Cart2fragment();
        cart2fragment.setArguments(newBundle);

        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.out,R.anim.in).replace(R.id.fragment_container, cart2fragment).commit();
    }


}
