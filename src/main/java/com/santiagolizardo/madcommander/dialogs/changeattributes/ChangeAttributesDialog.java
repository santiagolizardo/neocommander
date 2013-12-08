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
import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.santiagolizardo.madcommander.MainGUI;
import com.santiagolizardo.madcommander.components.localized.LocalizedButton;
import com.santiagolizardo.madcommander.dialogs.AbstractDialog;
import com.santiagolizardo.madcommander.resources.languages.Translator;
import com.santiagolizardo.madcommander.util.CalendarUtil;

public class ChangeAttributesDialog extends AbstractDialog implements ActionListener {

	private static final long serialVersionUID = -2519143127010077700L;

	private static final Logger LOGGER = Logger
			.getLogger(ChangeAttributesDialog.class.getName());

	private AttributesPanel attributesPanel;
	private DateTimePanel dateTimePanel;

	private JButton ok;
	private JButton cancel;

	public ChangeAttributesDialog(JFrame mainWindow) {
		super();

		setTitle(Translator._("Change_attributes..."));
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		attributesPanel = new AttributesPanel();
		dateTimePanel = new DateTimePanel();

		ok = new LocalizedButton("Ok");
		ok.addActionListener(this);
		cancel = new LocalizedButton("Cancel");
		cancel.addActionListener(this);

		defineLayout();
		setSize(240, 350);
		setLocationRelativeTo(mainWindow);
	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == ok) {
			List<File> selectedFiles = MainGUI.app.getSource()
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
			MainGUI.app.getSource().refreshFiles();
			dispose();
		} else if (source == cancel) {
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

		layout.putConstraint(SpringLayout.WEST, ok, 5, SpringLayout.WEST,
				contentPane);
		layout.putConstraint(SpringLayout.NORTH, ok, 5, SpringLayout.SOUTH,
				dateTimePanel);

		layout.putConstraint(SpringLayout.WEST, cancel, 5, SpringLayout.EAST,
				ok);
		layout.putConstraint(SpringLayout.NORTH, cancel, 5, SpringLayout.SOUTH,
				dateTimePanel);

		contentPane.setLayout(layout);

		contentPane.add(attributesPanel);
		contentPane.add(dateTimePanel);

		contentPane.add(ok);
		contentPane.add(cancel);

		pack();
	}
}
