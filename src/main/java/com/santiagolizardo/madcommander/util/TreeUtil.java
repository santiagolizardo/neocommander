/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
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
