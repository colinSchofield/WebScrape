package com.skillstest.sainsburys.webscrape.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstest.sainsburys.webscrape.model.Item;
import com.skillstest.sainsburys.webscrape.model.ItemList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Screenscrape the Sainsbury’s grocery site - Ripe Fruits page, converting the values into JSON.
 * <p>
 * Note: Ensure that the server performing the scrapping, has a properly configured <b>Selenium WebDriver</b>
 *
 * @see <a href="http://www.seleniumhq.org/projects/webdriver/">Selenium WebDriver</a>
 */
@Service
public class SainsburysManager {

    private Logger log = LoggerFactory.getLogger(SainsburysManager.class);

    @Autowired
    private WebDriver driver;

    @Value("${sainsburys.url}")
    private String urlToScrape;

    @Value("${sainsburys.pageTimeout:10}")
    private int pageTimeout;

    private static final String PRODUCT_LIST = "#productLister > ul > li";
    private static final String PRODUCT_INFO = "div.product > div > div.productInfoWrapper > div > h3 > a";
    private static final String UNIT_COST = "div.pricing > p.pricePerUnit";
    private static final String DESCRIPTION = "#information > productcontent > htmlcontent > div:nth-child(2)";

    public void screenScrapeAndPrintInJSONFormat() throws JsonProcessingException {

        log.debug("About to connect to Sainsbury's Ripe Fruit page (url: {}).", urlToScrape);

        openMainPage(driver);

        String mainPageContent = driver.getPageSource();
        log.debug("Main page is open (size: {} K)", mainPageContent.length() / 1024);

        Document doc = Jsoup.parse(mainPageContent);
        Elements elements = doc.select(PRODUCT_LIST);
        log.debug("The product list contains {} elements.", elements.size());

        List<Item> itemList = new ArrayList<>();
        for (Element element : elements) {

            Element product = element.select(PRODUCT_INFO).get(0);
            String title = product.text();
            String unitPriceAsString = element.select(UNIT_COST).get(0).text();
            float unitPrice = extractAndConvertNumber(unitPriceAsString);

            log.debug("About to drill down to obtain the product description.");

            driver.navigate().to(product.attr("href"));
            WebElement wait = new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("globalFooter")));

            String subHtml = driver.getPageSource();
            Document subDoc = Jsoup.parse(subHtml);
            String description = subDoc.select(DESCRIPTION).text();
            String size = subHtml.getBytes().length / 1024 + "K";

            log.debug("Storing the item values into the list");
            Item item = new Item(title, size, unitPrice, description);
            itemList.add(item);
            log.info("Fruit Item: {}", item);

            log.debug("About to reload the main list page.");
            openMainPage(driver);
        }

        log.debug("{} items have been created for the list.", itemList.size());
        log.debug("About to quit the WebDriver...");
        driver.quit();
        log.debug("Done.");

        log.debug("About to printout the Item List as a JSON value.");
        printItemListAsJson(itemList);
        log.debug("Done.");

    }

    private void openMainPage(WebDriver driver) {

        driver.navigate().to(urlToScrape);
        new WebDriverWait(driver, pageTimeout)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("globalFooter")));
    }

    /** Remove the textual value, i.e. £2.04/unit -> 2.04 */
    private Float extractAndConvertNumber(String numberString) {

        try {
            String stripped = numberString.substring(1, numberString.length() - 5);
            return Float.valueOf(stripped);
        } catch (Exception ex) {
            log.error("Error converting number " + numberString, ex);
            return 0f;
        }
    }

    /** Use Jackson to print the values scrapped from the Ripe Fruits page, in JSON */
    private void printItemListAsJson(List<Item> itemList) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("\n\n\n\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new ItemList(itemList)) + "\n\n\n\n");
    }
}
