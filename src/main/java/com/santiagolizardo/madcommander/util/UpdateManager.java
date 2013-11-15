/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;


public final class UpdateManager extends Thread {

	public final static String DOWNLOAD_URL = "http://sourceforge.net/projects/madcommander/files/";
	
	public static void checkForUpdate() {
		UpdateManager updateManager = new UpdateManager();
		updateManager.start();
	}

	private UpdateManager() {

	}

	public void run() {
		try {
			URL url = new URL("http://madcommander.sourceforge.net/version.html");
			InputStreamReader reader = new InputStreamReader(url.openStream());
			BufferedReader buffer = new BufferedReader(reader);
			String version = buffer.readLine();
			buffer.close();
			reader.close();
			int serverVersion = Integer.valueOf(version.replaceAll("\\.", ""))
					.intValue();
			int currentVersion = Integer.valueOf(
					MadCommander.APP_VERSION.replaceAll("\\.", "")).intValue();
			if (serverVersion > currentVersion) {
				StringBuffer text = new StringBuffer();
				text.append("New version \"");
				text.append(version);
				text
						.append("\" available.\n\nDo you want to go to the download site?\n");
				if (DialogFactory.showQuestionDialog(null, text.toString())) {
					SystemUtil.browse(null, DOWNLOAD_URL);
				}
			} else if (serverVersion <= currentVersion) {
				DialogFactory.showInformationMessage(null,
						"There are no updates availables.");
			}

		} catch (Exception e) {
			DialogFactory.showErrorMessage(null, e.getMessage());
		}

	}
}
