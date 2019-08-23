

package com.google.samples.apps.sunflower.data;

import java.util.Calendar;

import androidx.room.TypeConverter;


public class Converters {

    @TypeConverter
    public long calendarToDatestamp(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    @TypeConverter
    public Calendar datestampToCalendar(long value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(value);
        return calendar;
    }
}
