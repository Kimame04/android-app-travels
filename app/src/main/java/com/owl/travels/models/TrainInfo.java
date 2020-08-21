package com.owl.travels.models;

public class TrainInfo {
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
    }

    public String getTerminus() {
        return terminus;
    }

    public String getStatus() {
        return status;
    }
}
