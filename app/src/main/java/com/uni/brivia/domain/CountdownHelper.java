package com.uni.brivia.domain;

import androidx.annotation.NonNull;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;


/**
 * Static helper methods to calculate dates/millis related to the countdown timer in Home Screen.
 */
public class CountdownHelper {

    /**
     * @return milliseconds until the the start of a new day. It uses {@link Calendar}
     */
    @NonNull
    public static Long millisToTheEndOfDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return (c.getTimeInMillis() - System.currentTimeMillis());
    }


    /**
     * @return Formatted timer string based on the remaining millis. The format is HH:MM:SS
     */
    @NonNull
    public static String formatRemainingMillis(Long millisUntilFinished) {
        long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(hours);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.HOURS.toSeconds(hours) - TimeUnit.MINUTES.toSeconds(minutes);

        String minutesFormatted;
        if (minutes < 10) {
            minutesFormatted = "0" + minutes;
        } else {
            minutesFormatted = String.valueOf(minutes);
        }

        String secondsFormatted;
        if (seconds < 10) {
            secondsFormatted = "0" + seconds;
        } else {
            secondsFormatted = String.valueOf(seconds);
        }

        return hours + ":" + minutesFormatted + ":" + secondsFormatted;
    }


    /**
     * @return The percentage of how much is left until the day is done. (0 - 100)
     */
    @NonNull
    public static Integer remainingPercentage(Long millisUntilFinished) {
        long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
        double div = (double) hours / 24;
        return (int) (div * 100);
    }
}
