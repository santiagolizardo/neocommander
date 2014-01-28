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
package com.santiagolizardo.madcommander.dialogs.search;

import java.util.regex.Pattern;

public class SearchParams {

	private String searchFor;

	private String searchIn;

	private boolean recursive;

	private boolean fileSizeIsSelected;

	private String condition;

	private long size;

	private String measure;

	private Pattern pattern;

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public SearchParams() {
		searchFor = "";
		searchIn = "";
		recursive = false;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public boolean isRecursive() {
		return recursive;
	}

	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}

	public String getSearchFor() {
		return searchFor;
	}

	public void setSearchFor(String searchFor) {
		this.searchFor = searchFor;
	}

	public String getSearchIn() {
		return searchIn;
	}

	public void setSearchIn(String searchIn) {
		this.searchIn = searchIn;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public boolean fileSizeIsSelected() {
		return fileSizeIsSelected;
	}

	public void setFileSizeIsSelected(boolean fileSizeIsSelected) {
		this.fileSizeIsSelected = fileSizeIsSelected;
	}
}
