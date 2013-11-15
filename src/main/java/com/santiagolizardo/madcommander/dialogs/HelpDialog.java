/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.dialogs;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.io.FileUtils;

public class HelpDialog extends JDialog {

	private static final long serialVersionUID = 3552941757519747207L;

	public HelpDialog(String helpFile) {
		setTitle("MadCommander - Help");
		setModal(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(true);

		String content = "";
		File file = new File(helpFile);
		try {
			content = FileUtils.readFileToString(file);
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
			content = "The file content could not be read.";
		}

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBackground(getBackground());
		textArea.setFont(new Font("Arial", Font.PLAIN, 11));
		textArea.setText(content);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));
		scrollPane.setPreferredSize(new Dimension(320, 200));

		add(scrollPane);
		pack();
		setLocationRelativeTo(null);
	}
}
