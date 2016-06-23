package org.test;

import org.test.domain.Category;
import org.test.domain.Product;
import org.test.domain.ProductStatus;
import org.test.domain.shop.AbstractShop;
import org.test.domain.shop.CheapShop;
import org.test.domain.shop.PerfectShop;
import org.test.domain.shop.ShopFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Main application class.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public class Application {

	public static final int POOL_SIZE = 2;
	private static final AbstractShop perfectShop = ((ShopFactory) PerfectShop::getInstance).createShop();
	private static final AbstractShop cheapShop = ((ShopFactory) CheapShop::getInstance).createShop();
	private static final Logger LOG = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(POOL_SIZE);
		executorService.schedule((Runnable) () -> Application.supermarketRoutine(perfectShop), 0, TimeUnit.SECONDS);
		executorService.schedule((Runnable) () -> Application.supermarketRoutine(cheapShop), 10, TimeUnit.SECONDS);
		executorService.shutdown();
		try {
			executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			LOG.severe(e.getMessage());
		}
		LOG.info("All threads stopped!");
	}

	private static void supermarketRoutine(AbstractShop shop) {
		populateCategories(shop);
		changeStatusAndPrice(shop);
	}

	private static void populateCategories(AbstractShop shop) {
		List<Category> categories = getAvailableCategories(shop);
		for (Category category : categories) {
			for (int id = 0; id < 3; id++) {
				shop.addProduct(generateProduct(id, category));
			}
		}
	}

	private static Product generateProduct(int id, Category category) {
		Random random = new Random();
		BigDecimal price = new BigDecimal(random.nextInt(5));
		Product product = new Product("Product " + id, category, price, ProductStatus.AVAILABLE);
		LOG.info("Created product " + product);
		return product;
	}

	private static List<Category> getAvailableCategories(AbstractShop shop) {
		List<Category> categories = new ArrayList<>();
		categories.addAll(shop.getCategories());
		return categories;
	}

	private static void changeStatusAndPrice(AbstractShop shop) {
		List<Category> categories = getAvailableCategories(shop);
		Category absentCategory = categories.get(0);
		Product[] absentProducts = shop.getProducts(absentCategory);
		for (Product product : absentProducts) {
			shop.setProductStatus(product, ProductStatus.ABSENT);
			LOG.info("Absent product " + product);
		}
		for (int categoryIndex = 1; categoryIndex < categories.size(); categoryIndex++) {
			Product[] products = shop.getProducts(categories.get(categoryIndex));
			for (int productIndex = 0; productIndex < products.length; productIndex++) {
				Product currentProduct = products[productIndex];
				if (productIndex / 2 == 0) {
					shop.setProductStatus(currentProduct, ProductStatus.EXPECTED);
					LOG.info("Expected product " + currentProduct);
				} else {
					shop.setProductPrice(currentProduct, currentProduct.getPrice().multiply(new BigDecimal("1.2")));
					LOG.info("Change price product " + currentProduct);
				}
			}
		}
	}
}
