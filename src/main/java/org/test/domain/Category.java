package org.test.domain;

import java.util.Objects;

/**
 * Domain object represents product category in shop.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public class Category {

	private final String name;

	public Category(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Category category = (Category) o;
		return Objects.equals(name, category.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public String toString() {
		return "Document{{name=" + name + "}}";
	}
}
