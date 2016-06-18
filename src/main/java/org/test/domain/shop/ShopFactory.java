package org.test.domain.shop;

/**
 * Represents API for creating {@code Shop} object, do not specifying concrete classes.
 * See <a href="https://en.wikipedia.org/wiki/Factory_method_pattern">Factory method pattern</a> for details.
 * <p>Note, that these interface was created as <b>functional</b> for using in Java 8 and later applications.</p>
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
@FunctionalInterface
public interface ShopFactory {

	AbstractShop createShop();
}
