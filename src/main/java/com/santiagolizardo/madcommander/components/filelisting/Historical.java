/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
 */
package com.santiagolizardo.madcommander.components.filelisting;

import java.util.ArrayList;
import java.util.ListIterator;

import com.santiagolizardo.madcommander.MadCommander;
import com.santiagolizardo.madcommander.actions.HistoricalActions;

public class Historical {

	private ArrayList<String> history;
	private ListIterator<String> iterator;

	private MadCommander mainWindow;

	public Historical(MadCommander mainWindow) {
		history = new ArrayList<String>();
		iterator = history.listIterator();

		this.mainWindow = mainWindow;
	}

	public void updateActions() {
		boolean previousEnabled = iterator.hasPrevious();
		boolean nextEnabled = iterator.hasNext();

		HistoricalActions.getPreviousAction(mainWindow).setEnabled(
				previousEnabled);
		HistoricalActions.getNextAction(mainWindow).setEnabled(nextEnabled);
	}

	public void add(String path) {
		history.add(path);
		iterator = history.listIterator();
		while (iterator.hasNext())
			iterator.next();
		updateActions();
	}

	public String getPrevious() {
		String previous = iterator.previous();
		updateActions();
		return previous;
	}

	public String getNext() {
		String next = iterator.next();
		updateActions();
		return next;
	}
}
