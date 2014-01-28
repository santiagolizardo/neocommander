/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.dialogs.changeattributes;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.SpringLayout;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.localized.LocalizedButton;
import com.santiagolizardo.madcommander.dialogs.AbstractDialog;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.CalendarUtil;

public class ChangeAttributesDialog extends AbstractDialog implements
		ActionListener {

	private static final long serialVersionUID = -2519143127010077700L;

	private static final Logger LOGGER = Logger
			.getLogger(ChangeAttributesDialog.class.getName());

	private AttributesPanel attributesPanel;
	private DateTimePanel dateTimePanel;

	private JButton okButton;
	private JButton cancelButton;

	private MadCommander mainWindow;

	public ChangeAttributesDialog(MadCommander mainWindow) {
		super();

		this.mainWindow = mainWindow;

		setTitle(Translator._("Change attributes..."));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		attributesPanel = new AttributesPanel();
		dateTimePanel = new DateTimePanel();

		okButton = new LocalizedButton("Ok");
		okButton.addActionListener(this);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new LocalizedButton("Cancel");
		cancelButton.addActionListener(this);

		defineLayout();
		setSize(240, 350);
		setLocationRelativeTo(mainWindow);
	}

	public void actionPerformed(ActionEvent ev) {
		Object source = ev.getSource();
		if (source == okButton) {
			List<File> selectedFiles = mainWindow.getSource()
					.getSelectedFiles();
			File file = selectedFiles.get(0);
			if (attributesPanel.isReadOnly()) {
				file.setReadOnly();
			}
			String date = dateTimePanel.getDate();
			String time = dateTimePanel.getTime();
			long dateTime = CalendarUtil.convertDateTime(date, time);

			if (file.setLastModified(dateTime) == false) {
				LOGGER.severe("error setting last modified property");
			}
			mainWindow.getSource().refreshFiles();
			dispose();
		} else if (source == cancelButton) {
			dispose();
		}
	}

	private void defineLayout() {
		Container contentPane = getContentPane();

		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.WEST, attributesPanel, 5,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, attributesPanel, 5,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, dateTimePanel, 5,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, dateTimePanel, 5,
				SpringLayout.SOUTH, attributesPanel);

		layout.putConstraint(SpringLayout.WEST, okButton, 5, SpringLayout.WEST,
				contentPane);
		layout.putConstraint(SpringLayout.NORTH, okButton, 5,
				SpringLayout.SOUTH, dateTimePanel);

		layout.putConstraint(SpringLayout.WEST, cancelButton, 5,
				SpringLayout.EAST, okButton);
		layout.putConstraint(SpringLayout.NORTH, cancelButton, 5,
				SpringLayout.SOUTH, dateTimePanel);

		contentPane.setLayout(layout);

		contentPane.add(attributesPanel);
		contentPane.add(dateTimePanel);

		contentPane.add(okButton);
		contentPane.add(cancelButton);

		pack();
	}
}