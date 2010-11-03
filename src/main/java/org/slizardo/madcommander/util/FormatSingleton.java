/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FormatSingleton.java,v 1.1 2006/01/22 22:04:33 slizardo Exp $
 */
package org.slizardo.madcommander.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class FormatSingleton {

	private static SimpleDateFormat dateFormat;

	private static DecimalFormat decimalFormat;

	public static SimpleDateFormat getSimpleDateFormat() {
		if (dateFormat == null) {
			dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
		}

		return dateFormat;
	}

	public static DecimalFormat getSimpleDecimalFormat() {
		if (decimalFormat == null) {
			decimalFormat = new DecimalFormat("###,###");
		}

		return decimalFormat;
	}
}
