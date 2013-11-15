/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.util;

import java.awt.Component;
import java.awt.Desktop;
import java.net.URI;

import com.santiagolizardo.madcommander.util.gui.DialogFactory;


public class SystemUtil {

	public static void execute(Component component, String command) {
		Runtime runtime = Runtime.getRuntime();
		String[] args = new String[3];
		args[0] = "cmd.exe";
		args[1] = "/C";
		args[2] = command;

		try {
			runtime.exec(args);
		} catch (Exception e) {
			DialogFactory.showErrorMessage(component, e.getMessage());
		}
	}

	public static void browse(Component component, String address) {
		Desktop desktop = Desktop.getDesktop();

		try {
			desktop.browse(new URI(address));
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
