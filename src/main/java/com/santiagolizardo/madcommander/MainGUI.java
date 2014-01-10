/**
 * MadCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * MadCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * or the MadCommander website <http://sourceforge.net/projects/madcommander>. 
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander;

import javax.swing.SwingUtilities;

import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.services.LoggingServices;
import com.santiagolizardo.madcommander.util.gui.SwingUtil;

/**
 * Application entry point.
 */
public class MainGUI {

	public static void main(String[] args) {

		try {
			LoggingServices.init();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		String locale = "en_US";
		Translator.init(locale);

		// LockManager.check();

		/*
		 * We put the main frame in the event-dispatching thread
		 */
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SwingUtil.setSystemLookAndFeel();

				MadCommander app = new MadCommander();
				app.init();
				app.setVisible(true);
			}
		});
	}
}
