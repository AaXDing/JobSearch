package com.example.jobsearch.external;

import com.example.jobsearch.entity.ExtractRequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.io.IOException;

public class EdenAI {
    private static final String EDENAI_TOEKN = "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiYjdiMGIxZjUtZmUzZi00NDdjLTlhNzgtNDNmMGU3OTI2ZGU2IiwidHlwZSI6ImFwaV90b2tlbiJ9.8l74kD22KjqSxQypEXSMDXCiKBvLk4aBCuHllaRf4xU";

    private static final String EXTRACT_URL = "https://api.edenai.run/v2/text/keyword_extraction";

    public static void main(String[] args) {
        String s = "";

        EdenAI client = new EdenAI();

        Set<String> keywordSet = client.extract(s);

    }

    public Set<String> extract(String article) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();

        HttpPost request = new HttpPost(EXTRACT_URL); //create POST request
        request.setHeader("Content-type", "application/json");
        request.setHeader("Authorization", EDENAI_TOEKN);
        request.setHeader("accept", "application/json");
        ExtractRequestBody body = new ExtractRequestBody(article); //pass article string to an object

//        System.out.println("POST Created");

        String jsonBody;
        try {
            jsonBody = mapper.writeValueAsString(body); //write into request body into json
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

//        System.out.println("Reqeust Body Created");

        try {
            request.setEntity(new StringEntity(jsonBody)); //write json to request body
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

//        System.out.println("Request Body Written");

        ResponseHandler<Set<String>> responseHandler = response -> {
            if (response.getStatusLine().getStatusCode() != 200) { //If not 200OK return empty set
                return Collections.emptySet();
            }
            HttpEntity entity = response.getEntity(); //initiate entity, if entity failed return empty set
            if (entity == null) {
                return Collections.emptySet();
            }
            JsonNode root = mapper.readTree(entity.getContent()); //get content till nlpcloud's items
            JsonNode nlpcloud = root.get("nlpcloud");

            System.out.println(nlpcloud.asText());

            JsonNode nlpclouditems = nlpcloud.get("items");

            Set<String> keywords = new HashSet<>();
            Iterator<JsonNode> itemsIterator = nlpclouditems.elements();
            //Read Value and store in keywords set
            while (itemsIterator.hasNext()) {
                JsonNode itemNode = itemsIterator.next();
                String keyword = itemNode.get("keyword").asText();
                keywords.add(keyword);
                System.out.println(keyword);
            }

            return keywords;
        };

//        System.out.println("Response Handler Created");

        try {
            return httpClient.execute(request, responseHandler); //handle request sent
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("HTTP Failed");

        return Collections.emptySet();
    }
}
