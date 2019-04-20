import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SainburysProductsScraperTest {

    private String URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
    @Test
    void GetUrlResponseReturns200WhenUrlIsValid() {
        SainburysProductsScraper sainburysProductsScraper = new SainburysProductsScraper();
        HtmlPage page = sainburysProductsScraper.GetUrlResponse(URL);

        int statusCode = page.getWebResponse().getStatusCode();

        assertEquals(200, statusCode);
    }

    @Test
    void GetUrlResponseReturnsNullWhenUrlIsInvalid() {
        SainburysProductsScraper sainburysProductsScraper = new SainburysProductsScraper();
        HtmlPage page = sainburysProductsScraper.GetUrlResponse("bad url");
        assertNull(page);
    }

    @Test
    void GetAllProductsCountIsEqualToActualProductPageCount(){
        SainburysProductsScraper sainburysProductsScraper = new SainburysProductsScraper();
        HtmlPage page = sainburysProductsScraper.GetUrlResponse(URL);
        List<Product> products = sainburysProductsScraper.GetAllProducts(page);
        assertEquals(17, products.size());
    }

    @Test
    void GetProductNameAndURIForFirstProduct(){
        SainburysProductsScraper sainburysProductsScraper = new SainburysProductsScraper();
        HtmlPage page = sainburysProductsScraper.GetUrlResponse(URL);
        List<Product> products = sainburysProductsScraper.GetAllProducts(page);
        Product firstProduct = products.get(0);
        String productName = firstProduct.getProductName();
        String productUrl = firstProduct.getProductUrl();

        assertEquals("Sainsbury's Strawberries 400g", productName);
        assertEquals("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html", productUrl);

    }

    @Test
    void GetProductInformationFromProductUrl(){
        String productUrl = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
        SainburysProductsScraper scraper = new SainburysProductsScraper();
        Product product = new Product("Sainsbury's Strawberries 400g", productUrl);
        product = scraper.GetProductInformation(product);

        Product expectedProduct = new Product("Sainsbury's Strawberries 400g", productUrl, 33, 1.75, "by Sainsbury's strawberries");

        assertEquals(expectedProduct.getProductName(), product.getProductName());
        assertEquals(expectedProduct.getProductUrl(), product.getProductUrl());
        assertEquals(expectedProduct.getProductKcalPer100g(), product.getProductKcalPer100g());
        assertEquals(expectedProduct.getProductUnitPrice(), product.getProductUnitPrice());
        assertEquals(expectedProduct.getProductDescription(), product.getProductDescription());


    }
}