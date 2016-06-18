package org.test.business_logic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
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
		ShopFactory factory = () -> PerfectShop.INSTANCE;
		// execute
		AbstractShop shop = factory.createShop();
		// verify
		assertThat(shop, is(notNullValue()));
		assertTrue(shop instanceof PerfectShop);
	}

	@Test
	public void testCreateCheapShop() throws Exception {
		// setup
		ShopFactory factory = () -> CheapShop.INSTANCE;
		// execute
		AbstractShop shop = factory.createShop();
		// verify
		assertThat(shop, is(notNullValue()));
		assertTrue(shop instanceof CheapShop);
	}
}