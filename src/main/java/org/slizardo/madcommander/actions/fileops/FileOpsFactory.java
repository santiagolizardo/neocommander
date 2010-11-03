/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FileOpsFactory.java,v 1.2 2010/01/22 17:57:54 slizardo Exp $
 */
package org.slizardo.madcommander.actions.fileops;

import javax.swing.Action;

import org.slizardo.madcommander.MainGUI;


public class FileOpsFactory {

	private static FileOpsFactory singleton;

	public static FileOpsFactory getInstance() {
		if (singleton == null) {
			singleton = new FileOpsFactory();
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

	public Action getViewAction() {
		if (viewAction == null)
			viewAction = new ViewAction();

		return viewAction;
	}

	public Action getEditAction() {
		if (editAction == null)
			editAction = new EditAction();

		return editAction;
	}

	public Action getCopyAction() {
		if (copyAction == null)
			copyAction = new CopyAction();

		return copyAction;
	}

	public Action getMoveAction() {
		if (moveAction == null)
			moveAction = new MoveAction();

		return moveAction;
	}

	public Action getDeleteAction() {
		if (deleteAction == null)
			deleteAction = new DeleteAction();

		return deleteAction;
	}

	public Action getCreateDirAction() {
		if (createDirAction == null)
			createDirAction = new CreateDirAction();

		return createDirAction;
	}

	public Action getCreateEmptyFileAction() {
		if (createEmptyFileAction == null)
			createEmptyFileAction = new CreateEmptyFileAction(MainGUI.app);

		return createEmptyFileAction;
	}
}
