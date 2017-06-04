package com.skillstest.sainsburys.webscrape.service;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FieldChangeTest {

    /** Remove the textual value, i.e. £2.04/unit -> 2.04 */
    private Float extractAndConvertNumber(String numberString) {

        try {
            String stripped = numberString.substring(1, numberString.length() - 5);
            return Float.valueOf(stripped);
        } catch (Exception ex) {
            return 0f;
        }
    }

    @Test
    public void testValueConversion() {

        String unitPrice = "£2.04/unit";
        assertTrue(2.04f == extractAndConvertNumber(unitPrice));
    }

}