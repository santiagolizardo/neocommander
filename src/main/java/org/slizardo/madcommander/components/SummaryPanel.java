/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 * @version $Id: SummaryPanel.java,v 1.2 2010/01/21 17:02:48 slizardo Exp $
 */
package org.slizardo.madcommander.components;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;

public class SummaryPanel extends JPanel {

	public int sizeCount;
	public int sizeTotal;
    
    private JLabel sizes;

    public int filesCount;
	public int filesTotal;
    
    private JLabel files;

	public int dirsCount;
	public int dirsTotal;
    
    private JLabel dirs;

    public SummaryPanel() {
        sizes = new JLabel();
        sizes.setHorizontalAlignment(JLabel.CENTER);
        files = new JLabel();
        files.setHorizontalAlignment(JLabel.CENTER);
        dirs = new JLabel();
        dirs.setHorizontalAlignment(JLabel.CENTER);
        
        defineLayout();
    }
    
	public void clearCounts() {
		sizeCount = 0;
		filesCount = 0;
		dirsCount = 0;
	}

	public void clearTotals() {
		sizeTotal = 0;
		filesTotal = 0;
		dirsTotal = 0;
	}

	public void update() {
		StringBuffer buffer = new StringBuffer();
        
        buffer.setLength(0);
		buffer.append(FileUtils.byteCountToDisplaySize(sizeCount)).append(" / ");
		buffer.append(FileUtils.byteCountToDisplaySize(sizeTotal));
        sizes.setText(buffer.toString());
        
        buffer.setLength(0);
		buffer.append(filesCount).append(" / ");
		buffer.append(filesTotal).append(" file(s)");
        files.setText(buffer.toString());
        
        buffer.setLength(0);
		buffer.append(dirsCount).append(" / ");
		buffer.append(dirsTotal).append(" dir(s)");
        dirs.setText(buffer.toString());
	}
    
    private void defineLayout() {
        GridLayout gridLayout = new GridLayout(1, 3);
        setLayout(gridLayout);
        
        add(sizes);
        add(files);
        add(dirs);
    }
}
