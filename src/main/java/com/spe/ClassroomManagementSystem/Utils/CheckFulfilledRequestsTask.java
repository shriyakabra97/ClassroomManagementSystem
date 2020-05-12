package com.spe.ClassroomManagementSystem.Utils;

import java.sql.Date;
import java.util.TimerTask;

public class CheckFulfilledRequestsTask extends TimerTask {

    @Override
    public void run() {
        Date dateToday = new Date(System.currentTimeMillis());
        System.out.println(dateToday);
    }
}
