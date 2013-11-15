/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.dialogs.delete;

import java.awt.Component;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.santiagolizardo.madcommander.resources.images.IconFactory;


public class FileRenderer extends JLabel implements ListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1487522844646855287L;

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean selected, boolean cellHasFocus) {
		String fileName = value.toString();
		File file = new File(fileName); 
		setText(file.getName());
		setOpaque(true);
		setIcon(IconFactory.getIconForFile(file));
		if(selected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());					
		}
		
		return this;
	}	
}
