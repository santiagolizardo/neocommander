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
package com.santiagolizardo.madcommander.dialogs.bookmarks;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.localized.LocalizedButton;
import com.santiagolizardo.madcommander.dialogs.AbstractDialog;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ManageBookmarks extends AbstractDialog implements ActionListener {

	private static final long serialVersionUID = 2436015514011489788L;

	private MadCommander mainWindow;

	private JTextField bookmarkTextField;

	private JButton addButton;
	private JButton removeButton;

	private DefaultListModel<String> bookmarksModel;
	private JList<String> bookmarkList;
	private JScrollPane scroll;

	public ManageBookmarks(MadCommander mainWindow) {
		super();

		this.mainWindow = mainWindow;

		setTitle(Translator._("Manage bookmarks"));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		bookmarksModel = new DefaultListModel<>();
		bookmarkList = new JList<>(bookmarksModel);
		Dimension listDim = new Dimension(320, 140);
		bookmarkList.setMinimumSize(listDim);
		bookmarkList.setPreferredSize(listDim);
		bookmarkList.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent ev) {
						removeButton.setEnabled(bookmarkList.getSelectedValuesList()
								.size() == 1);
					}
				});
		refreshList();

		scroll = new JScrollPane(bookmarkList);

		bookmarkTextField = new JTextField(30);

		addButton = new LocalizedButton("Add");
		addButton.addActionListener(this);
		addButton.setIcon(IconFactory.newIcon("add.png"));
		getRootPane().setDefaultButton(addButton);

		removeButton = new LocalizedButton("Remove");
		removeButton.setEnabled(false);
		removeButton.addActionListener(this);
		removeButton.setIcon(IconFactory.newIcon("delete.png"));

		defineLayout();
		setLocationRelativeTo(mainWindow);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();
		String bookmarkName = bookmarkTextField.getText();
		if (source == addButton) {
			File file = new File(bookmarkName);
			if (file.exists()) {
				mainWindow.getConfigData().getBookmarks().add(bookmarkName);
				bookmarkTextField.setText("");
				refreshList();
			} else {
				DialogFactory.showErrorMessage(this,
						Translator._("The directory does not exist."));
			}
		} else if (source == removeButton) {
			mainWindow.getConfigData().getBookmarks().remove(bookmarkList.getSelectedIndex());
			refreshList();
		}
	}

	private void refreshList() {
		bookmarksModel.clear();
		for (String bookmark : mainWindow.getConfigData().getBookmarks()) {
			bookmarksModel.addElement(bookmark);
		}

		mainWindow.getMainMenu().getBookmarksMenu().refreshList();
	}

	private void defineLayout() {
		JPanel topPanel = new JPanel();
		topPanel.add(bookmarkTextField);
		topPanel.add(addButton);

		add(topPanel, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(removeButton, BorderLayout.SOUTH);

		pack();

		bookmarkTextField.requestFocusInWindow();
	}
}
