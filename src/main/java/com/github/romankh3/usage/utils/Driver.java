package com.github.romankh3.usage.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver(){}

    private static WebDriver driver;

    //sets the driver object
    public static WebDriver getDriver(){
        if (driver == null){
            String browserType = ConfigurationReader.getProperty("browser");

            switch (browserType){

                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-popup-blocking");
                    driver = new ChromeDriver(chromeOptions);
                    driver.manage().window().setSize(new Dimension(1920,1080));
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    driver.manage().window().setSize(new Dimension(1920,1080));
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().setSize(new Dimension(1920,1080));
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    driver.manage().window().setSize(new Dimension(1920,1080));
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    /*EdgeOptions option = new EdgeOptions();
                    option.addArguments("--remote-allow-origins=*");
                    option.addArguments("--deny-permission-prompts");*/
                    driver = new EdgeDriver();
                    driver.manage().window().setSize(new Dimension(1920,1080));
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            }
        }

        return driver;
    }

    //used for closing browser
    public static void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
