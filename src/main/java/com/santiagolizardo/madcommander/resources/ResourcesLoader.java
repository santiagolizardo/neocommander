/*
  This file is part of MadCommander, a file manager with two panels.

  MadCommander is free software: you can redistribute it and/or modify it under
  the terms of the GNU General Public License as published by the Free Software
  Foundation, either version 3 of the License, or (at your option) any later
  version.

  MadCommander is distributed in the hope that it will be useful, but WITHOUT
  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
  FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
  details.

  You should have received a copy of the GNU General Public License along with
  MadCommander. If not, see <http://www.gnu.org/licenses/>.
 */
package com.santiagolizardo.madcommander.resources;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class ResourcesLoader {

	private static final Logger logger = Logger.getLogger(ResourcesLoader.class
			.getName());

	public static String readResource(Class<?> clazz, String name) {
		Charset charset = StandardCharsets.UTF_8;
		StringBuilder resourceContent = new StringBuilder();

		try (InputStream is = clazz.getResourceAsStream(name)) {
			if (is != null) {
				byte[] bytes = new byte[1024];
				while (is.read(bytes) > 0) {
					resourceContent.append(new String(bytes, charset));
				}
			}
		} catch (IOException ioe) {
			logger.severe(ioe.getMessage());
		}

		return resourceContent.toString();
	}

}
