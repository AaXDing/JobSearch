package com.example.jobsearch.external;

import com.example.jobsearch.entity.Item;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.ResponseHandler;

import javax.json.Json;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class SerpAPIClient {
    private static final String URL_TEMPLATE = "https://serpapi.com/search?engine=google_jobs&q=%s&uule=%s&api_key=%s";  //%s : String 占位符

    private static final String API_KEY = "7604ca6dc69829ef3740854510601bc59f364e59299a590d061b50df48b35c08";

    public static void main(String[] args) {
        SerpAPIClient client = new SerpAPIClient();

        List<Item> list = client.search(40.714224,-2.961452, "software engineer");
        for (Item item: list) {
            System.out.println(item.getKeywords());
            break;
        }
    }

    private static final String DEFAULT_KEYWORD = "engineer";

    public List<Item> search(Double lat, Double lon, String keyword) {
        if (keyword == null) {
            keyword = DEFAULT_KEYWORD; //if keyword == null, give it a DEFAULT_KEYWORD
        }

        try {
            keyword = URLEncoder.encode(keyword, "UTF-8"); //transfer input keyword to URL format
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String address = "";

//        System.out.println("Keyword Parsed");

        GeoConverterClient converterClient = new GeoConverterClient();
        String uuleCode = converterClient.convert(lat, lon);

        String url = String.format(URL_TEMPLATE, keyword, uuleCode, API_KEY); //format URL from above

        CloseableHttpClient httpClient = HttpClients.createDefault(); //create a new httpclient object

//        System.out.println("httpClient Created");

        // Create a custom response handler, get response in ideal format
        ResponseHandler<List<Item>> responseHandler = response -> {
            if (response.getStatusLine().getStatusCode() != 200) {
                return Collections.emptyList();
            }

//            System.out.println("200 OK");

            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return Collections.emptyList();
            }

//            System.out.println("Entity OK");

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(entity.getContent());
            JsonNode results = root.get("jobs_results");
            Iterator<JsonNode> result = results.elements();

//            System.out.println("Result OK");

            List<Item> items = new ArrayList<>();

            while (result.hasNext()) {
                JsonNode itemNode = result.next();
                Item item = extract(itemNode);
                System.out.println(item.toString());
                items.add(item);
            }

            extractKeywords(items);

            return items;
        };

//        System.out.println("Response Handler Created");

        try {
            return httpClient.execute(new HttpGet(url), responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("Failed");

        return Collections.emptyList();
    }

    private static Item extract (JsonNode itemNode) {
        String job_id = itemNode.get("job_id").asText();
        String title = itemNode.get("title").asText();
        String companyName = itemNode.get("company_name").asText();
        String location = itemNode.get("location").asText();
        String via = itemNode.get("via").asText();
        String description = itemNode.get("description").asText();
        List<String> highlights = new ArrayList<String>();
        String url = "";
        Set<String> keywords = new HashSet<>();

        //Store all job highlights to highlights
        JsonNode highlights_node = itemNode.get("job_highlights");
        Iterator<JsonNode> highlight = highlights_node.elements();
        while (highlight.hasNext()) {
            Iterator<JsonNode> item = highlight.next().get("items").elements();
            while (item.hasNext()) {
                highlights.add(item.next().asText());
            }
        }

        //Get a link for application
        Iterator<JsonNode> url_it = itemNode.get("related_links").elements();
        if (url_it.hasNext()) {
            url = url_it.next().get("link").asText();
        }

        //Store extension(keywords) to keywords
        Iterator<JsonNode> extension_it = itemNode.get("extensions").elements();
        while (extension_it.hasNext()) {
            keywords.add(extension_it.next().asText());
        }

        return new Item(job_id, title, companyName, location, via, description, highlights, url, keywords, false);
    }

    private static void extractKeywords(List<Item> items) {
        EdenAI client = new EdenAI();
        for (Item item: items) {
            String article = item.getDescription() + ". " + String.join(". ", item.getJobHighlights());
            Set<String> keywords = new HashSet<>();
            keywords.addAll(client.extract(article, 3));
            keywords.addAll(item.getKeywords());
            item.setKeywords(keywords);
        }
    }
}
