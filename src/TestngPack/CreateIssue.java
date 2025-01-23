package TestngPack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

public class CreateIssue extends BaseClass {
	
	@Test(priority = 0) //code to locate project and get the project page
	public static void LocateProject() throws InterruptedException, IOException {
		//code to wait for manually login into the site
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);

		Actions action = new Actions(driver);
		
		//code to locate the webelement of Side menu bar
		WebElement trgt = driver.findElement(By.xpath("//*[@id=\"navbar\"]/div[3]/div[1]"));
		
		//code to mouse hover the side menu bar
		action.moveToElement(trgt).perform();
		
		//code to takescreenshot
		captureScreen(driver,"SideMenuBar");
			
		//code to locate the project and click on the project
		if (driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/ul/li[1]/a")).isDisplayed()) {
			driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/ul/li[1]/a")).click(); 
		} else {// locate the project another way
			driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/span")).click();
			driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/ul/li[1]/a")).click();
		}
		
		//code to takescreenshot
		captureScreen(driver,"ProjectPage");
	}

	@Test(priority = 1)// Code to Locate Issue and get its webpage
	public static void LocateMenuIssue() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		//code to locate the Monitor menu 
		WebElement monitor = driver.findElement(By.id("LOCK_Monitor"));
		Actions action = new Actions(driver);
		
		//code to takescreenshot
		captureScreen(driver,"MonitorSubmenu");
				
		//Mouse hover to see submenus
		action.moveToElement(monitor).perform();
		
		//code to locate submenu issue and click on it
		driver.findElement(By.xpath("//*[text()='Issues']")).click();
		
		//code to takescreenshot
		captureScreen(driver,"IssuePage");
	}

	@Test(priority = 2) //code to fill the issue form and submit
	public static void LocateIssue() throws InterruptedException, IOException {
		// locate the create issue and get its webpage
		driver.findElement(By.id("KEY_BUTTON_Add")).click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		
		// switch to the frame of create issue
		driver.switchTo().frame("contentframe");

		//code to locate Name and send data
		driver.findElement(By.name("CM_Name")).sendKeys("Save Button Working");
		
		//code to locate Description and send data
		driver.findElement(By.name("CM_Description")).sendKeys("The Save button is not working properly.");
		
		//code to select issue category
		Select s = new Select(driver.findElement(By.name("DN_IssueCategory")));
		s.selectByValue("1001571");// Internal
		
		//code to select issue type
		Select t = new Select(driver.findElement(By.name("DN_IssueType")));
		t.selectByValue("1001576");// Commercial
		
		//code to select issue Responsibility
		Select r = new Select(driver.findElement(By.name("DN_Responsibility")));
		r.selectByValue("77873");// Jigisha Shah
		
		//code to select issue Severity
		Select severity = new Select(driver.findElement(By.name("DN_Severity1")));
		severity.selectByVisibleText("Minor");
		
		//Code to select issue Priority
		Select priority = new Select(driver.findElement(By.name("CM_Priority")));
		priority.selectByVisibleText("Low");
		
		//code to set Duedate
		driver.findElement(By.name("CM_DUEDATE")).sendKeys("15-MAR-2021");
		
		//code to takescreenshot
		captureScreen(driver,"Filledform");
				
		//code to click on save button
		driver.findElement(By.id("SaveBtn")).click();
		
		//code to takescreenshot
		captureScreen(driver,"IssueCreated");
		
		//code to check whether the issue created successfully  or not
		String id = driver.findElement(By.id("CM_ItemCode")).getText();
		if (id != "") {
			System.out.println("Issue Created Successfully and Issue Id is " + id);
		} else
			System.out.println("Issue not created " + id);
	}


}
