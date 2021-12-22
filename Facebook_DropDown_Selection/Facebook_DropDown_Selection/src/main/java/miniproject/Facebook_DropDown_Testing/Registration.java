package miniproject.Facebook_DropDown_Testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.Assert;  

public class Registration {
    
    static String baseUrl = "http://www.fb.com";

    static WebDriver driver;

    //Navigating to the base URL
    public void navigate(){
      
        driver.navigate().to(baseUrl);

    }
    
    public void createDriver(String browser_input) throws Exception{
    	
    	driver = DriverSetup.getWebDriver(browser_input);

        navigate();

    }

	public void setFormValues(String first_name, String Last_name, String mobile_number, String pass, String dob, String gender)throws Exception{

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Create New Account")));

		driver.findElement(By.linkText("Create New Account")).click();
        //Clicking Create New Account Button in homepage
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname")));

        driver.findElement(By.name("firstname")).sendKeys(first_name);       			  		
        //Values are passed in Firstname TextBox 
        
        driver.findElement(By.name("lastname")).sendKeys(Last_name);            			  		
        //Values are passed in Surname TexBox

        driver.findElement(By.name("reg_email__")).sendKeys(mobile_number);    		  		
        //Values are passed in Phone_No TextBox
        
        if(!pass.equals("null"))
        	driver.findElement(By.name("reg_passwd__")).sendKeys(pass);    		  		
        //Values are passed in Password TextBox
        
        Select year = new Select(driver.findElement(By.name("birthday_year")));
        year.selectByValue(dob.substring(6, 10));
        
        Select month = new Select(driver.findElement(By.name("birthday_month")));
        month.selectByIndex(Integer.parseInt(dob.substring(3, 5))-1);
        
        Select day = new Select(driver.findElement(By.name("birthday_day")));
        day.selectByVisibleText(dob.substring(1,2));
        
        if(gender.equalsIgnoreCase("male")){
        		driver.findElement(By.cssSelector("input[value='2']")).click();               		 
        		//Male Radio Button is clicked
        	} else if(gender.equalsIgnoreCase("female")){        		
        		driver.findElement(By.cssSelector("input[value='1']")).click();               		
        		//Female Radio Button is clicked
        	} else {        		
        		driver.findElement(By.cssSelector("input[value='-1']")).click();               		
        		//Other Radio Button is clicked
        	}
        
        driver.findElement(By.name("websubmit")).click();                   		  		
        //Submit Button in Register Page is Clicked
        
        System.out.println(get_error_message_password());

        System.out.println(get_error_message_phone_no());
        
    }
    
    public String get_error_message_phone_no()throws Exception{
        	
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='large_form']/div[@class='mbm _a4y']/div[@class='_5dbb _5634']/i[@class='_5dbc img sp_98fCI7IVTTz sx_54513f']")));
        
        driver.findElement(By.xpath("//div[@class='large_form']/div[@class='mbm _a4y']/div[@class='_5dbb _5634']/i[@class='_5dbc img sp_98fCI7IVTTz sx_54513f']")).click();;
        
        //Fetching error statement from the phone number/email Textbox
    	WebElement element = driver.findElement(By.xpath("//div[@class='uiContextualLayerPositioner _572t uiLayer']/div[@class='uiContextualLayer uiContextualLayerLeft']/div[@class='_5v-0 _53im']/div[@class='_5633 _5634 _53ij']"));
        String output = element.getText();

        String excepted = "Please enter a valid mobile number or email address."; 
        
        //Validating error statement from the phone number/email Textbox
        Assert.assertEquals(output, excepted);

        return output;

    }
    
    public String get_error_message_password()throws Exception{
    	
    	WebElement inputBox = driver.findElement(By.name("reg_passwd__"));
    	String textInsideInputBox = inputBox.getAttribute("value");
    	
    	// Check whether input field is blank
    	if(!textInsideInputBox.isEmpty())
    		{
    			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='large_form']/div[@class='mbm _a4y']/div[@class='_5dbb _5634']/i[@class='_5dbc img sp_98fCI7IVTTz sx_54513f']")));    		
    			driver.findElement(By.xpath("//div[@class='large_form']/div[@class='mbm _a4y']/div[@class='_5dbb _5634']/i[@class='_5dbc img sp_98fCI7IVTTz sx_54513f']")).click();;
    		}
    	//Fetching error statement from the password Textbox
        WebElement element = driver.findElement(By.xpath("//div[@class=\"uiContextualLayer uiContextualLayerLeft\"]/div[@class='_5v-0 _53im']/div[@class='_5633 _5634 _53ij']"));
        String output = element.getText();
           
        String excepted = "Enter a combination of at least six numbers, letters and punctuation marks (such as ! and &).";
        
        //Validating error statement from the password Textbox

        Assert.assertEquals(output, excepted);

        return output;

    }
    public void closeBrowser()throws Exception{
    	
    	driver.quit();
    	
    }
}