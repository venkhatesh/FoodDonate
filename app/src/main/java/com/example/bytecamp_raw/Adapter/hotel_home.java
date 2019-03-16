package com.example.bytecamp_raw.Adapter;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bytecamp_raw.Model.hotel_home_model;
import com.example.bytecamp_raw.R;

import java.util.List;
import java.util.concurrent.TimeoutException;


public class hotel_home extends RecyclerView.Adapter<hotel_home.ViewHolder> {

    private Context context;
    private List<hotel_home_model> listItems;
    public hotel_home(Context context, List listitem){
        this.context=context;
        this.listItems=listitem;


    }

    @NonNull
    @Override
    public hotel_home.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hotel_home_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull hotel_home.ViewHolder viewHolder, int i) {
        int item = viewHolder.getAdapterPosition();

//        listItems.get(item).;

        viewHolder.date.setText(listItems.get(item).getDate());
        viewHolder.time.setText(listItems.get(item).getTime());
        viewHolder.status.setText(listItems.get(item).getStatus());
        switch(listItems.get(item).getStatus_progress())
        {
            case 1 :
                viewHolder.status.setTextColor(context.getResources().getColor(R.color.red));
                break;
            case 2:
                viewHolder.status.setTextColor(context.getResources().getColor(R.color.orange));
                break;
            case 3:
                viewHolder.status.setTextColor(context.getResources().getColor(R.color.green));
                break;
        }
//        viewHolder.status_progress.equals(1);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView time;
        public TextView status;
        public Integer status_progress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date= (TextView)itemView.findViewById(R.id.date_list);
            time=(TextView)itemView.findViewById(R.id.time_list);

        }
    }
}
