/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: FileListing.java,v 1.31 2010/01/26 17:57:11 slizardo Exp $
 */
package org.slizardo.madcommander.components.filelisting;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.slizardo.madcommander.MainGUI;
import org.slizardo.madcommander.components.PathLabel;
import org.slizardo.madcommander.components.SummaryPanel;
import org.slizardo.madcommander.components.filelisting.model.FileListingColumn;
import org.slizardo.madcommander.components.filelisting.model.FileListingModel;
import org.slizardo.madcommander.components.filelisting.model.FileListingRow;
import org.slizardo.madcommander.components.filelisting.renderers.FLCellRenderer;
import org.slizardo.madcommander.config.ConfigWrapper;
import org.slizardo.madcommander.util.gui.DialogFactory;
import org.slizardo.madcommander.util.gui.SwingUtil;


public class FileListing extends JPanel {

	private static final long serialVersionUID = -796592552883729956L;

	private static Logger logger = Logger.getLogger("FileListing");

	public enum Position {
		Left, Right
	}

	public enum Format {
		Brief, Full
	}

	public Position id;

	public Historical historical;

	public SummaryPanel summaryLabel;

	public PathLabel pathLabel;

	private JScrollPane scroll;

	private Format format;

	private FileListingHeader header;

	private FileListingTable table;
	private FLCellRenderer cellRenderer;

	private File currentPath;

	public FileListing(Position id) {
		super();

		this.id = id;

		currentPath = new File(System.getProperty("user.dir"));

		historical = new Historical();

		table = new FileListingTable(this);
		header = new FileListingHeader(table);

		format = Format.Full;

		cellRenderer = new FLCellRenderer(format);

		updateColumnRenderers();

		loadProperties();

		pathLabel = new PathLabel();

		summaryLabel = new SummaryPanel();

		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);

		SelectionListener listener = new SelectionListener(table, summaryLabel);
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
		currentPath = new File(path);
		refreshFiles();
	}

	public String getPath() {
		return currentPath.getAbsolutePath();
	}

	public void focusOnFile(String fileName) {
		table.clearSelection();
		for (int i = 0; i < table.getRowCount(); i++) {
			FileListingColumn column = (FileListingColumn) table.model
					.getValueAt(i, 0);
			File file = column.getFile();
			if (file.getAbsolutePath().equals(fileName)) {
				table.addRowSelectionInterval(i - 1, i);
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

	public ArrayList<File> getSelectedFiles() {
		return table.getSelectedFiles();
	}

	public void loadProperties() {
		String path = ConfigWrapper.getProperty("current.path." + id);
		currentPath = new File(path);
	}

	public void saveProperties() {
		ConfigWrapper.setProperty("current.path." + id, getPath());
	}

	public void setFilter(FileFilter filter) {
		table.setFileFilter(filter);
	}

	public void selectGroup(String type, String searchPattern,
			boolean caseSensitive) {
		StringBuffer reBuffer = new StringBuffer();
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
		StringBuffer reBuffer = new StringBuffer();
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
			currentPath = currentPath.getParentFile();
		} else {
			StringBuffer fileName = new StringBuffer();
			fileName.append(getPath());
			fileName.append(File.separator);
			fileName.append(file);
			File dir = new File(fileName.toString());
			if (dir.isDirectory()) {
				if (dir.listFiles() == null) {
					SwingUtil.beep();
					DialogFactory
							.showErrorMessage(MainGUI.app, "Access forbidden");
				} else {
					currentPath = dir;
					historical.add(dir.getParentFile().getAbsolutePath());
				}
			} else {
				StringBuffer buffer = new StringBuffer();
				buffer.append("Executing [ ");
				buffer.append(dir.getAbsolutePath());
				buffer.append(" ]");
				logger.info(buffer.toString());
				try {
					Desktop desktop = Desktop.getDesktop();
					desktop.open(dir);
				} catch (Exception e) {
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
			table.setRowSelectionInterval(table.getRowCount() - 1, table
					.getRowCount() - 1);
	}

	static int i = 0;
	
	public void focusByString(String pattern) {
		if(pattern == null) return;
		FileListingModel model = (FileListingModel) table.getModel();
		int rowCount = model.getRowCount();
		
		pattern = pattern.toLowerCase();
		for ( ; i < rowCount; i++) {
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

