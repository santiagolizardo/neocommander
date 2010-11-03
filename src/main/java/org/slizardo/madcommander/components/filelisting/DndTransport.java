/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: DndTransport.java,v 1.4 2010/01/21 17:02:44 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.slizardo.madcommander.components.filelisting.FileListing.Position;



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
