package com.example.rozerpayintigration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/** @noinspection ALL */
public class NextActivity extends AppCompatActivity {

    BottomNavigationView btmView;
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        btmView = findViewById(R.id.bottomNavigationView);


        btmView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId();

                if (id == R.id.home){
                    loadFrag(new HomeFragment(),false);

                } else if (id == R.id.points) {
                    loadFrag(new PointsFragment(),false);
                } else if (id == R.id.orders) {
                    loadFrag(new OrdersFragment(),false);
                }else {  // profile
                    loadFrag(new ProfileFragment(),true);
                }
                return true;
            }
        });



        // default item
        btmView.setSelectedItemId(R.id.profile);
    }

    public void loadFrag(Fragment fragment,boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag){
            ft.add(R.id.container, fragment);
        }else
            ft.replace(R.id.container, fragment);

        ft.commit();
    }
}