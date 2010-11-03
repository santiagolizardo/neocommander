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
 * @version $Id: Main.java,v 1.17 2010/01/21 17:02:48 slizardo Exp $
 */
package org.slizardo.madcommander;

import javax.swing.SwingUtilities;

import org.slizardo.madcommander.config.ConfigWrapper;
import org.slizardo.madcommander.resources.languages.Translator;
import org.slizardo.madcommander.services.LoggingServices;
import org.slizardo.madcommander.util.LockManager;
import org.slizardo.madcommander.util.gui.SwingUtil;


/**
 * Application entry point.
 */
public class MainGUI {

	/*
	 * This public static member can be acceded anywhere of application
	 */
	public static MadCommander app;

	public static void main(String[] args) {

		try {
			LoggingServices.init();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		try {
			ConfigWrapper.init();
			Translator.init();

			LockManager.check();

			String lookAndFeel = ConfigWrapper.getProperty("app.lookandfeel");
			if ("system".equals(lookAndFeel))
				SwingUtil.setSystemLookAndFeel();
			else
				SwingUtil.setCrossPlatformLookAndFeel();

			/*
			 * We put the main frame in the event-dispatching thread
			 */
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					app = new MadCommander();
					app.init();
					app.setVisible(true);
				}
			});
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
