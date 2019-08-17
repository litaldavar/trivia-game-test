package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

public class BrowserDriverFactory {
	
	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private String browser;

	public  BrowserDriverFactory(String browser) {
		this.browser = browser.toLowerCase();
	}
	
	public WebDriver CreateDriver() {
		Reporter.log("Create driver: " + browser , true);
		
		 switch(browser){
		  case "firefox":
			  System.setProperty("webdriver.gecko.driver",  "C:\\Selenium\\drive\\Firefox\\geckodriver.exe");
			  driver.set( new FirefoxDriver());
			  break;
		 
		  case "chrome":
			  System.setProperty("webdriver.chromedriver",  "C:\\Selenium\\drive\\chrome\\chromedriver.exe");
			  driver.set(new ChromeDriver());
			  break;
		  default:
			  Reporter.log("Do not know how to start: " + browser + ", starting chrome." , true);
			  System.setProperty("webdriver.chromedriver",  "C:\\Selenium\\drive\\chrome\\chromedriver.exe");
			  driver.set( new FirefoxDriver());
			 break;
		  }
		 
		 return driver.get();
	}
}
