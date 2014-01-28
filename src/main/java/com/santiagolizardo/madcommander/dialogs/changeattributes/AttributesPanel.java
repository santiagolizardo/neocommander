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
package com.santiagolizardo.madcommander.dialogs.changeattributes;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class AttributesPanel extends JPanel {

	private static final long serialVersionUID = 1533698409599832575L;
	
	private JCheckBox permRead;
	private JCheckBox permWrite;
	private JCheckBox permExecute;
	
	private JCheckBox readOnly;
	
	public AttributesPanel() {
		super();
				
		setBorder(BorderFactory.createTitledBorder(" Attributes "));
				
		permRead = new JCheckBox("Read");
		permWrite = new JCheckBox("Write");
		permExecute = new JCheckBox("Execute");
		
		readOnly = new JCheckBox("Read only");
	
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(permRead);
		add(permWrite);
		add(permExecute);
		add(Box.createVerticalStrut(10));
		add(readOnly);
	}
	
	public boolean isReadOnly() {
		return readOnly.isSelected();
	}
}
