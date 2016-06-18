package org.test.business_logic;

/**
 * Concrete shop class. Note, that this class created as
 * <a href="https://en.wikipedia.org/wiki/Singleton_pattern">singleton</a> due to business-rules.
 * <p>See <i>Effective Java 2nd Edition, Item 3: Enforce the singleton property with a private constructor or
 * an enum type</i> for the details how implement enum-based singletons.</p>
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public enum PerfectShop implements Shop {
	INSTANCE
}
