package com.spe.ClassroomManagementSystem.Service;

import java.sql.Time;

public class Interval {
    Time startTime;
    Time endTime;

    public Interval(Time startTime, Time endTime){
        this.startTime=startTime;
        this.endTime=endTime;
    }
}
