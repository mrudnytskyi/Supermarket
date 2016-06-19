package org.test.storage;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.test.domain.Category;
import org.test.domain.Product;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Class provides access to the <a href="https://www.mongodb.com">MongoDB</a> storage using
 * <a href="https://github.com/google/gson">Gson</a> library.
 *
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 */
public class MongoRepository implements Repository {

	public static final String DATABASE_NAME = "supermarket";
	public static final String COLLECTION_NAME = "products";

	private final MongoClient client;
	private final MongoCollection<Document> productsCollection;

	private final Gson gson = new Gson();
	private MongoDatabase database;

	public MongoRepository() {
		this("localhost", 27017);
	}

	public MongoRepository(String host, int port) {
		client = new MongoClient(host, port);
		database = client.getDatabase(DATABASE_NAME);
		if (database.getCollection(COLLECTION_NAME) == null) {
			database.createCollection(COLLECTION_NAME);
		}
		productsCollection = database.getCollection(COLLECTION_NAME);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		MongoCursor<Document> queriedProducts = productsCollection.find().iterator();
		List<Product> result = new ArrayList<>();
		while (queriedProducts.hasNext()) {
			Product product = gson.fromJson(queriedProducts.next().toJson(), Product.class);
			if (product.getCategory().equals(category)) {
				result.add(product);
			}
		}
		return result;
	}

	@Override
	public void insert(Product product) {
		productsCollection.insertOne(Document.parse(gson.toJson(product)));
	}

	@Override
	public void update(Product product) {
		ObjectId id = getObjectId(product);
		Document update = new Document("$set",
				new Document("price", product.getPrice().toString()).append("status", product.getStatus().name()));
		productsCollection.updateOne(eq("_id", id), update);
	}

	private ObjectId getObjectId(Product product) {
		ObjectId id = null;
		for (Document document : productsCollection.find(eq("title", product.getTitle()))) {
			String category = document.get("category").toString();
			if (category.equals(product.getCategory().toString())) {
				id = (ObjectId) document.get("_id");
			}
		}
		return id;
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
