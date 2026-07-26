/*
 * Copyright (C) 2026 Santiago Lizardo
 *
 * This file is part of NeoCommander.
 *
 * NeoCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NeoCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.santiagolizardo.madcommander.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class CalendarUtil {

    public static long convertDateTime(String date, String time) {
        var now = LocalDateTime.now();
        int day = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear();

        if(date != null) {
            String[] dates = date.split("/");
        
            day = Integer.parseInt(dates[0]);
            month = Integer.parseInt(dates[1]);
            year = Integer.parseInt(dates[2]);
        }

        int hour = now.getHour();
        int minutes = now.getMinute();

        if(time != null) {
            String[] times = time.split(":");

            hour = Integer.parseInt(times[0]);
            minutes = Integer.parseInt(times[1]);
        }
        
        var localDateTime = LocalDateTime.of(year, month, day, hour, minutes, now.getSecond(), now.getNano());
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();        
    }
}
