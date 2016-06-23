package org.test.domain.shop;

/**
 * Represents API for creating {@link AbstractShop} object, do not specifying concrete classes.
 * See <a href="https://en.wikipedia.org/wiki/Factory_method_pattern">Factory method pattern</a> for details.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public interface ShopFactory {

	AbstractShop createShop();
}
