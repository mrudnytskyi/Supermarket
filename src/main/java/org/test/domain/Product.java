package org.test.domain;

import java.util.Objects;

/**
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public class Product {
	private String title;
	private int price;
	private ProductStatus status;
	private Category category;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
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

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return price == product.price &&
				Objects.equals(title, product.title) &&
				status == product.status &&
				Objects.equals(category, product.category);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, price, status, category);
	}
}
