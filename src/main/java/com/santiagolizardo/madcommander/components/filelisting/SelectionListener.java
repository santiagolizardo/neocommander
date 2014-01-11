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

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.components.SummaryPanel;
import com.santiagolizardo.madcommander.components.filelisting.model.FileListingModel;
import com.santiagolizardo.madcommander.components.filelisting.model.FileListingRow;


public class SelectionListener implements ListSelectionListener {

	private JTable table;

	private SummaryPanel summaryLabel;
	
	private MadCommander mainWindow;

	public SelectionListener(MadCommander mainWindow, JTable table, SummaryPanel summaryLabel) {
		this.table = table;
		this.summaryLabel = summaryLabel;
		
		this.mainWindow = mainWindow;
	}

	public void valueChanged(ListSelectionEvent ev) {
		mainWindow.refreshButtons();
		
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
