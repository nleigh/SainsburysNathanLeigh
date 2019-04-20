import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.ArrayList;
import java.util.List;

public class SainsburysProductsMain {

    public static void main(String[] args){
        SainburysProductsScraper sainburysProductsScraper = new SainburysProductsScraper();
        String url = args[0];

        HtmlPage homepage = sainburysProductsScraper.GetUrlResponse(url);
        List<Product> products = sainburysProductsScraper.GetAllProducts(homepage);
        List<Product> populatedProducts = new ArrayList<>();
        for (Product product: products)
        {
            Product populatedProduct = sainburysProductsScraper.GetProductInformation(product);
            populatedProducts.add(populatedProduct);
        }
        String productsJson =  Product.generateResultsJson(populatedProducts);
        System.out.println(productsJson);
    }
}
