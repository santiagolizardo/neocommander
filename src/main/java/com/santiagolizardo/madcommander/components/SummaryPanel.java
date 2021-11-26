/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  MadCommander is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with MadCommander.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.components;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JPanel {

	public long sizeCount;
	public long sizeTotal;
    
    private final JLabel sizes;

    public int filesCount;
	public int filesTotal;
    
    private final JLabel files;

	public int dirsCount;
	public int dirsTotal;
    
    private final JLabel dirs;

    public SummaryPanel() {
		sizes = new JLabel();
        sizes.setHorizontalAlignment(JLabel.CENTER);
        files = new JLabel();
        files.setHorizontalAlignment(JLabel.CENTER);
        dirs = new JLabel();
        dirs.setHorizontalAlignment(JLabel.CENTER);
        
		Dimension preferredSize = getPreferredSize();
		setMaximumSize(new Dimension(Integer.MAX_VALUE, preferredSize.height));
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
		StringBuilder buffer = new StringBuilder();
        
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
