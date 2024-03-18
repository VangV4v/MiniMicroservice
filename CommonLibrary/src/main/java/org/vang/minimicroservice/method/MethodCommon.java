package org.vang.minimicroservice.method;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Random;

public class MethodCommon {

    public static Long generateAggregateIdentifier() {

        return System.currentTimeMillis() * 10;
    }
}