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

import com.santiagolizardo.madcommander.MainWindow;
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

	private MainWindow mainWindow;

	public ShortcutsPanel(MainWindow mainWindow) {
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
