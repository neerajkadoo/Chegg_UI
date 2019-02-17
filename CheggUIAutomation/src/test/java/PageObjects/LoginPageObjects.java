package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {

    WebDriver driver;

    public LoginPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="login-user")
    WebElement loginField;

    public void enterLogin(String loginID){
        loginField.sendKeys(loginID);
    }

    @FindBy(id="login-pass")
    WebElement passwordField;

    public void enterPassword(String userPassword){
        passwordField.sendKeys(userPassword);
    }

    @FindBy(xpath = "//*[@id=\"root\"]/form/button")
    WebElement submitButton;

    public void clickSubmitButton(){
        submitButton.click();
    }

    @FindBy(xpath = "//*[@id=\"root\"]/h4")
    WebElement LoginError;

    public boolean isErrorDisplayed(){
        LoginError.isDisplayed();
        return true;
    }

    public String getErrorText(){
        return LoginError.getText();
    }

}

