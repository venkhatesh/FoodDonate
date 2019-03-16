package com.example.bytecamp_raw.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bytecamp_raw.Activity.Fragment.AddFoodFragment;
import com.example.bytecamp_raw.Adapter.hotel_home;
import com.example.bytecamp_raw.Model.hotel_home_model;
import com.example.bytecamp_raw.R;

import java.util.ArrayList;
import java.util.List;

public class Hotel_Home extends Fragment {
    private RecyclerView contributions;
    private RecyclerView.Adapter contriAdapter;
    private List<hotel_home_model> listItems;
    CardView testData;
    FloatingActionButton fab;
    private CardView card1;
    private CardView card2;
    private CardView card3;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hotel__home, container, false);
        listItems=new ArrayList<>();
        listItems.add(new hotel_home_model("12 Jan 2018","12:30 pm","IN PROGRESS",1));
        listItems.add(new hotel_home_model("13 Jan 2018","12:30 pm","DONATED",2));
        listItems.add(new hotel_home_model("14 Jan 2018","12:30 pm","DONATED",2));
        listItems.add(new hotel_home_model("15 Jan 2018","12:30 pm","COMPOST",3));
        listItems.add(new hotel_home_model("16 Jan 2018","12:30 pm","COMPOST",3));

        return view;
    }

}
