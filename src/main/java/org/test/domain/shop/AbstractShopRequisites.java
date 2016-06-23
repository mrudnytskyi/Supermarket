package org.test.domain.shop;

import java.net.URL;

/**
 * Abstraction provides API for general information about shop.
 *
 * @author Myroslav Rudnytskyi
 * @version 23.06.2016
 */
public interface AbstractShopRequisites {

	String getName();

	String getDescription();

	URL getLogoUrl();

	String getEmail();
}
