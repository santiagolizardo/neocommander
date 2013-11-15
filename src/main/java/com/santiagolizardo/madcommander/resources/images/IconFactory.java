/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.resources.images;

import java.io.File;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class IconFactory {

	private static ImageIcon parentIcon;
	private static JFileChooser chooser;
	
	static {
		try {
			parentIcon = newIcon("parent_dir.gif");
			chooser = new JFileChooser();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ImageIcon newIcon(String name) {
		URL url = IconFactory.class.getResource(name);
		if (url == null) {
			return null;
		} else {
			return new ImageIcon(url);
		}
	}

	public static Icon getIconForFile(File file) {
        if(file == null) return null;
        if(!file.exists()) return null;
		if ("..".equals(file.getName())) {
			return parentIcon;
		} else {
			return chooser.getIcon(file);
		}
	}	
}
