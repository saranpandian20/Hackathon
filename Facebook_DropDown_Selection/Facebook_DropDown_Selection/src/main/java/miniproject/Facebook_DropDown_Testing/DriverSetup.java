package miniproject.Facebook_DropDown_Testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
    
    public static WebDriver getWebDriver(String browser){

    	WebDriver driver = null; 
    	
    	if(browser.equalsIgnoreCase("firefox"))
    	{
    		
    		System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");    
    		driver = new FirefoxDriver(); 
    		driver.manage().window().maximize();    		
    		
    	}
    	else if(browser.equalsIgnoreCase("chrome"))
    	{    	

    		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");    
    		driver = new ChromeDriver(); 
    		driver.manage().window().maximize();
    		
    	}
        return driver;
    }
}
