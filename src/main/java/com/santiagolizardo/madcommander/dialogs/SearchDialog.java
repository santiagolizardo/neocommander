/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.swing.SwingUtilities;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.localized.LocalizedButton;
import com.santiagolizardo.madcommander.dialogs.search.AdvancedTab;
import com.santiagolizardo.madcommander.dialogs.search.GeneralTab;
import com.santiagolizardo.madcommander.dialogs.search.SearchParams;
import com.santiagolizardo.madcommander.resources.images.IconFactory;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import com.santiagolizardo.madcommander.util.io.FileUtil;

public class SearchDialog extends AbstractDialog implements ActionListener {

	private static final long serialVersionUID = -6936490579285517802L;

	private GeneralTab generalTab;

	private AdvancedTab advancedTab;

	private JButton searchButton;
	private JButton cancelButton;

	private DefaultListModel<String> results;

	private JList<String> resultsList;

	private MadCommander mainWindow;

	public SearchDialog(MadCommander mainWindow) {
		super();

		this.mainWindow = mainWindow;

		setTitle("Find files");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(true);
		setAlwaysOnTop(true);

		generalTab = new GeneralTab(mainWindow);
		advancedTab = new AdvancedTab();

		results = new DefaultListModel<String>();
		resultsList = new JList<String>(results);
		resultsList.setCellRenderer(new ListCellRenderer<String>() {
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
			public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int selectedIndex = resultsList.getSelectedIndex();
					if (selectedIndex != -1) {
						String selected = results.get(selectedIndex).toString();
						String filePart = FileUtil.extractFilePart(new File(
								selected));
						String dirPart = FileUtil.extractDirPart(new File(
								selected));
						SearchDialog.this.mainWindow.getSource().setPath(
								dirPart);
						SearchDialog.this.mainWindow.getSource().focusOnFile(
								filePart);
					}
				}
			}
		});

		searchButton = new LocalizedButton("Search");
		searchButton.addActionListener(this);
		getRootPane().setDefaultButton(searchButton);

		cancelButton = new LocalizedButton("Cancel");
		cancelButton.addActionListener(this);

		defineLayout();
		setLocationRelativeTo(mainWindow);
	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == searchButton) {
			search();
		} else if (source == cancelButton) {
			dispose();
		}
	}

	private void search() {
		String searchFor = generalTab.getSearchFor().replace("*", ".*");
		String searchIn = generalTab.getSearchIn();

		SearchParams params = new SearchParams();
		params.setSearchFor(searchFor);
		params.setPattern(Pattern.compile(searchFor, Pattern.CASE_INSENSITIVE));
		params.setSearchIn(searchIn);
		params.setRecursive(generalTab.isRecursive());
		params.setFileSizeIsSelected(advancedTab.fileSizeIsSelected());
		if (params.fileSizeIsSelected()) {
			params.setCondition(advancedTab.getCondition());
			params.setMeasure(advancedTab.getMeasure());
			params.setSize(advancedTab.getSearchedSize());
		}

		results.removeAllElements();

		searchFiles(results, params);
	}

	private void searchFiles(DefaultListModel<String> model,
			final SearchParams params) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				File dir = new File(params.getSearchIn());
				if (dir.exists() && dir.isDirectory()) {
					File[] files = dir.listFiles();
					for (File file : files) {
						String fileName = file.getName();
						Matcher matcher = params.getPattern().matcher(fileName);
						if (matcher.matches()) {
							if (params.fileSizeIsSelected()) {
								String condition = params.getCondition();
								String measure = params.getMeasure();
								long length = file.length();
								if ("kbytes".equals(measure)) {
									length /= 1000;
								} else if ("mbytes".equals(measure)) {
									length /= 1000;
									length /= 1000;
								}
								if ("Equal".equals(condition)) {
									if (length == params.getSize()) {
										results.addElement(file
												.getAbsolutePath());
									}
								} else if ("More".equals(condition)) {
									if (length > params.getSize()) {
										results.addElement(file
												.getAbsolutePath());
									}
								} else if ("Less".equals(condition)) {
									if (length < params.getSize()) {
										results.addElement(file
												.getAbsolutePath());
									}
								}
							} else {
								results.addElement(file.getAbsolutePath());
							}
						}
						if (file.isDirectory() && params.isRecursive()) {
							params.setSearchIn(file.getAbsolutePath());
							searchFiles(results, params);
						}
					}
				} else {
					DialogFactory.showErrorMessage(mainWindow,
							"El directorio '" + params.getSearchIn()
									+ "' no existe.");
				}
			}
		});
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
		box.add(cancelButton);

		JScrollPane scrollPane = new JScrollPane(resultsList);
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
