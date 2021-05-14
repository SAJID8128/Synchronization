package waitsTestNG;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Function;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FluentWaitCarousel {

	public WebDriver driver;



	@BeforeTest
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("https://www.nike.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void markdownsTest() throws InterruptedException {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
		Thread.sleep(2000);


		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement markdowns = driver.findElement(By.xpath("//a[contains(text(),'Shop All Our New Markdowns')]"));

				if(markdowns.isEnabled()) {
					System.out.println("Markdowns detected");
				}
				return markdowns;
			}
		});
		element.click();
		Thread.sleep(2000);	
		driver.navigate().back();


	}

	@Test(priority = 2)
	public void joinNowTest() throws InterruptedException {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
		Thread.sleep(2000);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement joinNow = driver.findElement(By.xpath("//a[contains(text(),'Join Now')]"));

				if(joinNow.isEnabled()) {
					System.out.println("Join Now is displayed");
				}
				return joinNow;
			}
		});
		element.click();
		Thread.sleep(2000);
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
