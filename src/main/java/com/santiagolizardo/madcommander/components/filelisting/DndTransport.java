/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.filelisting;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.santiagolizardo.madcommander.components.filelisting.FileListing.Position;



public class DndTransport implements Transferable {

	public Position panelID;
	
	public DndTransport(Position panelID) {
		this.panelID = panelID;
	}

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
