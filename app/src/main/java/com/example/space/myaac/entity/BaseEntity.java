package com.example.space.myaac.entity;

import java.util.List;

/**
 * Created by space on 2018/3/21.
 */

public class BaseEntity<T> {

    private boolean error;

    private List<T> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
