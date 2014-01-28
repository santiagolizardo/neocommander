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

import java.awt.Toolkit;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SwingUtil {

	private static Toolkit toolkit;

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
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (UnsupportedLookAndFeelException ulaf) {
			ulaf.printStackTrace();
		} catch (IllegalAccessException ia) {
			ia.printStackTrace();
		} catch (InstantiationException i) {
			i.printStackTrace();
		}
	}
}
