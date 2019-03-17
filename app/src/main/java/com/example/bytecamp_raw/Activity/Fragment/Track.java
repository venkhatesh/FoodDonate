package com.example.bytecamp_raw.Activity.Fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bytecamp_raw.Activity.MissionDetail;
import com.example.bytecamp_raw.Model.HotelModel;
import com.example.bytecamp_raw.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by venkat on 17/3/19.
 */
public class Track extends Fragment {

    RecyclerView recyclerView;
    TrackAdapter adapter;
    List<String> ngoList,Location;
    private DatabaseReference mDatabase;
    String TAG = "Track";
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.track_fragment, container, false);


        recyclerView = (RecyclerView)view.findViewById(R.id.ngo_reycler);
        ngoList = new ArrayList<>();
        Location = new ArrayList<>();
        adapter = new TrackAdapter(ngoList,Location);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("hotel").child("Rangoli").child("Pickup Requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Log.d("Track", "onDataChange: " + snapshot.getKey());
                    ngoList.clear();
                    Location.clear();
                    ngoList.add(snapshot.getKey());
                    Location.add((String) snapshot.getValue());
                }
                recyclerView.setAdapter(adapter);

                Log.d(TAG, "onDataChange: " + ngoList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adapter.notifyDataSetChanged();

        return view;
    }

}

class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.MyViewHolder>{
    String TAG = "Track";
    List<String> ngoList,Location;
    private DatabaseReference mDatabase;
    public TrackAdapter(List<String> ngoList, List<String> Location){
        this.ngoList = ngoList;
        this.Location = Location;
    }
    @NonNull
    @Override
    public TrackAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ngo_list, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrackAdapter.MyViewHolder myViewHolder, int i) {
            myViewHolder.ngoName.setText(ngoList.get(i));
            myViewHolder.ngoLocation.setText(Location.get(i));
        mDatabase = FirebaseDatabase.getInstance().getReference();

        myViewHolder.accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDatabase.child("NGO").child(myViewHolder.ngoName.getText().toString()).child("flag").setValue("true");
                }
            });
        myViewHolder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("NGO").child(myViewHolder.ngoName.getText().toString()).child("flag").setValue("false");

            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+ngoList.size());
        return ngoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ngoName,ngoLocation;
        Button accept,reject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ngoName = itemView.findViewById(R.id.ngo_name);
            ngoLocation = itemView.findViewById(R.id.ngo_location);
            accept = itemView.findViewById(R.id.accept_btn);
            reject = itemView.findViewById(R.id.reject_btn);
        }
    }
}

