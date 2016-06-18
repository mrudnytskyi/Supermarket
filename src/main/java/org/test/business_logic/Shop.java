package org.test.business_logic;

import org.test.storage.Repository;

/**
 * Default implementation for {@code AbstractShop} interface.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public class Shop implements AbstractShop {

	private final Repository repository;
	private Category[] categories;

	public Shop(Repository repository, String... categoryNames) {
		this.repository = repository;
		createCategories(categoryNames);
	}

	// TODO : refactore to BuilderPattern for better interface
	// TODO : ShopBuilder.withRepository(new DBRepository()).withCategory("A").withCategory("B").build()
	private void createCategories(String[] categoryNames) {
		categories = new Category[categoryNames.length];
		for (int i = 0; i < categoryNames.length; i++) {
			categories[i] = new Category(categoryNames[i]);
		}
	}
}
