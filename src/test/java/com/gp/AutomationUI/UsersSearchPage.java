/**
 * 
 */
package com.gp.AutomationUI;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author GP
 * This class is used as the page object to test search filter in Users search page
 */
public class UsersSearchPage extends BasePage {
	
	//declare variables and constants
	private final String usersSearchPageUrl = "http://qainterview.merchante-solutions.com/admin/users";
	private final String usernameSearchId = "q_username"; //id
	private final String emailSearchId = "q_email";  //id
	private final String dateFromId = "q_created_at_gteq_datetime";
	private final String dateToId = "q_created_at_lteq_datetime";
	private final String filterBtnName = "commit"; //name
	private final String clearFilterClassName = "clear_filters_btn";
	private final String usernameDropDownXPath = "//*[@id=\"q_username_input\"]/select";
	private final String emailDropDownXPath = "//*[@id=\"q_email_input\"]/select";	
				
	//constructor
	public UsersSearchPage(WebDriver driver) {		
		super(driver);
		driver.get(usersSearchPageUrl);
	}
	
	//Navigate to New User page of we navigated away for any reason, like... a new user created successfully
	public void openPage() {	
			driver.get(usersSearchPageUrl);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	
	//method to check if the UserName field is displayed
	public boolean isUsernameDropdownDisplayed() {
		
		WebElement userNameElement = getElementByXpath(usernameDropDownXPath);
		if(userNameElement == null) {
			return false;
		}
		return userNameElement.isDisplayed();
	}
	
	//Select username operator from dropdown
	public void selectUsernameOperator(String operator)
	{
		WebElement userNameElement = getElementByXpath(usernameDropDownXPath);
		Select drpUser = new Select(userNameElement);
		drpUser.selectByVisibleText(operator);	
    }
	
	//get selected username operator from dropdown
	public String getSelectedUsernameOperator()
	{
		WebElement userNameElement = getElementByXpath(usernameDropDownXPath);
		Select drpUser = new Select(userNameElement);
		return drpUser.getFirstSelectedOption().getText();		
    }
	
	//Select email operator from dropdown
	public void selectEmailOperator(String operator)
	{
		WebElement emailElement = getElementByXpath(emailDropDownXPath);
		Select drpUser = new Select(emailElement);
		drpUser.selectByVisibleText(operator);	
    }
	
	//get selected email operator from dropdown
	public String getSelectedEmailOperator()
	{
		WebElement emailElement = getElementByXpath(emailDropDownXPath);
		Select drpUser = new Select(emailElement);
		return drpUser.getFirstSelectedOption().getText();		
    }
	
	//method to key-in username
	public void enterUserName(String userName)
	{
		WebElement userNameElement = getElementById(usernameSearchId);
		userNameElement.clear();
		userNameElement.sendKeys(userName);
    }
	
	//method to get username
	public String getUserName()
	{
		WebElement userNameElement = getElementById(usernameSearchId);
		return userNameElement.getAttribute("value");
    }
	
	//method to key-in email
	public void enterEmail(String email)
	{
		WebElement emailElement = getElementById(emailSearchId);
		emailElement.clear();
		emailElement.sendKeys(email);
    }
	
	//method to get email
	public String getEmail()
	{
		WebElement emailElement = getElementById(emailSearchId);
		return emailElement.getAttribute("value");
    }	
	
	//method to key-in From date
	public void enterFromDate(String dateAsString)
	{
		WebElement fromDateElement = getElementById(dateFromId);
		fromDateElement.clear();
		fromDateElement.sendKeys(dateAsString);
    }
	
	//method to get From date
	public String getFromDate()
	{
		WebElement fromDateElement = getElementById(dateFromId);
		return fromDateElement.getAttribute("value");
    }
	
	//method to key-in To date
	public void enterToDate(String dateAsString)
	{
		WebElement toDateElement = getElementById(dateToId);
		toDateElement.clear();
		toDateElement.sendKeys(dateAsString);
    }
	
	//method to get Todate
	public String getToDate()
	{
		WebElement toDateElement = getElementById(dateToId);
		return toDateElement.getAttribute("value");
    }
	
	//method Applyfilter
	public void applyFilter(){
		WebElement filterBtn = getElementByName(filterBtnName);
		filterBtn.click();
    }
	
	//method to clearfilter
	public void clearFilter(){
		WebElement clearFilterBtn = getElementByClassName(clearFilterClassName);
		clearFilterBtn.click();
    }
	
	//method to find the username exist in the user list
	public boolean isUserFound(String username)
	{
		String userResultXpath = ".//td[contains(text(),'" + username + "')]";
		WebElement userResult = getElementByXpath(userResultXpath);
				
		if (userResult != null)
		{
			return true;
		} else {
			return false;
		}
	}

}
