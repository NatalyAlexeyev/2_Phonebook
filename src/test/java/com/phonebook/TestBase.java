package com.phonebook;

import com.phonebook.core.ApplicationManager;
import org.openqa.selenium.devtools.v127.page.model.Screenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class TestBase {
   public Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", "chrome"));

   @BeforeSuite
    public void setUp() {
       logger.info("Testing in progress...");
       app.init();

    }

    @BeforeMethod
    public void starTest(Method method) {
        logger.info("Start test:[{}]", method.getName());
    }

    @AfterMethod
    public void stopTest(Method method, ITestResult result, ITestContext context) {
        StringBuilder parameters = new StringBuilder();
        for (String key : context.getAttributeNames()) {
            Object value = context.getAttribute(key);
            parameters.append(key).append("=").append(value).append(", ");
        }

        if (parameters.length() > 0) {
            parameters.setLength(parameters.length() - 2);
        }

        logger.info("Test is started with data: [" + parameters + "]");

       if (result.isSuccess()) {
            logger.info("Test is PASSED: [{}]", method.getName());
        } else {
            if (app.getUserhelper().isAlertPresent()) {
                logger.warn("Alert was present and has been accepted.");
            } else {
                logger.info("No alert present.");
            }
            String screenshotPath = app.getUserhelper().takeScreenshot();
            logger.error("Test is FAILED: [" + method.getName() + "], Screenshot: [" + screenshotPath + "]");
        }
        logger.info("Test is ended: [" + method.getName() + "]");
    }

    @AfterSuite(enabled = true)
    public void tearDown() {
        logger.info("All test end");
        app.stop();
    }
}
