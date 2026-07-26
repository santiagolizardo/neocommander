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

import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatSingleton {

	private static DateTimeFormatter dateTimeFormatter;
	private static NumberFormat decimalFormat;

	public static DateTimeFormatter getDateTimeFormatter() {
		if (dateTimeFormatter == null) {
			dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(java.time.format.FormatStyle.SHORT, java.time.format.FormatStyle.MEDIUM).withLocale(Locale.getDefault());
		}

		return dateTimeFormatter;
	}

	public static NumberFormat getSimpleDecimalFormat() {
		if (decimalFormat == null) {
			decimalFormat = DecimalFormat.getNumberInstance(Locale.getDefault());
		}

		return decimalFormat;
	}
}
