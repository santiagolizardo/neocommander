/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  MadCommander is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarUtil {

    public static long convertDateTime(String date, String time) {
        Calendar calendar = GregorianCalendar.getInstance();
        if(date != null) {
            String[] dates = date.split("/");
        
            int day = Integer.parseInt(dates[0]);
            int month = Integer.parseInt(dates[1])-1;
            int year = Integer.parseInt(dates[2]);
        
            calendar.set(Calendar.DATE, day);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.YEAR, year);
        }

        if(time != null) {
            String[] times = time.split(":");

            int hour = Integer.parseInt(times[0]);
            int minutes = Integer.parseInt(times[1]);
               
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minutes);
        }
        
        return calendar.getTime().getTime();        
    }
}
