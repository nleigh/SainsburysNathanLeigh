import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SainburysProductsScraperTest {

    private final String URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
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
    void GetAllProductsBringsBackMultipleProductNames(){
        SainburysProductsScraper sainburysProductsScraper = new SainburysProductsScraper();
        HtmlPage page = sainburysProductsScraper.GetUrlResponse(URL);
        List<Product> products = sainburysProductsScraper.GetAllProducts(page);
        assertEquals("Sainsbury's Strawberries 400g", products.get(0).getProductName());
        assertEquals("Sainsbury's Blueberries 200g", products.get(1).getProductName());
        assertEquals("Sainsbury's Raspberries 225g", products.get(2).getProductName());
    }

    @Test
    void GetAllProductsBringsBackMultiplePageUrls(){
        SainburysProductsScraper sainburysProductsScraper = new SainburysProductsScraper();
        HtmlPage page = sainburysProductsScraper.GetUrlResponse(URL);
        List<Product> products = sainburysProductsScraper.GetAllProducts(page);
        assertEquals("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html", products.get(0).getProductUrl());
        assertEquals("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-200g.html", products.get(1).getProductUrl());
        assertEquals("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-raspberries-225g.html", products.get(2).getProductUrl());
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
        assertEquals("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html", productUrl);
    }

    @Test
    void GetProductInformationFromProductUrl(){
        String productUrl = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
        SainburysProductsScraper scraper = new SainburysProductsScraper();
        Product product = new Product("Sainsbury's Strawberries 400g", productUrl);
        product = scraper.GetProductInformation(product);

        Product expectedProduct = new Product("Sainsbury's Strawberries 400g", productUrl, 33, 1.75, "by Sainsbury's strawberries");

        assertEquals(expectedProduct.getProductKcalPer100g(), product.getProductKcalPer100g());
        assertEquals(expectedProduct.getProductUnitPrice(), product.getProductUnitPrice());
        assertEquals(expectedProduct.getProductDescription(), product.getProductDescription());

        // 2nd product test
        productUrl = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-blueberries-200g.html";
        scraper = new SainburysProductsScraper();
        product = new Product("Sainsbury's Blueberries 200g", productUrl);
        product = scraper.GetProductInformation(product);

        expectedProduct = new Product("Sainsbury's Blueberries 200g", productUrl, 45, 1.75, "by Sainsbury's blueberries");

        assertEquals(expectedProduct.getProductKcalPer100g(), product.getProductKcalPer100g());
        assertEquals(expectedProduct.getProductUnitPrice(), product.getProductUnitPrice());
        assertEquals(expectedProduct.getProductDescription(), product.getProductDescription());
    }

    @Test
    void formatDescriptionStringReturnsSingleLineWhenMultipleLines(){
        String multipleLineString = "Line1\nLine2\nLine3";
        SainburysProductsScraper scraper = new SainburysProductsScraper();
        String result = scraper.formatDescriptionString(multipleLineString);
        assertEquals("Line1", result);
    }

    @Test
    void formatDescriptionStringReturnsEmptyWhenEmptyStringProvided(){
        String emptyString = "";
        SainburysProductsScraper scraper = new SainburysProductsScraper();
        String result = scraper.formatDescriptionString(emptyString);
        assertEquals("", result);
    }

    @Test
    void GetDoubleUnitPriceFromStringWhenStringContainsSymbolsAndNewLines(){
        SainburysProductsScraper scraper = new SainburysProductsScraper();
        String unitPrice = " £1.23 \n ";
        double result = scraper.GetUnitPriceFromString(unitPrice);
        assertEquals(1.23, result);
    }

    @Test
    void ConvertKCalStringToDoubleWhenStringContainsKcal(){
        SainburysProductsScraper scraper = new SainburysProductsScraper();
        int result = scraper.ConvertAndFormatKCalStringToInt("123kcal");
        assertEquals(123, result);
    }
}