package org.test.domain.shop;

import org.test.domain.Category;
import org.test.storage.MongoRepository;

import static org.test.domain.shop.ShopRequisites.Builder.createRequisites;

/**
 * Concrete shop class. Note, that this class created as
 * <a href="https://en.wikipedia.org/wiki/Singleton_pattern">singleton</a> due to business-rules.
 * <blockquote>If you need to use lazy initialization [...] on a static field, use the lazy
 * initialization holder class idiom.</blockquote> See <i>Effective Java 2nd Edition, Item 71: Use lazy
 * initialization judiciously</i> for the details.
 * <p>Note, that these solution is thread-safe, because class initialization phase is non-concurrent, so no further
 * synchronization is required in the {@link #getInstance()} method.</p> See
 * <a href="https://docs.oracle.com/javase/specs/jls/se7/html/jls-12.html#jls-12.4">JLS</a> for details.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public class CheapShop {

	private CheapShop() {
	}

	public static AbstractShop getInstance() {
		return CheapShopHolder.INSTANCE;
	}

	private static class CheapShopHolder {
		private static final AbstractShop INSTANCE = new Shop(
				createRequisites("Cheap Shop").withDescription("Shop provides basic goods, so has low prices")
						.withLogo("http://cheap.com.ua/logo.png").withEmail("admin@cheap.com.ua").build(),
				new MongoRepository(),
				new Category("Cheap Goods"),
				new Category("Basic Goods", "All goods, which are necessary for human being", null, null)
		);
	}
}
