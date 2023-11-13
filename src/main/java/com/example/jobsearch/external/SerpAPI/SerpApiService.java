package com.example.jobsearch.external.SerpAPI;

import com.google.gson.JsonObject;
import serpapi.SerpApiSearchException;

public class SerpApiService {

    public String search(SerpSearchParam params) {
        SerpAPIClient client = new SerpAPIClient(params.getParameters(), "google_jobs");
        try
        {
            JsonObject results = client.getJson();
            return results.toString();

        }
        catch (SerpApiSearchException ex) {

        }
        return null;
    }
}