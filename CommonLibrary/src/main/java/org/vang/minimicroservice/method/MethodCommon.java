package org.vang.minimicroservice.method;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Random;

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
}