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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ListUtilsTest {

	@Test
	public void testImplode() {
		List<String> names = Arrays.asList("Foo", "Bar", "John", "Doe");
		Assert.assertEquals("Foo,Bar,John,Doe", String.join(",", names));
		Assert.assertEquals("FooBarJohnDoe", String.join("", names));
		Assert.assertEquals("", String.join(",", Collections.emptyList()));
	}

	@Test
	public void testExplode() {
		List<String> names = ListsUtils.explode(",", "Foo,Bar,John,Doe");
		Assert.assertEquals(4, names.size());
	}

	@Test
	public void testExplodeEmptyString() {
		List<String> names = ListsUtils.explode(",", "");
		Assert.assertTrue(names.isEmpty());
	}
}
