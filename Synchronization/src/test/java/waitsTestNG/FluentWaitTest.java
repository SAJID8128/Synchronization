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

public class FluentWaitTest {

	public WebDriver driver;

	

	@BeforeTest
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("https://weather.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

	}
	
	@Test
	public void radarTest() throws InterruptedException {
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
				Thread.sleep(2000);
		
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement radar = driver.findElement(By.xpath("//span[contains(text(),'Radar')]"));
				
				if(radar.isEnabled()) {
					System.out.println("Radar detected");
				}
				return radar;
			}
		});
		element.click();
		Thread.sleep(2000);

	}

	@Test
	public void weekendTest() throws InterruptedException {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5))
				.ignoring(NoSuchElementException.class);
				Thread.sleep(2000);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement weekend = driver.findElement(By.xpath("//span[contains(text(),'Weekend')]"));
				
				if(weekend.isEnabled()) {
					System.out.println("Weekend weather displayed");
				}
				return weekend;
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
