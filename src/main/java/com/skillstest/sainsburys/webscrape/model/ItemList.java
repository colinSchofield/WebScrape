package com.skillstest.sainsburys.webscrape.model;

import java.util.List;

/** Contains a list of the stock items, that will then be converted into a JSON list using Jackson */
public class ItemList {

    private List<Item> results;
    /** The total value is calculated on the fly and is equal to the sum of the Item unit prices */
    private float total;

    public ItemList(List<Item> results) {
        this.results = results;
    }

    public List<Item> getResults() {
        return results;
    }

    public void setResults(List<Item> results) {
        this.results = results;
    }

    public float getTotal() {

        float totalFromAllItems = 0f;
        for (Item item : results) {
            totalFromAllItems += item.getUnit_price();
        }

        return totalFromAllItems;
    }
}
