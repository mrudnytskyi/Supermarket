package org.test.storage;

import org.test.domain.Category;
import org.test.domain.Product;

import java.util.List;

/**
 * Provides API for storage access.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public interface Repository {

	List<Product> findByCategory(Category category);

	void insert(Product product);
}
