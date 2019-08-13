package Steps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class BaseSteps {

	protected WebDriver driver;

	public BaseSteps(WebDriver driver) {
		this.driver = driver;

	}

	// Step 1: open main page
	protected void OpenPage(String url) {
		driver.get(url);
	}

	// get page title
	protected String getTitle() {
		return driver.getTitle();
	}

	protected boolean textConfirm(String expectedText) {
		return driver.getPageSource().contains(expectedText);
	}

	/**
	 * Wait for specific ExpectedCondition for the given amount of time in seconds
	 */
	private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}

	/**
	 * Wait for given number of seconds for element with given locator to be visible
	 * on the page
	 */
	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
				break;
			} catch (StaleElementReferenceException e) {
				Reporter.log(e.getMessage() , true);
			}
			attempts++;
		}
	}

	/** Click on element with given locator when its visible */
	protected void click(By locator) {
		Reporter.log("find locaator: " + locator.toString(), true);
		waitForVisibilityOf(locator, 5);
		WebElement e = driver.findElement(locator);
		if (e == null) {
			Reporter.log("Error element not found " + locator.toString(), true);
			
		}
		//Reporter.log(e.getTagName(), true);
		e.click();
		Reporter.log("end click", true);
	}
	
	protected void sleep(int m) {
		sleep(m);
	}
	
	/** Find element using given locator */
	protected WebElement find(By locator) {
		WebElement e = driver.findElement(locator);
		if(e == null) {
			Reporter.log("Error element not found",true);
		}
		return driver.findElement(locator);

	}
	
	/** Type given text into element with given locator */
	protected void type(String text, By locator) {
		waitForVisibilityOf(locator, 5);
		find(locator).sendKeys(text);
	}
	
	/** Find all elements using given locator */
	protected List<WebElement> findAll(By locator) {
		return driver.findElements(locator);
	}
	
	/** Fill answer line with answer text */
	protected void fillAnswer(String answer , int line , By locator) {
		List<WebElement> aList = findAll(locator);
		int aSize = aList.size();
		if( aSize >0 && line < aSize) {
			aList.get(line).sendKeys(answer);			
		}
		else {
			Reporter.log("Answer Line was not found" , true);
		}
	}
	
	/** Confirm answer in a specific line answer */
	protected boolean confirmAnswerText(String answer , int line , By locator) {
		List<WebElement> aList = findAll(locator);
		int aSize = aList.size();
		if( aSize >0 && line < aSize) {
			if(aList.get(line).getAttribute("value").intern() == answer) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			Reporter.log("Answer Line was not found" , true);
			return false;
		}
	}
	
	/** choose radio button for the correct answer*/
	protected void checkAnswer(int line , By locator) {
		List<WebElement> aList = findAll(locator);
		int aSize = aList.size();
		if( aSize >0 && line < aSize) {
			aList.get(line).click();			
		}
		else {
			Reporter.log("Answer Radio button Line was not found" , true);
		}
	}
	
	/** confirm radio button checked*/
	protected boolean confirmAnswerRadio(int line , By locator) {
		List<WebElement> aList = findAll(locator);
		int aSize = aList.size();
		if( aSize >0 && line < aSize) {
			if(aList.get(line).isSelected()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			Reporter.log("Answer Radio button Line was not found" , true);
			return false;
		}
	}

}
