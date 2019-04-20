import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.util.List;

class Product {
    private final String ProductName;
    private final String ProductUrl;
    private int ProductKCalPer100g;
    private double ProductUnitPrice;
    private String ProductDescription;


    Product(String productName, String productUrl, int productKCalPer100g, double productUnitPrice, String productDescription) {
        ProductName = productName;
        ProductUrl = productUrl;
        ProductKCalPer100g = productKCalPer100g;
        ProductUnitPrice = productUnitPrice;
        ProductDescription = productDescription;
    }

    Product(String productName, String productUrl) {
        ProductName = productName;
        ProductUrl = productUrl;
    }

    static String generateResultsJson(List<Product> productList) {

        JsonArray products = new JsonArray();

        BigDecimal grossTotal = new BigDecimal(String.valueOf(0.0));
        for (Product prod : productList) {
            JsonObject product = new JsonObject();
            product.addProperty("title", prod.ProductName);

            if (prod.ProductKCalPer100g != 0){
                product.addProperty("kcal_per_100g", prod.ProductKCalPer100g);
            }
            product.addProperty("unit_price", prod.ProductUnitPrice);
            grossTotal = grossTotal.add(new BigDecimal(String.valueOf(prod.ProductUnitPrice))) ;

            product.addProperty("description", prod.ProductDescription);
            products.add(product);
        }

        JsonObject results = new JsonObject();
        results.add("results", products);


        JsonObject total = new JsonObject();
        double grossTotalDouble = grossTotal.doubleValue();
        total.addProperty("gross", grossTotalDouble);

        double vat = calculateVat(grossTotal);

        total.addProperty("vat", vat);
        results.add("total", total);

        return results.toString();
    }

    private static double calculateVat(BigDecimal grossTotal) {
        BigDecimal hundred = new BigDecimal("100.00");
        String VAT_AMOUNT = "20.00";
        BigDecimal vatPercentage = new BigDecimal(VAT_AMOUNT);
        BigDecimal onePercentOfGross = grossTotal.divide(hundred, 3, BigDecimal.ROUND_HALF_UP);
        BigDecimal vatAmount = onePercentOfGross.multiply(vatPercentage);
        return vatAmount.doubleValue();
    }


    String getProductUrl() {
        return ProductUrl;
    }


    String getProductName() {
        return ProductName;
    }
    int getProductKcalPer100g() {
        return ProductKCalPer100g;
    }

    double getProductUnitPrice() {
        return ProductUnitPrice;
    }

    String getProductDescription() {
        return ProductDescription;
    }
}

