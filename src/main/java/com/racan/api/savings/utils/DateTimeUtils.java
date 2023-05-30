package com.racan.api.savings.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class DateTimeUtils {

    private static final String ZONE_ID = "Europe/London";
    private static final int OPENING_HOURS = 8;
    private static final int CLOSING_HOURS = 18;
    private static final int ZERO_MINUTES = 0;
    private static final LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of(ZONE_ID));


    public static boolean isWeekDay() {
        switch (localDateTime.getDayOfWeek()) {
            case SATURDAY:
                return false;
            case SUNDAY:
                return false;
            default:
                return true;
        }
    }

    public static boolean isWorkingHours() {
        LocalTime startingHour = LocalTime.of(OPENING_HOURS, ZERO_MINUTES);
        LocalTime closingHour = LocalTime.of(CLOSING_HOURS, ZERO_MINUTES);
        LocalTime currentHour = LocalTime.of(localDateTime.getHour(), ZERO_MINUTES);
        return isTimeInRange(currentHour, startingHour, closingHour);
    }

    public static boolean isTimeInRange(LocalTime checkDate, LocalTime firstDate, LocalTime secondDate) {
        return !(checkDate.isBefore(firstDate) || checkDate.isAfter(secondDate));
    }

}
