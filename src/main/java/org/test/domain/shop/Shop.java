package org.test.domain.shop;

import org.test.domain.Category;
import org.test.domain.Product;
import org.test.domain.ProductStatus;
import org.test.storage.Repository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Default implementation for {@code AbstractShop} interface.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public class Shop implements AbstractShop {

	private static final Logger LOG = Logger.getLogger(Shop.class.getName());

	private final AbstractShopRequisites requisites;

	private final Repository repository;

	private final Set<Category> categories = new HashSet<>();

	public Shop(AbstractShopRequisites requisites, Repository repository, Category... categories) {
		checkNotNull(requisites);
		checkNotNull(repository);
		this.requisites = requisites;
		this.repository = repository;
		for (Category category : categories) {
			boolean added = this.categories.add(category);
			if (!added) {
				LOG.severe("Ignoring not unique shop category...");
			}
		}
	}

	@Override
	public AbstractShopRequisites getRequisites() {
		return requisites;
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
