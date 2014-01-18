package com.santiagolizardo.madcommander.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListsUtils {

	public static String implode(String separator, List list) {
		if (list.isEmpty()) {
			return "";
		}

		StringBuilder buffer = new StringBuilder();
		int i = 0;
		for (; i < list.size() - 1; i++) {
			buffer.append((String) list.get(i));
			buffer.append(separator);
		}
		buffer.append(list.get(i));
		return buffer.toString();
	}

	public static List<String> explode(String separator, String string) {
		if (string.isEmpty()) {
			return new ArrayList<>();
		}
		
		return new ArrayList<>(Arrays.asList(string.split(separator)));
	}
}
