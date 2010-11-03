/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: PopupMenu.java,v 1.8 2010/01/22 17:57:54 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.slizardo.madcommander.actions.GeneralActionFactory;
import org.slizardo.madcommander.actions.fileops.FileOpsFactory;
import org.slizardo.madcommander.actions.fileops.TouchAction;


public class PopupMenu extends JPopupMenu {

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
