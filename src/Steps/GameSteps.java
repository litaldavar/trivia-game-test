package Steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class GameSteps extends BaseSteps {

	private String pageUrl = "https://svcollegetest.000webhostapp.com/";
	private By startButton = By.id("startB");
	
	//Repetitive for all questions
	private By qInput = By.xpath("//*[@id=\"myform1\"]/div/div/div/input");
	//header image
	private By heder = By.xpath("/html/body/div[1]/header/img");
	//Repetitive for all questions
	private By nextQButton = By.id("nextquest");
	//Repetitive table for all answers
	private By answersTable = By.name("answer1");
	//Repetitive table for answers radio buttons
	private By RadioAnswerTable = By.name("answer");
	private By playButton = By.xpath("//*[@id=\"secondepage\"]/center/button[1]");
	//Repetitive next answer button
	private By nextAbutton = By.id("btnnext");
	
	private int qNum = 2;
	

	public GameSteps(WebDriver driver) {
		super(driver);
	}	
	// open site 
	public void openPage()  {
		OpenPage(pageUrl);
	}

	//confirm text -- boolean
	public boolean confirmText(String expectedText) {
		Reporter.log("text expected: " + expectedText , true);
		return textConfirm(expectedText);
	}
	
	//get the title of the page
	public String getPageTitle() {
		return getTitle();
	}
	
	//click on start button
	public void startGame() {
		
		Reporter.log("Start game!!" , true);
		click(startButton);
		
	}
	
	//enter a question 
	public void enterqQuestion(String question) {
		Reporter.log("Enter first question" , true);
		type(question, qInput);
		click(heder);
		
	}
	//confirm question input 
	public boolean confirmQInput(String input) {
		
		if( find(qInput).getAttribute("value").intern() == input)
			return true;
		else {
			return false;
		}
	}
	
	//click on next button for answers screen 
	public void clickQNext() {
		click(nextQButton);		
	}
	
	//** fill answer in answer screen in a specific line */
	public void fillAnswer(String answer , int line) {
		fillAnswer(answer , line , answersTable);
		click(heder);		
	}
	
	/** confirm answer text*/
	public boolean confirmAInput(String answer , int line) {
		if(confirmAnswerText(answer, line, answersTable)) {
			return true;
		}
		return false;
	}
	
	/** check correct answer*/
	public void checkAnswer(int line) {
		checkAnswer(line, RadioAnswerTable);
	}
	
	/** confirm radio button for answer checked*/
	public boolean confirmAChecked(int line) {
		if(confirmAnswerRadio(line, RadioAnswerTable)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/** check answer in play*/
	public void checkAnswerPlay(int line) {
		checkAnswer(line ,By.name("answertest" + qNum) );
	}
	
	/** confirm radio button for answer checked*/
	public boolean confirmAPlayChecked(int line) {
		if(confirmAnswerRadio(line, By.name("answertest" + qNum))) {
			qNum--;
			return true;
		}
		else {
			qNum--;
			return false;
		}
	}
	
	/** Click Play button*/
	public void clickPlay() {
		click(playButton);
	}
	
	/** Click next game question */
	public void clickANext() {
		click(nextAbutton);
	}

}
