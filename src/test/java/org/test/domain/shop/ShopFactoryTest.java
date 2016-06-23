package org.test.domain.shop;

import org.junit.Test;
import org.test.domain.Category;

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 * @see PerfectShop
 * @see CheapShop
 */
public class ShopFactoryTest {

	@Test
	public void testCreatePerfectShop() throws Exception {
		// setup
		Set<Category> expectedCategories = new HashSet<Category>() {{
			add(new Category("Perfect Goods"));
			add(new Category("Exotic Goods", "Exotic goods sale at 7% discount!", null, new BigDecimal(7)));
			add(new Category("Luxury Goods", "Wanna some luxury?", new URL("http://perfect.com.ua/luxuary.png"), null));
		}};
		ShopFactory factory = new PerfectShopFactory();
		// execute
		AbstractShop shop = factory.createShop();
		// verify
		assertThat(shop, is(notNullValue()));
		assertTrue(shop instanceof Shop);
		assertEquals(shop.getCategories(), expectedCategories);
	}

	@Test
	public void testCreateCheapShop() throws Exception {
		// setup
		Set<Category> expectedCategories = new HashSet<Category>() {{
			add(new Category("Cheap Goods"));
			add(new Category("Basic Goods", "All goods, which are necessary for human being", null, null));
		}};
		ShopFactory factory = new CheapShopFactory();
		// execute
		AbstractShop shop = factory.createShop();
		// verify
		assertThat(shop, is(notNullValue()));
		assertTrue(shop instanceof Shop);
		assertEquals(shop.getCategories(), expectedCategories);
	}
}