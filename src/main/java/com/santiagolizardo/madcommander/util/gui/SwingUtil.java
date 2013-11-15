/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
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
