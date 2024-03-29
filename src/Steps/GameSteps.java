package Steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import java.util.Stack;
import java.util.concurrent.TimeUnit;


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
	//Repetitive back button for questions
	private By backQButton = By.id("backquest");
	private By quitSetUp = By.xpath("//*[@id=\"secondepage\"]/center/button[2]");
	private By tryAgainButton = By.xpath("//*[@id=\"markpage\"]/center/button[1]");
	private By QuitGameButton = By.xpath("//*[@id=\"markpage\"]/center/button[2]");
	private By facebookButton = By.xpath("//*[@id=\"fackBook2\"]/img");

	
	private int qNum = 2;
	public Stack<String> stackQ;

	public GameSteps(WebDriver driver) {
		super(driver);
		stackQ = new Stack<String>();
	}
	
	public int getqNum(){
		return this.qNum;
	}
	
	public void setqNum(int q) {
		this.qNum = q;
	}
	// open site 
	public void openPage()  {
		OpenPage(pageUrl);
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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

	/** click back button for getting back to previous question*/
	public void clicBackQ() {
		click(backQButton);
	}
	
	/** click quit button after setup */
	public void clickquitSetup() {
		click(quitSetUp);
	}
	
	/**click Try again button*/
	public void clickTryAgain() {
		click(tryAgainButton);
	}
	
	/**click quit game button */
	public void clickQuit() {
		click(QuitGameButton);
	}
	
	/**confirm there was an Alert*/
	public boolean isAlert() {
		return alertShow();
	}
	
	/** add question to the stack*/
	public void add2stackQ(String q) {
		Reporter.log("New question pushed is: " + q);
		stackQ.push(q);
	}
	
	/** get current question*/
	public String getFromStackQ() {
		if(!stackQ.empty()) {
			Reporter.log("from pop function => stack is => " + stackQ , true);
			String Q = stackQ.pop();						
			return Q;
		}
		return "Error: no Question found";
	}
	
	public String getQuestion(int i) {
		
		By b =  By.xpath("//*[@id="+ i + "]/h3");
		String Q = getGameQuestion(b);		
		return Q;
		
	}
	
	public void shareWithFacebook() {
		click(facebookButton);
	}
	
	public boolean clickOkonAlert() {
		return alertClickOk();
	}
	
}
