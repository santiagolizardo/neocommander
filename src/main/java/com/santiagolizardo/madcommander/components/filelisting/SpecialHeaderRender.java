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

import com.santiagolizardo.madcommander.resources.images.IconFactory;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class SpecialHeaderRender extends JLabel implements TableCellRenderer {


	private final FileListingHeader header;

	public SpecialHeaderRender(FileListingHeader header) {
		this.header = header;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int colIndex) {
		SelectableColumnHeader columnHeader = (SelectableColumnHeader) value;
		setText(columnHeader.getText());
		setBorder(BorderFactory.createEtchedBorder());
		setHorizontalAlignment(JLabel.CENTER);
		if (columnHeader.isActive()) {
			String path;
			if (header.isReversedOrder()) {
				path = "order_desc.gif";
			} else {
				path = "order_asc.gif";
			}
			setIcon(IconFactory.newIcon(path));
		} else {
			setIcon(null);
		}

		return this;
	}
}
