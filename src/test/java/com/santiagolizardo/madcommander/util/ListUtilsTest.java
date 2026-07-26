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

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListUtilsTest {

	@Test
	public void testImplode() {
		List<String> names = List.of("Foo", "Bar", "John", "Doe");
		Assertions.assertEquals("Foo,Bar,John,Doe", String.join(",", names));
		Assertions.assertEquals("FooBarJohnDoe", String.join("", names));
		Assertions.assertEquals("", String.join(",", Collections.emptyList()));
	}

	@Test
	public void testExplode() {
		List<String> names = ListsUtils.explode(",", "Foo,Bar,John,Doe");
		Assertions.assertEquals(4, names.size());
	}

	@Test
	public void testExplodeEmptyString() {
		List<String> names = ListsUtils.explode(",", "");
		Assertions.assertTrue(names.isEmpty());
	}
}
