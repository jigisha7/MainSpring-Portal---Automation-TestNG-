package TestngPack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class viewIssue extends BaseClass {

	// To click element
	public static void clickelement(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		driver.findElement(by).click();
	}

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

		// clicking Issues in monitor
		clickelement(By.id("LOCK_Issues"));

	}

	// Validating header of Issues
	@Test(priority = 2)
	public static void Header() throws IOException {
		//Locating header elements
		String Id = driver.findElement(By.id("CM_SEQNUMBER-textEl")).getText();
		String name = driver.findElement(By.id("CM_NAME-textEl")).getText();
		String status = driver.findElement(By.id("CM_OVERALLSTATUS-textEl")).getText();
		String user = driver.findElement(By.id("CM_CURRENTPARTYNAME-textEl")).getText();
		String priority = driver.findElement(By.id("CM_PRIORITY-textEl")).getText();
		String stage = driver.findElement(By.id("CM_CURRENTSTAGENAME-textEl")).getText();
		
		// validating header elements
		Assert.assertEquals(Id, "ID");
		Assert.assertEquals(name, "Name");
		Assert.assertEquals(status, "Overall Status");
		Assert.assertEquals(user, "Current User");
		Assert.assertEquals(priority, "Priority");
		Assert.assertEquals(stage, "Workflow Queue");
		captureScreen(driver,"IssueHeader");
	}

	// Sorting Issues on name
	@Test(priority = 3)
	public static void sortIssue() throws InterruptedException, IOException {
		// Click on Name to sort
		clickelement(By.id("CM_NAME-textEl"));
		captureScreen(driver,"SortingIssues");
	}

	// Clicking Peak issue
	@Test(priority = 4)
	public static void Inissue() throws InterruptedException, IOException {

		Actions action2 = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath("//*[text()='Peak']"));
		action2.doubleClick(elementLocator).perform();
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		captureScreen(driver,"ViewIssue");
	}

	
}
