/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * MadCommander is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * MadCommander. If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.components.filelisting;

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
import java.util.List;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.actions.SelectDriveAction;
import com.santiagolizardo.madcommander.actions.fileops.FileOpsFactory;
import com.santiagolizardo.madcommander.components.filelisting.model.AttributesComparator;
import com.santiagolizardo.madcommander.components.filelisting.model.DateComparator;
import com.santiagolizardo.madcommander.components.filelisting.model.ExtensionComparator;
import com.santiagolizardo.madcommander.components.filelisting.model.FileListingModel;
import com.santiagolizardo.madcommander.components.filelisting.model.FileListingRow;
import com.santiagolizardo.madcommander.components.filelisting.model.NameComparator;
import com.santiagolizardo.madcommander.components.filelisting.model.SizeComparator;
import com.santiagolizardo.madcommander.util.actions.InputMapUtil;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.InvalidDnDOperationException;
import java.io.IOException;
import java.util.logging.Logger;

public class FileListingTable extends JTable implements Runnable, FocusListener {

	private static final long serialVersionUID = 1317659226807828074L;

	private static final Logger logger = Logger.getLogger(FileListingTable.class.getName());

	private FileListing fileListing;

	private DragSource dragSource;

	public DropTarget dropTarget;

	public FileListingModel model;

	private FileFilter filter;

	private boolean first;

	private MainWindow mainWindow;

	public FileListingTable(final MainWindow mainWindow,
			FileListing fileListing) {
		super();

		this.mainWindow = mainWindow;

		first = true;

		setColumnSelectionAllowed(false);
		setShowHorizontalLines(false);
		setShowVerticalLines(true);
		setAutoCreateColumnsFromModel(false);

		model = new FileListingModel();
		setModel(model);

		this.fileListing = fileListing;

		FileListingKeyListener myKeyListener = new FileListingKeyListener(mainWindow);
		FileListingMouseListener myMouseListener = new FileListingMouseListener(mainWindow);

		addKeyListener(myKeyListener);
		addMouseListener(myMouseListener);

		dragSource = new DragSource();

		DragOut dragOut = new DragOut(dragSource, fileListing, this);

		dragSource.createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_COPY_OR_MOVE, dragOut);

		DropIn dropIn = new DropIn(fileListing);
		dropTarget = new DropTarget(this, dropIn);

		defineKeyBindings();
	}

	private void defineKeyBindings() {
		InputMap inputMap = getInputMap();
		ActionMap actionMap = getActionMap();

		FileOpsFactory fops = FileOpsFactory.getInstance(mainWindow);

		InputMapUtil.putAction(this, new SelectDriveAction(mainWindow));
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

	static int a = 0;

	public void refresh() {
		run();
	}

	@Override
	public void run() {
		model.clear();
		File dir = new File(fileListing.getPath());
		model.isRoot = dir.getParentFile() == null;
		if (dir.exists() == false) {
			dir = new File(System.getProperty("user.dir"));
		}
		fileListing.pathLabel.setText(dir.getAbsolutePath());

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
					String tabName = (dir.getParent() == null ? dir.getAbsolutePath() : dir.getName());
					tabbedPane.setTitleAt(selectedIndex, tabName);
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

	@Override
	public void focusGained(FocusEvent event) {
		mainWindow.currentPanel = fileListing.position;
		mainWindow.getSource().historical.updateActions();
	}

	@Override
	public void focusLost(FocusEvent event) {
	}

	public List<File> getSelectedFiles() {
		List<File> selectedFiles = new ArrayList<>();
		int[] selectedRows = getSelectedRows();
		for (int selectedRow : selectedRows) {
			selectedFiles.add(model.getRow(selectedRow).getFile());
		}

		return selectedFiles;
	}
}
