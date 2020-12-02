package com.example.skmall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.skmall.Models.GroceryItem;
import com.example.skmall.Models.Review;

import java.util.ArrayList;

public class GroceryItemActivity extends AppCompatActivity implements AddReviewDialog.AddReview {
    private static final String TAG = "GroceryItemActivity";
    private ImageView itemImage;
    private ImageView firstEmptyStar, firstFilledStar, secondEmptyStar, secondFilledStar, thirdEmptyStar,
            thirdFilledStar, fourEmptyStar, fourFilledStar, fiveEmptyStar, fiveFilledStar;
    private RecyclerView reviewRecView;
    private Button btnAddToCart;
    private TextView txtName, txtPrice, txtAvailabilty, description;
    private RelativeLayout AddReviewLayout;
    private GroceryItem incomingItem;
    private Utils util;
    private int CurrentRate;
    private ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_item);
        initViews();
        Intent intent = getIntent();
        util = new Utils(this);
        try {
            incomingItem = intent.getParcelableExtra("item");
            this.CurrentRate = incomingItem.getRate();
            changeVisibilty(CurrentRate);
            setView();
        } catch (NullPointerException e) {
            e.printStackTrace();

        }


    }

    /**
     * responsible for setting initial values
     */
    private void setView() {
        Log.d(TAG, "setView: started");
        txtName.setText(incomingItem.getName());
        txtPrice.setText(String.valueOf(incomingItem.getPrice()));
        if (incomingItem.getAvailableAmount() > 0) {
            txtAvailabilty.setText(String.valueOf(incomingItem.getAvailableAmount()) + "available ");

        }
        Glide.with(this).asBitmap().load(incomingItem.getImageUrl()).into(itemImage);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo:add to cart activity

                util.AddItemToCart(incomingItem.getId());
                Intent intent=new Intent(GroceryItemActivity.this,CartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        /**
         * handel stars
         */
        handleStars();


        adapter = new ReviewAdapter();

        reviewRecView.setAdapter(adapter);
        reviewRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Review> reviews = util.getReviewForItem(incomingItem.getId());

        if (reviews != null) {
            adapter.setReviews(reviews);
        }

        AddReviewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo show dailog

                AddReviewDialog addReviewDialog = new AddReviewDialog();
                Bundle bundle = new Bundle();
                bundle.putParcelable("item", incomingItem);
                addReviewDialog.setArguments(bundle);
                addReviewDialog.show(getSupportFragmentManager(), "add review Dailog");
            }
        });


    }

    private void initViews() {
        itemImage = findViewById(R.id.itemImage);

        firstEmptyStar = findViewById(R.id.firstEmptyStar);
        firstFilledStar = findViewById(R.id.firstFilledStar);
        secondEmptyStar = findViewById(R.id.secondEmptyStar);
        secondFilledStar = findViewById(R.id.secondFilledStar);
        thirdEmptyStar = findViewById(R.id.thirdEmptyStar);
        thirdFilledStar = findViewById(R.id.thirdFilledStar);
        fourEmptyStar = findViewById(R.id.fourEmptyStar);
        fourFilledStar = findViewById(R.id.fourFilledStar);
        fiveEmptyStar = findViewById(R.id.fiveEmptyStar);
        fiveFilledStar = findViewById(R.id.fiveFilledStar);

        reviewRecView = findViewById(R.id.reviewRecView);

        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtAvailabilty = findViewById(R.id.txtAvailabilty);
        description = findViewById(R.id.description);

        AddReviewLayout = findViewById(R.id.AddReviewLayout);

        btnAddToCart = findViewById(R.id.btnAddToCart);


    }

    @Override
    public void onAddReviewResult(Review review) {
        Log.d(TAG, "onAddReviewResult: started");

        util.addReview(review);
        ArrayList<Review> reviewa = util.getReviewForItem(incomingItem.getId());
        if (reviewa != null) {
            adapter.setReviews(reviewa);
        }
    }

    private void handleStars() {
        Log.d(TAG, "handleStars: started");

        firstEmptyStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkifRateHAsChanged(1)) {
                    updatedataBase(1);
                    changeVisibilty(1);

                }
            }
        });
        secondEmptyStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkifRateHAsChanged(2)) {
                    updatedataBase(2);
                    changeVisibilty(2);

                }
            }
        });

        thirdEmptyStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkifRateHAsChanged(3)) {
                    updatedataBase(3);
                    changeVisibilty(3);

                }
            }
        });
        fourEmptyStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkifRateHAsChanged(4)) {
                    updatedataBase(4);
                    changeVisibilty(4);

                }
            }
        });

        fiveEmptyStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkifRateHAsChanged(5)) {
                    updatedataBase(5);
                    changeVisibilty(5);

                }
            }
        });

        firstFilledStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkifRateHAsChanged(1)) {
                    updatedataBase(1);
                    changeVisibilty(1);

                }
            }
        });

        secondFilledStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkifRateHAsChanged(2)) {
                    updatedataBase(2);
                    changeVisibilty(2);

                }
            }
        });

        thirdFilledStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkifRateHAsChanged(3)) {
                    updatedataBase(3);
                    changeVisibilty(3);

                }
            }
        });

        fourFilledStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkifRateHAsChanged(4)) {
                    updatedataBase(4);
                    changeVisibilty(4);

                }
            }
        });

        fiveFilledStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkifRateHAsChanged(5)) {
                    updatedataBase(5);
                    changeVisibilty(5);

                }
            }
        });
    }

    private void updatedataBase(int newRate) {
        Log.d(TAG, "updatedataBase: started");
        util.updateRate(incomingItem, newRate);

    }

    private void changeVisibilty(int newRate) {
        Log.d(TAG, "changeVisibilty: started");
        switch (newRate) {
            case 0:
                firstEmptyStar.setVisibility(View.VISIBLE);
                secondEmptyStar.setVisibility(View.VISIBLE);
                thirdEmptyStar.setVisibility(View.VISIBLE);
                fourEmptyStar.setVisibility(View.VISIBLE);
                fiveEmptyStar.setVisibility(View.VISIBLE);

                firstFilledStar.setVisibility(View.GONE);
                secondFilledStar.setVisibility(View.GONE);
                thirdFilledStar.setVisibility(View.GONE);
                fourFilledStar.setVisibility(View.GONE);
                fiveFilledStar.setVisibility(View.GONE);

                break;
            case 1:
                firstEmptyStar.setVisibility(View.GONE);
                secondEmptyStar.setVisibility(View.VISIBLE);
                thirdEmptyStar.setVisibility(View.VISIBLE);
                fourEmptyStar.setVisibility(View.VISIBLE);
                fiveEmptyStar.setVisibility(View.VISIBLE);

                firstFilledStar.setVisibility(View.VISIBLE);
                secondFilledStar.setVisibility(View.GONE);
                thirdFilledStar.setVisibility(View.GONE);
                fourFilledStar.setVisibility(View.GONE);
                fiveFilledStar.setVisibility(View.GONE);

                break;

            case 2:
                firstEmptyStar.setVisibility(View.GONE);
                secondEmptyStar.setVisibility(View.GONE);
                thirdEmptyStar.setVisibility(View.VISIBLE);
                fourEmptyStar.setVisibility(View.VISIBLE);
                fiveEmptyStar.setVisibility(View.VISIBLE);

                firstFilledStar.setVisibility(View.VISIBLE);
                secondFilledStar.setVisibility(View.VISIBLE);
                thirdFilledStar.setVisibility(View.GONE);
                fourFilledStar.setVisibility(View.GONE);
                fiveFilledStar.setVisibility(View.GONE);

                break;
            case 3:
                firstEmptyStar.setVisibility(View.GONE);
                secondEmptyStar.setVisibility(View.GONE);
                thirdEmptyStar.setVisibility(View.GONE);
                fourEmptyStar.setVisibility(View.VISIBLE);
                fiveEmptyStar.setVisibility(View.VISIBLE);

                firstFilledStar.setVisibility(View.VISIBLE);
                secondFilledStar.setVisibility(View.VISIBLE);
                thirdFilledStar.setVisibility(View.VISIBLE);
                fourFilledStar.setVisibility(View.GONE);
                fiveFilledStar.setVisibility(View.GONE);

                break;
            case 4:
                firstEmptyStar.setVisibility(View.GONE);
                secondEmptyStar.setVisibility(View.GONE);
                thirdEmptyStar.setVisibility(View.GONE);
                fourEmptyStar.setVisibility(View.GONE);
                fiveEmptyStar.setVisibility(View.VISIBLE);

                firstFilledStar.setVisibility(View.VISIBLE);
                secondFilledStar.setVisibility(View.VISIBLE);
                thirdFilledStar.setVisibility(View.VISIBLE);
                fourFilledStar.setVisibility(View.VISIBLE);
                fiveFilledStar.setVisibility(View.GONE);

                break;

            case 5:
                firstEmptyStar.setVisibility(View.GONE);
                secondEmptyStar.setVisibility(View.GONE);
                thirdEmptyStar.setVisibility(View.GONE);
                fourEmptyStar.setVisibility(View.GONE);
                fiveEmptyStar.setVisibility(View.GONE);

                firstFilledStar.setVisibility(View.VISIBLE);
                secondFilledStar.setVisibility(View.VISIBLE);
                thirdFilledStar.setVisibility(View.VISIBLE);
                fourFilledStar.setVisibility(View.VISIBLE);
                fiveFilledStar.setVisibility(View.VISIBLE);

                break;

            default:
                firstFilledStar.setVisibility(View.GONE);
                secondFilledStar.setVisibility(View.GONE);
                thirdFilledStar.setVisibility(View.GONE);
                fourFilledStar.setVisibility(View.GONE);
                fiveFilledStar.setVisibility(View.GONE);

                firstEmptyStar.setVisibility(View.VISIBLE);
                secondEmptyStar.setVisibility(View.VISIBLE);
                thirdEmptyStar.setVisibility(View.VISIBLE);
                fourEmptyStar.setVisibility(View.VISIBLE);
                fiveEmptyStar.setVisibility(View.VISIBLE);
                break;
        }
    }

    private boolean checkifRateHAsChanged(int newRate) {
        if (newRate == CurrentRate) {
            return false;
        } else {
            return true;
        }

    }
}
