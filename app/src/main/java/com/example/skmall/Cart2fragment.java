package com.example.skmall;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.example.skmall.Models.Order;

public class Cart2fragment extends Fragment {
    private static final String TAG = "Cart2fragment";
    private EditText edtTxtAddress,edtTxtZipCode,edtTxtPhoneNo,edtTxtemail;
    private Button btnBack,btnNext;
    private Order incoming_order;
    private RelativeLayout Cart2Relayout,addressReLayout,zipCodeReLayout,phoneNoReLayout,emailReLayout;
    private NestedScrollView nestedScrollView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2_layout_cart, container, false);
        initView(view);
        Bundle bundle=getArguments();
        if(null!=bundle){
            incoming_order=bundle.getParcelable("order");
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.out,R.anim.in).replace(R.id.fragment_container,new CartFirstFragment()).commit();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()){
                    passData();
                }else{
                    final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setTitle("Error");
                    builder.setMessage("plz fill fields");
                    builder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.create().show();
                }
            }
        });

        Cart2Relayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
            }
        });
        addressReLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
            }
        });
        zipCodeReLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
            }
        });
        phoneNoReLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
            }
        });
        emailReLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyboard();
            }
        });


        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                closeKeyboard();
            }
        });
        return view;
    }

    private void passData(){
        Log.d(TAG, "passData: started");
        Bundle bundle=new Bundle();
        bundle.putParcelable("order",incoming_order);
        Cart3Fragment cart3Fragment=new Cart3Fragment();
        cart3Fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.in,R.anim.out).replace(R.id.fragment_container,cart3Fragment).commit();
    }

    private boolean validateData(){
        Log.d(TAG, "validateData: started");
        if(edtTxtAddress.getText().toString().equals("")){
            return false;
        }
        if(edtTxtemail.getText().toString().equals("")){
            return false;
        }
        if(edtTxtPhoneNo.getText().toString().equals("")){
            return false;
        }
        if(edtTxtZipCode.getText().toString().equals("")){
            return false;
        }
        if(edtTxtAddress.getText().toString().equals("")){
            return false;
        }
        return true;
    }


    private void initView(View view){
        Log.d(TAG, "initView: started");
        edtTxtAddress=view.findViewById(R.id.edtTxtAddress);
        edtTxtZipCode=view.findViewById(R.id.edtTxtZipCode);
        edtTxtPhoneNo=view.findViewById(R.id.edtTxtPhoneNo);
        edtTxtemail=view.findViewById(R.id.edtTxtemail);

        nestedScrollView=view.findViewById(R.id.nestedScrollView);

        btnBack=view.findViewById(R.id.btnBack);
        btnNext=view.findViewById(R.id.btnNext);
        Cart2Relayout=view.findViewById(R.id.Cart2Relayout);
        addressReLayout=view.findViewById(R.id.addressReLayout);
        zipCodeReLayout=view.findViewById(R.id.zipCodeReLayout);
        phoneNoReLayout=view.findViewById(R.id.phoneNoReLayout);
        emailReLayout=view.findViewById(R.id.emailReLayout);

    }
    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (null != view) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }
}
