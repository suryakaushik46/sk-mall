package com.example.skmall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private FrameLayout fragment_container;
    private NavigationView navigationDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*SharedPreferences sharedPreferences=getSharedPreferences("fakeDatabase",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("number",3);
        editor.apply();*/
        //permanent database example



        initView();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.drawer_open,R.string.drawer_closed);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationDrawer.setNavigationItemSelectedListener(this);

        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction() ;
        transaction.replace(R.id.fragment_container,new MainFragment());
        transaction.commit();
    }

    private void  initView(){
        Log.d(TAG, "initView: started");
        drawer=findViewById(R.id.drawer);
        toolbar= findViewById(R.id.toolbar);
        fragment_container=findViewById(R.id.fragment_container);
        navigationDrawer=findViewById(R.id.navigationDrawer);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.cart:

                Intent intent=new Intent(MainActivity.this,CartActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.categories:
              ShowAllActivitiesDailog showAllActivitiesDailog=new ShowAllActivitiesDailog();
              showAllActivitiesDailog.show(getSupportFragmentManager(),"all categories");
                break;
            case R.id.about:
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("About");
                builder.setMessage("want to see source code ????");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1=new Intent(MainActivity.this, WebViewActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();


                break;
            case R.id.terms:
                AlertDialog.Builder termsBuilder=new AlertDialog.Builder(MainActivity.this);
                termsBuilder.setTitle("terms");
                termsBuilder.setMessage("Not so like this just for fun");
                termsBuilder.setPositiveButton("LOL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                termsBuilder.create().show();
                break;
            case R.id.licenses:
                Licence_Dialog licence_dialog=new Licence_Dialog();
                licence_dialog.show(getSupportFragmentManager(),"showed");
                break;
            default:
                break;
        }
        return false;
    }
}
