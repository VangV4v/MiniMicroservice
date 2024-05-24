package org.vang.minimicroservice.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateCommon {

    public static String getDateTimeCurrent() {

        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String checkDateFormatAndConvert(String dateString) {

        if(dateString == null || dateString.isEmpty()) {
            return null;
        }
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return localDate.toString();
    }

}
