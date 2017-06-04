package com.skillstest.sainsburys.webscrape.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** The WebDriverConfig class configures the type of Selenium WebDriver you wish to use */
@Configuration
public class WebDriverConfig {

    @Bean
    public WebDriver configureWebDriver() {
        return new ChromeDriver();
    }
}
