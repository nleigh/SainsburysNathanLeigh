import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.util.ArrayList;
import java.util.List;

class SainburysProductsScraper {

    HtmlPage GetUrlResponse(String websiteUrl){
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        try {
            return client.getPage(websiteUrl);
        }catch(Exception e){
            return null;
        }
    }

    List<Product>GetAllProducts(HtmlPage page) {
        String productListXPathQuery = "//div[contains(@class, 'productNameAndPromotions')]";
        List<DomElement> productListDomElements = page.getByXPath(productListXPathQuery);
        List<Product> products = new ArrayList<>();

        for (DomElement productDomElement : productListDomElements){
            Product product = new Product(GetProductName(productDomElement), GetProductUrl(productDomElement));
            products.add(product);
        }

        return products;
    }

    Product GetProductInformation(Product product){
        HtmlPage productPage = GetUrlResponse(product.getProductUrl());
        double unitPrice = getProductUnitPriceFromProductPage(productPage);
        int productKCalPer100g = getProductKCalPer100gFromProductPage(productPage);
        String productDescription = getProductDescriptionFromProductPage(productPage);

        return new Product(product.getProductName(), product.getProductUrl(), productKCalPer100g, unitPrice, productDescription);
    }

    private String GetProductName(DomElement product) {
        HtmlElement aHref = product.getElementsByTagName("a").get(0);
        return aHref.asText();
    }

    private String GetProductUrl(DomElement product) {
        HtmlAnchor aHref = (HtmlAnchor) product.getElementsByTagName("a").get(0);
        String productUrl = aHref.getHrefAttribute();
        return productUrl.replace("../../../../../../","https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/");
    }

    private int getProductKCalPer100gFromProductPage(HtmlPage productPage) {
        // Omit the kcal_per_100g field, if calories are unavailable.
        try {
            String nutritionLevelXPathQuery = "//td[contains(@class, 'nutritionLevel1')]";
            List<DomElement> nutritionLevelListDomElements = productPage.getByXPath(nutritionLevelXPathQuery);
            DomElement nutritionLevelElement = nutritionLevelListDomElements.get(0);
            DomNode kCalNode = nutritionLevelElement.getFirstChild();
            String kCalString = kCalNode.toString();
            return ConvertAndFormatKCalStringToInt(kCalString);
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    int ConvertAndFormatKCalStringToInt(String kCalString) {
        kCalString = kCalString.replace("kcal", "");
        return Integer.parseInt(kCalString);
    }

    private double getProductUnitPriceFromProductPage(HtmlPage productPage) {
        String productPriceXPathQuery = "//p[contains(@class, 'pricePerUnit')]";
        List<DomElement> productListDomElements = productPage.getByXPath(productPriceXPathQuery);
        DomElement pTagUnitPrice = productListDomElements.get(0);
        DomNode unitPriceNode = pTagUnitPrice.getFirstChild();
        String stringUnitPrice = unitPriceNode.getTextContent();
        return GetUnitPriceFromString(stringUnitPrice);
    }

    double GetUnitPriceFromString(String unitPriceString) {
        unitPriceString = unitPriceString.trim();
        unitPriceString = unitPriceString.replace("£", "");
        return Double.parseDouble(unitPriceString);
    }

    private String getProductDescriptionFromProductPage(HtmlPage productPage) {
        try{
            String productTextXPathQuery = "//div[contains(@class, 'productText')]";
            List<DomElement> productListDomElements = productPage.getByXPath(productTextXPathQuery);
            DomElement firstClassProductDescription = productListDomElements.get(0);

            DomNodeList<HtmlElement> pTagProductDescription = firstClassProductDescription.getElementsByTagName("p");
            HtmlElement firstPTagProductDescription = pTagProductDescription.get(0);
            String descriptionString = firstPTagProductDescription.getFirstChild().asText();

            return formatDescriptionString(descriptionString);
        }
        catch(Exception e){
            return "";
        }
    }

    String formatDescriptionString(String descriptionString) {
        if (descriptionString.equals("") || descriptionString.isEmpty()){
            return "";
        }

        // If the description is spread over multiple lines, scrape only the first line.
        String[] firstLineOfDescription = descriptionString.split("\n");
        return firstLineOfDescription[0];
    }

}