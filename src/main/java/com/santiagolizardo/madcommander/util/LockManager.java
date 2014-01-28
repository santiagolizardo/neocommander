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

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;

/**
 * Controls if the application can run multiple instances at the same time.
 * 
 * @author Santiago Lizardo
 * 
 */
public final class LockManager {

	private static final Logger LOGGER = Logger.getLogger(LockManager.class
			.getName());

	private LockManager() {
	}

	public static void check() throws IOException {
		boolean allowInstances = false;

		if (!allowInstances) {
			String tempDir = System.getProperty("java.io.tmpdir");
			String lockPath = tempDir.concat(File.separator)
					.concat(MainWindow.APP_NAME).concat(".lock");
			LOGGER.info(lockPath);

			File lock = new File(lockPath);
			if (lock.exists()) {
				DialogFactory.showErrorMessage(null,
						"You can not run multiple instances of MadCommander!");
				System.exit(1);
			} else {
				lock.createNewFile();
				lock.deleteOnExit();
			}
		}
	}
}
