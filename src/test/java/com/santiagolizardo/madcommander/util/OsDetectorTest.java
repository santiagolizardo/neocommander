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
package com.santiagolizardo.madcommander.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OsDetectorTest {

	private String originalOsName;
	
	@Before
	public void setUp() {
		this.originalOsName = System.getProperty("os.name");
	}
	
	@After
	public void tearDown() {
		System.setProperty("os.name", originalOsName);
		OsDetector.reset();
	}
	
	@Test
	public void testWindowsIsDetected() throws Exception {
		System.setProperty("os.name", "Windows 95");
		Assert.assertEquals(Os.Windows, OsDetector.get());
	}

	@Test
	public void testMacOsIsDetected() throws Exception {
		System.setProperty("os.name", "Mac OS X");
		Assert.assertEquals(Os.Osx, OsDetector.get());
	}
	
	@Test(expected = Exception.class)
	public void testUnknownOsThrowsException() throws Exception {
		System.setProperty("os.name", "Foo Bar OS");
		OsDetector.get();
	}
}
