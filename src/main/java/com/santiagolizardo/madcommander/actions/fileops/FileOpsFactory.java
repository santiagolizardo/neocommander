/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.actions.fileops;

import javax.swing.Action;

import com.santiagolizardo.madcommander.MadCommander;

public class FileOpsFactory {

	private static FileOpsFactory singleton;

	public static FileOpsFactory getInstance(MadCommander mainWindow) {
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

	private MadCommander mainWindow;

	public FileOpsFactory(MadCommander mainWindow) {
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
