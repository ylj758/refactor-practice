package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {
        int year, month, date, hour, minute;

        Time yearTime = new Time(0, 4, "Year string is less than 4 characters", "Year is not an integer", 2000, 2012, "Year cannot be less than 2000 or more than 2012");
        year = getTime(yearTime);

        Time monthTime = new Time(5, 7, "Month string is less than 2 characters", "Month is not an integer", 1, 12, "Month cannot be less than 1 or more than 12");
        month = getTime(monthTime);

        Time dateTime = new Time(8, 10, "Date string is less than 2 characters", "Date is not an integer", 1, 31, "Date cannot be less than 1 or more than 31");
        date = getTime(dateTime);

        if (dateAndTimeString.substring(11, 12).equals("Z")) {
            hour = 0;
            minute = 0;
        } else {
            Time hourTime = new Time(11, 13, "Hour string is less than 2 characters", "Hour is not an integer", 0, 23, "Hour cannot be less than 0 or more than 23");
            hour = getTime(hourTime);

            Time minuteTime = new Time(14, 16, "Minute string is less than 2 characters", "Minute is not an integer", 0, 59, "Minute cannot be less than 0 or more than 59");
            minute = getTime(minuteTime);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(year, month - 1, date, hour, minute, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private int getTime(Time time) {
        int year;
        try {
            String yearString = dateAndTimeString.substring(time.getStart(), time.getEnd());
            year = Integer.parseInt(yearString);
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(time.getStringIndexOutOfBoundsException());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(time.getNumberFormatException());
        }
        if (year < time.getMin() || year > time.getMax())
            throw new IllegalArgumentException(time.getIllegalArgumentException());
        return year;
    }
}
