/**
 * 
 */
package com.gp.AutomationUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author GP
 * This class serves as the base class for the page objects and has common methods 
 * to interact with the page elements using WebDriver
 */
public class BasePage {
	
protected WebDriver driver;

public BasePage (WebDriver driver) 
{
	this.driver = driver;
}

public String getTitle() 
{
	return driver.getTitle();
}

public String getUrl() 
{
	return driver.getCurrentUrl();
}

public WebElement getElementById(String id)
{
	return driver.findElement(By.id(id));
}

public WebElement getElementByName(String name)
{
	return driver.findElement(By.name(name));
}

public WebElement getElementByLinkText(String linkText)
{
	return driver.findElement(By.linkText(linkText));
}

public WebElement getElementByXpath(String Xpath)
{
	return driver.findElement(By.xpath(Xpath));
}

public WebElement getElementByClassName(String className)
{
	return driver.findElement(By.className(className));
}


}
