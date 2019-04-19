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
}
