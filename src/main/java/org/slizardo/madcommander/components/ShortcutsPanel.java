/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: ShortcutsPanel.java,v 1.12 2010/01/22 17:57:42 slizardo Exp $
 */
package org.slizardo.madcommander.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

import org.slizardo.madcommander.actions.fileops.FileOpsFactory;


public class ShortcutsPanel extends JPanel {

	private static final Border BUTTON_BORDER = new SoftBevelBorder(
			SoftBevelBorder.RAISED);

	private JButton view;

	private JButton edit;

	private JButton copy;

	private JButton move;

	private JButton createDir;

	private JButton delete;

	public ShortcutsPanel() {
		super();

		FileOpsFactory fops = FileOpsFactory.getInstance();
		
		view = new Button(fops.getViewAction());
		edit = new Button(fops.getEditAction());
		copy = new Button(fops.getCopyAction());
		move = new Button(fops.getMoveAction());
		createDir = new Button(fops.getCreateDirAction());
		delete = new Button(fops.getDeleteAction());

		defineLayout();
	}

	private void defineLayout() {
		GridBagLayout bagLayout = new GridBagLayout();
		setLayout(bagLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;

		add(view, c);
		add(edit, c);
		add(copy, c);
		add(move, c);
		add(createDir, c);
		add(delete, c);
	}

	private class Button extends JButton {

		public Button(Action action) {
			super(action);

			setBorder(BUTTON_BORDER);
			setFocusable(false);
		}
	}
}
