package UITests;

import PageObjects.LoginPageObjects;
import Utilities.Constants;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;


public class LoginTest {

    static Logger logger = Logger.getLogger(LoginTest.class);

    private WebDriver driver;
    LoginPageObjects login;

    @BeforeMethod
    public void setUp() throws Exception {

        logger.info("Running test " + Thread.currentThread().getStackTrace()[1].getMethodName());
        // Start Chrome
        driver = new ChromeDriver();

        // Import URL from constants file
        String baseURL = Constants.productPage;

        //Create loginPage object
        login = new LoginPageObjects(driver);

        // Maximize the browser's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Open Product Page URL
        driver.get(baseURL);
        logger.info("Base URL " + baseURL);


    }

    //Method to login user and check error message for invalid login

    @Test(priority = 1, description = "This test casse validates invalid login and then logs in valid user")
    public void loginUser(){

        logger.info("Running test " + Thread.currentThread().getStackTrace()[1].getMethodName());

        //To check the error message, click on the Submit button without entering login ID / password
        login.clickSubmitButton();
        logger.info("Submit button clicked without entering login ID password");

        //Check if the page shows error message
        login.isErrorDisplayed();
        logger.info("Error message for invalid login is displayed");

        //Get the error text for verification
        login.getErrorText();
        logger.info("Error message on page is - " +login.getErrorText());

        //Asset the error message showsn on webpage with expected error message
        Assert.assertEquals("The email or password is invalid.", login.getErrorText());
        logger.info("Assert for invalid error message passed");

        //Enter valid login id
        login.enterLogin("testuser+je8ds0@example.com");

        //Enter valid password
        login.enterPassword("testje8ds0");

        //Click on Submit button
        login.clickSubmitButton();
        logger.info("User is logged in successfully.");
    }

    @AfterMethod
    public void afterClass() {
        driver.close();
    }

}
