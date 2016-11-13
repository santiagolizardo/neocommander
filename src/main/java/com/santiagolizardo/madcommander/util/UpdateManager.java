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

import com.santiagolizardo.madcommander.AppConstants;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import java.io.IOException;


public final class UpdateManager extends Thread {
	
	public static void checkForUpdate() {
		UpdateManager updateManager = new UpdateManager();
		updateManager.start();
	}

	private UpdateManager() {

	}

	@Override
	public void run() {
		try {
			URL url = new URL("http://madcommander.sourceforge.net/version.html");
			String version;
			try (InputStreamReader reader = new InputStreamReader(url.openStream()); BufferedReader buffer = new BufferedReader(reader)) {
				version = buffer.readLine();
			}
			int serverVersion = Integer
					.parseInt(version.replaceAll("\\.", ""));
			int currentVersion = Integer.parseInt(AppConstants.APP_VERSION.replaceAll("\\.", ""));
			if (serverVersion > currentVersion) {
				StringBuilder text = new StringBuilder();
				text.append("New version \"");
				text.append(version);
				text
						.append("\" available.\n\nDo you want to go to the download site?\n");
				if (DialogFactory.showQuestionDialog(null, text.toString())) {
					SystemUtil.browse(null, AppConstants.DOWNLOAD_URL);
				}
			} else if (serverVersion <= currentVersion) {
				DialogFactory.showInformationMessage(null,
						"There are no updates availables.");
			}

		} catch (IOException | NumberFormatException e) {
			DialogFactory.showErrorMessage(null, e.getMessage());
		}

	}
}
