/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.filelisting;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.santiagolizardo.madcommander.components.SummaryPanel;
import com.santiagolizardo.madcommander.components.filelisting.model.FileListingModel;
import com.santiagolizardo.madcommander.components.filelisting.model.FileListingRow;


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
