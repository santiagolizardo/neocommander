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
package com.santiagolizardo.madcommander.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;

/**
 *
 * @author santiago
 */
public class ListUtilsTest {

	public void testImplode() {
		List<String> names = Arrays.asList(new String[]{"Foo", "Bar", "John", "Doe"});
		Assert.assertEquals("Foo,Bar,John,Doe", ListsUtils.implode(",", names));
		Assert.assertEquals("FooBarJohnDoe", ListsUtils.implode("", names));
		Assert.assertEquals("", ListsUtils.implode(",", new ArrayList()));
	}

	public void testExplode() {
		List<String> names = ListsUtils.explode(",", "Foo,Bar,John,Doe");
		Assert.assertEquals(4, names.size());

		names = ListsUtils.explode(",", "");
		Assert.assertTrue(names.isEmpty());
	}
}
