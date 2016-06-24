package org.test.domain;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class represents encoder/decoder to transform {@code Category} between object notation and
 * <a href = "https://en.wikipedia.org/wiki/BSON">BSON</a>.
 *
 * @author Myroslav Rudnytskyi
 * @version 23.06.2016
 * @see Category
 */
public class CategoryCodec implements Codec<Category> {

	public static final String NAME_FIELD = "name";
	public static final String DESCRIPTION_FIELD = "description";
	public static final String LOGO_URL_FIELD = "logoUrl";
	public static final String DISCOUNT_PERCENTAGE_FIELD = "discountPercentage";

	@Override
	public Category decode(BsonReader reader, DecoderContext decoderContext) {
		reader.readStartDocument();
		String name = NAME_FIELD.equals(reader.readName()) ? reader.readString() : "";
		String description = DESCRIPTION_FIELD.equals(reader.readName()) ? reader.readString() : "";
		String logoUrl = LOGO_URL_FIELD.equals(reader.readName()) ? reader.readString() : "";
		String discountPercentage = DISCOUNT_PERCENTAGE_FIELD.equals(reader.readName()) ? reader.readString() : "";
		try {
			return new Category(name, description, new URL(logoUrl), new BigDecimal(discountPercentage));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void encode(BsonWriter writer, Category category, EncoderContext encoderContext) {
		writer.writeStartDocument();
		writer.writeName(NAME_FIELD);
		writer.writeString(category.getName());
		writer.writeName(DESCRIPTION_FIELD);
		writer.writeString(category.getDescription());
		writer.writeName(LOGO_URL_FIELD);
		writer.writeString(category.getLogoUrl().toString());
		writer.writeName(DISCOUNT_PERCENTAGE_FIELD);
		writer.writeString(category.getDiscountPercentage().toString());
		writer.writeEndDocument();
	}

	@Override
	public Class<Category> getEncoderClass() {
		return Category.class;
	}
}
