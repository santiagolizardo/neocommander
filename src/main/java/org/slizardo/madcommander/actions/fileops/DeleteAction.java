/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: DeleteAction.java,v 1.3 2010/01/22 17:57:54 slizardo Exp $
 */
package org.slizardo.madcommander.actions.fileops;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.filelisting.FileListing;
import org.slizardo.madcommander.dialogs.delete.DeleteDialog;
import org.slizardo.madcommander.dialogs.progressive.DeleteProgressDialog;
import org.slizardo.madcommander.resources.images.IconFactory;
import org.slizardo.madcommander.resources.languages.Translator;


class DeleteAction extends AbstractAction {

	//private ProgressMonitor monitor;
	//private DeleteTask task;
	//private Timer timer;
	
	public DeleteAction() {
		super(Translator.text("Delete"), IconFactory.newIcon("F8.gif"));
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F8"));
	}

	public void actionPerformed(ActionEvent event) {
		FileListing listing = MainGUI.app.getSource();

		ArrayList<File> selectedFiles = listing.getSelectedFiles();
		DeleteDialog dialog = new DeleteDialog();
		for(File file : selectedFiles) {
			dialog.addFile(file);
		}
		dialog.setVisible(true);
		if(dialog.getReturnValue() == DeleteDialog.OK) {
			selectedFiles = dialog.getSelectedFiles();
//			task = new DeleteTask(selectedFiles);
//			timer = new Timer(1000, new ActionListener() {
//				public void actionPerformed(ActionEvent event) {
//					monitor.setProgress(task.getProgress());
//					monitor.setNote(task.getCurrentFile());
//					if(monitor.isCanceled() || task.isDone()) {
//						monitor.close();
//						task.cancel();
//						timer.stop();
//					}
//				}
//			});
//			monitor = new ProgressMonitor(Main.app, Translator.text("Deleting"), "", 0, selectedFiles.size());
//			monitor.setProgress(0);
//			monitor.setMillisToDecideToPopup(2 * 1000);
//			task.start();
//			timer.start();
			DeleteProgressDialog progressDialog = new DeleteProgressDialog();
			progressDialog.setSelectedFiles(selectedFiles);
			progressDialog.begin();
		}
	}
}
