package com.skillstest.sainsburys.webscrape.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstest.sainsburys.webscrape.model.Item;
import com.skillstest.sainsburys.webscrape.model.ItemList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class JsonObjectTest {

    ItemList itemList;

    @Before
    public void setup() {

        Item item1 = new Item("title 1", "100K", 12.2f, "description");
        Item item2 = new Item("title 2", "50K", 1.2f, "description 2");
        List<Item> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);

        itemList = new ItemList(list);
    }

    @Test
    public void testJsonObject() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String jsonAsString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(itemList);
        assertNotNull(jsonAsString);
        assertTrue(jsonAsString.contains("results"));
        assertTrue(jsonAsString.contains("\"total\" : 13.4"));
        System.out.println("value: " + jsonAsString);
    }
}