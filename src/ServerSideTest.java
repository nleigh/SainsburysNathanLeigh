import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServerSideTest {

    private String URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    @Test
    void GetUrlResponseReturns200WhenUrlIsValid() {
        ServerSide serverSide = new ServerSide();
        HtmlPage page = serverSide.GetUrlResponse(URL);

        int statusCode = page.getWebResponse().getStatusCode();

        assertEquals(200, statusCode);
    }

    @Test
    void GetUrlResponseReturnsNullWhenUrlIsInvalid() {
        ServerSide serverSide = new ServerSide();
        HtmlPage page = serverSide.GetUrlResponse("bad url");
        assertNull(page);
    }

    @Test
    void GetAllProductsCountIsEqualToActualProductPageCount(){
        ServerSide serverSide = new ServerSide();
        HtmlPage page = serverSide.GetUrlResponse(URL);
        List<Product> products = serverSide.GetAllProducts(page);
        assertEquals(17, products.size());
    }

    @Test
    void GetProductNameAndURIForFirstProduct(){
        ServerSide serverSide = new ServerSide();
        HtmlPage page = serverSide.GetUrlResponse(URL);
        List<Product> products = serverSide.GetAllProducts(page);
        Product firstProduct = products.get(0);
        String productName = firstProduct.getProductName();
        String productUrl = firstProduct.getProductUrl();

        assertEquals("Sainsbury's Strawberries 400g", productName);
        assertEquals("https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html", productUrl);

    }
}