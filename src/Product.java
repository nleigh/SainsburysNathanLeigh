import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class Product {
    private String ProductName;
    private String ProductUrl;
    private int ProductKCalPer100g;
    private double ProductUnitPrice;
    private String ProductDescription;


    public Product(String productName, String productUrl, int productKcalPer100g, double productUnitPrice, String productDescription) {
        ProductName = productName;
        ProductUrl = productUrl;
        ProductKCalPer100g = productKcalPer100g;
        ProductUnitPrice = productUnitPrice;
        ProductDescription = productDescription;
    }

    public Product(String productName, String productUrl) {
        ProductName = productName;
        ProductUrl = productUrl;
    }

    public static String generateResultsJson(List<Product> productList) {

        JsonArray products = new JsonArray();

        for (Product prod : productList) {
            JsonObject product = new JsonObject();
            product.addProperty("title", prod.ProductName);

            if (prod.ProductKCalPer100g != 0){
                product.addProperty("kcal_per_100g", prod.ProductKCalPer100g);
            }
            product.addProperty("unit_price", prod.ProductUnitPrice);
            product.addProperty("description", prod.ProductDescription);
            products.add(product);
        }

        JsonObject results = new JsonObject();
        results.add("results", products);

        return results.toString();
    }

    public String getProductUrl() {
        return ProductUrl;
    }


    public String getProductName() {
        return ProductName;
    }
    public int getProductKcalPer100g() {
        return ProductKCalPer100g;
    }

    public double getProductUnitPrice() {
        return ProductUnitPrice;
    }

    public String getProductDescription() {
        return ProductDescription;
    }
}

