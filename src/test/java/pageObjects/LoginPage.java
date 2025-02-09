package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public WebDriver ldriver;
    public LoginPage(WebDriver rdriver){
        ldriver=rdriver;
        PageFactory.initElements(rdriver, this);
    }
    @FindBy(id="Email")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(id="Password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(xpath = "//button[normalize-space()='Log in']")
    @CacheLookup
    WebElement btnLogin;

    @FindBy(xpath = "//a[normalize-space()='Logout']")
    @CacheLookup
    WebElement lnkLogout;
    public void setUserName(String uname){
        txtEmail.clear();
        txtEmail.sendKeys(uname);
    }
    public void setPassword(String password){
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }
    public void clickLogin(){
        btnLogin.click();
    }
    public void clickLogout() throws InterruptedException {
        Thread.sleep(3000);
        lnkLogout.click();
    }
}
