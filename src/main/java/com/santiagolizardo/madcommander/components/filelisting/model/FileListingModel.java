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
package com.santiagolizardo.madcommander.components.filelisting.model;

import com.santiagolizardo.madcommander.components.filelisting.FileListing.Format;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListingModel extends AbstractTableModel {


	public boolean isRoot;

	private int columnCount;

	public Format format;

	public List<FileListingRow> data;

	public FileListingModel() {
		data = new ArrayList<>();
		isRoot = false;
		columnCount = 0;

		format = Format.Full;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}

	public void columnAdded(TableColumnModelEvent event) {
		columnCount++;
	}

	public void columnRemoved(TableColumnModelEvent event) {
		columnCount--;
	}

	public void columnMarginChanged(ChangeEvent event) {
	}

	public void columnMoved(TableColumnModelEvent event) {
	}

	public void columnSelectionChanged(ListSelectionEvent event) {
	}

	@Override
	public synchronized Object getValueAt(int row, int column) {
		if (row > data.size())
			throw new IllegalArgumentException("index out of bounds (row)");
		FileListingRow fileListingRow = data.get(row);

		if (format.equals(Format.Brief) && column > 2
				|| format.equals(Format.Full) & column > 4)
			throw new IllegalArgumentException("index out of bounds (column)");

		Object value;

		switch (column) {
		case 0:
			value = fileListingRow.getName();
			break;
		case 1:
			value = fileListingRow.getExtension();
			break;
		case 2:
			if (format.equals(Format.Brief)) {
				value = fileListingRow.getAttributes();
			} else {
				value = fileListingRow.getSize();
			}
			break;
		case 3:
			value = fileListingRow.getDate();
			break;
		case 4:
			value = fileListingRow.getAttributes();
			break;
		default:
			return null;
		}

		return new FileListingColumn((byte) column, fileListingRow.getFile(),
				value);
	}

	public void addRow(FileListingRow row) {
		data.add(row);
	}

	@Override
	public Class<FileListingRow> getColumnClass(int arg0) {
		return FileListingRow.class;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		int i = (isRoot ? 0 : 1);
		// the cell is editable
		return (row >= i && column == 0);
	}

	public FileListingRow getRow(int index) {
		return data.get(index);
	}

	public void removeRow(int index) {
		data.remove(index);
	}

	public void addSystemDirs() {
		if (!isRoot) {
			data.add(0, new FileListingRow(new File("..")));
		}
	}

	public void clear() {
		data.clear();
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}
}
