package tests;


import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterSuite;

public class BaseTests {
	
	public WebDriver driver;
  
  @BeforeMethod(alwaysRun = true)
  @Parameters("browser")	 
  public void beforeMethod(@Optional("chrome") String browser) {
	 Reporter.log("Method start",true);
	// Reporter.log("Test is running on " + browser, true);
	 //in every Method we start at fresh with browser closed"
	 // we open the browser and log on to the game site"
	/* switch(browser){
	  case "firefox":
		  System.setProperty("webdriver.gecko.driver",  "C:\\Selenium\\drive\\Firefox\\geckodriver.exe");
		  driver = new FirefoxDriver();
		  break;
	 
	  case "chrome":
		  System.setProperty("webdriver.chromedriver",  "C:\\Selenium\\drive\\chrome\\chromedriver.exe");
		  driver = new ChromeDriver();
		  break;
	  default:
		  Reporter.log("Do not know how to start: " + browser + ", starting chrome." , true);
		  System.setProperty("webdriver.chromedriver",  "C:\\Selenium\\drive\\chrome\\chromedriver.exe");
		  driver = new FirefoxDriver();
		 break;
	  }*/
	 BrowserDriverFactory factory = new BrowserDriverFactory(browser);
	 driver = factory.CreateDriver();
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod() throws InterruptedException {
	  Thread.sleep(2000);	  
	Reporter.log("End of Method" , true);
	  driver.quit();
	  
  }

  @BeforeSuite
  public void beforeSuite() {
	  Reporter.log("Suite starts", true);
  }

  @AfterSuite
  public void afterSuite() {
	  Reporter.log("Suite finished!!" , true);
  }
  

}
