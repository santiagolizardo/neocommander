/*
 * Copyright (C) 2026 Santiago Lizardo
 *
 * This file is part of NeoCommander.
 *
 * NeoCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NeoCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.santiagolizardo.madcommander.components.filelisting.renderers;

import com.santiagolizardo.madcommander.components.filelisting.FileListing.Format;
import com.santiagolizardo.madcommander.components.filelisting.model.FileListingColumn;
import com.santiagolizardo.madcommander.resources.images.IconFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;


public class FileListingCellRenderer extends DefaultTableCellRenderer {


	private Format format;

	public FileListingCellRenderer(Format format) {
		super();

		this.format = format;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) super
				.getTableCellRendererComponent(table, value, isSelected,
						hasFocus, row, column);

		renderer.setIcon(null);
		renderer.setHorizontalAlignment(JLabel.LEFT);

		FileListingColumn colValue = (FileListingColumn) value;
		File file = colValue.file();

		if (column == 0) {
			Icon icon = IconFactory.getIconForFile(file);
			renderer.setIcon(icon);
		} else {
			if (format == Format.Brief) {
				switch (column) {
					case 1 -> renderer.setHorizontalAlignment(JLabel.CENTER);
					case 2, 3 -> renderer.setHorizontalAlignment(JLabel.RIGHT);
					case 4 -> renderer.setHorizontalAlignment(JLabel.CENTER);
				}
			} else {
				switch (column) {
					case 1 -> renderer.setHorizontalAlignment(JLabel.CENTER);
					case 2 -> renderer.setHorizontalAlignment(JLabel.RIGHT);
				}
			}
		}

		if (!isSelected) {
			if (row % 2 == 0) {
				renderer.setBackground(Color.WHITE);
			} else {
				renderer.setBackground(new Color(255, 255, 212));
			}
			
			if(file.isHidden())
				renderer.setForeground(Color.GRAY);
			else
				renderer.setForeground(Color.BLACK);			
		}
			
		return renderer;
	}
}
