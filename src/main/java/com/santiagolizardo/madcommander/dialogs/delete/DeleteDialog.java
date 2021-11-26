/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  MadCommander is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.dialogs.delete;

import com.santiagolizardo.madcommander.resources.languages.Translator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeleteDialog extends JDialog {


	private final DefaultListModel<File> model;
	private final JList<File> list;
	private final JScrollPane scroll;

	private final JButton okButton;
	private final JButton cancelButton;

	public static final int OK = 1;
	public static final int CANCEL = 0;

	private int returnValue;

	public DeleteDialog() {
		super();

		setTitle(Translator.tr("Delete files/directories"));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		model = new DefaultListModel<>();
		list = new JList<>(model);
		list.setCellRenderer(new FileRenderer());

		scroll = new JScrollPane(list);

		returnValue = CANCEL;

		okButton = new JButton("Ok");
		okButton.addActionListener((ActionEvent ev) -> {
			returnValue = OK;
			dispose();
		});

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener((ActionEvent ev) -> dispose());

		defineLayout();
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent event) {
				okButton.requestFocusInWindow();
			}
		});
	}

	@Override
	public JRootPane getRootPane() {
		JRootPane superRootPane = super.getRootPane();
		superRootPane.registerKeyboardAction((ActionEvent ev) -> {
			setVisible(false);
			dispose();
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		superRootPane.setDefaultButton(okButton);
		return superRootPane;
	}
	
	public int getReturnValue() {
		return returnValue;
	}

	public List<File> getSelectedFiles() {
		List<File> selectedFiles = new ArrayList<>();
		selectedFiles.addAll(list.getSelectedValuesList());
		return selectedFiles;
	}

	public void addFile(File file) {
		model.addElement(file);
		list.clearSelection();
		list.setSelectionInterval(0, model.size() - 1);
	}

	private void defineLayout() {
		JLabel label = new JLabel(Translator.tr(
				"Do you really want to delete the selected files/directories?"));
		
		Container contentPane = getContentPane();

		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST,
				this);
		layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH,
				this);

		layout.putConstraint(SpringLayout.WEST, scroll, 5, SpringLayout.WEST,
				this);
		layout.putConstraint(SpringLayout.NORTH, scroll, 5, SpringLayout.SOUTH,
				label);

		layout.putConstraint(SpringLayout.WEST, okButton, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, okButton, 5, SpringLayout.SOUTH,
				scroll);

		layout.putConstraint(SpringLayout.NORTH, cancelButton, 5, SpringLayout.SOUTH,
				scroll);

		layout.putConstraint(SpringLayout.EAST, contentPane, 5,
				SpringLayout.EAST, scroll);
		layout.putConstraint(SpringLayout.SOUTH, contentPane, 5,
				SpringLayout.SOUTH, okButton);
		layout.putConstraint(SpringLayout.EAST, cancelButton, 0, SpringLayout.EAST,
				scroll);

		setLayout(layout);

		add(label);
		add(scroll);
		add(okButton);
		add(cancelButton);

		pack();
	}
}
