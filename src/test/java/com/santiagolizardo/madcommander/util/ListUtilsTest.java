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
