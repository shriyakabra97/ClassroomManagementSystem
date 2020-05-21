package com.spe.ClassroomManagementSystem.Utils;

import java.sql.Date;
import java.util.Calendar;

public class DateUtils {
    public static int getDayOfTheWeekFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfTheWeekIndex = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfTheWeekIndex;
    }
}
