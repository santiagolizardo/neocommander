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

import java.util.ArrayList;
import java.util.ListIterator;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.actions.HistoricalActions;

public class Historical {

	private ArrayList<String> history;
	private ListIterator<String> iterator;

	private MainWindow mainWindow;

	public Historical(MainWindow mainWindow) {
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
