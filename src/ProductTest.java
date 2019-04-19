import org.junit.jupiter.api.Test;
import org.json.*;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void generateResultsJsonForSingleProduct(){
        Product product = new Product("apple","www");
        List<Product> products = new ArrayList<Product>();
        products.add(product);
        String result = Product.generateResultsJson(products);

        JSONObject obj = new JSONObject(result);
        JSONArray arr = obj.getJSONArray("results");
        String title = "";
        for (int i = 0; i < arr.length(); i++)
        {
            title = arr.getJSONObject(i).getString("title");
        }
        assertEquals("apple", title);
    }
}
