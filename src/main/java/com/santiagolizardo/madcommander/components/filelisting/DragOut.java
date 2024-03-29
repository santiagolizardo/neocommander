/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.santiagolizardo.madcommander.components.filelisting;

import java.awt.*;
import java.awt.dnd.*;
import java.io.File;
import java.util.List;

public class DragOut implements DragGestureListener, DragSourceListener {

	private final DragSource dragSource;
	private final FileListing fileListing;
	private final FileListingTable fileListingTable;
	
	public DragOut(DragSource dragSource, FileListing fileListing, FileListingTable fileListingTable) {
		super();
		
		this.dragSource = dragSource;
		this.fileListing = fileListing;
		this.fileListingTable = fileListingTable;
	}
	
	@Override
	public void dragGestureRecognized(DragGestureEvent event) {
		DndTransport dndCommand = new DndTransport(fileListing.position);
		Cursor cursor;
		if (event.getDragAction() == DnDConstants.ACTION_COPY) {
			cursor = DragSource.DefaultCopyDrop;
		} else { // DnDConstants.ACTION_MOVE:
			cursor = DragSource.DefaultMoveDrop;
		}
		List<File> selectedFiles = fileListingTable.getSelectedFiles();
		if (selectedFiles.size() > 0) {
			dragSource.startDrag(event, cursor, dndCommand, this);
		}
	}

	@Override
	public void dragDropEnd(DragSourceDropEvent arg0) {
	}

	@Override
	public void dragEnter(DragSourceDragEvent arg0) {
	}

	@Override
	public void dragExit(DragSourceEvent arg0) {
	}

	@Override
	public void dragOver(DragSourceDragEvent arg0) {
	}

	@Override
	public void dropActionChanged(DragSourceDragEvent arg0) {
	}
}
