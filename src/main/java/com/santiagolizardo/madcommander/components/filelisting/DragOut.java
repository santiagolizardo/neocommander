/*
 * Copyright (C) 2026 Santiago Lizardo
 *
 * This file is part of NeoCommander.
 *
 * NeoCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NeoCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
