package com.example.bytecamp_raw.Activity.Fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bytecamp_raw.Activity.MissionDetail;
import com.example.bytecamp_raw.Adapter.HotelsAdapter;
import com.example.bytecamp_raw.Model.HotelModel;
import com.example.bytecamp_raw.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

/**
 * Created by venkat on 23/2/19.
 */
public class DistributionFragment extends Fragment {

    RecyclerView recyclerView;
    HotelsAdapter adapter;
    HotelModel model;
    ArrayList<HotelModel> hotelList;
    private DatabaseReference mDatabase;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.distribution_fragment, container, false);
        recyclerView = view.findViewById(R.id.distribution_recycler);
        hotelList = new ArrayList<HotelModel>();
        adapter = new HotelsAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("hotel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hotelList.clear();
                for (DataSnapshot uniquesnapshot : dataSnapshot.getChildren()){
                    Log.d("DetailActivity ", "Food Type " + uniquesnapshot.getValue(HotelModel.class).getName());

                    hotelList.add(uniquesnapshot.getValue(HotelModel.class));
                }

                Log.d(TAG, "onDataChange: siiize " + hotelList.size());
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

    public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.MyViewHolder> {

        public HotelsAdapter(Context context) {
            this.context = context;
            Log.d(ContentValues.TAG, "HotelsAdapter: size " + hotelList.size());
        }
        Context context;

        @NonNull
        @Override
        public HotelsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.hotel_card, viewGroup, false);
            return new HotelsAdapter.MyViewHolder(itemView);
        }



        @Override
        public void onBindViewHolder(@NonNull final HotelsAdapter.MyViewHolder myViewHolder, int i) {
            final HotelModel hotelModel = hotelList.get(i);
            myViewHolder.hotelName.setText(hotelModel.getFoodType());
            myViewHolder.description.setText(hotelModel.getDescription());
            myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Detailes = new Intent(context, MissionDetail.class);
                    Detailes.putExtra("name",hotelModel.getName());
                    context.startActivity(Detailes);
                }
            });
            Log.d("HotelsAdapter", "onBindViewHolder: " + hotelModel.getName());
        }

        @Override
        public int getItemCount() {
            return (hotelList == null) ? 0 : hotelList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView hotelName, description, year, genre;
            CardView cardView;

            public MyViewHolder(View view) {
                super(view);
                hotelName = (TextView) view.findViewById(R.id.hotel_nm);
                description = (TextView) view.findViewById(R.id.hotel_desptn);
                cardView = view.findViewById(R.id.card_view);
            }
        }
    }

}
