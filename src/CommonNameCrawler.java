import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

class WebScraper {
    public void scrape(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements elements = document.getAllElements();

        for (Element element : elements) {
            System.out.println(element.text());
        }
    }
}

public class CommonNameCrawler {
    public static void main(String[] args) {
        WebScraper webScraper = new WebScraper();
        try {
            webScraper.scrape("https://www.google.com/search?q=Accipiter+gentilis");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
