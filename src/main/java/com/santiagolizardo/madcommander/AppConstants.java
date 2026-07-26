/*
 * Copyright (C) 2026 Santiago Lizardo
 *
 * This file is part of NeoCommander.
 *
 * NeoCommander is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NeoCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.santiagolizardo.madcommander;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class AppConstants {

  private AppConstants() {

  }

  private static final Properties PROPS = loadProperties();

  public final static String APP_NAME = PROPS.getProperty("app.name", "NeoCommander");
  public final static String APP_VERSION = PROPS.getProperty("app.version", "unknown");
  public final static String APP_URL = PROPS.getProperty("app.url", "https://github.com/santiagolizardo/neocommander");
  public final static String DOWNLOAD_URL = APP_URL + "/releases";

  private static Properties loadProperties() {
    Properties props = new Properties();
    try (InputStream is = AppConstants.class.getResourceAsStream("/version.properties")) {
      if (is != null) {
        props.load(is);
      }
    } catch (IOException e) {
      // Fall through to defaults
    }
    return props;
  }

}
