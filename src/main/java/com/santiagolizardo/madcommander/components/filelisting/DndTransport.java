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

package com.santiagolizardo.madcommander.components.filelisting;

import com.santiagolizardo.madcommander.components.filelisting.FileListing.Position;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;



public record DndTransport(Position panelID) implements Transferable {

	public Object getTransferData(DataFlavor dataFlavor)
			throws UnsupportedFlavorException, IOException {
		return panelID.toString();
	}

	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] { DataFlavor.stringFlavor };
	}

	public boolean isDataFlavorSupported(DataFlavor dataFlavor) {
		return (dataFlavor.equals(DataFlavor.stringFlavor));
	}
}
