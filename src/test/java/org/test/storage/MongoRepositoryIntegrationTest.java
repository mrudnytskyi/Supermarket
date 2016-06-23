package org.test.storage;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.test.domain.Category;
import org.test.domain.Product;
import org.test.domain.ProductStatus;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Myroslav Rudnytskyi
 * @version 18.06.2016
 * @see MongoRepository
 */
public class MongoRepositoryIntegrationTest {

	private final Gson gson = new Gson();
	private MongoRepository repository;
	private MongoDatabase mongoDatabase;
	private MongoCollection<Document> productsCollection;
	private Product expectedProduct;
	private Document expectedProductDocument;
	private Category category;

	@Before
	public void setUp() throws Exception {
		repository = new MongoRepository();
		mongoDatabase = repository.getMongoDatabase();
		productsCollection = mongoDatabase.getCollection(MongoRepository.COLLECTION_NAME);
		category = new Category("test category", "this is test category", new URL("http://example.com/none.png"),
				new BigDecimal(5));
		expectedProduct = new Product("test", category, new BigDecimal(10), ProductStatus.AVAILABLE);
		expectedProductDocument = Document.parse(gson.toJson(expectedProduct));
	}

	@After
	public void tearDown() throws Exception {
		mongoDatabase.drop();
		repository.close();
	}

	@Test
	public void testFindByCategory() throws Exception {
		// setup
		productsCollection.insertOne(expectedProductDocument);
		// execute
		List<Product> actualProducts = repository.findByCategory(category);
		// verify
		assertThat(actualProducts, is(notNullValue()));
		assertThat(actualProducts.size(), is(1));
		assertThat(actualProducts.get(0), is(expectedProduct));
	}

	@Test
	public void testInsert() throws Exception {
		// setup
		// execute
		repository.insert(expectedProduct);
		// verify
		assertThat(productsCollection, is(notNullValue()));
		assertThat(productsCollection.count(), is(1L));
		Product actualProduct = gson.fromJson(productsCollection.find().first().toJson(), Product.class);
		assertThat(actualProduct, is(expectedProduct));
	}

	@Test
	public void testUpdate() throws Exception {
		// setup
		productsCollection.insertOne(expectedProductDocument);
		// execute
		expectedProduct.setStatus(ProductStatus.EXPECTED);
		expectedProduct.setPrice(new BigDecimal(100));
		repository.update(expectedProduct);
		// verify
		assertThat(productsCollection, is(notNullValue()));
		assertThat(productsCollection.count(), is(1L));
		Product actualProduct = gson.fromJson(productsCollection.find().first().toJson(), Product.class);
		assertThat(actualProduct, is(expectedProduct));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalConstructorParameters() throws Exception {
		new MongoRepository("", 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullConstructorParameters() throws Exception {
		new MongoRepository(null, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIllegalConstructorArguments() throws Exception {
		new MongoRepository("localhost", -4);
	}
}