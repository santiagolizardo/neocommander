/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  MadCommander is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.util.gui;

import com.santiagolizardo.madcommander.AppConstants;

import javax.swing.*;
import java.awt.*;

public class DialogFactory {

	public static void showInformationMessage(Component parent, String message) {
		JOptionPane.showMessageDialog(parent, message, AppConstants.APP_NAME,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showErrorMessage(Component parent, String message) {
		JOptionPane.showMessageDialog(parent, message, AppConstants.APP_NAME,
				JOptionPane.ERROR_MESSAGE);
	}

	public static boolean showQuestionDialog(Component parent, String message) {
		return (JOptionPane.showConfirmDialog(parent, message,
				AppConstants.APP_NAME, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
	}

	public static String showInputDialog(Component parent, String message) {
		return JOptionPane.showInputDialog(parent, message,
				AppConstants.APP_NAME, JOptionPane.INFORMATION_MESSAGE);
	}
}
