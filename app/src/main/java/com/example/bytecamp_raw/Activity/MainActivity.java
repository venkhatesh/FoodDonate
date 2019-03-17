package com.example.bytecamp_raw.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.example.bytecamp_raw.Activity.Fragment.ActivityFragment;
import com.example.bytecamp_raw.Activity.Fragment.DistributionFragment;
import com.example.bytecamp_raw.Activity.Fragment.ProfileFragment;
import com.example.bytecamp_raw.Activity.Fragment.HomeFragment;
import com.example.bytecamp_raw.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.LENGTH_LONG;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    String CHANNEL_ID = "my_channel_01";// The id of the channel.
    CharSequence name = "NotifName";// The user-visible name of the channel.
    int importance = NotificationManager.IMPORTANCE_HIGH;
    NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            Fragment currentFragment=getSupportFragmentManager().findFragmentById(R.id.frame_container);
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    item.setVisible(true);
                    if (!(currentFragment instanceof DistributionFragment)) {
                        fragment = new DistributionFragment();
                        getSupportFragmentManager().beginTransaction().hide(currentFragment).show(fragment).commit();
                        loadFragment(fragment);
                    }
                        return true;

                case R.id.navigation_activity:
                    item.setVisible(true);
                    if (!(currentFragment instanceof ActivityFragment)) {
                        fragment = new ActivityFragment();
                        getSupportFragmentManager().beginTransaction().hide(currentFragment).show(fragment).commit();
                        loadFragment(fragment);
                    }
                    return true;
                case R.id.navigation_profile:
                    item.setVisible(true);
                    if (!(currentFragment instanceof ProfileFragment)) {
                        fragment = new ProfileFragment();
                        getSupportFragmentManager().beginTransaction().hide(currentFragment).show(fragment).commit();
                        loadFragment(fragment);
                    }
                    return true;
                case R.id.ngo_list:
                    item.setVisible(false);
                    if (!(currentFragment instanceof ProfileFragment)) {
                    fragment = new Hotel_Home();
                    loadFragment(fragment);
                    }
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
        SharedPreferences prefs =getApplicationContext().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        final String Prefname = prefs.getString("name",null);
        final String ngoName = prefs.getString("NGOname",null);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("NGO").child("RHA").child("flag").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("DataChange", dataSnapshot.toString());
                String bool= (String) dataSnapshot.getValue();
                if (bool.equals("false"))
                {
                    Log.d("SnapshotValueFalse", "onDataChange: ");
                    Toast.makeText(MainActivity.this,"Notif aana chahiye !",LENGTH_LONG).show();
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(MainActivity.this)
                                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                                    .setContentTitle("Pickup Request Update from Restaurant")
                                    .setContentText("Your request has been rejected")
                            .setChannelId(CHANNEL_ID);


                    // Gets an instance of the NotificationManager service//

                    NotificationManager mNotificationManager =

                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


                            mNotificationManager.notify(001, mBuilder.build());

                            showNotification(MainActivity.this,"Bantai","Notif");
                }
                else if (bool.equals("true"))
                {
                    Toast.makeText(MainActivity.this,"Notif aana chahiye !",LENGTH_LONG).show();
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(MainActivity.this)
                                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                                    .setContentTitle("Pickup Request Update from Restaurant")
                                    .setContentText("Your request has been approved !")
                            .setChannelId(CHANNEL_ID);


                    // Gets an instance of the NotificationManager service//

                    NotificationManager mNotificationManager =

                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


                    mNotificationManager.notify(001, mBuilder.build());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void showNotification(Context context, String title, String body) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body);

//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//        stackBuilder.addNextIntent(intent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
//                0,
//                PendingIntent.FLAG_UPDATE_CURRENT
//        );
//        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }

}
