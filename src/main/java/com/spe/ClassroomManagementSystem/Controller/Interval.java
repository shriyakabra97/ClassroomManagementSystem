package com.spe.ClassroomManagementSystem.Controller;

import java.sql.Time;

public class Interval {
    Time startTime;
    Time endTime;

    public Interval(Time startTime, Time endTime){
        this.startTime=startTime;
        this.endTime=endTime;
    }
}
