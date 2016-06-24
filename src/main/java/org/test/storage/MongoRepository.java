package org.test.storage;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.test.domain.Category;
import org.test.domain.CategoryCodec;
import org.test.domain.Product;
import org.test.domain.ProductStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.mongodb.MongoClient.getDefaultCodecRegistry;
import static com.mongodb.MongoClientOptions.builder;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromCodecs;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * Class provides access to the <a href="https://www.mongodb.com">MongoDB</a> storage using
 * <a href="https://github.com/google/gson">Gson</a> library.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public class MongoRepository implements Repository {

	public static final String COLLECTION_NAME = "products";

	private final MongoClient client;
	private final MongoCollection<Document> productsCollection;
	private final Gson gson = new Gson();
	private MongoDatabase database;

	public MongoRepository() {
		this("localhost", 27017);
	}

	public MongoRepository(String host, int port) {
		client = createMongoClient(host, port);
		database = client.getDatabase("supermarket");
		productsCollection = createOrGetProductsCollection();
	}

	private MongoClient createMongoClient(String host, int port) {
		checkArgument(!isNullOrEmpty(host));
		checkArgument(port > 0);

		MongoClientOptions options = builder().codecRegistry(fromRegistries(
				fromCodecs(new CategoryCodec()),
				getDefaultCodecRegistry())
		).build();
		return new MongoClient(host + ":" + port, options);
	}

	private MongoCollection<Document> createOrGetProductsCollection() {
		if (database.getCollection(COLLECTION_NAME) == null) {
			database.createCollection(COLLECTION_NAME);
		}
		return database.getCollection(COLLECTION_NAME);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		List<Product> result = new ArrayList<>();
		productsCollection.find(createFullEqualityFilter(category)).forEach(
				(Block<? super Document>) document -> result.add(gson.fromJson(document.toJson(), Product.class))
		);
		return result;
	}

	@Override
	public void insert(Product product) {
		productsCollection.insertOne(Document.parse(gson.toJson(product)));
	}

	@Override
	public void update(Product product) {
		String title = product.getTitle();
		Category category = product.getCategory();
		BigDecimal price = product.getPrice();
		ProductStatus status = product.getStatus();

		Bson update = new Document("$set", new Document("price", price.intValue()).append("status", status.name()));
		Bson filter = and(eq("title", title), createFullEqualityFilter(category));
		productsCollection.updateOne(filter, update);
	}

	private Bson createFullEqualityFilter(Category category) {
		List<Bson> eql = new ArrayList<Bson>() {{
			add(eq("category.name", category.getName()));
		}};
		if (category.getDescription() != null) {
			eql.add(eq("category.description", category.getDescription()));
		}
		if (category.getLogoUrl() != null) {
			eql.add(eq("category.logoUrl", category.getLogoUrl().toString()));
		}
		if (category.getDiscountPercentage() != null) {
			eql.add(eq("category.discountPercentage", category.getDiscountPercentage().intValue()));
		}
		return and(eql);
	}

	@Override
	public void close() {
		client.close();
	}

	/**
	 * for test purposes!
	 *
	 * @return wrapped database
	 */
	public MongoDatabase getMongoDatabase() {
		return database;
	}
}
