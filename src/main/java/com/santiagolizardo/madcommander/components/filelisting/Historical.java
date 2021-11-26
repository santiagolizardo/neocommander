/**
 * This file is part of MadCommander, a file manager with two panels.
 *
 * MadCommander is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * MadCommander is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * MadCommander. If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.components.filelisting;

import com.santiagolizardo.madcommander.MainWindow;
import com.santiagolizardo.madcommander.actions.HistoricalActions;

import java.util.ArrayList;
import java.util.List;

public class Historical {

	private int position;
	private List<String> history;

	private final MainWindow mainWindow;

	public Historical(MainWindow mainWindow) {
		history = new ArrayList<>();
		position = 0;
		this.mainWindow = mainWindow;
	}

	public void updateActions() {
		boolean previousEnabled = position > 0;
		boolean nextEnabled = position < history.size();

		HistoricalActions.getPreviousAction(mainWindow).setEnabled(
				previousEnabled);
		HistoricalActions.getNextAction(mainWindow).setEnabled(nextEnabled);
	}

	public void add(String path) {
		history.add(path);
		position++;

		if (position != history.size()) {
			history = history.subList(0, position);
		}

		updateActions();
	}

	public String getPrevious() {
		String previous = history.get(--position);
		updateActions();
		return previous;
	}

	public String getNext() {
		String next = null;
		if (position + 1 < history.size()) {
			history.get(++position);
			updateActions();
		}
		return next;
	}
}
