package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getUserhelper().isSignOutButtonPresent()) {
            app.getUserhelper().logout();
        }
        app.driver.get("https://telranedu.web.app/login");

    }

    @Test
    public void loginExistedUserPositiveTest1() {
        app.getUserhelper().clickLoginLink();
        app.getUserhelper().fillInRegistrationForm(new User()
                .setEmail("milka_0401katze@gmx.de")
                .setPassword("Password1@"));
        app.getUserhelper().clickOnLoginButton();
        Assert.assertTrue(app.getUserhelper().isSignOutButtonPresent());
    }

    @Test
    public void loginExistedUserPositiveTest2(ITestContext context) {
        String email = "delete_account_" + System.currentTimeMillis() + "@gmail.com";
        String password = "Password1@";
        context.setAttribute("email",email);
        context.setAttribute("password",password);
        app.getUserhelper().register(email, password);
    }
    @Test
    public void loginNegativeWOEmailTest() {
        app.getUserhelper().clickLoginLink();
        app.getUserhelper().fillInRegistrationForm(new User()
                // .setEmail("Milka_0401katze@gmx.de")
                .setPassword("Password1@"));
        app.getUserhelper().clickOnLoginButton();
        Assert.assertEquals(app.getUserhelper().alertTextPresent(), "Wrong email or password","Message are not equal");
        Assert.assertTrue(app.getUserhelper().isAlertPresent());
    }

    @Test
    public void loginExistedUserPositiveTest2() {
        app.getUserhelper().login("milka_0401katze@gmx.de", "Password1@");
    }

    @AfterMethod(enabled = true)
    public void postCondition() {
        try {
            app.getUserhelper().logout();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }
}
