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
package com.santiagolizardo.madcommander.dialogs;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.io.FileUtils;

public class HelpDialog extends AbstractDialog {

	private static final long serialVersionUID = 3552941757519747207L;

	public HelpDialog(JFrame mainWindow, String helpFile) {
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
		setLocationRelativeTo(mainWindow);
	}
}
