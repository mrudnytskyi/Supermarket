package org.test.domain.shop;

import org.test.domain.Category;
import org.test.domain.Product;

import java.util.Set;

/**
 * Shop abstraction.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public interface AbstractShop {

	Set<Category> getCategories();

	Product[] getProducts(Category category);

	void addProduct(Product product, Category category);

}
