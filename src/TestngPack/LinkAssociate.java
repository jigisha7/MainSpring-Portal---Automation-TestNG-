package TestngPack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import org.openqa.selenium.JavascriptExecutor;

public class LinkAssociate extends BaseClass {
	

	
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
  		public void LinkedCards() throws InterruptedException
  		{
          Actions action = new Actions(driver); 
          //Select the issue from the Monitor drop down
          driver.findElement(By.id("LOCK_Issues")).click();
          driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
           
          //To locate the Team5
          Actions action2 = new Actions(driver);
          //WebElement elementLocator=driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div/div/div[1]/div/div/div[2]/div[2]/div[1]/div[1]/div[2]/table[86]/tbody/tr/td[1]/div"));
          WebElement elementLocator=driver.findElement(By.xpath("//*[text()='Team5']"));
          action2.doubleClick(elementLocator).perform();
          driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

          //To locate the LinkedCards from the header menu
            driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[7]/iframe")));
            WebElement Element= driver.findElement(By.xpath("/html/body/form/div[6]/div/div/ul/li[3]/a"));
            JavascriptExecutor Executor=(JavascriptExecutor)driver;
            Executor.executeScript("arguments[0].click();", Element);
           
            //Click the three dots below the Action
           driver.switchTo().frame(driver.findElement(By.xpath("/html/body/form/table[2]/tbody/tr/td/iframe[2]")));
           WebElement Element1=driver.findElement(By.xpath("/html/body/table/tbody/tr/td/div/div[2]/div[1]/div/div/div[2]/div[1]/div/div[1]/div/div/span[9]/span"));
           JavascriptExecutor Executor1=(JavascriptExecutor)driver;
           Executor1.executeScript("arguments[0].click();", Element1);
           
           //Click the "Search and Link" option
           driver.findElement(By.xpath("/html/body/div[1]/div[3]/ul/li[2]")).click();
           
           //select any specific work item
           driver.findElement(By.xpath("/html/body/table/tbody/tr/td/div/div[2]/div[1]/div/div[1]/div/div[3]/div/div[2]/div[1]/table/tbody/tr[2]/td[1]/span/div/span/span[1]/input")).click();
           
           
           driver.findElement(By.xpath("/html/body/table/tbody/tr/td/div/div[2]/div[1]/div/div[1]/div/div[3]/div/div[3]/div/button/span[1]")).click();
           
  		}
}
