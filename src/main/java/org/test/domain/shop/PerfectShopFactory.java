package org.test.domain.shop;

/**
 * Concrete factory for {@link PerfectShop} objects.
 *
 * @author Myroslav Rudnytskyi
 * @version 23.06.2016
 */
public class PerfectShopFactory implements ShopFactory {

	@Override
	public AbstractShop createShop() {
		return PerfectShop.getInstance();
	}
}
