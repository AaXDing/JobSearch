package com.example.jobsearch.external.SerpAPI;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import serpapi.SerpApiHttpClient;
import serpapi.SerpApiSearchException;

import java.util.HashMap;
import java.util.Map;

public class SerpAPIClient {
    public static final String SERP_API_KEY_NAME = "0abe1818382b74202a3a11ed54d7021369be40f76854f61032623cf0193832c0";
    public static String serp_api_key_default;
    private String api_key;
    private String engine;
    public Map<String, String> parameter;
    private static Gson gson = new Gson();
    public SerpApiHttpClient search;

    public SerpAPIClient(Map<String, String> parameter, String api_key, String engine) {
        this.parameter = parameter;
        this.api_key = api_key;
        this.engine = engine;
    }

    public SerpAPIClient(Map<String, String> parameter, String engine) {
        this.parameter = parameter;
        this.engine = engine;
    }

    public SerpAPIClient(String engine) {
        this.parameter = new HashMap();
        this.engine = engine;
    }

    public SerpAPIClient(String serp_api_key, String engine) {
        this.api_key = serp_api_key;
        this.engine = engine;
    }

    public Map<String, String> buildQuery(String path, String output) throws SerpApiSearchException {
        if (this.search == null) {
            this.search = new SerpApiHttpClient(path);
            this.search.setHttpConnectionTimeout(6000);
        } else {
            this.search.path = path;
        }

        this.parameter.put("source", "java");
        if (this.parameter.get("api_key") == null) {
            if (this.api_key != null) {
                this.parameter.put("api_key", this.api_key);
            } else if (getSerpApiKey() != null) {
                this.parameter.put("api_key", getSerpApiKey());
            }
        }

        this.parameter.put("engine", this.engine);
        this.parameter.put("output", output);
        return this.parameter;
    }

    public static String getSerpApiKey() {
        return serp_api_key_default;
    }

    public String getHtml() throws SerpApiSearchException {
        Map<String, String> query = this.buildQuery("/search", "html");
        return this.search.getResults(query);
    }

    public JsonObject getJson() throws SerpApiSearchException {
        Map<String, String> query = this.buildQuery("/search", "json");
        return this.asJson(this.search.getResults(query));
    }

    public JsonObject asJson(String content) {
        JsonElement element = (JsonElement)gson.fromJson(content, JsonElement.class);
        return element.getAsJsonObject();
    }

    public SerpApiHttpClient getClient() {
        return this.search;
    }

    public JsonArray getLocation(String q, Integer limit) throws SerpApiSearchException {
        Map<String, String> query = this.buildQuery("/locations.json", "json");
        query.remove("output");
        query.remove("api_key");
        query.put("q", q);
        query.put("limit", limit.toString());
        String s = this.search.getResults(query);
        return (JsonArray)gson.fromJson(s, JsonArray.class);
    }

    public JsonObject getSearchArchive(String searchID) throws SerpApiSearchException {
        Map<String, String> query = this.buildQuery("/searches/" + searchID + ".json", "json");
        query.remove("output");
        query.remove("q");
        return this.asJson(this.search.getResults(query));
    }

    public JsonObject getAccount() throws SerpApiSearchException {
        Map<String, String> query = this.buildQuery("/account", "json");
        query.remove("output");
        query.remove("q");
        return this.asJson(this.search.getResults(query));
    }

}