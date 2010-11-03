/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: SelectionListener.java,v 1.3 2006/03/10 21:32:46 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.slizardo.madcommander.components.SummaryPanel;
import org.slizardo.madcommander.components.filelisting.model.FileListingModel;
import org.slizardo.madcommander.components.filelisting.model.FileListingRow;


public class SelectionListener implements ListSelectionListener {

	private JTable table;

	private SummaryPanel summaryLabel;

	public SelectionListener(JTable table, SummaryPanel summaryLabel) {
		this.table = table;
		this.summaryLabel = summaryLabel;
	}

	public void valueChanged(ListSelectionEvent event) {
		FileListingModel model = (FileListingModel) table.getModel();
		summaryLabel.clearCounts();
		int[] selectedRows = table.getSelectedRows();
		for (int numRow : selectedRows) {
			FileListingRow row = model.getRow(numRow);
			if (row.file.isDirectory()) {
				summaryLabel.dirsCount++;
			} else {
				summaryLabel.sizeCount += row.file.length();
				summaryLabel.filesCount++;
			}
		}
		summaryLabel.update();
	}
}
