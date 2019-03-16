package com.example.bytecamp_raw.Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.bytecamp_raw.Activity.Fragment.ActivityFragment;
import com.example.bytecamp_raw.Activity.Fragment.AddFoodFragment;
import com.example.bytecamp_raw.Activity.Fragment.DistributionFragment;
import com.example.bytecamp_raw.Activity.Fragment.ProfileFragment;
import com.example.bytecamp_raw.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DonateActivity extends AppCompatActivity {

    private TextView mTextMessage;
    FloatingActionButton fab;
    private DatabaseReference mDatabase;
    String TAG = "DonateActivity";
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
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("hotel").child("Rangoli").child("Pickup Requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Log.d(TAG, "onDataChange: " + snapshot.getKey());
                    sendNotification();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment fragment = new AddFoodFragment();
                fragment.show(getSupportFragmentManager(), AddFoodFragment.class.getSimpleName());

            }
        });

        navigation.getMenu().removeItem(R.id.navigation_activity);
        navigation.getMenu().removeItem(R.id.navigation_profile);

    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.hotel_cotainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendNotification() {
        Log.d(TAG, "sendNotification: ");
        //Get an instance of NotificationManager//
   }
}
