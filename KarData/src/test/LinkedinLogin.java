package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LinkedinLogin {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		   // TODO Auto-generated method stub

        System.setProperty("webdriver.chrome.driver", "/home/bhavik/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.linkedin.com/");
        
        driver.findElement(By.id("login-email")).sendKeys("karthickj@enqos.com");
        driver.findElement(By.id("login-password")).sendKeys("Chennai@123");
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(2000);
        //driver.findElement(By.cssSelector("input[id*='a11y-ember']")).sendKeys("Data Scientist");
        driver.findElement(By.xpath("//div[@role='search']//input[@placeholder='Search']")).sendKeys("bhavik parekh");
        Thread.sleep(1000);
        driver.findElement(By.className("nav-search-button")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@data-vertical='PEOPLE']")).click();
        Thread.sleep(2000);
        List<WebElement> button = new ArrayList<WebElement>();
        JavascriptExecutor jsx = (JavascriptExecutor)driver;
        jsx.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(3000);
        try
        {
        	do
	        {
	        	// Button //button.addAll(driver.findElements(By.xpath("//div[contains(@class,'search-result__actions')]/div[contains(@id,'ember')]/button")));
        		//Name element - below line
        		button.addAll(driver.findElements(By.xpath("//div[contains(@class,'search-result__info pt3 pb4 ph0')]/a/h3")));
	        	System.out.println(button.size());
	        	for(WebElement test: button)
	        	{
	        		System.out.println(test.getText());
	        		
	        		
	        	}
	        	driver.findElement(By.xpath("//button [@class='next']")).click();
	        	Thread.sleep(2000);
	        	jsx.executeScript("window.scrollBy(0,500)", "");
	        	Thread.sleep(1000);
	        	button.clear();
	        }while(driver.findElement(By.xpath("//button [@class='next']")).isDisplayed());
        	
        }
        catch (Exception e) {
			button.addAll(driver.findElements(By.xpath("//div[contains(@class,'search-result__actions')]/div[contains(@id,'ember')]/button")));
        	System.out.println(button.size());
		}
        driver.quit();
        
	}

}
