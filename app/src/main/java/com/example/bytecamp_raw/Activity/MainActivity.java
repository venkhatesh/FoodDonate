package com.example.bytecamp_raw.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.FragmentTransaction;

import com.example.bytecamp_raw.Activity.Fragment.ActivityFragment;
import com.example.bytecamp_raw.Activity.Fragment.DistributionFragment;
import com.example.bytecamp_raw.Activity.Fragment.ProfileFragment;
import com.example.bytecamp_raw.Activity.Fragment.HomeFragment;
import com.example.bytecamp_raw.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    item.setVisible(true);
                    fragment = new DistributionFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_activity:
                    item.setVisible(true);
                    fragment = new ActivityFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    item.setVisible(true);
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.ngo_list:
                    item.setVisible(false);
                    fragment = new Hotel_Home();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new DistributionFragment());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().removeItem(R.id.ngo_list);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
