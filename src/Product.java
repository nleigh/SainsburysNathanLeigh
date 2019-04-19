import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.util.List;

public class Product {
    private String ProductName;

    public String getProductUrl() {
        return ProductUrl;
    }

    private String ProductUrl;

    public String getProductName() {
        return ProductName;
    }

    public Product(String productName, String productUri) {
        ProductName = productName;
        ProductUrl = productUri;
    }

    public static String generateResultsJson(List<Product> productList){

        JsonArray products = new JsonArray();

        for (Product prod: productList)
        {
            JsonObject product = new JsonObject();
            product.addProperty("title", prod.ProductName);
            products.add(product);
        }

        JsonObject results = new JsonObject();
        results.add("results", products);

        return results.toString();
    }
}
