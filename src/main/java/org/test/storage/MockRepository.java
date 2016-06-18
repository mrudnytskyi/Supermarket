package org.test.storage;

import org.test.domain.Category;
import org.test.domain.Product;

import java.util.Collections;
import java.util.List;

/**
 * Does nothing {@link Repository} implementation!
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public class MockRepository implements Repository {

	@Override
	public List<Product> findByCategory(Category category) {
		return Collections.emptyList();
	}

	@Override
	public void insert(Product product) {
	}
}
