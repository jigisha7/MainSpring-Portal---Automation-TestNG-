package TestngPack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


public class Modifyanissue extends BaseClass {
	
	@Test(priority=0)
	public static void LocateProject() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);

		Actions action = new Actions(driver);
		WebElement trgt = driver.findElement(By.xpath("//*[@id=\"navbar\"]/div[3]/div[1]"));
		action.moveToElement(trgt).perform();//to perform the action:view the side menu bar 
		if(driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/ul/li[1]/a")).isDisplayed()) 
		{
			driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/ul/li[1]/a")).click();//locate the project 1st way 
		}
		else
		{// locate the project another way
			driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/span")).click();
			driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/ul/li[1]/a")).click();
		}
	}//to locate the project and get project webpage
	@Test(priority=1)
	public static void LocateMenuIssue() throws InterruptedException 
	{
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		WebElement monitar = driver.findElement(By.id("LOCK_Monitor"));//locate the menu:Monitor
		Actions action = new Actions(driver);
		action.moveToElement(monitar).perform();//to show the submenu's of monitor menu
		driver.findElement(By.xpath("//*[text()='Issues']")).click();//Locate submenu issue and get its webpage 
	}//Loacte Issue and get its webpage
	
	@Test(priority=2)
	public void modify()
	{
		Actions action = new Actions(driver);
        WebElement trgt = driver.findElement(By.xpath("//*[@id='navbar']/div[3]/div[1]"));
        action.moveToElement(trgt).perform();
        driver.findElement(By.xpath("//*[@id='projectIcon']/ul/li[1]/a")).click();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        WebElement monitor = driver.findElement(By.id("LOCK_Monitor"));
        action.moveToElement(monitor).perform();
        driver.findElement(By.id("LOCK_Issues")).click();
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        
        
      
        // To locate the Team5
        Actions action2 = new Actions(driver);
        WebElement elementLocator=driver.findElement(By.xpath("//*[text()='Team5']")); 
        action2.doubleClick(elementLocator).perform();
        driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
        
        //Enter the new Description
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[7]/iframe")));
        WebElement Element =driver.findElement(By.xpath("//*[@id='CM_Description']"));
        JavascriptExecutor Executor=(JavascriptExecutor)driver;
        Executor.executeScript("arguments[0].value='Dummy Which is created for Mainspring';", Element);
        
        //Select the severity from the drop down
        Select severity=new Select(driver.findElement(By.xpath("//*[@id='DN_Severity1']")));
        severity.selectByVisibleText("Minor");
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        
       //select the issue category
        Select priority=new Select(driver.findElement(By.xpath("//*[@id='CM_Priority']")));
        priority.selectByVisibleText("Low");
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        
        
        //To save the changes
        driver.findElement(By.cssSelector("#SaveBtn")).click();
}
}