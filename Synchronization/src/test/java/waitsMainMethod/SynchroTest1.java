package waitsMainMethod;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SynchroTest1 {

	public static void main(String[]args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.navigate().to("https://www.facebook.com/r.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// Implicitly Wait:
		// 1. is always applied globally
		// 2. is available for all the webelements
		// 3. dynamic in nature
		// 4. it can be changed anywhere and at any time in your code

		WebElement firstName = driver.findElement(By.name("firstname"));
		WebElement lastName = driver.findElement(By.name("lastname"));
		WebElement cellNumber = driver.findElement(By.name("reg_email__"));
		WebElement password = driver.findElement(By.name("reg_passwd__"));

		sendKeys(driver, firstName, 10, "Tom");
		sendKeys(driver, lastName, 5, "Cruise");
		sendKeys(driver, cellNumber, 5, "1234567899");
		sendKeys(driver, password, 10, "password");

		WebElement signUp = driver.findElement(By.name("websubmit"));
		clickOn(driver, signUp, 5);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // Overrides previous implicitly wait function 
																	     // on line 19.		
	}
		
	// Explicit Wait:
	// 1. no explicit keyword or method
	// 2. available with WebDriverWait with some ExpectedConditions
	// 3. specific to element
	// 4. dynamic in nature
	// 5. We should never use implicit wait and explicit wait together:
	// ---selenium webDriver will wait for the element first because of IMPLICIT WAIT and the EXPLICIT WAIT will be applied
	// ---hence, total synchronization wait will be increased for each element.
    public static void sendKeys(WebDriver driver, WebElement element, int timeout, String value) {		
	new WebDriverWait(driver, timeout). // Explicitly wait
	until(ExpectedConditions.visibilityOf(element));
	element.sendKeys(value);
    }

    
    public static void clickOn(WebDriver driver, WebElement element, int timeout) {
	new WebDriverWait(driver, timeout).
	until(ExpectedConditions.elementToBeClickable(element));
	element.click();
    }


}
