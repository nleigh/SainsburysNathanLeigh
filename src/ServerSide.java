import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.util.List;

public class ServerSide{

    public HtmlPage GetUrlResponse(String websiteUrl){
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        try {
            return client.getPage(websiteUrl);
        }catch(Exception e){
            return null;
        }

    }

    public List<DomElement> GetAllProducts(HtmlPage page) {
        String productListXPathQuery = "//div[contains(@class, 'productNameAndPromotions')]";
        List<DomElement> productList = page.getByXPath(productListXPathQuery);
        return productList;
    }
}