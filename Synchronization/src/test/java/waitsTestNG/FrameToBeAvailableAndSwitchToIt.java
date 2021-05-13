package waitsTestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameToBeAvailableAndSwitchToIt {

	public WebDriver driver;

	@BeforeTest
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("https://www.w3schools.com/html/html_iframe.asp");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

	}

	@Test
	public void iframeTest() {

		driver.findElement(By.xpath("//body/div[@id='belowtopnav']/div[1]/div[1]/div[3]/iframe[1]"));

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//body/div[@id='belowtopnav']/div[1]/div[1]/div[3]/iframe[1]")));

		System.out.println("Successfull iframe switch!");

	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
