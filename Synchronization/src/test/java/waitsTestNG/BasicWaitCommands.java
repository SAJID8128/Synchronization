package waitsTestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicWaitCommands {

	public WebDriver driver;

	@BeforeTest
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("https://www.expedia.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}	

	@Test(priority = 0)
	public void elementToBeClickable() {

		WebDriverWait wait = new WebDriverWait(driver, 12);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'More travel')]")));
		element.click();
	}
	
	@Test(priority = 1)
	public void invisibilityOfElementLocated() {
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		boolean element = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//select[@id='site-selector']")));
		System.out.println(element);
	}

	@Test(priority = 2)
	public void presenceOfElementLocated() {

		WebDriverWait wait = new WebDriverWait(driver, 12);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Sign in')]")));
		element.click();
	}

	@Test(priority = 3)
	public void visibilityOfElementLocated() {

		WebDriverWait wait = new WebDriverWait(driver, 12);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@id='countryCode']")));

		driver.findElement(By.cssSelector("#phoneNumber")).sendKeys("9876543210");
	}
	
	@AfterTest
	public void tearDown() throws InterruptedException {
		Thread.sleep(1000);
		driver.close();
	}

}