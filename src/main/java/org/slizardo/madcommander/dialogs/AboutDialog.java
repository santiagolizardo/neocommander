/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: AboutDialog.java,v 1.12 2010/01/26 17:56:28 slizardo Exp $
 */
package org.slizardo.madcommander.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.slizardo.madcommander.MadCommander;
import org.slizardo.madcommander.components.LinkLabel;
import org.slizardo.madcommander.components.localized.LocalizedButton;
import org.slizardo.madcommander.resources.images.IconFactory;


public class AboutDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1558869937506126714L;

	private JLabel iconLabel;

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

		final ImageIcon icon = IconFactory.newIcon("che_petit.jpg");
		iconLabel = new JLabel(icon);
		iconLabel.setToolTipText("Ernesto Che Guevara");

		StringBuffer buffer = new StringBuffer();
		buffer.append(MadCommander.APP_NAME).append(" version ").append(
				MadCommander.APP_VERSION).append("\n");
		buffer.append("Copyright 2010 by Santiago Lizardo Oscares\n\n");
		buffer.append("e-mail: santiago.lizardo@gmail.com");
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
		add(iconLabel, c);

		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 2;
		add(text, c);

		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 3;
		add(webSite, c);

		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 4;
		c.fill = GridBagConstraints.NONE;
		add(ok, c);
	}
}
