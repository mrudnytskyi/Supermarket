package org.test.domain;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Domain object represents product category in shop.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public class Category {

	private final String name;

	private String description;

	private URL logoUrl;

	private BigDecimal discountPercentage;

	public Category(String name) {
		this(name, null, null, null);
	}

	public Category(String name, String description, URL logoUrl, BigDecimal discountPercentage) {
		checkArgument(!isNullOrEmpty(name));
		this.name = name;
		this.description = description;
		this.logoUrl = logoUrl;
		this.discountPercentage = discountPercentage;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public URL getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(URL logoUrl) {
		this.logoUrl = logoUrl;
	}

	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Category category = (Category) o;
		return Objects.equals(discountPercentage, category.discountPercentage) &&
				Objects.equals(name, category.name) &&
				Objects.equals(description, category.description) &&
				Objects.equals(logoUrl, category.logoUrl);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, description, logoUrl, discountPercentage);
	}

	@Override
	public String toString() {
		return "Category{name=" + name + ", description=" + description + ", logoUrl=" + logoUrl +
				", discountPercentage=" + discountPercentage + "}";
	}
}
