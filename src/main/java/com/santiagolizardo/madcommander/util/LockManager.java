/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import com.santiagolizardo.madcommander.MadCommander;
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
					.concat(MadCommander.APP_NAME).concat(".lock");
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
