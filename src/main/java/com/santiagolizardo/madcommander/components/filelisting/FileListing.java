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

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.components.PathLabel;
import com.santiagolizardo.madcommander.components.SummaryPanel;
import com.santiagolizardo.madcommander.components.filelisting.model.FileListingColumn;
import com.santiagolizardo.madcommander.components.filelisting.model.FileListingModel;
import com.santiagolizardo.madcommander.components.filelisting.model.FileListingRow;
import com.santiagolizardo.madcommander.components.filelisting.renderers.FileListingCellRenderer;
import com.santiagolizardo.madcommander.util.gui.DialogFactory;
import com.santiagolizardo.madcommander.util.gui.SwingUtil;
import java.io.IOException;

public class FileListing extends JPanel {

	private static final long serialVersionUID = -796592552883729956L;

	private static final Logger LOGGER = Logger.getLogger(FileListing.class
			.getName());

	public enum Position {
		Left, Right
	}

	public enum Format {
		Brief, Full
	}

	public Position position;

	public Historical historical;

	public SummaryPanel summaryLabel;

	public PathLabel pathLabel;

	private JScrollPane scroll;

	private Format format;

	private FileListingHeader header;

	private FileListingTable table;
	private FileListingCellRenderer cellRenderer;

	private File currentPath;

	private MainWindow mainWindow;

	public FileListing(final MainWindow mainWindow, Position position) {
		super();

		this.position = position;

		this.mainWindow = mainWindow;

		currentPath = new File(System.getProperty("user.dir"));

		historical = new Historical(mainWindow);

		table = new FileListingTable(mainWindow, this);
		header = new FileListingHeader(table);

		format = Format.Full;

		cellRenderer = new FileListingCellRenderer(format);

		updateColumnRenderers();

		pathLabel = new PathLabel();

		summaryLabel = new SummaryPanel();

		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);

		SelectionListener listener = new SelectionListener(mainWindow, table, summaryLabel);
		table.getSelectionModel().addListSelectionListener(listener);

		setBorder(BorderFactory.createBevelBorder(10));

		defineLayout();

		ToolTipManager.sharedInstance().unregisterComponent(table);
		ToolTipManager.sharedInstance().unregisterComponent(header);

		refreshFiles();
	}

	private void updateColumnRenderers() {
		TableColumnModel columnModel = table.getColumnModel();

		for (byte i = 0; i < (format == Format.Full ? 5 : 3); i++) {
			TableColumn tableColumn = columnModel.getColumn(i);
			tableColumn.setCellRenderer(cellRenderer);
		}
	}

	public FileListingHeader getHeader() {
		return header;
	}

	public void setFormat(Format format) {
		this.format = format;

		FileListingModel model = (FileListingModel) table.getModel();
		model.setFormat(format);
		header.setFormat(format);
		cellRenderer.setFormat(format);

		updateColumnRenderers();
	}

	public Format getFormat() {
		return format;
	}

	public void refreshFiles() {
		table.refresh();
	}

	public void setPath(String path) {
		File newPath = new File(path);
		if (newPath.exists()) {
			currentPath = newPath;
			refreshFiles();
		}
	}

	public String getPath() {
		if (currentPath == null) {
			currentPath = new File(".");
			LOGGER.warning("currentPath is null");
		}
		return currentPath.getAbsolutePath();
	}

	public void focusOnFilePath(String filePath) {
		table.clearSelection();
		int rowCount = table.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			FileListingColumn column = (FileListingColumn) table.model
					.getValueAt(i, 0);
			File file = column.getFile();
			if (file.getAbsolutePath().equals(filePath)) {
				table.scrollRectToVisible(table.getCellRect(i, i, true));
				table.addRowSelectionInterval(i, i);
				break;
			}
		}
	}

	public void focusOnFileName(String fileName) {
		table.clearSelection();
		int rowCount = table.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			FileListingColumn column = (FileListingColumn) table.model
					.getValueAt(i, 0);
			File file = column.getFile();
			if (file.getName().equals(fileName)) {
				table.scrollRectToVisible(table.getCellRect(i, i, true));
				table.addRowSelectionInterval(i, i);
				break;
			}
		}
	}

	public boolean isReversedOrder() {
		return header.isReversedOrder();
	}

	public void print() {
		try {
			table.print();
		} catch (Exception e) {
			DialogFactory
					.showErrorMessage(getParent(), e.getLocalizedMessage());
		}
	}

	public void selectAll() {
		table.selectAll();
		if (!table.model.isRoot) {
			table.removeRowSelectionInterval(0, 0);
		}
	}

	public void unselectAll() {
		table.clearSelection();
	}

	public void invertSelection() {
		int i = (table.model.isRoot ? 0 : 1);
		for (; i < table.getRowCount(); i++) {
			if (table.isRowSelected(i)) {
				table.removeRowSelectionInterval(i, i);
			} else {
				table.addRowSelectionInterval(i, i);
			}
		}
	}

	public int getNumberOfSelectedRows() {
		return table.getSelectedRowCount();
	}

	public List<File> getSelectedFiles() {
		return table.getSelectedFiles();
	}

	public void setFilter(FileFilter filter) {
		table.setFileFilter(filter);
	}

	public void selectGroup(String type, String searchPattern,
			boolean caseSensitive) {
		StringBuilder reBuffer = new StringBuilder();
		if ("Contains".equals(type)) {
			reBuffer.append(".*").append(searchPattern).append(".*");
		} else if ("Starts with".equals(type)) {
			reBuffer.append("^").append(searchPattern).append(".*");
		} else if ("Ends with".equals(type)) {
			reBuffer.append(".*").append(searchPattern).append("$");
		} else {
			reBuffer.append("^").append(searchPattern).append("$");
		}
		String regexp = reBuffer.toString();
		Pattern pattern = (caseSensitive ? Pattern.compile(regexp) : Pattern
				.compile(regexp, Pattern.CASE_INSENSITIVE));

		int i = (table.model.isRoot ? 0 : 1);
		for (; i < table.getRowCount(); i++) {
			FileListingRow row = (FileListingRow) table.model.getRow(i);
			String fileName = row.getName();
			Matcher matcher = pattern.matcher(fileName);
			if (matcher.matches()) {
				table.addRowSelectionInterval(i, i);
			}
		}
	}

	public void unselectGroup(String type, String searchPattern,
			boolean caseSensitive) {
		StringBuilder reBuffer = new StringBuilder();
		if ("Contains".equals(type)) {
			reBuffer.append(".*").append(searchPattern).append(".*");
		} else if ("Starts with".equals(type)) {
			reBuffer.append("^").append(searchPattern).append(".*");
		} else if ("Ends with".equals(type)) {
			reBuffer.append(".*").append(searchPattern).append("$");
		} else {
			reBuffer.append("^").append(searchPattern).append("$");
		}
		String regexp = reBuffer.toString();
		Pattern pattern = (caseSensitive ? Pattern.compile(regexp) : Pattern
				.compile(regexp, Pattern.CASE_INSENSITIVE));

		int i = (table.model.isRoot ? 0 : 1);
		for (; i < table.getRowCount(); i++) {
			FileListingRow row = (FileListingRow) table.model.getRow(i);
			String fileName = row.getName();
			Matcher matcher = pattern.matcher(fileName);
			if (matcher.matches()) {
				table.removeRowSelectionInterval(i, i);
			}
		}
	}

	public void setReversedOrder(boolean reversedOrder) {
		header.setReversedOrder(reversedOrder);
		header.repaint();
		refreshFiles();
	}

	public void execute() {
		int row = table.getSelectedRow();
		if (row == -1) {
			return;
		}
		String file = table.getModel().getValueAt(row, 0).toString();
		if ("..".equals(file)) {
			historical.add(currentPath.getAbsolutePath());
			currentPath = currentPath.getParentFile();
		} else {
			StringBuilder fileName = new StringBuilder();
			fileName.append(getPath());
			fileName.append(File.separator);
			fileName.append(file);
			File dir = new File(fileName.toString());
			if (dir.isDirectory()) {
				if (dir.listFiles() == null) {
					SwingUtil.beep();
					DialogFactory.showErrorMessage(mainWindow,
							"Access forbidden");
				} else {
					historical.add(dir.getAbsolutePath());
					currentPath = dir;
				}
			} else {
				LOGGER.info(String.format("Executing '%s'", dir.getAbsolutePath()));
				try {
					Desktop desktop = Desktop.getDesktop();
					desktop.open(dir);
				} catch (IOException e) {
					DialogFactory.showErrorMessage(getParent(), e.getMessage());
				}
			}
		}

		refreshFiles();
	}

	private void defineLayout() {
		setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 1.0;

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.weighty = 0.0;
		add(pathLabel, c);

		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 2;
		c.weighty = 1.0;
		add(scroll, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 3;
		c.weighty = 0.0;
		add(summaryLabel, c);
	}

	@Override
	public void requestFocus() {
		super.requestFocus();

		table.requestFocus();
	}

	public void selectFirstEntry() {
		if (table.getRowCount() > 0)
			table.setRowSelectionInterval(0, 0);
	}

	public void selectLastEntry() {
		if (table.getRowCount() > 0)
			table.setRowSelectionInterval(table.getRowCount() - 1,
					table.getRowCount() - 1);
	}

	static int i = 0;

	public void focusByString(String pattern) {
		if (pattern == null)
			return;
		FileListingModel model = (FileListingModel) table.getModel();
		int rowCount = model.getRowCount();

		pattern = pattern.toLowerCase();
		for (; i < rowCount; i++) {
			FileListingRow row = model.getRow(i);
			if (row.getName().toLowerCase().startsWith(pattern))
				break;
		}

		if (i < rowCount)
			table.getSelectionModel().setSelectionInterval(i, i++);
		else
			i = 0;
	}
}
