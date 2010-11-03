/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: LockManager.java,v 1.9 2010/01/21 17:02:48 slizardo Exp $
 */
package org.slizardo.madcommander.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.slizardo.madcommander.MadCommander;
import org.slizardo.madcommander.config.ConfigWrapper;
import org.slizardo.madcommander.util.gui.DialogFactory;


/**
 * Controls if the application can run multiple instances at the same time.
 * 
 * @author Santiago Lizardo
 * 
 */
public final class LockManager {

	private static Logger logger = Logger
			.getLogger(LockManager.class.getName());

	private LockManager() {
	}

	public static void check() throws IOException {
		boolean allowInstances = ConfigWrapper
				.getBooleanProperty("allow.instances");

		if (!allowInstances) {
			String tempDir = System.getProperty("java.io.tmpdir");
			String lockPath = tempDir.concat(File.separator).concat(
					MadCommander.APP_NAME).concat(".lock");
			logger.info(lockPath);

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
