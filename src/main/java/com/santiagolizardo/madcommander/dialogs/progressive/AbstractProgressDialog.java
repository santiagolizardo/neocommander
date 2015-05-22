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
package com.santiagolizardo.madcommander.dialogs.progressive;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.resources.languages.Translator;

public abstract class AbstractProgressDialog extends JDialog implements
		Runnable, ActionListener {

	private static final long serialVersionUID = -2258800923807602822L;

	protected static final Logger logger = Logger
			.getLogger(AbstractProgressDialog.class.getName());

	protected JLabel currentFileLabel;

	protected JProgressBar currentBar;

	protected JProgressBar totalBar;

	protected JButton cancelButton;

	protected String srcPath;

	protected String dstPath;

	protected FileListing sourceListing;

	protected FileListing destinyListing;

	protected MyProcess myProcess;

	private JPanel panel;

	private MainWindow mainWindow;

	public AbstractProgressDialog(MainWindow mainWindow) {
		super();

		this.mainWindow = mainWindow;

		setTitle(MainWindow.APP_NAME);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);

		currentBar = new JProgressBar(0, 100);
		totalBar = new JProgressBar(0, 100);

		currentFileLabel = new JLabel(Translator.tr("Current file:"));

		cancelButton = new JButton(Translator.tr("Cancel"));
		cancelButton.addActionListener(this);

		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
		panel.setLayout(new GridLayout(5, 1));
		panel.add(currentFileLabel);
		panel.add(new JLabel(Translator.tr("Current progress:")));
		panel.add(currentBar);
		panel.add(new JLabel(Translator.tr("Total progress:")));
		panel.add(totalBar);

		sourceListing = mainWindow.getSource();
		destinyListing = mainWindow.getDestination();

		srcPath = sourceListing.getPath();
		dstPath = destinyListing.getPath();

		myProcess = new MyProcess();

		defineLayout();
		setLocationRelativeTo(null);
	}

	public void begin() {
		Thread thread = new Thread(this);
		thread.start();

		Thread t = new Thread(new Runnable() {
			public void run() {
				while (!myProcess.isTotalComplete()) {
					currentFileLabel.setText("Current file: "
							+ myProcess.getCurrentFile());
					currentBar.setValue(myProcess.currentProgress);
					totalBar.setValue(myProcess.totalProgress);
					if (myProcess.cancel)
						break;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
				}

				mainWindow.getSource().refreshFiles();
				mainWindow.getDestination().refreshFiles();

				dispose();
			}
		});
		t.start();

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == cancelButton) {
			myProcess.cancel();
			dispose();
		}
	}

	private void defineLayout() {
		add(panel, BorderLayout.CENTER);
		add(cancelButton, BorderLayout.SOUTH);

		pack();
	}
}
