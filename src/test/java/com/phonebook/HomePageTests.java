package com.phonebook;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {
    @BeforeMethod
    public void preCondition() {
        app.driver.get("https://telranedu.web.app/home");
    }

    @Test
    public void isHomeComponentPresentTest() {
        Assert.assertTrue(app.getHomehelper().isHomeComponentPresent(),"Item not found on page");
        System.out.println("'HomeComponent' element found on the home page");
    }

    @AfterMethod
    public void postCondition() {
        //app.getUserhelper().logout();
    }

}