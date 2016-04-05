package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class Impression {

    @JsonProperty("impression_list_name")
    private String listName;

    @JsonProperty("products")
    private List<Product> products;

    public String getListName() {
        return listName;
    }

    public Impression setListName(String listName) {
        this.listName = listName;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Impression setProducts(List<Product> products) {
        this.products = products;
        return this;
    }
}
