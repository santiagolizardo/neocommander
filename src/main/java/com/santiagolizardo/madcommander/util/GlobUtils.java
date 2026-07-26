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

import java.util.regex.Pattern;

public class GlobUtils {

	public static Pattern convertGlobToRegexp(String glob) {
		String regexp = glob.replace(".", "\\.");
		regexp = regexp.replace("?", ".");
		regexp = regexp.replace("*", ".*");
		regexp = "^" + regexp + "$";
		return Pattern.compile(regexp);
	}
}
