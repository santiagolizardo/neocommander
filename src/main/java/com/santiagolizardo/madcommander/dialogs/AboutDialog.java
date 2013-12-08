/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.LinkLabel;

public class AboutDialog extends AbstractDialog {

	private static final long serialVersionUID = 1558869937506126714L;

	private JTextArea text;

	private JLabel webSite;

	public AboutDialog(JFrame mainWindow) {
		super();

		setTitle(MadCommander.APP_NAME);
		setSize(420, 200);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setFocusable(true);
		setLocationRelativeTo(mainWindow);

		StringBuffer buffer = new StringBuffer();
		buffer.append(MadCommander.APP_NAME).append(" v").append(
				MadCommander.APP_VERSION).append("\n");
		buffer.append("Written by Santiago Lizardo Oscares\nhttp://www.santiagolizardo.com\n");
		text = new JTextArea();
		text.setEditable(false);
		text.setText(buffer.toString());

		webSite = new LinkLabel(MadCommander.APP_URL);

		text.setBackground(webSite.getBackground());
		text.setFont(webSite.getFont());

		defineLayout();
	}

	private void defineLayout() {
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);

		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		add(text, c);

		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 2;
		add(webSite, c);
	}
}
