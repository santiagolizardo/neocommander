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

package com.santiagolizardo.madcommander.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OsDetectorTest {

	private String originalOsName;
	
	@BeforeEach
	public void setUp() {
		this.originalOsName = System.getProperty("os.name");
	}
	
	@AfterEach
	public void tearDown() {
		System.setProperty("os.name", originalOsName);
		OsDetector.reset();
	}
	
	@Test
	public void testWindowsIsDetected() throws Exception {
		System.setProperty("os.name", "Windows 95");
		Assertions.assertEquals(Os.Windows, OsDetector.get());
	}

	@Test
	public void testMacOsIsDetected() throws Exception {
		System.setProperty("os.name", "Mac OS X");
		Assertions.assertEquals(Os.Osx, OsDetector.get());
	}
	
	@Test
	public void testUnknownOsThrowsException() throws Exception {
		System.setProperty("os.name", "Foo Bar OS");
		Assertions.assertThrows(Exception.class, () -> {
			OsDetector.get();
		});
	}
}
