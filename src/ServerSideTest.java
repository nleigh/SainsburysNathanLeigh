import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;
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
    void GetFirstProductFromPage(){
        ServerSide serverSide = new ServerSide();
        HtmlPage page = serverSide.GetUrlResponse(URL);
        String firstProduct = serverSide.GetFirstProduct(page);
        String expectedFirstProduct = "Sainsbury's Strawberries 400g";
        assertEquals(expectedFirstProduct, firstProduct);
    }
}