package com.anjarul.toDo.helperUtilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    public static String DATE_PATTERN_READABLE = "MMM dd, YYYY";
    public static String DATETIME_PATTERN_READABLE = "MMM dd, YYYY HH:mm a";
    public static String SERVER_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static String HTML_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm";

    // Formats
    public static DateFormat getServerDateFormat() {
        return new SimpleDateFormat(SERVER_DATE_TIME_PATTERN);
    }

    public static DateFormat getHtmlDateFormat() {
        return new SimpleDateFormat(HTML_DATE_TIME_PATTERN);
    }

    public static DateFormat getReadableDateFormat() {
        return new SimpleDateFormat(DATE_PATTERN_READABLE);
    }

    public static DateFormat getReadableDateTimeFormat() {
        return new SimpleDateFormat(DATETIME_PATTERN_READABLE);
    }


    // Impl
    public static String getReadableDate(Date date) {
        return getReadableDateFormat().format(date);
    }
    public static String getReadableDateTime(Date date) {
        return getReadableDateTimeFormat().format(date);
    }

    public static long getDateDifference(Date date) {


        return TimeUnit.DAYS.convert(Math.abs(new Date().getTime() - date.getTime()), TimeUnit.MILLISECONDS);

    }

}
