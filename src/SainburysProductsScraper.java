import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.util.ArrayList;
import java.util.List;

public class SainburysProductsScraper {

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

    public Product GetProductInformation(Product product){

        HtmlPage productPage = GetUrlResponse(product.getProductUrl());

        double unitPrice = getProductUnitPriceFromProductPage(productPage);
        int productKCalPer100g = getProductKCalPer100gFromProductPage(productPage);
        String productDescription = getProductDescriptionFromProductPage(productPage);

        Product populatedProduct = new Product(product.getProductName(), product.getProductUrl(), productKCalPer100g, unitPrice, productDescription);
        return populatedProduct;
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

    private int getProductKCalPer100gFromProductPage(HtmlPage productPage) {

        return 33;
    }

    private double getProductUnitPriceFromProductPage(HtmlPage productPage) {
        String productPriceXPathQuery = "//p[contains(@class, 'pricePerUnit')]";
        List<DomElement> productListDomElements = productPage.getByXPath(productPriceXPathQuery);
        DomElement pTagUnitPrice = productListDomElements.get(0);
        DomNode unitPriceNode = pTagUnitPrice.getFirstChild();

        String stringUnitPrice = unitPriceNode.getTextContent();
        stringUnitPrice = stringUnitPrice.trim();
        stringUnitPrice = stringUnitPrice.replace("£", "");
        double unitPrice = Double.parseDouble(stringUnitPrice);
        return unitPrice;
    }


    private String getProductDescriptionFromProductPage(HtmlPage productPage) {

        return "by Sainsbury's strawberries";

    }

}