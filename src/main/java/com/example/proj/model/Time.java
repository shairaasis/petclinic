package com.example.proj.model;

public class Time {
    private int timeId;
    private String time;
    public Time() {}

    public Time(int timeId, String time) {
        this.timeId = timeId;
        this.time = time;
    }
    public int getTimeId() {
        return timeId;
    }
    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    
}
