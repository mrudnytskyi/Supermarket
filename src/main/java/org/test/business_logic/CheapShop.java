package org.test.business_logic;

import org.test.storage.Repository;

import java.util.Set;

/**
 * Concrete shop class. Note, that this class created as
 * <a href="https://en.wikipedia.org/wiki/Singleton_pattern">singleton</a> due to business-rules.
 * <p>See <i>Effective Java 2nd Edition, Item 3: Enforce the singleton property with a private constructor or
 * an enum type</i> for the details how implement enum-based singletons.</p>
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public enum CheapShop implements AbstractShop {
	INSTANCE(new Shop(new Repository() {
	}, "Cheap Goods", "Basic Goods"));

	private final Shop shop;

	CheapShop(Shop shop) {
		this.shop = shop;
	}

	@Override
	public Set<Category> getCategories() {
		return shop.getCategories();
	}
}
