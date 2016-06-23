package org.test.domain.shop;

import org.test.domain.Category;
import org.test.domain.Product;
import org.test.domain.ProductStatus;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Shop abstraction represents actions, which can be done using system.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public interface AbstractShop {

	AbstractShopRequisites getRequisites();

	Set<Category> getCategories();

	Product[] getProducts(Category category);

	void addProduct(Product product);

	void setProductStatus(Product product, ProductStatus status);

	void setProductPrice(Product product, BigDecimal price);
}
