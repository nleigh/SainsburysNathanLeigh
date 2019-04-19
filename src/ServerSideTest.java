import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServerSideTest {

    @Test
    void GetUrlResponseReturns200WhenUrlIsValid() {
        String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
        ServerSide serverSide = new ServerSide();
        HtmlPage page = serverSide.GetUrlResponse(url);

        int statusCode = page.getWebResponse().getStatusCode();

        assertEquals(200, statusCode);
    }

    @Test
    void GetUrlResponseReturnsNullWhenUrlIsInvalid() {
        String url = "bad url";
        ServerSide serverSide = new ServerSide();
        HtmlPage page = serverSide.GetUrlResponse(url);
        assertNull(page);
    }
}