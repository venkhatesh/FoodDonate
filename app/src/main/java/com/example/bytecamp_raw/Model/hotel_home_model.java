package com.example.bytecamp_raw.Model;

public class hotel_home_model {
    private String date;
    private String time;
    private String Status;
    private int status_progress;

    public hotel_home_model(String date, String time, String status, int status_progress) {
        this.date = date;
        this.time = time;
        this.Status = status;
        this.status_progress = status_progress;
    }

    public String getDate() {
        return date;
    }



    public String getTime() {
        return time;
    }



    public String getStatus() {
        return Status;
    }



    public int getStatus_progress() {
        return status_progress;
    }





}
