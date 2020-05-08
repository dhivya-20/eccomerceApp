package ecommerceApp;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCart 
{
	public static void main(String[] args) 
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait e=new WebDriverWait(driver, 5);

		// array of items to be added
		String[] items = { "Cucumber", "Potato", "Corn","Carrot"};
		addItems(driver,items);
		
		driver.findElementByCssSelector("img[alt='Cart']").click();
		driver.findElementByXPath("//button[text()='PROCEED TO CHECKOUT']").click();;
		
		driver.findElementByCssSelector("input.promoCode").sendKeys("rahulshettyacademy");
		driver.findElementByCssSelector("button.promoBtn").click();
		
		e.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.promoInfo")));
		
		String text=driver.findElementByCssSelector("span.promoInfo").getText();
		System.out.println(text);
	}	
	
	
	public static void addItems(ChromeDriver driver,String[] items)
	{
		int j=0;

		// find the names of the product
		List<WebElement> list = driver.findElementsByCssSelector("h4.product-name");

		for (int i = 0; i < list.size(); i++) 
		{
			// 'Brocolli - 1 kg' crop only 'Brocolli'
			String[] names = list.get(i).getText().split("-");

			// remove the white space
			String newnames = names[0].trim();

			// convert array into arraylist
			List<String> arrayList = Arrays.asList(items);

			if (arrayList.contains(newnames)) 
			{
				j++;
				driver.findElementsByXPath("//div[@class=\"product-action\"]").get(i).click();
				if (j==items.length) 
				{
					break;
				}
			}
		}
	}
}
