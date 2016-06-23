package org.test.domain.shop;

import org.test.domain.Category;
import org.test.storage.MongoRepository;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

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
public class PerfectShop {

	private PerfectShop() {
	}

	public static AbstractShop getInstance() {
		return PerfectShopHolder.INSTANCE;
	}

	private static class PerfectShopHolder {
		private static final AbstractShop INSTANCE = new Shop(
				createRequisites("Perfect Shop").withDescription("Shop provides only perfect luxury or exotic goods")
						.withEmail("contact@perfect.com.ua").withLogo("http://perfect.com.ua/shop-logo.png").build(),
				new MongoRepository(),
				new Category("Perfect Goods"),
				new Category("Luxury Goods", "Wanna some luxury?", createURL("http://perfect.com.ua/luxuary.png"), null),
				new Category("Exotic Goods", "Exotic goods sale at 7% discount!", null, new BigDecimal(7))
		);

		private static URL createURL(String url) {
			try {
				return new URL(url);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}
}
