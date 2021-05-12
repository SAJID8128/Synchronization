package waitsMainMethod;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FluentWaitGeneric {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();		
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
				
		driver.findElement(By.name("q")).sendKeys("Selenium");
		driver.findElement(By.name("q")).sendKeys(Keys.RETURN);	
		
		By elementLocator = By.xpath("//h3[contains(text(),'SeleniumHQ Browser Automation')]");
		waitForElement(driver, elementLocator);
		
	}
	
	public static WebElement waitForElement(WebDriver driver, By locator) {
		
		// Fluent wait declaration
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) 
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
		
		// Use of Fluent wait
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
				
		});
		
		return element;
	}
	
}
