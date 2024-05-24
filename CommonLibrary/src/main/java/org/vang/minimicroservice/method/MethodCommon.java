package org.vang.minimicroservice.method;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MethodCommon {

    public static Long generateAggregateIdentifier() {

        return System.currentTimeMillis() * 10;
    }

    public static int getIndexById(String id) {
        for (int i = 0; i < id.length(); i++) {
            char characterOfPoint = id.charAt(i);
            char characterOfPointNext = id.charAt(i+1);
            if(((int)characterOfPoint < 65 || characterOfPoint > 90) && ((int)characterOfPointNext < 65 || characterOfPointNext > 90)) {
                return i;
            }
        }
        return -1;
    }

    public static Date convertStringToDate(String dateString) {

        if(dateString == null || dateString.isEmpty()) {
            return null;
        }
        Date date = null;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString);
        date = Date.valueOf(localDate);
        return date;
    }

    public static Timestamp convertStringToTimestamp(String dateString) {

        Timestamp timestamp = null;
        if(dateString == null || dateString.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
        timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
    }
}