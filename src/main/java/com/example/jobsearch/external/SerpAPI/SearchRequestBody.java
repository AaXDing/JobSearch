package com.example.jobsearch.external.SerpAPI;

import java.io.Serializable;

public class SearchRequestBody implements Serializable {
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public SearchRequestBody() {
    }

    private String query;

}