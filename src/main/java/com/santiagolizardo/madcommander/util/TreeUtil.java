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
package com.santiagolizardo.madcommander.util;

import java.io.File;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class TreeUtil {

	public static void addDirs(JTree tree, DefaultMutableTreeNode node,
			File file) {
		File[] files = file.listFiles();
		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			File dir = files[i];
			if (dir.isDirectory()) {
				DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(dir
						.getName());
				node.insert(dirNode, node.getChildCount());
				((DefaultTreeModel) tree.getModel()).reload();
				TreeUtil.addDirs(tree, dirNode, dir);
			}
		}
	}
}
