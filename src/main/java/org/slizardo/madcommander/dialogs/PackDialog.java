/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: PackDialog.java,v 1.13 2010/01/22 10:03:54 slizardo Exp $
 */
package org.slizardo.madcommander.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.localized.LocalizedButton;
import org.slizardo.madcommander.components.localized.LocalizedLabel;
import org.slizardo.madcommander.controller.Controller;
import org.slizardo.madcommander.controller.PackTypes;
import org.slizardo.madcommander.resources.languages.Translator;


public class PackDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = -3805898808819160470L;

	private JTextField fileName;

	private JLabel extension;

	private JRadioButton zip;

	private JRadioButton jar;

	private JRadioButton gzip;

	private JButton compress;

	private JPanel panel;

	public PackDialog() {
		super();

		setTitle(Translator.text("Pack..."));

		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		final String path = MainGUI.app.getSource().getPath();

		fileName = new JTextField(20);
		fileName.setText(path + File.separator + "archive");

		extension = new JLabel(".zip");

		panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Compression type "));
		panel.setLayout(new GridLayout(3, 1));

		ButtonGroup group = new ButtonGroup();

		zip = new JRadioButton("ZIP");
		zip.setSelected(true);
		zip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				extension.setText(".zip");
			}
		});
		group.add(zip);

		jar = new JRadioButton("JAR");
		jar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				extension.setText(".jar");
			}
		});
		group.add(jar);

		gzip = new JRadioButton("GZIP");
		gzip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				extension.setText(".gzip");
			}
		});
		group.add(gzip);

		panel.add(zip);
		panel.add(jar);
		panel.add(gzip);

		defineLayout();
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent event) {
		PackTypes type;
		if (zip.isSelected())
			type = PackTypes.ZIP;
		else if (jar.isSelected())
			type = PackTypes.JAR;
		else
			type = PackTypes.GZIP;

		Controller.compress(fileName.getText(), type);
		dispose();
	}

	private void defineLayout() {
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		add(new LocalizedLabel("Filename:"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridheight = 2;
		add(fileName, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 2;
		add(extension, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 3;
		add(panel, c);

		compress = new LocalizedButton("Compress");
		compress.addActionListener(this);

		c.fill = GridBagConstraints.NONE;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 4;
		c.insets = new Insets(5, 5, 5, 5);
		add(compress, c);
		
		pack();
	}
}
