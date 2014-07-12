/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MadCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatSingleton {

	private static DateFormat dateFormat;
	private static NumberFormat decimalFormat;

	public static DateFormat getSimpleDateFormat() {
		if (dateFormat == null) {
			dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.getDefault());
		}

		return dateFormat;
	}

	public static NumberFormat getSimpleDecimalFormat() {
		if (decimalFormat == null) {
			decimalFormat = DecimalFormat.getNumberInstance(Locale.getDefault());
		}

		return decimalFormat;
	}
}
