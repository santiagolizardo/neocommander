/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: CalendarUtil.java,v 1.2 2006/03/21 17:25:40 slizardo Exp $
 */
package org.slizardo.madcommander.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarUtil {

    public static long convertDateTime(String date, String time) {
        Calendar calendar = GregorianCalendar.getInstance();
        if(date != null) {
            String[] dates = date.split("/");
        
            int day = Integer.valueOf(dates[0]).intValue();
            int month = Integer.valueOf(dates[1]).intValue()-1;
            int year = Integer.valueOf(dates[2]).intValue();
        
            calendar.set(Calendar.DATE, day);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.YEAR, year);
        }

        if(time != null) {
            String[] times = time.split(":");

            int hour = Integer.valueOf(times[0]).intValue();
            int minutes = Integer.valueOf(times[1]).intValue();
               
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minutes);
        }
        
        return calendar.getTime().getTime();        
    }
}
