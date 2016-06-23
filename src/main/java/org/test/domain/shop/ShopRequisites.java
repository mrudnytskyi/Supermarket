package org.test.domain.shop;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class encapsulate general information about shop and created immutable for productivity purposes.
 * <p>Note, that it uses <a href="https://en.wikipedia.org/wiki/Fluent_interface">Fluent interface pattern</a> for
 * creation, because of great amount of {@link String} parameters. Sample code snippet:
 * <pre>{@code
 *     import static org.test.domain.shop.ShopRequisites.Builder.createRequisites;
 *     // more code
 *     ShopRequisites shop = createRequisites("name").withLogo("no_logo.png").withEmail("admin@sample.com").build();
 *     }
 * </pre>
 * </p>
 *
 * @author Myroslav Rudnytskyi
 * @version 23.06.2016
 */
public class ShopRequisites implements AbstractShopRequisites {

	private final String name;
	private final String description;
	private final URL logoUrl;
	private final String email;

	private ShopRequisites(Builder builder) {
		this.name = builder.name;
		this.description = builder.description;
		this.logoUrl = builder.logoUrl;
		this.email = builder.email;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public URL getLogoUrl() {
		return logoUrl;
	}

	@Override
	public String getEmail() {
		return email;
	}

	public static class Builder {
		private String name;
		private String description;
		private URL logoUrl;
		private String email;

		private Builder() {
		}

		public static Builder createRequisites(String name) {
			Builder builder = new Builder();
			builder.name = name;
			return builder;
		}

		public Builder withDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder withLogo(String logoUrl) {
			try {
				this.logoUrl = new URL(logoUrl);
			} catch (MalformedURLException e) {
				throw new IllegalArgumentException(e);
			}
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public ShopRequisites build() {
			return new ShopRequisites(this);
		}
	}
}
