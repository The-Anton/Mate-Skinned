package com.solvabit.mate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventRegRecyclerViewAdapter extends RecyclerView.Adapter<EventRegRecyclerViewHolder> {
    registerevent registerevent;
    ArrayList<Event_reg_card> eventsArrayList;

    public EventRegRecyclerViewAdapter(registerevent registerevent, ArrayList<Event_reg_card> eventsArrayList){
        this.registerevent= registerevent;
        this.eventsArrayList = eventsArrayList;
    }
    @NonNull
    @Override
    public EventRegRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context;
        LayoutInflater layoutInflater = LayoutInflater.from(registerevent.getBaseContext());
        View view = layoutInflater.inflate(R.layout.card_elements, parent,false);

        return new EventRegRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventRegRecyclerViewHolder holder, int position) {
        holder.sub_event_name.setText(eventsArrayList.get(position).getSub_event_name());
        holder.prize.setText(eventsArrayList.get(position).getPrize());
    }

    @Override
    public int getItemCount() {
        return eventsArrayList.size();
    }
}
