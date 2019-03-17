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
import android.widget.Button;
import android.widget.TextView;

import com.example.bytecamp_raw.Activity.Fragment.AddFoodFragment;
import com.example.bytecamp_raw.Adapter.hotel_home;
import com.example.bytecamp_raw.Model.hotel_home_model;
import com.example.bytecamp_raw.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Hotel_Home extends Fragment {
    private RecyclerView status;
    private List<hotel_home_model> listItems;
    CardView testData;
    FloatingActionButton fab;
    private CardView card1;
    private CardView card2;
    private CardView card3;
    RecyclerView recyclerView;
    statusAdapter adapter;
    ArrayList<String> FoodStatus,FoodDate,FoodTime;
    private DatabaseReference mDatabase;


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

        recyclerView = (RecyclerView)view.findViewById(R.id.status_recycler);
        FoodDate = new ArrayList<>();
        FoodStatus = new ArrayList<>();
        FoodTime = new ArrayList<>();
        adapter = new statusAdapter(FoodStatus,FoodTime,FoodDate);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("hotel").child("Rangoli").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    if (snapshot.getKey().equals("DonationTime")){
                        FoodDate.add(snapshot.getValue().toString());
                        FoodTime.add(snapshot.getValue().toString());
                    }
                    if (snapshot.getKey().equals("Status")){
                        FoodStatus.add(snapshot.getValue().toString());
                    }
                }
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }

}

class statusAdapter extends RecyclerView.Adapter<statusAdapter.MyViewHolder>{
    ArrayList<String> FoodDate,FoodTime,FoodStatus;
    private DatabaseReference mDatabase;


    public statusAdapter(ArrayList<String> FoodStatus, ArrayList<String> FoodTime,ArrayList<String> FoodDate){
        this.FoodDate = FoodDate;
        this.FoodTime = FoodTime;
        this.FoodStatus = FoodStatus;
    }
    @NonNull
    @Override
    public statusAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.status_list, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull statusAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.foodStatus.setText(FoodStatus.get(i));
        myViewHolder.foodTime.setText(FoodTime.get(i));
        myViewHolder.foodDate.setText(FoodDate.get(i));
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public int getItemCount() {
        return FoodDate.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView foodDate,foodTime,foodStatus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foodDate = itemView.findViewById(R.id.food_date);
            foodTime = itemView.findViewById(R.id.food_time);
            foodStatus = itemView.findViewById(R.id.food_status);

        }
    }
}