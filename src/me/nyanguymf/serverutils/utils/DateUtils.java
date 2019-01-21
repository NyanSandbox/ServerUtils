/**
 * DateUtils.java 2019-01-20
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.utils;

/**
 * @author nyanguymf
 *
 */
public class DateUtils {
    private final static int DAY_IN_MILLIS = 86_400_000;
    private final static int HOUR_IN_MILLIS = 3_600_000;
    private final static int MIN_IN_MILLIS = 60_000;
    private final static int SEC_IN_MILLIS = 1_000;

    public static String parseTime(long millis) {
        if (millis < SEC_IN_MILLIS)
            return "0";

        StringBuffer time = new StringBuffer();

        int sec     = 0;
        int min     = 0;
        int hours   = 0;
        int days    = 0;

        if (millis >= DAY_IN_MILLIS) {
            days = (int) (millis / DAY_IN_MILLIS);
            millis -= days * DAY_IN_MILLIS;
        }

        if (millis >= HOUR_IN_MILLIS) {
            hours = (int) (millis / HOUR_IN_MILLIS);
            millis -= hours * HOUR_IN_MILLIS;
        }

        if (millis >= MIN_IN_MILLIS) {
            min = (int) (millis / MIN_IN_MILLIS);
            millis -= min * MIN_IN_MILLIS;
        }

        if (millis >= SEC_IN_MILLIS) {
            sec = (int) (millis / SEC_IN_MILLIS);
            millis -= sec * SEC_IN_MILLIS;
        }

        time.append(days != 0 ? days + "d" : "");
        time.append(hours != 0 ? " " + hours + "h" : "");
        time.append(min != 0 ? " " + min + "m" : "");
        time.append(sec != 0 ? " " + sec + "s" : "");

        return time.toString();
    }
}
