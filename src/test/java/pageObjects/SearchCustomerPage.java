package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.util.List;

public class SearchCustomerPage {
    public WebDriver ldriver;
    WaitHelper waitHelper;
    public SearchCustomerPage(WebDriver rdriver){
        ldriver=rdriver;
        PageFactory.initElements(rdriver, this);
        waitHelper=new WaitHelper(ldriver);
    }
    @FindBy(how= How.ID, using="SearchEmail")
    @CacheLookup
    WebElement email;

    @FindBy(how= How.ID, using="SearchFirstName")
    @CacheLookup
    WebElement FN;

    @FindBy(how= How.ID, using="SearchLastName")
    @CacheLookup
    WebElement LN;

    @FindBy(how= How.ID, using="SearchMonthOfBirth")
    @CacheLookup
    WebElement drpMonth;

    @FindBy(how= How.ID, using="SearchDayOfBirth")
    @CacheLookup
    WebElement drpdobDay;

    @FindBy(how= How.ID, using="SearchCompany")
    @CacheLookup
    WebElement company;

    @FindBy(how= How.XPATH, using="//div[@class='k-multiselect-wrap k-floatwrap']")
    @CacheLookup
    WebElement customerRole;

    @FindBy(how=How.XPATH, using="//li[contains(text(),'Administrators')]")
    @CacheLookup
    WebElement administratorItem;

    @FindBy(how=How.XPATH, using="//li[contains(text(),'Registered')]")
    @CacheLookup
    WebElement RegisteredItem;

    @FindBy(how=How.XPATH, using="//li[contains(text(),'Guests')]")
    @CacheLookup
    WebElement GuestsItem;

    @FindBy(how=How.XPATH, using="//li[contains(text(),'Vendors')]")
    @CacheLookup
    WebElement VendorsItem;

    @FindBy(how= How.ID, using="search-customers")
    @CacheLookup
    WebElement btnSearch;

    @FindBy(how=How.XPATH, using="//table[@role='grid']")
    WebElement tableSearchResult;

    @FindBy(how=How.XPATH, using="//table[@id='customers-grid']")
    WebElement table;

    @FindBy(how=How.XPATH, using="//table[@id='customers-grid']//tbody/tr")
    List<WebElement> tableRows;

    @FindBy(how=How.XPATH, using="//table[@id='customers-grid']//tbody/tr/td")
    List<WebElement> tableColumns;

    public void setEmail(String mail){
        waitHelper.WaitForElement(email, 30);
        email.clear();
        email.sendKeys(mail);
    }

    public void setFN(String firstName){
        waitHelper.WaitForElement(email, 30);
        FN.clear();
        FN.sendKeys(firstName);
    }

    public void setLN(String lastName){
        waitHelper.WaitForElement(email, 30);
        LN.clear();
        LN.sendKeys(lastName);
    }

    public void clickSearch(){
        btnSearch.click();
        waitHelper.WaitForElement(btnSearch, 30);
    }
    public int getNoOfRows(){
        return(tableRows.size());
    }

    public int getNoOfColumns(){
        return(tableColumns.size());
    }
    public boolean searchCustomerByEmail(String email1)
    {
        boolean flag=false;
        for (int i=1; i<=getNoOfRows();i++)
        {
            String emailid=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();

            System.out.println(emailid);

            if(emailid.equals(email1))
            {
                flag=true;
            }
        }

        return flag;
    }
    public boolean searchCustomerByName(String Name)
    {
        boolean flag=false;
        for (int i=1; i<=getNoOfRows();i++)
        {
            String name=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[3]")).getText();

            String names[]=name.split(" ");  // separating fname&lname

            if(names[0].equals("Victoria") && names[1].equals("Terces"))

            {
                flag=true;
            }
        }

        return flag;

    }
}
