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
package com.santiagolizardo.madcommander.components.filelisting;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.actions.GeneralActionFactory;
import com.santiagolizardo.madcommander.actions.fileops.FileOpsFactory;
import com.santiagolizardo.madcommander.actions.fileops.TouchAction;


public class PopupMenu extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7953562218031804003L;

	public PopupMenu(MainWindow mainWindow) {
		super();

		FileOpsFactory fops = FileOpsFactory.getInstance(mainWindow);
		
		JMenuItem view = new JMenuItem(fops.getViewAction());
		JMenuItem edit = new JMenuItem(fops.getEditAction());
		JMenuItem copy = new JMenuItem(fops.getCopyAction());
		JMenuItem move = new JMenuItem(fops.getMoveAction());
		JMenuItem delete = new JMenuItem(fops.getDeleteAction());
		JMenuItem touch = new JMenuItem(new TouchAction(mainWindow));
		JMenuItem createEF = new JMenuItem(fops.getCreateEmptyFileAction());

		JMenuItem addToBookmarks = new JMenuItem(GeneralActionFactory.getAddToBookmarksAction(mainWindow));

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
