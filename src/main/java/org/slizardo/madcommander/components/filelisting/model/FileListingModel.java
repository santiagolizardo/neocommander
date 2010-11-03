/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FileListingModel.java,v 1.12 2010/01/21 17:02:49 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.table.AbstractTableModel;

import org.slizardo.madcommander.components.filelisting.FileListing.Format;


public class FileListingModel extends AbstractTableModel {

	public boolean isRoot;

	private int columnCount;

	public Format format;

	public List<FileListingRow> data;

	public FileListingModel() {
		data = new ArrayList<FileListingRow>();
		isRoot = false;
		columnCount = 0;

		format = Format.Full;
	}

	public int getRowCount() {
		return data.size();
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

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

	public synchronized Object getValueAt(int row, int column) {
		if (row > data.size())
			throw new IllegalArgumentException("index out of bounds (row)");
		FileListingRow fileListingRow = data.get(row);

		if (format.equals(Format.Brief) && column > 2
				|| format.equals(Format.Full) & column > 4)
			throw new IllegalArgumentException("index out of bounds (column)");

		Object value = null;

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

	public Class<FileListingRow> getColumnClass(int arg0) {
		return FileListingRow.class;
	}

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
