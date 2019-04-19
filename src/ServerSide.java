import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.util.ArrayList;
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

    public List<Product>GetAllProducts(HtmlPage page) {
        String productListXPathQuery = "//div[contains(@class, 'productNameAndPromotions')]";
        List<DomElement> productListDomElements = page.getByXPath(productListXPathQuery);
        List<Product> products = new ArrayList<Product>();

        for (DomElement productDomElement : productListDomElements){
            Product product = new Product(GetProductName(productDomElement), GetProductUrl(productDomElement));
            products.add(product);
        }

        return products;
    }

    public String GetProductName(DomElement product) {
        HtmlElement aHref = product.getElementsByTagName("a").get(0);
        String urlAsText = aHref.asText();
        return urlAsText;
    }

    public String GetProductUrl(DomElement product) {
        HtmlElement aHref = product.getElementsByTagName("a").get(0);
        String productUrl = aHref.getBaseURI();
        return productUrl;
    }
}