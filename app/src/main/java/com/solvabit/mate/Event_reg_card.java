package com.solvabit.mate;

public class Event_reg_card {
    String sub_event_name;
    String prize;

    public Event_reg_card(String sub_event_name, String prize){
        this.sub_event_name= sub_event_name;
        this.prize= prize;
    }

    public String getSub_event_name() {
        return sub_event_name;
    }

    public void setSub_event_name(String sub_event_name) {
        this.sub_event_name = sub_event_name;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
