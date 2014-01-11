/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
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

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.filelisting.FileListing;
import com.santiagolizardo.madcommander.components.localized.LocalizedButton;
import com.santiagolizardo.madcommander.components.localized.LocalizedLabel;

public abstract class AbstractProgressDialog extends JDialog implements
		Runnable, ActionListener {

	private static final long serialVersionUID = -2258800923807602822L;

	protected static Logger logger = Logger
			.getLogger(AbstractProgressDialog.class.getName());

	protected JLabel currentFileLabel;

	protected JProgressBar currentBar;

	protected JProgressBar totalBar;

	protected JButton cancel;

	protected String srcPath;

	protected String dstPath;

	protected FileListing src;

	protected FileListing dst;

	protected MyProcess myProcess;

	private JPanel panel;

	private MadCommander mainWindow;

	public AbstractProgressDialog(MadCommander mainWindow) {
		super();

		this.mainWindow = mainWindow;

		setTitle("MadCommander");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);

		currentBar = new JProgressBar(0, 100);
		totalBar = new JProgressBar(0, 100);

		currentFileLabel = new LocalizedLabel("Current file:");

		cancel = new LocalizedButton("Cancel");
		cancel.addActionListener(this);

		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
		panel.setLayout(new GridLayout(5, 1));
		panel.add(currentFileLabel);
		panel.add(new LocalizedLabel("Current progress:"));
		panel.add(currentBar);
		panel.add(new LocalizedLabel("Total progress:"));
		panel.add(totalBar);

		src = mainWindow.getSource();
		dst = mainWindow.getDestination();

		srcPath = src.getPath();
		dstPath = dst.getPath();

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
					} catch (Exception e) {
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

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == cancel) {
			myProcess.cancel();
			dispose();
		}
	}

	private void defineLayout() {
		add(panel, BorderLayout.CENTER);
		add(cancel, BorderLayout.SOUTH);

		pack();
	}
}
