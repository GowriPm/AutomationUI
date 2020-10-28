/**
 * 
 */
package com.gp.AutomationUI;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @author GP
 * This class serves has the tests to test the search filter functionality in Users search page
 */
public class UsersSearchTest extends BaseTest {
	
	   private UsersSearchPage userSearchPage;
	   
	   @BeforeClass
	    public void startTest(){
	    	this.userSearchPage = new UsersSearchPage(driver);
	    }   
	
	    //check if page exist
		@Test(priority=1)
		@Parameters("expectedTitle")
		public void checkPageExistsTest(String expectedTitle) {
			
			String actualTitle = userSearchPage.getTitle();
			Assert.assertEquals(actualTitle,expectedTitle);					
		}
		
		//check UserName field existence
		@Test(priority=2)
		@Parameters("expectedStatus")
		public void checkUsernameDropdownDisplayedTest(boolean expectedStatus) {

			boolean actual = userSearchPage.isUsernameDropdownDisplayed();
			Assert.assertEquals(actual,expectedStatus);
		}

		//keyin user
		@Test(priority=3)
		@Parameters("username")
		public void checkUserNameReadWriteTest(String username) {
			
			//enter user name 
			userSearchPage.enterUserName(username);
			
			//Make sure the keyed in username was entered as keyed in.
			String actual = userSearchPage.getUserName();
			
			Assert.assertEquals(actual,username);
		}

		@Test(priority=4)
		@Parameters("email")
		public void checkemailReadWriteTest(String email) {			
			//enter email
			userSearchPage.enterEmail(email);
			
			//Make sure the keyed in email was entered as keyed in.
			String actual = userSearchPage.getEmail();
			Assert.assertEquals(actual,email);
			
		}
			
		@Test(priority=5)
		@Parameters("fromDate")
		public void checkFromDateReadWriteTest(String fromDate) {
			
			//From date entered in TestNG is in "yyyy-MM-dd" format as expected.			
			//enter From date 
			userSearchPage.enterFromDate(fromDate);
			
			//Make sure the keyed in From date was entered as keyed in.
			String actual = userSearchPage.getFromDate();
			
			Assert.assertEquals(actual,fromDate);
		}
		
		@Test(priority=5)
		@Parameters("toDate")
		public void checkToDateReadWriteTest(String toDate) {			

			//enter To date 
			userSearchPage.enterToDate(toDate);
			
			//Make sure the keyed in To date was entered as keyed in.
			String actual = userSearchPage.getToDate();
			
			Assert.assertEquals(actual,toDate);
		}
		
		@Test(priority=7)
		@Parameters({"username","email","usernameOperator","emailOperator", "fromDate"} )
		public void ApplyFiltersTest(String username, String email, String usernameOperator, String emailOperator, String fromDate) {
			
			//select username operator from drop down
			userSearchPage.selectUsernameOperator(usernameOperator);
					
			//enter username criteria 
			userSearchPage.enterUserName(username);
				
			//select email operator from drop down
			userSearchPage.selectEmailOperator(emailOperator);
					
			//enter email criteria 
			userSearchPage.enterEmail(email);
			
			//enter From Date
			userSearchPage.enterFromDate(fromDate);
			
			//set current date as toDate, because we are searching for a user created by previous test (NewUserTest)
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String toDate = simpleDateFormat.format(new Date());	
			
			//enter To date
			userSearchPage.enterToDate(toDate);
			
			//Apply search filter
			userSearchPage.applyFilter();

			boolean actual = userSearchPage.isUserFound(username);
			String message = "";
			
			if (!actual) message = "Expected user " + username + " not found"; //create message only when user not found
			
			Assert.assertTrue(actual, message);	
		}

		@Test(priority=8)
		@Parameters({"username","email","usernameOperator","emailOperator", "fromDate", "toDate"} )
		public void clearFiltersTest(String username, String email, String usernameOperator, String emailOperator, String fromDate, String toDate) {		
			 
			//select username operator from drop down
			userSearchPage.selectUsernameOperator(usernameOperator);
					
			//enter username criteria 
			userSearchPage.enterUserName(username);
				
			//select email operator from drop down
			userSearchPage.selectEmailOperator(emailOperator);
					
			//enter email criteria 
			userSearchPage.enterEmail(email);
			
			//enter From Date
			userSearchPage.enterFromDate(fromDate);
			
			//set current date as toDate, because we are searching for a user created by previous test (NewUserTest)
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String toDateTtext = simpleDateFormat.format(new Date());	
			
			//enter To date
			userSearchPage.enterToDate(toDateTtext);
			
			//clear Search filter
			userSearchPage.clearFilter();
			
			//check username text is cleared
			String usernametext= userSearchPage.getUserName();
			Assert.assertTrue(usernametext.isEmpty());
			
			//check email text is cleared
			String emailtext= userSearchPage.getEmail();
			Assert.assertTrue(emailtext.isEmpty());
			
			//check fromDate is cleared
			
			String fromDateText = userSearchPage.getFromDate();
			Assert.assertTrue(fromDateText.isEmpty());
						
			//check toDate is cleared
			String toDateText = userSearchPage.getToDate();
			Assert.assertTrue(toDateText.isEmpty());
							
		}

}
