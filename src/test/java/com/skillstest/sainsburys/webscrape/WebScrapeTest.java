package com.skillstest.sainsburys.webscrape;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skillstest.sainsburys.webscrape.service.SainsburysManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WebScrapeTest {

    @Autowired
    @InjectMocks
    private SainsburysManager sainsburysManager;

    @Mock
    WebDriver driver;

    @Mock
    WebDriverWait wait;

    @Mock
    WebDriver.Navigation navigation;

    @Before
    public void setup() throws IOException {

        String mainPage = getFileAsString("mainPage.html");
        String subPage = getFileAsString("subPage.html");

        when(driver.navigate()).thenReturn(navigation);
        when(driver.getPageSource()).thenReturn(mainPage, subPage);
    }

    private String getFileAsString(String fileName) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(getClass().getClassLoader().
                getResource(fileName).getFile()), Charset.defaultCharset());
        return lines.stream().map(s -> s + "\n").collect(Collectors.joining());
    }

    @Test
    public void testSainsburysManager() throws JsonProcessingException {

        sainsburysManager.screenScrapeAndPrintInJSONFormat();
        int ix = 0;
    }
}
