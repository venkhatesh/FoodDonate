package com.example.bytecamp_raw.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.bytecamp_raw.R;

public class startMission extends AppCompatActivity {

    CheckBox hardwareChk,physicalChk;
    Button hardwarebtn,physicalbtn;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_mission);
         flag = 0;
        hardwareChk = findViewById(R.id.hardwareCheckbox);
        physicalChk = findViewById(R.id.checkboxEdible);
        hardwarebtn = findViewById(R.id.hardwarebutton);
        physicalbtn = findViewById(R.id.humanbutton);
        hardwarebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hardwareChk.isChecked()){
                    flag=1;
                }
            }
        });
        physicalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!physicalChk.isChecked() && flag==1){
                    Intent maps = new Intent(getApplicationContext(),MapsActivity.class);
                    startActivity(maps);
                }
            }
        });

    }
}
