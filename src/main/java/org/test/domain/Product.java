package org.test.domain;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Domain object represents product.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public class Product {
	private final String title;
	private final Category category;
	private BigDecimal price;
	private ProductStatus status;

	public Product(String title, Category category) {
		this.title = title;
		this.category = category;
	}

	public Product(String title, Category category, BigDecimal price, ProductStatus status) {
		this(title, category);
		this.price = price;
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Objects.equals(price, product.price) &&
				Objects.equals(title, product.title) &&
				status == product.status &&
				Objects.equals(category, product.category);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, price, status, category);
	}

	@Override
	public String toString() {
		return "Document{{" + "title=" + title + ", category=" + category + ", price=" + price + ", status=" + status + "}}";
	}
}
