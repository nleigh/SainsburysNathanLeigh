import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.ArrayList;
import java.util.List;

class SainsburysProductsMain {

    public static void main(String[] args){
        if(args.length == 0)
        {
            System.out.println("Please provide the test url as an argument");
            System.exit(0);
        }

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
