package com.example.bytecamp_raw.Activity.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.bytecamp_raw.R;
import com.example.bytecamp_raw.Services.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by venkat on 23/2/19.
 */
public class AddFoodFragment extends BottomSheetDialogFragment {
    BottomSheetDialog bottomSheetDialog;
    FirebaseDatabase database;
    DatabaseReference myref;
    EditText hotelName,foodType,foodQty,foodFresh,foodDescription;
    Button submit;
    ImageView btcancel;
    RadioButton veg_btn,nonveg_btn;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialogTheme);
        View sheetView = getActivity().getLayoutInflater().inflate(R.layout.add_food, null);
        bottomSheetDialog.setContentView(sheetView);
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogTheme);
        bottomSheetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        bottomSheetDialog.show();
        database = FirebaseDatabase.getInstance();
        myref = database.getReference("hotel");
        btcancel = sheetView.findViewById(R.id.bt_cancel);
        hotelName = sheetView.findViewById(R.id.hotel_name);
        foodType = sheetView.findViewById(R.id.food_type);
        foodQty = sheetView.findViewById(R.id.food_quantity);
        foodFresh = sheetView.findViewById(R.id.food_freshness);
        submit = sheetView.findViewById(R.id.bt_confirm);
        foodDescription = sheetView.findViewById(R.id.food_description);
        veg_btn = sheetView.findViewById(R.id.radio_veg);
        nonveg_btn = sheetView.findViewById(R.id.radio_nonveg);
        final Date currentTime = Calendar.getInstance().getTime();
        final GPSTracker gps = new GPSTracker(getActivity());

        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.cancel();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myref =  FirebaseDatabase.getInstance().getReference();
                myref.child("hotel").child(hotelName.getText().toString()).child("Name").setValue(hotelName.getText().toString());
                myref.child("hotel").child(hotelName.getText().toString()).child("FoodType").setValue(hotelName.getText().toString());
                myref.child("hotel").child(hotelName.getText().toString()).child("Freshness").setValue(foodFresh.getText().toString());
                myref.child("hotel").child(hotelName.getText().toString()).child("Quantity").setValue(foodQty.getText().toString());
                myref.child("hotel").child(hotelName.getText().toString()).child("Location").child("Lat").setValue("22.539970");
                myref.child("hotel").child(hotelName.getText().toString()).child("Location").child("Long").setValue("88.370240");
                myref.child("hotel").child(hotelName.getText().toString()).child("DonationTime").setValue(currentTime.toString());
                myref.child("hotel").child(hotelName.getText().toString()).child("Description").setValue(foodDescription.getText().toString());
                if (veg_btn.isChecked()){
                    myref.child("hotel").child(hotelName.getText().toString()).child("class").setValue("veg");
                }else{
                    myref.child("hotel").child(hotelName.getText().toString()).child("class").setValue("nonveg");
                }
//                myref.child("hotel").child(hotelName.getText().toString()).child("distribution").child("Name").setValue(hotelName.getText().toString());
//                myref.child("hotel").child(hotelName.getText().toString()).child("distribution").child("FoodType").setValue(foodType.getText().toString());
//                myref.child("hotel").child(hotelName.getText().toString()).child("distribution").child("Freshness").setValue(foodFresh.getText().toString());
//                myref.child("hotel").child(hotelName.getText().toString()).child("distribution").child("Quantity").setValue(foodQty.getText().toString());
//                myref.child("hotel").child(hotelName.getText().toString()).child("distribution").child("Location").child("Lat").setValue("22.539970");
//                myref.child("hotel").child(hotelName.getText().toString()).child("distribution").child("Location").child("Long").setValue("88.370240");
//                myref.child("hotel").child(hotelName.getText().toString()).child("distribution").child("DonationTime").setValue(currentTime.toString());
//                myref.child("hotel").child(hotelName.getText().toString()).child("distribution").child("Description").setValue(foodDescription.toString());

            }
        });

        return bottomSheetDialog;
    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }


}
