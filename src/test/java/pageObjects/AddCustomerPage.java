package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
    public WebDriver ldriver;
    public AddCustomerPage(WebDriver rdriver){
        ldriver=rdriver;
        PageFactory.initElements(ldriver, this);
    }
    By lnkCustomers_menu=By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
    By lnkCustomers_menuitem=By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
    By addNewbtn=By.xpath("//a[normalize-space()='Add new']");
    By txtEmail=By.xpath("//input[@id='Email']");
    By txtPassword=By.xpath("//input[@id='Password']");
    By fisrtName=By.name("FirstName");
    By lastName=By.name("LastName");
    By genderMalebtn=By.xpath("//input[@id='Gender_Male']");
    By genderFemalebtn=By.id("Gender_Female");
    By dob=By.xpath("//input[@id='DateOfBirth']");
    By companyName=By.name("Company");
    By customerRole=By.xpath("//div[@class='input-group-append input-group-required']//div[@role='listbox']");
    By administratorselect=By.xpath("//li[normalize-space()='Administrators']");
    By forumModeratorSelect=By.xpath("//li[normalize-space()='Forum Moderators']");
    By guestsSelect=By.xpath("//li[normalize-space()='Guests']");
    By registeredSelect=By.xpath("//li[normalize-space()='Registered']");
    By vendorsSelect=By.xpath("//li[contains(text(),'Vendors')]");
    By mgrOfVendor=By.xpath("//select[@id='VendorId']");
    By adminContent=By.name("AdminComment");
    By saveBtn=By.xpath("//button[@name='save']");

    //Action methods
    public String getPageTitle(){
        return ldriver.getTitle();
    }
    public void clickOnCustomerMenu(){
        ldriver.findElement(lnkCustomers_menu).click();
    }
    public void clickOnCustomerMenuItem() throws InterruptedException {
        Thread.sleep(3000);
        ldriver.findElement(lnkCustomers_menuitem).click();
    }
    public void clickOnAddnewbutton(){
        ldriver.findElement(addNewbtn).click();
    }
    public void setEmail(String email){
        ldriver.findElement(txtEmail).sendKeys(email);
    }
    public void setPassword(String pwd){
        ldriver.findElement(txtPassword).sendKeys(pwd);
    }
    public void setFirstName(String fname){
        ldriver.findElement(fisrtName).sendKeys(fname);
    }
    public void setLastName(String lname){
        ldriver.findElement(lastName).sendKeys(lname);
    }
    public void setCustomerRoles(String role) throws InterruptedException {
        //ldriver.findElement(By.xpath("//span[@title='delete']")).click();
        if(!role.equals("Vendors")){
            Thread.sleep(3000);
            ldriver.findElement(By.xpath("//*[@id='SelectedCustomerRoleIds_taglist']/li/span[1]")).click();
        }
        ldriver.findElement(customerRole).click();
        WebElement listitem;
        Thread.sleep(3000);
        if(role.equals("Administrators")){
            listitem=ldriver.findElement(administratorselect);
        } else if (role.equals("Forum Moderators")) {
            listitem=ldriver.findElement(forumModeratorSelect);
        } else if (role.equals("Guests")) {
            listitem=ldriver.findElement(guestsSelect);
        } else if (role.equals("Registered")) {
            listitem=ldriver.findElement(registeredSelect);
        }else {
            listitem=ldriver.findElement(vendorsSelect);
        }
        Thread.sleep(2000);
        listitem.click();
        Thread.sleep(2000);
        ldriver.findElement(By.xpath("//span[@title='delete']")).click();
        //JavascriptExecutor js=(JavascriptExecutor) ldriver;
        //js.executeScript("arguments[0].click();", listitem);
    }
    public void setManagerVendor(String value){
        Select drp=new Select(ldriver.findElement(mgrOfVendor));
        drp.selectByVisibleText(value);
    }
    public void setGender(String gender){
        if(gender.equals("Male")){
            ldriver.findElement(genderMalebtn).click();
        } else if (gender.equals("Female")) {
            ldriver.findElement(genderFemalebtn).click();
        }else{
            ldriver.findElement(genderFemalebtn).click();//default
        }
    }
    public void setDob(String Dob){
        ldriver.findElement(dob).sendKeys(Dob);
    }
    public void setCompanyName(String cname){
        ldriver.findElement(companyName).sendKeys(cname);
    }
    public void setAdminXContent(String content){
        ldriver.findElement(adminContent).sendKeys(content);
    }
    public void clickOnSave(){
        ldriver.findElement(saveBtn).click();
    }
}
