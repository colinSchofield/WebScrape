package com.skillstest.sainsburys.webscrape.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** The WebDriverConfig class configures the type of Selenium WebDriver you wish to use */
@Configuration
public class WebDriverConfig {

    @Value("${sainsburys.pageTimeout:10}")
    private int pageTimeout;

    @Bean
    public WebDriver configureWebDriver() {
        return new ChromeDriver();
    }

    @Bean
    public WebDriverWait configureWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, pageTimeout);
    }
}
