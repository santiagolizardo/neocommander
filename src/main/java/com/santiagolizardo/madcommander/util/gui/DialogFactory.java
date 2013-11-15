/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.util.gui;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.santiagolizardo.madcommander.MadCommander;


public class DialogFactory {

	public static void showInformationMessage(Component parent, String message) {
		JOptionPane.showMessageDialog(parent, message, MadCommander.APP_NAME,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showErrorMessage(Component parent, String message) {
		JOptionPane.showMessageDialog(parent, message, MadCommander.APP_NAME,
				JOptionPane.ERROR_MESSAGE);
	}

	public static boolean showQuestionDialog(Component parent, String message) {
		return (JOptionPane.showConfirmDialog(parent, message,
				MadCommander.APP_NAME, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}

	public static String showInputDialog(Component parent, String message) {
		return JOptionPane.showInputDialog(parent, message,
				MadCommander.APP_NAME, JOptionPane.OK_CANCEL_OPTION);
	}
}
