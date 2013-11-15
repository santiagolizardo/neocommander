/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.filelisting;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.santiagolizardo.madcommander.actions.GeneralActionFactory;
import com.santiagolizardo.madcommander.actions.fileops.FileOpsFactory;
import com.santiagolizardo.madcommander.actions.fileops.TouchAction;


public class PopupMenu extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7953562218031804003L;

	public PopupMenu() {
		super();

		FileOpsFactory fops = FileOpsFactory.getInstance();
		
		JMenuItem view = new JMenuItem(fops.getViewAction());
		JMenuItem edit = new JMenuItem(fops.getEditAction());
		JMenuItem copy = new JMenuItem(fops.getCopyAction());
		JMenuItem move = new JMenuItem(fops.getMoveAction());
		JMenuItem delete = new JMenuItem(fops.getDeleteAction());
		JMenuItem touch = new JMenuItem(new TouchAction());
		JMenuItem createEF = new JMenuItem(fops.getCreateEmptyFileAction());

		JMenuItem addToBookmarks = new JMenuItem(GeneralActionFactory.getAddToBookmarksAction());

		add(view);
		add(edit);
		addSeparator();
		add(copy);
		add(move);
		add(delete);
		addSeparator();
		add(touch);
		add(createEF);
		addSeparator();
		add(addToBookmarks);
	}
}
