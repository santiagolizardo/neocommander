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
package com.santiagolizardo.madcommander.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ResourcesLoader {

	private static final Logger logger = Logger.getLogger(ResourcesLoader.class
			.getName());

	public static String readResource(Class<?> clazz, String name) {
		Charset charset = Charset.forName("UTF-8");
		StringBuilder resourceContent = new StringBuilder();

		try (InputStream is = clazz.getResourceAsStream(name)) {
			byte[] bytes = new byte[1024];
			while (is.read(bytes) > 0) {
				resourceContent.append(new String(bytes, charset));
			}
		} catch (IOException ioe) {
			logger.severe(ioe.getMessage());
		}

		return resourceContent.toString();
	}

	public static Collection<String> getResources(Pattern pattern)
			throws IOException {
		List<String> names = new ArrayList<>();

		final String classPath = System.getProperty("java.class.path");
		String path = classPath.split(File.pathSeparator)[0];
		File file = new File(path);
		if (file.isDirectory()) {
			addResourcesFromDirectory(names, file, pattern);
		} else {
			addResourcesFromJar(names, file, pattern);
		}

		return names;
	}

	private static void addResourcesFromJar(List<String> names, File file,
			Pattern pattern) throws IOException {
		try (ZipFile zipFile = new ZipFile(file)) {
			Enumeration<?> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String fileName = entry.getName();
				Matcher matcher = pattern.matcher(fileName);
				if (matcher.matches()) {
					String result = matcher.group(1);
					if (!names.contains(result)) {
						names.add(result);
					}
				}
			}
		}
	}

	private static void addResourcesFromDirectory(List<String> names,
			File directory, Pattern pattern) throws IOException {
		File[] fileList = directory.listFiles();
		for (File file : fileList) {
			if (file.isDirectory()) {
				addResourcesFromDirectory(names, file, pattern);
			} else {
				String fileName = file.getCanonicalPath();
				Matcher matcher = pattern.matcher(fileName);
				if (matcher.matches()) {
					String result = matcher.group(1);
					if (!names.contains(result)) {
						names.add(result);
					}
				}
			}
		}
	}
}
