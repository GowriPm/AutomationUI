/**
 * 
 */
package com.gp.AutomationUI;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author GP
 * This class serves has the tests to test New User Page
 */
public class NewUserTest extends BaseTest {
	
	private NewUserPage newUserPage;
	
    @BeforeClass
    public void startTest(){
    	this.newUserPage = new NewUserPage(driver);
    }    
    	
	//check if page exist
	@Test(priority=1)
	@Parameters("expectedTitle")
	public void checkPageExistsTest(String expectedTitle) {
		
		String actualTitle = newUserPage.getPageTitle();
		Assert.assertEquals(actualTitle,expectedTitle);		
		
	}
	
	//check UserName field existence
	@Test(priority=2)
	@Parameters("expectedStatus")
	public void checkUsernameDisplayedTest(boolean expectedStatus) {

		boolean actual = newUserPage.isUsernameDisplayed();
		Assert.assertEquals(actual,expectedStatus);
	}

	//keyin username
	@Test(priority=3)
	@Parameters("username")
	public void checkUserNameReadWriteTest(String username) {
		
		//enter user name 
		newUserPage.enterUserName(username);
		
		//Make sure the keyed in username was entered as keyed in.
		String actual = newUserPage.getUserName();
		
		Assert.assertEquals(actual,username);
	}

	//key in Paassword 
	@Test(priority=4)
	@Parameters("password")
	public void checkPasswordReadWriteTest(String password) {				
		//enter user name 
		newUserPage.enterPassword(password);
		
		//Make sure the keyed in password was entered as keyed in.
		String actual = newUserPage.getPassword();
		
		Assert.assertEquals(actual,password);
	}
	//method to check if the password is masked
	@Test(priority=5)
	public void checkPasswordMaskedTest() {
		
		String expectedType = "password";
		
		String actual = newUserPage.getPasswordType();
		Assert.assertEquals(actual,expectedType);
	}

    //check to see if the user can key in email
	@Test(priority=6)
	@Parameters("email")
	public void checkemailReadWriteTest(String email) {			
		//enter email
		newUserPage.enterEmail(email);
		
		//Make sure the keyed in email was entered as keyed in.
		String actual = newUserPage.getEmail();
		Assert.assertEquals(actual,email);
		
	}
	
	//create user method 
	@Test(priority=7)
	@Parameters({"username","password","email"} )
	public void createUserTest(String username, String password, String email) {
		
		//enter user name
		newUserPage.enterUserName(username);
				
		//enter password 
		newUserPage.enterPassword(password);
			
		//enter email
		newUserPage.enterEmail(email);
		
		//create user
		newUserPage.createUser();
		
		String actualTitle = newUserPage.getPageTitle();
		
		//Check if page title contains username : example title - "<abc123> | Active Admin Depot"
		Assert.assertTrue(actualTitle.contains(username));		

	}
	
	//check to see if the cancel user navigates to user search Url 
	@Test(priority=8)
	@Parameters("usersSearchUrl")
	public void cancelTest(String usersSearchUrl) {		
		 
		//open the page again because the create user goes to a different page
			newUserPage.openPage();
		
		//click on cancel
			newUserPage.cancelUser();
			
		//check it goes to users page by getting the current url
			 String actual = newUserPage.getUrl();
			 
		//verify the retrieved url(actual) matches expected
			 Assert.assertEquals(actual,usersSearchUrl);		
	}
	
	//negative tests (just add 1 for now)
	@Test(priority=9)
	@Parameters({"username","invalidPassword","email","shortPasswordMessage"} )
	public void createUserWithInvalidPasswordLengthTest(String username, String invalidPassword, String email, String shortPasswordMessage) {
				 
		//open the page again because the cancel user goes to a different page
		newUserPage.openPage();
			
		//enter user name
		newUserPage.enterUserName(username);
				
		//enter password 
		newUserPage.enterPassword(invalidPassword);
			
		//enter email
		newUserPage.enterEmail(email);
		
		//create user
		newUserPage.createUser();
		
		boolean actual = newUserPage.isErrorMessageDisplayed(shortPasswordMessage);
		
		Assert.assertTrue(actual);
	}	
}
