/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.actions.fileops.FileOpsFactory;

public class ShortcutsPanel extends JPanel {

	private static final long serialVersionUID = 6490973152006379145L;

	private static final Border BUTTON_BORDER = new SoftBevelBorder(
			SoftBevelBorder.RAISED);

	private JButton viewButton;
	private JButton editButton;
	private JButton copyButton;
	private JButton moveButton;
	private JButton createDirButton;
	private JButton deleteButton;

	private MadCommander mainWindow;

	public ShortcutsPanel(MadCommander mainWindow) {
		super();

		this.mainWindow = mainWindow;

		FileOpsFactory fops = FileOpsFactory.getInstance(mainWindow);

		viewButton = new Button(fops.getViewAction());
		editButton = new Button(fops.getEditAction());
		copyButton = new Button(fops.getCopyAction());
		moveButton = new Button(fops.getMoveAction());
		createDirButton = new Button(fops.getCreateDirAction());
		deleteButton = new Button(fops.getDeleteAction());

		defineLayout();
	}

	private void defineLayout() {
		GridBagLayout bagLayout = new GridBagLayout();
		setLayout(bagLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;

		add(viewButton, c);
		add(editButton, c);
		add(copyButton, c);
		add(moveButton, c);
		add(createDirButton, c);
		add(deleteButton, c);
	}

	private class Button extends JButton {

		private static final long serialVersionUID = -409157035363687532L;

		public Button(Action action) {
			super(action);

			setBorder(BUTTON_BORDER);
			setFocusable(false);
		}
	}

	public void refreshButtons(List<File> selectedFiles) {
		int numSelectedFiles = selectedFiles.size();
		boolean oneFileIsSelected = (1 == numSelectedFiles);
		boolean manyFilesAreSelected = (numSelectedFiles > 1);

		viewButton.setEnabled(oneFileIsSelected);
		editButton.setEnabled(oneFileIsSelected);
		copyButton.setEnabled(manyFilesAreSelected);
		moveButton.setEnabled(manyFilesAreSelected);
		deleteButton.setEnabled(manyFilesAreSelected);
	}
}
