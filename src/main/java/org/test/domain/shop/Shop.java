package org.test.domain.shop;

import com.google.common.base.Preconditions;
import org.test.domain.Category;
import org.test.domain.Product;
import org.test.domain.ProductStatus;
import org.test.storage.Repository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Default implementation for {@code AbstractShop} interface.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public class Shop implements AbstractShop {

	private static final Logger LOG = Logger.getLogger(Shop.class.getName());

	private final Repository repository;

	private final Set<Category> categories = new HashSet<>();

	public Shop(Repository repository, String... categoryNames) {
		Preconditions.checkNotNull(repository);
		this.repository = repository;
		for (String categoryName : categoryNames) {
			boolean added = categories.add(new Category(categoryName));
			if (!added) {
				LOG.severe("Ignoring not unique shop category...");
			}
		}
	}

	@Override
	public Set<Category> getCategories() {
		return categories;
	}

	@Override
	public Product[] getProducts(Category category) {
		List<Product> found = repository.findByCategory(category);
		return found.toArray(new Product[found.size()]);
	}

	@Override
	public void addProduct(Product product) {
		repository.insert(product);
	}

	@Override
	public void setProductStatus(Product product, ProductStatus status) {
		product.setStatus(status);
		repository.update(product);
	}

	@Override
	public void setProductPrice(Product product, BigDecimal price) {
		product.setPrice(price);
		repository.update(product);
	}
}
