package com.example.bytecamp_raw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cdflynn.android.library.checkview.CheckView;

public class mission_complete extends AppCompatActivity {
    CheckView mCheckView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_complete);
        mCheckView=findViewById(R.id.check);
        mCheckView.check();

    }
}
