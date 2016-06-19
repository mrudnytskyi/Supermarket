package org.test.domain;

import org.junit.Test;

/**
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 * @see Category
 */
public class CategoryTest {

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalConstructorParameter() throws Exception {
		new Category("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullConstructorParameter() throws Exception {
		new Category(null);
	}
}