/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
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
