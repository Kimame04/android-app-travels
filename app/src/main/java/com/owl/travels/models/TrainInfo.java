package com.owl.travels.models;

import java.util.ArrayList;

public class TrainInfo {
    private static ArrayList<TrainInfo> list = new ArrayList<>();
    private String terminus;
    private String status;
    public TrainInfo(int mins, String terminus){
        this.terminus = terminus;
        if (mins>0)
            this.status = mins + " minutes";
        else if (mins == 0)
            this.status = "Arriving";
        else
            this.status = "Do not board";
        list.add(this);
    }

    public String getTerminus() {
        return terminus;
    }

    public String getStatus() {
        return status;
    }

    public static ArrayList<TrainInfo> getList() {
        return list;
    }
}
