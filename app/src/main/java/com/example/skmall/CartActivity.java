package com.example.skmall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CartActivity extends AppCompatActivity {
    private static final String TAG = "CartActivity";
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initViews();
        initbottomNavigationView();

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,new CartFirstFragment());
        transaction.commit();
    }

    private void initViews(){
        Log.d(TAG, "initViews: started");
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
    }

    private void initbottomNavigationView(){
        Log.d(TAG, "initbottomNavigationView: started");
        bottomNavigationView.setSelectedItemId(R.id.cart);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.search:
                        Intent intent=new Intent(CartActivity.this,SearchActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                    case R.id.home:
                         Intent newintent=new Intent(CartActivity.this,MainActivity.class);
                         newintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(newintent);
                        break;
                    case R.id.cart:
                        Toast.makeText(CartActivity.this,"your are in cart",Toast.LENGTH_SHORT);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

}
