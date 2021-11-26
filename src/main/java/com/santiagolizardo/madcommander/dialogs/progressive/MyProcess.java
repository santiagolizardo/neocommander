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
package com.santiagolizardo.madcommander.dialogs.progressive;

import java.util.List;
import java.util.logging.Logger;

public class MyProcess {

	protected static Logger logger = Logger
			.getLogger(MyProcess.class.getName());

	protected String currentFile;

	public int currentProgress;

	public int totalProgress;

	protected String srcPath;

	protected String dstPath;

	protected List<String> files;

	public boolean cancel;

	public MyProcess() {
		super();

		cancel = false;
	}

	public void cancel() {
		cancel = true;
	}

	public String getCurrentFile() {
		return currentFile;
	}

	public boolean isCurrentComplete() {
		return (currentProgress >= 100);
	}

	public boolean isTotalComplete() {
		return (totalProgress >= 100);
	}
}
