package com.example.skmall;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.skmall.Models.GroceryItem;
import com.example.skmall.Models.Review;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddReviewDialog extends DialogFragment {
    private static final String TAG = "AddReviewDialog";
    private EditText edtReview, userName1;
    private TextView itemName1, txtWarning;
    private Button btnAdd;
    private int itemId = 0;

    public interface AddReview{
        void onAddReviewResult(Review review);
    }

    private AddReview addReview;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dailog_add_review, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Review");
        builder.setView(view);

        initViews(view);
        Bundle bundle = getArguments();
        try {
            GroceryItem item = bundle.getParcelable("item");
            itemName1.setText(item.getName());
            this.itemId=item.getId();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReview();
                dismiss();
            }
        });

        return builder.create();
    }

    private void addReview() {
        Log.d(TAG, "addReview: started");
        String review=edtReview.getText().toString();
        String name=userName1.getText().toString();
       String date= getCurrentDate();
        Review review1=new Review(itemId,name,date,review);

        try {
          addReview=(AddReview)getActivity();
          addReview.onAddReviewResult(review1);
        }catch (ClassCastException e){
            e.printStackTrace();
        }

    }

    private void initViews(View view) {
        Log.d(TAG, "initViews: started");
        edtReview = view.findViewById(R.id.edtReview);
        userName1 = view.findViewById(R.id.userName1);

        itemName1 = view.findViewById(R.id.itemName1);
        txtWarning = view.findViewById(R.id.txtWarning);

        btnAdd = view.findViewById(R.id.btnAdd);

    }

    private String getCurrentDate(){
        Date date= Calendar.getInstance().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-YYYY");
        return sdf.format(date);
    }
}
