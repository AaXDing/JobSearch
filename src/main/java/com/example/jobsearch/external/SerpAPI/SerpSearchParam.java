package com.example.jobsearch.external.SerpAPI;

import java.util.HashMap;
import java.util.Map;

public class SerpSearchParam {

    private Map<String, String> parameters;
    private static String API_KEY = ""; //TODO not registered yet

    public SerpSearchParam() {
        this.parameters = new HashMap<String, String>();
        this.parameters.put("api_key", API_KEY);
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public SerpSearchParam populateAPIParam(SearchRequestBody body) {
        this.addEngine("google_jobs");
        if (body.getQuery() != null) this.addQuery(body.getQuery());
        return this;
    }


    public SerpSearchParam addQuery(String query) {
        this.parameters.put("q", query);
        return this;
    }

    public SerpSearchParam addEngine(String engine) {
        this.parameters.put("engine", engine);
        return this;
    }

    public SerpSearchParam addLocation(String location) {
        this.parameters.put("location", location);
        return this;
    }

    public SerpSearchParam addUule(String uule) {
        this.parameters.put("uule", uule);
        return this;
    }

    public SerpSearchParam addGoogleDomain(String domain) {
        this.parameters.put("google_domain", domain);
        return this;
    }

    public SerpSearchParam addCountryCode(String code) {
        this.parameters.put("gl", code);
        return this;
    }

    public SerpSearchParam addLanguageCode(String code) {
        this.parameters.put("hl", code);
        return this;
    }

    public SerpSearchParam addPagination(String start) {
        this.parameters.put("start", start);
        return this;
    }


    // Advanced Google Jobs Parameters
    public SerpSearchParam addChips(String chips) {
        this.parameters.put("chips", chips);
        return this;
    }

    public SerpSearchParam addlrad(String radius) {
        this.parameters.put("lrad", radius);
        return this;
    }

    public SerpSearchParam addltype(String type) {
        this.parameters.put("ltype", type);
        return this;
    }

    public SerpSearchParam addOutputType(String type) {
        //type can be json or html
        this.parameters.put("output", type);
        return this;
    }

}