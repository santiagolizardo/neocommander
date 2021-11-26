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
package com.santiagolizardo.madcommander.util.gui;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class SwingUtil {
	
	private static final Logger logger = Logger.getLogger(SwingUtil.class.getName());

	private static final Toolkit toolkit;

	static {
		toolkit = Toolkit.getDefaultToolkit();
	}
    
    public static void beep() {
        toolkit.beep();
    }

	public static void setSystemLookAndFeel() {
		setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}

	public static void setCrossPlatformLookAndFeel() {
		setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	}

	private static void setLookAndFeel(String className) {
		try {
			UIManager.setLookAndFeel(className);
		} catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException cnf) {
			logger.warning(cnf.getMessage());
		}
	}
}
