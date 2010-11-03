/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FileRenderer.java,v 1.2 2006/03/21 17:25:39 slizardo Exp $
 */
package org.slizardo.madcommander.dialogs.delete;

import java.awt.Component;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.slizardo.madcommander.resources.images.IconFactory;


public class FileRenderer extends JLabel implements ListCellRenderer {

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
