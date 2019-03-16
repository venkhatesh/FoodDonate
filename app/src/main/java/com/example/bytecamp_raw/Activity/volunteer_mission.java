package com.example.bytecamp_raw.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bytecamp_raw.R;


public class volunteer_mission extends AppCompatActivity {
    Button start_mission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_mission);
        start_mission= findViewById(R.id.start_button);
        start_mission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),volunteer_qrcode.class);
                startActivity(intent);
            }
        });

    }
}
