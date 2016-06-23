package org.test.domain.shop;

/**
 * Concrete factory for {@link CheapShop} objects.
 *
 * @author Myroslav Rudnytskyi
 * @version 23.06.2016
 */
public class CheapShopFactory implements ShopFactory {

	@Override
	public AbstractShop createShop() {
		return CheapShop.getInstance();
	}
}
