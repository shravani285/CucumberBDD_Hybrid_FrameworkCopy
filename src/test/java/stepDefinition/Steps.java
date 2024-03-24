package stepDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Steps extends BaseClass{

    @Before
    public void setup() throws IOException {
        logger= (org.apache.logging.log4j.core.Logger) LogManager.getLogger("nopCommerce");//Added logger
        PropertyConfigurator.configure("Log4j.properties");
        //Reading properties
        configProp=new Properties();
        FileInputStream configPropfile=new FileInputStream("config.properties");
        configProp.load(configPropfile);

        String br=configProp.getProperty("browser");
        if (br.equals("chrome")) {
            configProp.getProperty("chromebrowser");
            driver = new ChromeDriver();
        } else if (br.equals("firefox")) {
            configProp.getProperty("firefoxbrowser");
            driver=new FirefoxDriver();
        }

        logger.info("*********** Launching Browser ************");
    }

    @Given("User Launch Chrome browser")
    public void user_launch_chrome_browser() {

        lp=new LoginPage(driver);
    }

    @When("User opens URL {string}")
    public void user_opens_url(String url) {
        logger.info("*********** Opening URL *************");
        driver.get(url);
        driver.manage().window().maximize();

    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String email, String pwd) {
        logger.info("************ Providing Login details **************");
        lp.setUserName(email);
        lp.setPassword(pwd);
    }

    @When("Click on Login")
    public void click_on_login() {
        logger.info("************ Logging in **************");
        lp.clickLogin();
    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String title) {
        if(driver.getPageSource().contains("Login was unsuccessful.")){
            driver.close();
            logger.info("************ Login Passed **************");
            Assert.assertTrue(false);
        }else{
            logger.info("************ Login Failed **************");
            Assert.assertEquals(title, driver.getTitle());
        }

    }

    @When("User click on Log out link")
    public void user_click_on_log_out_link() throws InterruptedException {
        logger.info("************ click on logout link **************");
        lp.clickLogout();
        Thread.sleep(3000);
    }

    @Then("close browser")
    public void close_browser() {
        logger.info("************ closing browser **************");
        driver.quit();

    }
    //Add new Customer feature step definition
    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        addCust =new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
    }

    @When("User click on customers Menu")
    public void user_click_on_customers_menu() {
        addCust.clickOnCustomerMenu();
    }

    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() throws InterruptedException {
        addCust.clickOnCustomerMenuItem();
    }

    @When("click on Add new button")
    public void click_on_add_new_button() {
        addCust.clickOnAddnewbutton();
    }

    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
        Assert.assertEquals("Add a new customer / nopCommerce administration",addCust.getPageTitle());
    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
        logger.info("************ Adding new customer **************");
        logger.info("************ Providing customer details **************");
        String email=randomString()+"@gmail.com";
        addCust.setEmail(email);
        addCust.setPassword("test123");
        String fn=randomString1();
        addCust.setFirstName(fn);
        addCust.setLastName("ms");
        addCust.setGender("Female");
        addCust.setDob("04/25/1994");//mm/dd/yyyy
        addCust.setCompanyName("Automation Testing");
        addCust.setCustomerRoles("Guests");
        Thread.sleep(3000);
        addCust.setManagerVendor("Vendor 2");
        addCust.setAdminXContent("Automation testing test......");
    }

    @When("click on save button")
    public void click_on_save_button() throws InterruptedException {
        logger.info("************ Saving the data **************");
        addCust.clickOnSave();
        Thread.sleep(2000);
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String msg) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The new customer has been added successfully."));
    }

    //steps for searching customer
    @When("Enter customer Email")
    public void enter_customer_email() {
        logger.info("************ Searching a customer by email id **************");
        searchcust =new SearchCustomerPage(driver);
        searchcust.setEmail("kiyjcycyhjc676008@gmail.com");
    }
    @When("CLick on search button")
    public void c_lick_on_search_button() throws InterruptedException {
        searchcust.clickSearch();
        Thread.sleep(3000);
    }
    @Then("User should find Email in the Search table")
    public void user_should_find_email_in_the_search_table() {
        boolean status=searchcust.searchCustomerByEmail("kiyjcycyhjc676008@gmail.com");
        Assert.assertEquals(true, status);
    }
    //Search customer using firstname & last name
    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
        logger.info("************ Searching customer by Name **************");
        searchcust =new SearchCustomerPage(driver);
        searchcust.setFN("Virat");
    }
    @When("Enter customer LastName")
    public void enter_customer_last_name() {
        searchcust.setLN("Kohli");
    }
    @Then("User should find Nmae in the Search table")
    public void user_should_find_Name_in_the_search_table() {
        boolean status=searchcust.searchCustomerByName("Virat Kohli");
        Assert.assertEquals(true, status);
    }

}
