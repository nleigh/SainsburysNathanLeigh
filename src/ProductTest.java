import org.junit.jupiter.api.Test;
import org.json.*;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void generateResultsJsonForSingleProduct() {
        Product product = new Product("test title", "www", 123, 1.11, "test description");
        List<Product> products = new ArrayList<>();
        products.add(product);
        String result = Product.generateResultsJson(products);

        JSONObject obj = new JSONObject(result);
        JSONArray arr = obj.getJSONArray("results");
        String title = "";
        int kCalPer100g = 0;
        double unitPrice = 0;
        String description = "";

        for (int i = 0; i < arr.length(); i++) {
            title = arr.getJSONObject(i).getString("title");
            kCalPer100g = arr.getJSONObject(i).getInt("kcal_per_100g");
            unitPrice = arr.getJSONObject(i).getDouble("unit_price");
            description = arr.getJSONObject(i).getString("description");
        }

        assertEquals("test title", title);
        assertEquals(123, kCalPer100g);
        assertEquals(1.11, unitPrice);
        assertEquals("test description", description);

    }

    @Test
    void generateTotalJsonForSingleProduct() {
        Product product = new Product("test title", "www", 123, 2.40, "test description");
        List<Product> products = new ArrayList<>();
        products.add(product);
        String result = Product.generateResultsJson(products);

        JSONObject obj = new JSONObject(result);
        JSONObject total = obj.getJSONObject("total");

        double gross = total.getDouble("gross");
        double vat = total.getDouble("vat");

        assertEquals(2.40, gross);
        assertEquals(0.48, vat);
    }

    @Test
    void generateTotalJsonForMultipleProducts() {
        Product product1 = new Product("test1", "www", 123, 1.75, "test description");
        Product product2 = new Product("test2", "www", 123, 1.75, "test description");
        Product product3 = new Product("test3", "www", 123, 1.5, "test description");

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        String result = Product.generateResultsJson(products);

        JSONObject obj = new JSONObject(result);
        JSONObject total = obj.getJSONObject("total");

        double gross = total.getDouble("gross");
        double vat = total.getDouble("vat");

        assertEquals(5, gross);
        assertEquals(1, vat);
    }

}
