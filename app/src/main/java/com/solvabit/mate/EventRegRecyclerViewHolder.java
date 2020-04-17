package com.solvabit.mate;

import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class EventRegRecyclerViewHolder extends RecyclerView.ViewHolder {


    public   TextView sub_event_name, prize;
    public   RadioButton radio_bt;

    public EventRegRecyclerViewHolder(View itemView) {
        super(itemView);

        sub_event_name= itemView.findViewById(R.id.sub_event_name_txt);
        prize = itemView.findViewById(R.id.prize_txt);
        radio_bt = itemView.findViewById(R.id.radio_bt_);
    }


}
