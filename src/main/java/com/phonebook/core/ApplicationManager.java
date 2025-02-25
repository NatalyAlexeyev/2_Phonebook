package com.phonebook.core;

import com.phonebook.fw.ContactHelper;
import com.phonebook.fw.HomeHelper;
import com.phonebook.fw.UserHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ApplicationManager {

    public WebDriver driver;
    public WebDriverWait wait;

    UserHelper userhelper;
    HomeHelper homehelper;
    ContactHelper contacthelper;
    private final String browser;

    public ApplicationManager(String browser) {
    this.browser = browser;
    }

    public UserHelper getUserhelper() {
        return userhelper;
    }

    public HomeHelper getHomehelper() {
        return homehelper;
    }

    public ContactHelper getContacthelper() {
        return contacthelper;
    }

    public void init() {

        if (browser.equalsIgnoreCase("chrome")) {
           ChromeOptions options = new ChromeOptions();
           options.addArguments("windows-size=1920x1080");
          // options.addArguments("headless");
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }

        //driver = new ChromeDriver();
        driver.get("https://telranedu.web.app/home");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));


        userhelper = new UserHelper(driver, wait);
        homehelper = new HomeHelper(driver, wait);
        contacthelper = new ContactHelper(driver, wait);


    }

    public void stop() {
        driver.quit();
    }
}
