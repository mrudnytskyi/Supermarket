package org.test.domain.shop;

import org.junit.Test;
import org.mockito.Mockito;
import org.test.domain.Category;
import org.test.domain.Product;
import org.test.storage.MockRepository;
import org.test.storage.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

/**
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 * @see Shop
 */
public class ShopTest {

	@Test
	public void testGetCategories() throws Exception {
		// setup
		Set<Category> expectedCategories = new HashSet<Category>() {{
			add(new Category("Test category"));
		}};
		Shop shop = new Shop(new MockRepository(), "Test category");
		// execute
		Set<Category> actualCategories = shop.getCategories();
		// verify
		assertThat(actualCategories, is(notNullValue()));
		assertEquals(expectedCategories, actualCategories);
	}

	@Test
	public void testGetCategoriesEmpty() throws Exception {
		// setup
		Shop shop = new Shop(new MockRepository());
		// execute
		Set<Category> actualCategories = shop.getCategories();
		// verify
		assertThat(actualCategories, is(notNullValue()));
		assertTrue(actualCategories.isEmpty());
	}

	@Test
	public void testGetProducts() throws Exception {
		// setup
		Repository mock = Mockito.mock(Repository.class);
		Category category = new Category("test");
		Mockito.when(mock.findByCategory(category)).thenReturn(new ArrayList<Product>() {{
			add(new Product());
		}});
		Shop shop = new Shop(mock);
		// execute
		Product[] actualProducts = shop.getProducts(category);
		// verify
		assertThat(actualProducts, is(notNullValue()));
		assertArrayEquals(new Product[]{new Product()}, actualProducts);
		Mockito.verify(mock, times(1));
	}
}