package com.example.bytecamp_raw.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bytecamp_raw.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login_Page extends AppCompatActivity {

    private DatabaseReference mDatabase;
    EditText userName,pass1,pass2;
    Button hotel,volunteer,ngo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);
        userName = findViewById(R.id.user_name);
        pass1 = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        hotel = findViewById(R.id.hotel_button);
        ngo = findViewById(R.id.ngo_button);
        volunteer=findViewById(R.id.volunteer_button);
        Spinner dropdown = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"NGO", "Hotel", "Volunteer"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if (selectedItem.equals("NGO")){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hotelIntent = new Intent(getApplicationContext(),DonateActivity.class);
                startActivity(hotelIntent);
            }
        });
        ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
                editor.putString("name", userName.getText(  ).toString());
                editor.putString("NGOname","FoodHelperz");
                editor.apply();

                mDatabase.child("volunteer").child(userName.getText().toString()).child("name").setValue(userName.getText().toString());
                mDatabase.child("volunteer").child(userName.getText().toString()).child("count").setValue(0);
                Intent volunteerIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(volunteerIntent);
            }
        });

        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),volunteer_mission.class);
                startActivity(intent);
            }
        });


    }
}
