package TestngPack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InvalidWorkflow extends BaseClass {
	// List of Issues
	@Test(priority = 1)
	public static void ViewIssues() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		Actions action = new Actions(driver);

		// opening side menu
		WebElement trgt = driver.findElement(By.xpath("//*[@id=\"navbar\"]/div[3]/div[1]/span"));
		action.moveToElement(trgt).perform();

		// Selecting project
		if (driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/ul/li[1]/a")).isDisplayed()) {
			driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/ul/li[1]/a")).click();
		} else {
			driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/span")).click();
			driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/ul/li[1]/a")).click();
		}

		// clicking monitor
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		WebElement monitor = driver.findElement(By.id("LOCK_Monitor"));
		action.moveToElement(monitor).perform();

		// Issues in monitor
		viewIssue.clickelement(By.id("LOCK_Issues"));

		// Click on Name to sort
		viewIssue.clickelement(By.id("CM_NAME-textEl"));
	}

	// unassign performer and approver
	@Test(priority = 2)
	public static void invalidWorkflow() throws InterruptedException, IOException {

		Actions action2 = new Actions(driver);
		// Clicking Peak issue
		WebElement elementLocator = driver.findElement(By.xpath("//*[text()='Peak']"));
		action2.doubleClick(elementLocator).perform();
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

		// Switching frame
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='contentframe']")));
		// workflow
		WebElement Element = driver.findElement(By.xpath("/html/body/form/div[6]/div/div/ul/li[2]/a"));
		JavascriptExecutor Executor = (JavascriptExecutor) driver;
		Executor.executeScript("arguments[0].click();", Element);

		Thread.sleep(2000);

		// Switching frame
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='1255610']")));

		// initiator
		WebElement init = driver.findElement(By.xpath("//*[@id='List']/table/tbody[2]/tr[1]/td[2]/select"));
		Select initiator = new Select(init);
		initiator.selectByVisibleText("Jigisha Shah");

		// performer
		WebElement perform = driver.findElement(By.xpath("//*[@id='List']/table/tbody[2]/tr[3]/td[2]/select"));
		Select performer = new Select(perform);
		performer.selectByVisibleText("UnAssigned");

		// Approver
		WebElement Appr = driver.findElement(By.xpath("//*[@id='List']/table/tbody[2]/tr[5]/td[2]/select"));
		Select Approver = new Select(Appr);
		Approver.selectByVisibleText("UnAssigned");
		Thread.sleep(2000);

		captureScreen(driver,"InvalidWorkflow");
		// Switching frame
		driver.switchTo().parentFrame();
		// To save
		viewIssue.clickelement(By.xpath("//*[@id='SaveBtn']"));
		//System.out.println("-----saved---- ");
		

	}

}
