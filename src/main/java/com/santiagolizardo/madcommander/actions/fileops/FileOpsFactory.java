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
package com.santiagolizardo.madcommander.actions.fileops;

import javax.swing.Action;

import com.santiagolizardo.madcommander.MainWindow;

public class FileOpsFactory {

	private static FileOpsFactory singleton;

	public static FileOpsFactory getInstance(MainWindow mainWindow) {
		if (singleton == null) {
			singleton = new FileOpsFactory(mainWindow);
		}

		return singleton;
	}

	private Action viewAction;
	private Action editAction;

	private Action copyAction;
	private Action moveAction;
	private Action createDirAction;
	private Action deleteAction;

	private Action createEmptyFileAction;

	private MainWindow mainWindow;

	public FileOpsFactory(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public Action getViewAction() {
		if (viewAction == null)
			viewAction = new ViewAction(mainWindow);

		return viewAction;
	}

	public Action getEditAction() {
		if (editAction == null)
			editAction = new EditAction(mainWindow);

		return editAction;
	}

	public Action getCopyAction() {
		if (copyAction == null)
			copyAction = new CopyAction(mainWindow);

		return copyAction;
	}

	public Action getMoveAction() {
		if (moveAction == null)
			moveAction = new MoveAction(mainWindow);

		return moveAction;
	}

	public Action getDeleteAction() {
		if (deleteAction == null)
			deleteAction = new DeleteAction(mainWindow);

		return deleteAction;
	}

	public Action getCreateDirAction() {
		if (createDirAction == null)
			createDirAction = new CreateDirAction(mainWindow);

		return createDirAction;
	}

	public Action getCreateEmptyFileAction() {
		if (createEmptyFileAction == null)
			createEmptyFileAction = new CreateEmptyFileAction(mainWindow);

		return createEmptyFileAction;
	}
}
