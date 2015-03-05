/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * MadCommander is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * MadCommander. If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.dialogs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.components.localized.LocalizedButton;
import com.santiagolizardo.madcommander.dialogs.search.AdvancedTab;
import com.santiagolizardo.madcommander.dialogs.search.GeneralTab;
import com.santiagolizardo.madcommander.dialogs.search.SearchParams;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.util.GlobUtils;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import com.santiagolizardo.madcommander.util.io.FileUtil;
import java.util.regex.Matcher;

public class SearchDialog extends AbstractDialog implements ActionListener {

	private static final long serialVersionUID = -6936490579285517802L;

	private GeneralTab generalTab;

	private AdvancedTab advancedTab;

	private JButton searchButton;
	private JButton closeButton;

	private DefaultListModel<String> results;

	private JList<String> resultsList;

	private MainWindow mainWindow;

	public SearchDialog(MainWindow mainWindow) {
		super(mainWindow);

		this.mainWindow = mainWindow;

		setTitle("Find files");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(true);
		setAlwaysOnTop(true);

		generalTab = new GeneralTab(mainWindow);
		advancedTab = new AdvancedTab();

		results = new DefaultListModel<>();
		resultsList = new JList<>(results);
		resultsList.setCellRenderer(new ListCellRenderer<String>() {
			@Override
			public Component getListCellRendererComponent(JList arg0,
					String fileName, int arg2, boolean selected, boolean arg4) {
				File file = new File(fileName);
				Icon icon = IconFactory.getIconForFile(file);
				JLabel label = new JLabel(fileName);
				if (selected) {
					label.setBackground(Color.BLUE);
					label.setOpaque(true);
				}
				label.setIcon(icon);
				return label;
			}
		});
		resultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resultsList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int selectedIndex = resultsList.getSelectedIndex();
					if (selectedIndex != -1) {
						String selected = results.get(selectedIndex);
						String filePart = FileUtil.extractFilePart(new File(
								selected));
						String dirPart = FileUtil.extractDirPart(new File(
								selected));
						SearchDialog.this.mainWindow.getSource().setPath(
								dirPart);
						SearchDialog.this.mainWindow.getSource().focusOnFilePath(
								filePart);
					}
				}
			}
		});

		searchButton = new LocalizedButton("Search");
		searchButton.addActionListener(this);
		getRootPane().setDefaultButton(searchButton);

		closeButton = new LocalizedButton("Close");
		closeButton.addActionListener(this);

		defineLayout();
		setLocationRelativeTo(mainWindow);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == searchButton) {
			search();
		} else if (source == closeButton) {
			dispose();
		}
	}

	private void search() {
		String searchFor = generalTab.getSearchFor();
		String searchIn = generalTab.getSearchIn();

		SearchParams params = new SearchParams();
		params.setSearchFor(searchFor);
		params.setPattern(GlobUtils.convertGlobToRegexp(searchFor));
		params.setSearchIn(searchIn);
		params.setRecursive(generalTab.isRecursive());
		params.setFileSizeIsSelected(advancedTab.fileSizeIsSelected());
		if (params.fileSizeIsSelected()) {
			params.setCondition(advancedTab.getCondition());
			params.setMeasure(advancedTab.getMeasure());
			params.setSize(advancedTab.getSearchedSize());
		}

		results.removeAllElements();

		searchFiles(params);
	}

	private void searchFiles(final SearchParams params) {
		File searchDirectory = new File(params.getSearchIn());
		if (!searchDirectory.exists() || !searchDirectory.isDirectory()) {
			DialogFactory.showErrorMessage(SearchDialog.this, String.format("Invalid search directory: " + searchDirectory.getAbsolutePath()));
			return;
		}
		File[] files = searchDirectory.listFiles();
		for (File file : files) {
			String fileName = file.getName();
			Matcher matcher = params.getPattern().matcher(fileName);
			if (matcher.matches()) {
				if (params.fileSizeIsSelected()) {
					String condition = params.getCondition();
					String measure = params.getMeasure();
					long length = file.length();
					switch (measure) {
						case "kbytes":
							length /= 1000;
							break;
						case "mbytes":
							length /= 1000;
							length /= 1000;
							break;
					}
					switch (condition) {
						case "Equal":
							if (length == params.getSize()) {
								results.addElement(file
										.getAbsolutePath());
							}
							break;
						case "More":
							if (length > params.getSize()) {
								results.addElement(file
										.getAbsolutePath());
							}
							break;
						case "Less":
							if (length < params.getSize()) {
								results.addElement(file
										.getAbsolutePath());
							}
							break;
					}
				} else {
					results.addElement(file.getAbsolutePath());
				}
			}
			if (file.isDirectory() && params.isRecursive()) {
				SearchParams sp = params;
				sp.setSearchIn(file.getAbsolutePath());
				searchFiles(sp);
			}
		}
	}

	private void defineLayout() {
		JTabbedPane tabbed = new JTabbedPane();

		tabbed.add("General", generalTab);
		tabbed.add("Advanced", advancedTab);

		Box box = new Box(BoxLayout.Y_AXIS);
		Dimension boxSize = new Dimension(80, 100);
		box.setMinimumSize(boxSize);
		box.setPreferredSize(boxSize);
		box.add(searchButton);
		box.add(closeButton);

		Dimension listSize = new Dimension(640, 200);

		JScrollPane scrollPane = new JScrollPane(resultsList);
		scrollPane.setMinimumSize(listSize);
		scrollPane.setPreferredSize(listSize);
		scrollPane.setBorder(BorderFactory.createTitledBorder(" Results "));

		SpringLayout mainLayout = new SpringLayout();
		Container container = getContentPane();
		container.setLayout(mainLayout);
		container.add(tabbed);
		container.add(box);
		container.add(scrollPane);

		mainLayout.putConstraint(SpringLayout.NORTH, tabbed, 5,
				SpringLayout.NORTH, container);
		mainLayout.putConstraint(SpringLayout.WEST, tabbed, 5,
				SpringLayout.WEST, container);

		mainLayout.putConstraint(SpringLayout.NORTH, box, 5,
				SpringLayout.NORTH, container);
		mainLayout.putConstraint(SpringLayout.EAST, box, -5, SpringLayout.EAST,
				container);

		mainLayout.putConstraint(SpringLayout.NORTH, scrollPane, 5,
				SpringLayout.SOUTH, tabbed);
		mainLayout.putConstraint(SpringLayout.WEST, scrollPane, 5,
				SpringLayout.WEST, container);

		mainLayout.putConstraint(SpringLayout.EAST, container, 5,
				SpringLayout.EAST, scrollPane);
		mainLayout.putConstraint(SpringLayout.SOUTH, container, 5,
				SpringLayout.SOUTH, scrollPane);

		pack();
	}
}
