/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FileListingTable.java,v 1.14 2010/01/22 17:57:54 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting;

import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.actions.SelectDriveAction;
import org.slizardo.madcommander.actions.fileops.FileOpsFactory;
import org.slizardo.madcommander.components.filelisting.model.AttributesComparator;
import org.slizardo.madcommander.components.filelisting.model.DateComparator;
import org.slizardo.madcommander.components.filelisting.model.ExtensionComparator;
import org.slizardo.madcommander.components.filelisting.model.FileListingModel;
import org.slizardo.madcommander.components.filelisting.model.FileListingRow;
import org.slizardo.madcommander.components.filelisting.model.NameComparator;
import org.slizardo.madcommander.components.filelisting.model.SizeComparator;
import org.slizardo.madcommander.util.actions.InputMapUtil;


public class FileListingTable extends JTable implements Runnable, FocusListener {

	private FileListing fileListing;

	private DragSource dragSource;

	public DropTarget dropTarget;

	public FileListingModel model;

	private FileFilter filter;

	private boolean first;

	public FileListingTable(FileListing fileListing) {
		super();

		first = true;

		setColumnSelectionAllowed(false);
		setShowHorizontalLines(false);
		setShowVerticalLines(true);
		setAutoCreateColumnsFromModel(false);

		model = new FileListingModel();
		setModel(model);

		getTableHeader().setUpdateTableInRealTime(true);

		addFocusListener(this);

		this.fileListing = fileListing;

		FLKeyListener myKeyListener = new FLKeyListener();
		FLMouseListener myMouseListener = new FLMouseListener();

		addKeyListener(myKeyListener);
		addMouseListener(myMouseListener);

		DragOut dragOut = new DragOut();
		dragSource = new DragSource();
		dragSource.createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_COPY_OR_MOVE, dragOut);

		DropIn dropIn = new DropIn();
		dropTarget = new DropTarget(this, dropIn);

		defineKeyBindings();
	}

	private void defineKeyBindings() {
		InputMap inputMap = getInputMap();
		ActionMap actionMap = getActionMap();

		FileOpsFactory fops = FileOpsFactory.getInstance();
		
		InputMapUtil.putAction(this, new SelectDriveAction());
		InputMapUtil.putAction(this, fops.getViewAction());
		InputMapUtil.putAction(this, fops.getEditAction());
		InputMapUtil.putAction(this, fops.getCopyAction());
		InputMapUtil.putAction(this, fops.getMoveAction());
		InputMapUtil.putAction(this, fops.getCreateDirAction());

		// Delete action has two keys assigned
		InputMapUtil.putAction(this, fops.getDeleteAction());
		inputMap.put(KeyStroke.getKeyStroke("DELETE"), "DELETE");
		actionMap.put("DELETE", fops.getDeleteAction());
	}

	public void setFileFilter(FileFilter filter) {
		this.filter = filter;
	}

	public void refresh() {
		try {
			SwingUtilities.invokeLater(this);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void run() {
		model.clear();
		File dir = new File(fileListing.getPath());
		model.isRoot = dir.getParentFile() == null;
		if (dir.exists() == false) {
			dir = new File(System.getProperty("user.dir"));
		}
		fileListing.pathLabel.setText(dir.getAbsolutePath());
		// TODO boolean showHiddens =
		// ConfigWrapper.getBooleanProperty("show.hiddens");

		fileListing.summaryLabel.clearTotals();
		File[] files = dir.listFiles(filter);
		for (File file : files) {
			if (file.isDirectory()) {
				fileListing.summaryLabel.dirsTotal++;
			} else {
				fileListing.summaryLabel.filesTotal++;
				fileListing.summaryLabel.sizeTotal += file.length();
			}
			FileListingRow row = new FileListingRow(file);
			model.addRow(row);
			model.fireTableDataChanged();
		}
		fileListing.summaryLabel.update();
		JTabbedPane tabbedPane = (JTabbedPane) fileListing.getParent();

		if (tabbedPane != null) {
			if (first) {
				first = false;
			} else {
				int selectedIndex = tabbedPane.getSelectedIndex();
				if (selectedIndex != -1) {
					tabbedPane.setTitleAt(selectedIndex, dir.getName());
				}
			}
			tabbedPane.validate();
		}

		Comparator<FileListingRow> comparator = null;
		switch (fileListing.getHeader().getActiveColumn()) {
		case 0:
			comparator = new NameComparator();
			break;
		case 1:
			comparator = new ExtensionComparator();
			break;
		case 2:
			comparator = new SizeComparator();
			break;
		case 3:
			comparator = new DateComparator();
			break;
		case 4:
			comparator = new AttributesComparator();
			break;
		}
		Collections.sort(model.data, comparator);
		if (fileListing.getHeader().isReversedOrder()) {
			Collections.reverse(model.data);
		}

		model.addSystemDirs();
		model.fireTableDataChanged();
	}

	public void focusGained(FocusEvent event) {
		MainGUI.app.currentPanel = fileListing.id;
		MainGUI.app.getSource().historical.updateActions();
	}

	public void focusLost(FocusEvent event) {
	}

	public ArrayList<File> getSelectedFiles() {
		ArrayList<File> selectedFiles = new ArrayList<File>();
		int[] selectedRows = getSelectedRows();
		for (int selectedRow : selectedRows) {
			selectedFiles.add(model.getRow(selectedRow).getFile());
		}

		return selectedFiles;
	}

	private class DragOut implements DragGestureListener, DragSourceListener {

		public void dragGestureRecognized(DragGestureEvent event) {
			DndTransport dndCommand = new DndTransport(fileListing.id);
			Cursor cursor = null;
			if (event.getDragAction() == DnDConstants.ACTION_COPY) {
				cursor = DragSource.DefaultCopyDrop;
			} else { // DnDConstants.ACTION_MOVE:
				cursor = DragSource.DefaultMoveDrop;
			}
			ArrayList<File> selectedFiles = getSelectedFiles();
			if (selectedFiles.size() > 0) {
				dragSource.startDrag(event, cursor, dndCommand, this);
			}
		}

		public void dragDropEnd(DragSourceDropEvent arg0) {
		}

		public void dragEnter(DragSourceDragEvent arg0) {
		}

		public void dragExit(DragSourceEvent arg0) {
		}

		public void dragOver(DragSourceDragEvent arg0) {
		}

		public void dropActionChanged(DragSourceDragEvent arg0) {
		}
	}

	private class DropIn extends DropTargetAdapter {

		public void drop(DropTargetDropEvent event) {
			if (event.getSource().equals(this)) {
				event.rejectDrop();
				return;
			}
			Transferable transferable = event.getTransferable();
			if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				try {
					final String panelID = transferable.getTransferData(
							DataFlavor.stringFlavor).toString();
					if (panelID.equals(fileListing.id.toString())) {
						event.rejectDrop();
						return;
					}
					event.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

					final int action = event.getDropAction();
					event.getDropTargetContext().dropComplete(true);
					if (action == DnDConstants.ACTION_COPY) {
						// Controller.copyFiles();
					} else {
						// Controller.moveFiles();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				event.rejectDrop();
			}
		}
	}
}
