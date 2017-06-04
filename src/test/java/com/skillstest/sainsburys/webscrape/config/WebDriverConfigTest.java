package com.skillstest.sainsburys.webscrape.config;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

/** Confirm that a WebDriver is configured correctly */
public class WebDriverConfigTest {

    @Test
    public void testWebDriver() throws Exception {

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://google.com");
        Thread.sleep(2000);
        assertTrue("Check that we can load a standard web page, if not, make sure that there is a Selenium Web Driver is loaded!",
                driver.getPageSource().contains("google"));

        driver.quit();
    }

}