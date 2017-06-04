package com.skillstest.sainsburys.webscrape;

import com.skillstest.sainsburys.webscrape.service.SainsburysManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebScrapeApplication implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(WebScrapeApplication.class);

	@Autowired
	private SainsburysManager sainsburys;

	public static void main(String[] args) {
		SpringApplication.run(WebScrapeApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		log.debug("Application started...");

		sainsburys.screenScrapeAndPrintInJSONFormat();

		log.debug("Application has completed.");
	}
}
