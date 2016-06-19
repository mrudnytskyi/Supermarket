package org.test.domain.shop;

import org.junit.Test;
import org.mockito.Mockito;
import org.test.domain.Category;
import org.test.domain.Product;
import org.test.domain.ProductStatus;
import org.test.storage.MockRepository;
import org.test.storage.Repository;

import java.math.BigDecimal;
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
		Product product = new Product("test product", new Category("test category"));
		Mockito.when(mock.findByCategory(category)).thenReturn(new ArrayList<Product>() {{
			add(product);
		}});
		Shop shop = new Shop(mock);
		// execute
		Product[] actualProducts = shop.getProducts(category);
		// verify
		assertThat(actualProducts, is(notNullValue()));
		assertArrayEquals(new Product[]{product}, actualProducts);
		Mockito.verify(mock, times(1));
	}

	@Test(expected = NullPointerException.class)
	public void testNullConstructorParameter() throws Exception {
		new Shop(null);
	}

	@Test
	public void testSetProductStatus() throws Exception {
		// setup
		Product product = new Product("title", new Category("category"), new BigDecimal(10), ProductStatus.EXPECTED);
		Shop shop = new Shop(new MockRepository());
		// execute
		shop.setProductStatus(product, ProductStatus.ABSENT);
		// verify
		assertThat(product.getStatus(), is(ProductStatus.ABSENT));
	}

	@Test
	public void testSetProductPrice() throws Exception {
		// setup
		Product product = new Product("title", new Category("category"), new BigDecimal(10), ProductStatus.AVAILABLE);
		Shop shop = new Shop(new MockRepository());
		// execute
		shop.setProductPrice(product, new BigDecimal(100));
		// verify
		assertThat(product.getPrice(), is(new BigDecimal(100)));

	}
}