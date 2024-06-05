package com.wposs.apirickandmorty.Models;

import java.util.List;

public class Characters extends BaseModel{
    Info info;
    List<Character> results;

    public Info getInfo() {
        return info;
    }

    public List<Character> getResults() {
        return results;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public void setResults(List<Character> results) {
        this.results = results;
    }
}
