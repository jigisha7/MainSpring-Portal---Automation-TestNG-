package TestngPack;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class validWorkflow extends BaseClass {

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
			System.out.println("inside IF");
			clickelement(By.xpath("//*[@id=\"projectIcon\"]/ul/li[1]/a"));
		} else {
			System.out.println("inside ELSE");
			clickelement(By.xpath("//*[@id=\"projectIcon\"]/span"));
			// click aug8 dummy proj
			clickelement(By.xpath("//*[@id=\"projectIcon\"]/ul/li[1]/a"));
		}

		// clicking monitor
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		WebElement monitor = driver.findElement(By.id("LOCK_Monitor"));
		action.moveToElement(monitor).perform();

		// Issues in monitor
		clickelement(By.id("LOCK_Issues"));
		System.out.println("till View ");

		// Click on Name to sort
		clickelement(By.id("CM_NAME-textEl"));
		
	}

	// assign initiator,performer and approver
	@Test(priority = 2)
	public static void workflow() throws InterruptedException, IOException {

		Actions action2 = new Actions(driver);
		// Clicking Peak issue
		WebElement elementLocator = driver.findElement(By.xpath("//*[text()='Peak']"));
		action2.doubleClick(elementLocator).perform();
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		System.out.println("*CLICK  DONE*");

		// Switching frame
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='contentframe']")));

		// workflow
		WebElement Element = driver.findElement(By.xpath("/html/body/form/div[6]/div/div/ul/li[2]/a"));
		JavascriptExecutor Executor = (JavascriptExecutor) driver;
		Executor.executeScript("arguments[0].click();", Element);
		System.out.println("**Workflow**");
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
		performer.selectByVisibleText("Poonam Singh");

		// Approver
		WebElement Appr = driver.findElement(By.xpath("//*[@id='List']/table/tbody[2]/tr[5]/td[2]/select"));
		Select Approver = new Select(Appr);
		Approver.selectByVisibleText("Kundan Kumarsharma");
		Thread.sleep(2000);

		// Switching frame
		driver.switchTo().parentFrame();
		// To save
		clickelement(By.xpath("//*[@id='SaveBtn']"));
		System.out.println("-----saved---- ");
		
		captureScreen(driver,"validworkflow");
		// To return
		clickelement(By.xpath("//*[@id='CancelBtn']"));
		
	}

}
