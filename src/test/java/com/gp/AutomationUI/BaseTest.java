/**
 * 
 */
package com.gp.AutomationUI;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * @author GP
 * This class serves as the base class for the test classes * 
 */
public class BaseTest {
	
	protected static WebDriver driver;
	
	@BeforeClass
    public static void setUp(){
		System.setProperty("webdriver.chrome.driver","C:\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
  
    @AfterClass
    public static void tearDown(){
        driver.close();
    }

}
