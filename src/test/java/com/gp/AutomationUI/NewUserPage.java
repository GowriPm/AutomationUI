/**
 * 
 */
package com.gp.AutomationUI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author GP
 * This class is used as the page object to test NewUserPage
 */
public class NewUserPage extends BasePage {
	
	//declare variables and constants
	private final String newPageUrl = "http://qainterview.merchante-solutions.com/admin/users/new";
	private final String usernameId = "user_username";
	private final String passwordId = "user_password";
	private final String emailId = "user_email";
	private final String createUserId = "commit";
	private final String cancelId = "Cancel";
	
	//constructor - pass webdriver to the base class constructor
	public NewUserPage(WebDriver driver) {		
		super(driver);
		driver.get(newPageUrl);
	}
	
	//Navigate to New User page of we navigated away for any reason, like... a new user created successfully
	public void openPage() {	
			driver.get(newPageUrl);
			driver.manage().window().maximize();
		}
	
	//method to check if the UserName field is displayed
	public boolean isUsernameDisplayed() {
		
		WebElement userNameElement = getElementById(usernameId);
		if(userNameElement == null) {
			return false;
		}
		return userNameElement.isDisplayed();
	}
	
	//TBD - check displayed status for other elements
	
	//method to key-in username
	public void enterUserName(String userName)
	{
		WebElement userNameElement = getElementById(usernameId);
		userNameElement.clear();
		userNameElement.sendKeys(userName);
    }
	
	//method to get username
	public String getUserName()
	{
		WebElement userNameElement = getElementById(usernameId);
		return userNameElement.getAttribute("value");
    }
		
	//method to key-in password
	public void enterPassword(String password)
	{
		//TBD - store encrypted password in TestNG and decrypt before enter
		WebElement userNameElement = getElementById(passwordId);
		userNameElement.clear();
		userNameElement.sendKeys(password);
    }
	
	//method to get password
	public String getPassword()
	{
		//TBD - encrypt to match the store encrypted password in TestNG
		
		WebElement userNameElement = getElementById(passwordId);
		return userNameElement.getAttribute("value");
    }
		
	//method to get password type
	public String getPasswordType()
	{
		WebElement userNameElement = getElementById(passwordId);
		return userNameElement.getAttribute("type");
    }
	
	//method to key-in email
	public void enterEmail(String email)
	{
		WebElement userNameElement = getElementById(emailId);
		userNameElement.clear();
		userNameElement.sendKeys(email);
    }
	
	//method to get email
	public String getEmail()
	{
		WebElement userNameElement = getElementById(emailId);
		return userNameElement.getAttribute("value");
    }
	
	//method to create new user
	public void createUser(){
		WebElement createUser = getElementByName(createUserId);
		createUser.click();
    }
	
	//method to cancel off new user page
	public void cancelUser(){
		WebElement cancelUser = getElementByLinkText(cancelId);
		cancelUser.click();	
    }
	
	//method to get page title
	public String getPageTitle (){
		return super.getTitle();
	}

	//method to get current url
	public String getCurrentUrl (){
		return super.getUrl();
	}
	
	//method to check if a given error message is displayed on the page
	public boolean isErrorMessageDisplayed (String errorMessage) {
		if(driver.getPageSource().contains(errorMessage)){
			return true;
		}else{
			return false;
		}
	}
}


