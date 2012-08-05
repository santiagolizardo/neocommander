/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: ManageBookmarks.java,v 1.6 2006/03/21 15:04:42 slizardo Exp $
 */
package org.slizardo.madcommander.dialogs.bookmarks;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.model.BookmarksModel;
import org.slizardo.madcommander.components.localized.LocalizedButton;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;
import org.slizardo.madcommander.util.gui.DialogFactory;


public class ManageBookmarks extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2436015514011489788L;
	private JList list;
	private JScrollPane scroll;

	private JTextField bookmark;
	
	private JButton add;
	private JButton remove;
	
	public ManageBookmarks() {
		super();

		setTitle(Translator.text("Manage_bookmarks"));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		list = new JList(MainGUI.app.bookmarksModel);
		scroll = new JScrollPane(list);
		
		bookmark = new JTextField(30);
		
		add = new LocalizedButton("Add");
		add.addActionListener(this);
		add.setIcon(IconFactory.newIcon("add.png"));
		remove = new LocalizedButton("Remove");
		remove.addActionListener(this);
		remove.setIcon(IconFactory.newIcon("delete.png"));
		
		defineLayout();		
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		BookmarksModel bookmarksModel = (BookmarksModel)list.getModel();
		if(source == add) {
			String bookmarkName = bookmark.getText();
			File file = new File(bookmarkName);
			if(file.exists()) {
				bookmarksModel.addBookmark(bookmarkName);
				bookmark.setText("");
			} else {
				DialogFactory.showErrorMessage(this, "Directory does not exists.");
			}
		} else
		if(source == remove) {
			
		}
	}
	
	private void defineLayout() {
		Container contentPane = getContentPane();
		SpringLayout layout = new SpringLayout();
		
		layout.putConstraint(SpringLayout.WEST, bookmark, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, bookmark, 5, SpringLayout.NORTH, contentPane);
		
		layout.putConstraint(SpringLayout.WEST, add, 5, SpringLayout.EAST, bookmark);
		layout.putConstraint(SpringLayout.NORTH, add, 5, SpringLayout.NORTH, contentPane);
		layout.putConstraint(SpringLayout.EAST, add, 0, SpringLayout.EAST, scroll);

		layout.putConstraint(SpringLayout.EAST, remove, 0, SpringLayout.EAST, add);
		layout.putConstraint(SpringLayout.NORTH, remove, 5, SpringLayout.SOUTH, add);
		
		layout.putConstraint(SpringLayout.WEST, scroll, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, scroll, 5, SpringLayout.SOUTH, remove);
		layout.putConstraint(SpringLayout.SOUTH, contentPane, 5, SpringLayout.SOUTH, scroll);
		layout.putConstraint(SpringLayout.EAST, contentPane, 5, SpringLayout.EAST, scroll);
		
		setLayout(layout);
		
		add(bookmark);
		add(scroll);
		add(add);
		add(remove);

		pack();
	}
}
