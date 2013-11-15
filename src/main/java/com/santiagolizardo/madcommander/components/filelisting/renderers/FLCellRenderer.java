package com.santiagolizardo.madcommander.components.filelisting.renderers;

import java.awt.Color;
import java.awt.Component;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.santiagolizardo.madcommander.components.filelisting.FileListing.Format;
import com.santiagolizardo.madcommander.components.filelisting.model.FileListingColumn;
import com.santiagolizardo.madcommander.resources.images.IconFactory;


public class FLCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8224673036449358242L;
	private Format format;

	public FLCellRenderer(Format format) {
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
		File file = colValue.getFile();

		if (column == 0) {
			Icon icon = IconFactory.getIconForFile(file);
			renderer.setIcon(icon);
		} else {
			if (format == Format.Brief) {
				switch (column) {
				case 1:
					renderer.setHorizontalAlignment(JLabel.CENTER);
					break;
				case 2:
					renderer.setHorizontalAlignment(JLabel.RIGHT);
					break;
				case 3:
					renderer.setHorizontalAlignment(JLabel.RIGHT);
					break;
				case 4:
					renderer.setHorizontalAlignment(JLabel.CENTER);
					break;
				}
			} else {
				switch (column) {
				case 1:
					renderer.setHorizontalAlignment(JLabel.CENTER);
					break;
				case 2:
					renderer.setHorizontalAlignment(JLabel.RIGHT);
					break;
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
