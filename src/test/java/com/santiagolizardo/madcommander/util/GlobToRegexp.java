/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.santiagolizardo.madcommander.util;

import junit.framework.Assert;

public class GlobToRegexp {

	public void testConversion()
	{
		Assert.assertEquals("^.*\\.txt$", GlobUtils.convertGlobToRegexp("*.txt").toString());
	}
}
