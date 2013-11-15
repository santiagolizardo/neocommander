/**
 * MadCommander
 * http://www.madcommander.com/
 * 
 * @author slizardo
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
