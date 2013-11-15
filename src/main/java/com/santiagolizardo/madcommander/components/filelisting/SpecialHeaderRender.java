/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.filelisting;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.santiagolizardo.madcommander.resources.images.IconFactory;

public class SpecialHeaderRender extends JLabel implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4282579332132643029L;
	private FileListingHeader header;

	public SpecialHeaderRender(FileListingHeader header) {
		this.header = header;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int colIndex) {
		SelectableColumnHeader columnHeader = (SelectableColumnHeader) value;
		setText(columnHeader.getText());
		setBorder(BorderFactory.createEtchedBorder());
		setHorizontalAlignment(JLabel.CENTER);
		if (columnHeader.isActive()) {
			String path = null;
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
