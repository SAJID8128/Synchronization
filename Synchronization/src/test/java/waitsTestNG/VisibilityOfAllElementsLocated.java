package waitsTestNG;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VisibilityOfAllElementsLocated {
	
	public WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("https://www.target.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	@Test
	public void allElements() throws InterruptedException {
		
		WebElement BestBuy = driver.findElement(By.xpath("//span[contains(text(),'Categories')]"));
		BestBuy.click();
		Thread.sleep(1000);
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//body/div[3]/div[1]/div[1]/ul[1]/li")));
		
List<WebElement> list = driver.findElements(By.xpath("//body/div[3]/div[1]/div[1]/ul[1]/li"));
		
		System.out.println("Auto Suggest List :: " + list.size());
		
		for(int i = 0 ;i< list.size();i++)
		{
			System.out.println(list.get(i).getText());
			
			if(list.get(i).getText().equals("Categories"))
			{
				list.get(i).click();
				break;
			}
		}
		
		
	}

}
