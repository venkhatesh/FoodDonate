package com.example.bytecamp_raw.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.bytecamp_raw.Model.HotelModel;
import com.example.bytecamp_raw.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class MissionDetail extends AppCompatActivity {
    Button start,end;
    public android.support.v7.widget.Toolbar toolbar;
    private DatabaseReference mDatabase;
    Button join;
    String hotelName,foodType,quantity,freshness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_detail);
        Intent i = getIntent();
        final String name = i.getStringExtra("name");
        start = findViewById(R.id.start_location);
        end = findViewById(R.id.end_location);
        join = findViewById(R.id.join_button);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final ArrayList<HotelModel> arrayList = new ArrayList<HotelModel>();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double lat = 19.0356;
                double lng= 73.0228;
                String strUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + "Label which you want" + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double lat = 19.1579;
                double lng= 72.9935;
                String strUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + "Label which you want" + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
        mDatabase.child("hotel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot uniquesnapshot : dataSnapshot.getChildren()){
                    Log.d("DetailActivity ", "Food Type " + uniquesnapshot.getValue(HotelModel.class).getFoodType());
                    if (uniquesnapshot.getValue(HotelModel.class).getName().equals(name)){
                        hotelName = uniquesnapshot.getValue(HotelModel.class).getName();
                        foodType = uniquesnapshot.getValue(HotelModel.class).getFoodType();
                        quantity = uniquesnapshot.getValue(HotelModel.class).getQuantity();
                        freshness = uniquesnapshot.getValue(HotelModel.class).getFreshness();
                        Log.d("MissionDetail", "onDataChange: " + hotelName);

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs =getApplicationContext().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
                final String Prefname = prefs.getString("name",null);
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("volunteer").child(Prefname).child("HotelName").setValue(hotelName);
                mDatabase.child("volunteer").child(Prefname).child("FoodType").setValue(foodType);
                mDatabase.child("volunteer").child(Prefname).child("Quantity").setValue(quantity);
                mDatabase.child("volunteer").child(Prefname).child("Freshness").setValue(freshness);
                finish();

            }
        });

    }
}
