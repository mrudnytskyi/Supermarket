package org.test.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Myroslav Rudnytskyi
 * @version 19.06.2016
 * @see Product
 */
public class ProductTest {

	private Category category;

	@Before
	public void setUp() throws Exception {
		category = new Category("category");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalConstructorParameters() throws Exception {
		new Product("", category, null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullConstructorParameters() throws Exception {
		new Product(null, category);
	}

	@Test(expected = NullPointerException.class)
	public void testNullConstructorArguments() throws Exception {
		new Product("title", null);
	}

	@Test
	public void testHashCode() throws Exception {
		// setup
		Product product = new Product("title", category, new BigDecimal(10), ProductStatus.ABSENT);
		int expectedHashCode = product.hashCode();
		// execute
		product.setPrice(new BigDecimal(10));
		product.setStatus(ProductStatus.ABSENT);
		int actualHashCode = product.hashCode();
		// verify
		assertEquals(actualHashCode, expectedHashCode);
	}
}