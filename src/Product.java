import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private String ProductName;
    private String ProductUrl;
    private int ProductKCalPer100g;
    private double ProductUnitPrice;
    private String ProductDescription;
    private static double VAT_AMOUNT = 20;


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
        BigDecimal vatPercentage = new BigDecimal("20.00");
        BigDecimal onePercentOfGross = grossTotal.divide(hundred, 3, BigDecimal.ROUND_HALF_UP);
        BigDecimal vatAmount = onePercentOfGross.multiply(vatPercentage);
        return vatAmount.doubleValue();
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

