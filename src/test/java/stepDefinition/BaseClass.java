package stepDefinition;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    public LoginPage lp;
    public AddCustomerPage addCust;
    public SearchCustomerPage searchcust;
    public static Logger logger;
    public Properties configProp;
    public static String randomString(){
        //generates random string for unique mail
        String generatedString1= RandomStringUtils.randomAlphabetic(5);
        return (generatedString1);
    }
    public static String randomString1(){
        //generates random string for unique first name
        String generatedString2= RandomStringUtils.randomAlphabetic(3);
        return (generatedString2);
    }
}
