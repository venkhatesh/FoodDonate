package com.example.bytecamp_raw.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bytecamp_raw.Activity.MissionDetail;
import com.example.bytecamp_raw.Model.HotelModel;
import com.example.bytecamp_raw.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by venkat on 24/2/19.
 */
public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.MyViewHolder> {

    public HotelsAdapter(ArrayList<HotelModel> hotelModels, Context context) {
        this.hotelModels = hotelModels;
        this.context = context;
        Log.d(TAG, "HotelsAdapter: size " + hotelModels.size());
    }
    public ArrayList<HotelModel> hotelModels;
    Context context;

    @NonNull
    @Override
    public HotelsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hotel_card, viewGroup, false);
        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull final HotelsAdapter.MyViewHolder myViewHolder, int i) {
                final HotelModel hotelModel = hotelModels.get(i);
                myViewHolder.hotelName.setText(hotelModel.getName());
                myViewHolder.description.setText(hotelModel.getDescription());
                myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent Detailes = new Intent(context, MissionDetail.class);
                        Detailes.putExtra("name",hotelModel.getName());
                        context.startActivity(Detailes);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return (hotelModels == null) ? 0 : hotelModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
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
