package com.phonebook;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase {
    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void preCondition() {
        app.getUserhelper().login("Milka_0401katze@gmx.de", "Password1@");
        app.getContacthelper().deleteAllContacts();
        app.getContacthelper().addNewContactPositiveData(CONTACT_NAME);
    }

    @Test
    public void createOneAndDeleteOneContactTest() {
        int sizeBefore = app.getContacthelper().actualSizeOfContacts();
        System.out.println("Size before deletion: " + sizeBefore);

        app.getContacthelper().deleteOneContact();

        app.wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className(app.getContacthelper().CONTACT_LOCATOR), sizeBefore));

        int sizeAfterDelete = app.getContacthelper().actualSizeOfContacts();
        System.out.println("Size after deletion: " + sizeAfterDelete);

        // We check that after deletion the number of contacts has decreased by 1
        Assert.assertEquals(sizeBefore - 1, sizeAfterDelete, "Count is not equal");
    }

    @Test
    public void deleteAllContactTests() {
        app.getContacthelper().deleteAllContacts();
        Assert.assertEquals(app.getContacthelper().actualSizeOfContacts(), 0, "Count is not equal");
    }

    @AfterMethod
    public void postCondition() {
        try {
            app.getUserhelper().logout();
        } catch (Exception e) {
            logger.error("Logout failed: {}", e.getMessage());
        }
    }
}


