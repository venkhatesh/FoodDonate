package com.example.bytecamp_raw.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.bytecamp_raw.Activity.Fragment.ActivityFragment;
import com.example.bytecamp_raw.Activity.Fragment.AddFoodFragment;
import com.example.bytecamp_raw.Activity.Fragment.DistributionFragment;
import com.example.bytecamp_raw.Activity.Fragment.ProfileFragment;
import com.example.bytecamp_raw.R;


public class DonateActivity extends AppCompatActivity {

    private TextView mTextMessage;
    FloatingActionButton fab;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    {
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        item.setVisible(true);
                        fragment = new Hotel_Home();
                        loadFragment(fragment);
                        return true;
                    case R.id.ngo_list:
                        item.setVisible(true);
                        fragment = new Hotel_Home();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_activity:
                        item.setVisible(false);
                        fragment = new ActivityFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_profile:
                        item.setVisible(false);
                        fragment = new ProfileFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment fragment = new AddFoodFragment();
                fragment.show(getSupportFragmentManager(), AddFoodFragment.class.getSimpleName());

            }
        });
    //    mbotttm.getMenu().removeItem(R.id.navigation_activity);
    //    mbotttm.getMenu().removeItem(R.id.navigation_profile);
        navigation.getMenu().removeItem(R.id.navigation_activity);
        navigation.getMenu().removeItem(R.id.navigation_profile);
/*
        navigation.findViewById(R.id.navigation_activity).setVisibility(View.GONE);
        navigation.findViewById(R.id.navigation_profile).setVisibility(View.GONE);
 */   }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.hotel_cotainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
