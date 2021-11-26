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

public class OsDetector {

	private static Os currentOs = null;
	
	public static void reset() {
		currentOs = null;
	}

	public static Os get() throws Exception {
		if (null != currentOs) {
			return currentOs;
		}
		final String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("windows")) {
			currentOs = Os.Windows;
		} else if (osName.contains("linux")) {
			currentOs = Os.Linux;
		} else if (osName.contains("osx") || osName.contains("os x")) {
			currentOs = Os.Osx;
		}
		if (currentOs == null) {
			throw new Exception("Unable to recognize OS with name: " + osName);
		}
		return currentOs;
	}
}
