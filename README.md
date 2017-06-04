# Instuctions for Sainsbury’s Skill Test
##### Synopsis
This programme has been written in Java 1.8 as a Spring Boot application. It uses both the Selenium and jSoup libraries. There are a number of Unit Tests which can be run to verify that it is working correctly.

First thing, is to ensure that you have the appropriate **Selenium WebDriver**. I have use Chrome - if you wish to change this, you will need to edit the programme in the file called `WebDriverConfig`.

##### how to run the App
You must have the following to run the application:
- Java 1.8 compiler 
- Apache maven 3 

Then do the following:
```
1. mvn clean install
2. mvn spring-boot:run
```

The first command will compile the programme and ensure that all the unit test cases pass (note: this will generate a fair amount of logging). 

The second will run the console application, generating the JSON values.


Enjoy!  
Colin Schofield  
e: colin_sch@yahoo.com  
p: 07490 686 382  
