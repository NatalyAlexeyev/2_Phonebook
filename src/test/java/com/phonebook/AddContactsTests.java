package com.phonebook;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactsTests extends TestBase {
    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void preCondition() {
        app.getUserhelper().login("milka_0401katze@gmx.de", "Password1@");
        app.getContacthelper().deleteAllContacts();
    }

    @Test(invocationCount =2)
    public void addContactPositiveTest() {
        app.getContacthelper().addNewContactPositiveData(CONTACT_NAME);
        Assert.assertTrue(app.getContacthelper().isContactAdded(CONTACT_NAME));
    }

    @Test(priority = 1)
    public void addContactPositiveWODescriptionTest() {
        app.getContacthelper().addNewContactPositiveDataWODescription(CONTACT_NAME);
        Assert.assertTrue(app.getContacthelper().isContactAdded(CONTACT_NAME));
    }

    @AfterMethod
    public void postCondition() {
       // app.getContacthelper().deleteAllContacts();
        app.getUserhelper().logout();
    }
}
