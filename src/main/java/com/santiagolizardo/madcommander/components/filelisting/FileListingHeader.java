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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.santiagolizardo.madcommander.components.filelisting.FileListing.Format;


public class FileListingHeader extends JTableHeader implements MouseListener {

	private static final long serialVersionUID = -7521856271497035896L;

	private FileListingTable listingTable;

	public Format format;

	private int activeColumn;

	private boolean reversedOrder;

	private SelectableColumnHeader[] columns = { new SelectableColumnHeader("Name", true),
			new SelectableColumnHeader("Ext"), new SelectableColumnHeader("Size"),
			new SelectableColumnHeader("Date"), new SelectableColumnHeader("Attr") };

	private void removeColumns() {
		TableColumnModel columnModel = getColumnModel();
		int numCols = columnModel.getColumnCount();
		for (int i = numCols - 1; i >= 0; i--) {
			columnModel.removeColumn(columnModel.getColumn(i));
		}
	}

	public void setFormat(Format format) {
		this.format = format;

		removeColumns();
		if (format.equals(Format.Brief)) {
			addBriefColumns();
		} else {
			addFullColumns();
		}
		revalidate();
	}

	public FileListingHeader(FileListingTable listingTable) {
		super(listingTable.getColumnModel());
		// super();

		listingTable.setTableHeader(this);

		setUpdateTableInRealTime(true);
		setReorderingAllowed(true);
		setDefaultRenderer(new SpecialHeaderRender(this));

		addMouseListener(this);

		this.listingTable = listingTable;

		addFullColumns();

		activeColumn = 0;
		reversedOrder = false;
	}

	public void mouseClicked(MouseEvent event) {
		TableColumnModel columnModel = getColumnModel();
		int currentIndex = columnModel.getColumnIndexAtX(event.getX());
		int oldIndex = activeColumn;

		if (activeColumn == currentIndex) {
			reversedOrder = !reversedOrder;
		} else {
			columns[oldIndex].setActive(false);
			columns[currentIndex].setActive(true);
			activeColumn = currentIndex;
		}
		repaint();
		listingTable.refresh();

		columnModel.getColumn(oldIndex).setHeaderValue(columns[oldIndex]);
		columnModel.getColumn(currentIndex).setHeaderValue(
				columns[currentIndex]);
		repaint();
	};

	public int getActiveColumn() {
		return activeColumn;
	}

	public void setReversedOrder(boolean reversedOrder) {
		this.reversedOrder = reversedOrder;
	}

	public boolean isReversedOrder() {
		return reversedOrder;
	}

	private void addFullColumns() {
		TableColumnModel columnModel = getColumnModel();
		for (byte i = 0; i < columns.length; i++) {
			TableColumn tableColumn = new TableColumn(i);
			tableColumn.setHeaderValue(columns[i]);
			columnModel.addColumn(tableColumn);
		}
		
		columnModel.getColumn(0).setPreferredWidth(240);
		columnModel.getColumn(1).setPreferredWidth(50);
		columnModel.getColumn(4).setPreferredWidth(60);		
	}

	private void addBriefColumns() {
		TableColumnModel columnModel = getColumnModel();
		for (byte i = 0, x = 0; i < columns.length; i++) {
			if (i == 2 || i == 3)
				continue;
			TableColumn tableColumn = new TableColumn(x);
			tableColumn.setHeaderValue(columns[i]);
			columnModel.addColumn(tableColumn);
			x++;
		}
		
		columnModel.getColumn(0).setPreferredWidth(240);
		columnModel.getColumn(1).setPreferredWidth(50);				
	}

	public void mouseEntered(MouseEvent event) {
	};

	public void mouseExited(MouseEvent event) {
	};

	public void mousePressed(MouseEvent event) {
	};

	public void mouseReleased(MouseEvent event) {
	};
}
