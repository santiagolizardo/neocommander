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

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.InvalidDnDOperationException;
import java.io.IOException;
import java.util.logging.Logger;

public class DropIn extends DropTargetAdapter {

	private static final Logger LOGGER = Logger.getLogger(FileListing.class
			.getName());

	private FileListing fileListing;

	public DropIn(FileListing fileListing) {
		super();

		this.fileListing = fileListing;
	}

	@Override
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
				if (panelID.equals(fileListing.position.toString())) {
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
			} catch (UnsupportedFlavorException | InvalidDnDOperationException | IOException e) {
				LOGGER.warning(e.getMessage());
			}

		} else {
			event.rejectDrop();
		}
	}
}
