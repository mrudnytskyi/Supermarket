package org.test.business_logic;

import org.junit.Test;

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
			add(new Category("Exotic Goods"));
			add(new Category("Luxury Goods"));
		}};
		ShopFactory factory = () -> PerfectShop.INSTANCE;
		// execute
		AbstractShop shop = factory.createShop();
		// verify
		assertThat(shop, is(notNullValue()));
		assertTrue(shop instanceof PerfectShop);
		assertEquals(shop.getCategories(), expectedCategories);
	}

	@Test
	public void testCreateCheapShop() throws Exception {
		// setup
		Set<Category> expectedCategories = new HashSet<Category>() {{
			add(new Category("Cheap Goods"));
			add(new Category("Basic Goods"));
		}};
		ShopFactory factory = () -> CheapShop.INSTANCE;
		// execute
		AbstractShop shop = factory.createShop();
		// verify
		assertThat(shop, is(notNullValue()));
		assertTrue(shop instanceof CheapShop);
		assertEquals(shop.getCategories(), expectedCategories);
	}
}