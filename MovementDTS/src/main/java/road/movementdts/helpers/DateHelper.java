package road.movementdts.helpers;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Niek on 14/05/14.
 *  Aidas 2014
 */
public class DateHelper
{
    public static Pair<Calendar, Calendar> getDateRange(int month, int year) {
        Calendar begining, end;

        {
            Calendar calendar = getCalendarForNow(month, year);
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            setTimeToBeginningOfDay(calendar);
            begining = calendar;
        }

        {
            Calendar calendar = getCalendarForNow(month, year);
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            setTimeToEndofDay(calendar);
            end = calendar;
        }

        return new Pair<>(begining, end);
    }

    private static Calendar getCalendarForNow(int month, int year) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());

        calendar.set(Calendar.MONTH, month -1);
        calendar.set(Calendar.YEAR, year);
        return calendar;
    }

    private static void setTimeToBeginningOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    private static void setTimeToEndofDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }
}
