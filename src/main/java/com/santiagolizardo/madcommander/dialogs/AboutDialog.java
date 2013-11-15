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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.LinkLabel;
import com.santiagolizardo.madcommander.components.localized.LocalizedButton;


public class AboutDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1558869937506126714L;

	private JTextArea text;

	private JLabel webSite;

	private JButton ok;

	public AboutDialog() {
		super();

		setTitle(MadCommander.APP_NAME);
		setSize(270, 300);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setFocusable(true);
		setLocationRelativeTo(null);

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

		ok = new LocalizedButton("Ok");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			};
		});

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

		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 3;
		c.fill = GridBagConstraints.NONE;
		add(ok, c);
	}
}
