/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: SpecialHeaderRender.java,v 1.6 2010/01/21 17:02:45 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.slizardo.madcommander.resources.images.IconFactory;


public class SpecialHeaderRender extends JLabel implements TableCellRenderer {

	private FileListingHeader header;

	public SpecialHeaderRender(FileListingHeader header) {
		this.header = header;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int rowIndex, int colIndex) {
		SelectableColumnHeader columnHeader = (SelectableColumnHeader) value;
		setText(columnHeader.getText());
		// setBorder(UIManager.getBorder("TableHeader.cellBorder"));
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
