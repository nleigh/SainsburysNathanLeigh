import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

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

}