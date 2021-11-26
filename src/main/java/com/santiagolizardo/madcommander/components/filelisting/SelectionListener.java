/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  MadCommander is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.components.filelisting;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.components.SummaryPanel;
import com.santiagolizardo.madcommander.components.filelisting.model.FileListingModel;
import com.santiagolizardo.madcommander.components.filelisting.model.FileListingRow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class SelectionListener implements ListSelectionListener {

	private final JTable table;

	private final SummaryPanel summaryLabel;
	
	private final MainWindow mainWindow;

	public SelectionListener(MainWindow mainWindow, JTable table, SummaryPanel summaryLabel) {
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
