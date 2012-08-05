/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: DeleteDialog.java,v 1.10 2010/01/22 17:57:54 slizardo Exp $
 */
package org.slizardo.madcommander.dialogs.delete;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

public class DeleteDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 384674050228582953L;

	private JLabel label;

	private DefaultListModel model;
	private JList list;
	private JScrollPane scroll;

	private JButton ok;
	private JButton cancel;

	public static final int OK = 1;
	public static final int CANCEL = 0;

	private int returnValue;

	public DeleteDialog() {
		super();

		setTitle("Delete files/directories");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		label = new JLabel(
				"Do you really want to delete the selected files/directories?");
		model = new DefaultListModel();
		list = new JList(model);
		list.setCellRenderer(new FileRenderer());

		scroll = new JScrollPane(list);

		returnValue = CANCEL;

		ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				returnValue = OK;
				dispose();
			}
		});

		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});

		defineLayout();
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent event) {
				ok.requestFocusInWindow();
			}
		});
	}

	public int getReturnValue() {
		return returnValue;
	}

	public List<File> getSelectedFiles() {
		List<File> selectedFiles = new ArrayList<File>();
		for (Object value : list.getSelectedValues()) {
			selectedFiles.add(new File(value.toString()));
		}
		return selectedFiles;
	}

	public void addFile(File file) {
		model.addElement(file);
		list.clearSelection();
		list.setSelectionInterval(0, model.size() - 1);
	}

	private void defineLayout() {
		Container contentPane = getContentPane();

		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST,
				this);
		layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH,
				this);

		layout.putConstraint(SpringLayout.WEST, scroll, 5, SpringLayout.WEST,
				this);
		layout.putConstraint(SpringLayout.NORTH, scroll, 5, SpringLayout.SOUTH,
				label);

		layout.putConstraint(SpringLayout.WEST, ok, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, ok, 5, SpringLayout.SOUTH,
				scroll);

		layout.putConstraint(SpringLayout.NORTH, cancel, 5, SpringLayout.SOUTH,
				scroll);

		layout.putConstraint(SpringLayout.EAST, contentPane, 5,
				SpringLayout.EAST, scroll);
		layout.putConstraint(SpringLayout.SOUTH, contentPane, 5,
				SpringLayout.SOUTH, ok);
		layout.putConstraint(SpringLayout.EAST, cancel, 0, SpringLayout.EAST,
				scroll);

		setLayout(layout);

		add(label);
		add(scroll);
		add(ok);
		add(cancel);

		pack();
	}
}
