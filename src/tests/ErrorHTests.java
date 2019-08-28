package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Steps.GameSteps;

public class ErrorHTests extends TestUtilities {

	private String qText = "Please type here your question";
	private String aText = "Please enter 4 possible answers and Mark the right one! ";
	private String playText = "You finished to build the test - lets play!!";
	private String QuitSetUp = "Quit";
	private String playStartText = "Test";
	private String q1Text = " question number: 1";
	private String a1Text = " question number: 1";
	private String q2Text = " question number: 2";
	private String a2Text = " question number: 2";
	private String q3Text = " question number: 3";
	private String a3Text = " question number: 3";

	private String SiteTitle = "SVCOLLEGE TEST";

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" })
	public void answerNotChecked() {
		GameSteps steps = new GameSteps(driver);

		Reporter.log("Answer at setup is not checked", true);

		// Step 2: open the game site steps.openPage(); sleep(3000);
		Assert.assertEquals(steps.getPageTitle(), SiteTitle, "Error: url is wrong");

		// Step 3: click on start button steps.startGame();
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q1Text), "Wrong screen - not question screen");

		// Step 4: write question 1 Reporter.log("enter question 1 with A", true);
		steps.enterqQuestion("A");
		Assert.assertTrue(steps.confirmQInput("A"), "The question is not A");

		sleep(2000);

		// Step 5: click next button to fill answers steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a1Text),
				"This is not Answers screen for question 1");

		// Step 6: fill answer A1 -- first line steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0), "Answer in line 1 is not A");

		// Step 7: fill answer A2 -- second line steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 8: fill answer A3 -- third line steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 9: fill answer A4 -- fourth line steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not D");

		// Step 10: don't check answer but click next button steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.isAlert(), "Error message didn't show - no answer was checked");
	}

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" })
	public void overMaxCharsQ() {
		GameSteps steps = new GameSteps(driver);

		Reporter.log("Over max characters in a question", true);

		steps.openPage();
		sleep(3000);
		Assert.assertEquals(steps.getPageTitle(), SiteTitle, "Error: url is wrong");

		// Step 3: click on start button steps.startGame();
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q1Text), "Wrong screen - not question screen");

		Reporter.log("entered question: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", true);
		// Step 4: write question 1
		Reporter.log("enter question 1 with A", true);
		steps.enterqQuestion("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		// 51 Characters sleep(1000);
		Assert.assertTrue(steps.confirmQInput("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
				"The question is not AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

		// Step 5: click next button to fill answers steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmQInput("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"),
				"Error message didn't show - no error message when max characters in question is over 50");

	}

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" })
	public void numberAsAnswer() {

		GameSteps steps = new GameSteps(driver);

		Reporter.log("number as an answer in setup", true);

		// Step 2: open the game site steps.openPage(); sleep(3000);
		Assert.assertEquals(steps.getPageTitle(), SiteTitle, "Error: url is wrong");

		// Step 3: click on start button steps.startGame();
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q1Text), "Wrong screen - not question screen");

		// Step 4: write question 1 Reporter.log("enter question 1 with A", true);
		steps.enterqQuestion("A");
		Assert.assertTrue(steps.confirmQInput("A"), "The question is not A");

		sleep(2000);

		// Step 5: click next button to fill answers steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a1Text),
				"This is not Answers screen for question 1");

		Reporter.log("fill answer as 1", true);
		// Step 6: fill answer A1 -- first line
		steps.fillAnswer("1", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("1", 0), "Answer in line 1 is not 1");

		// Step 7: fill answer A2 -- second line steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 8: fill answer A3 -- third line steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 9: fill answer A4 -- fourth line steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not D");

		// Step 10: check first line as correct answer steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1");

		sleep(2000);

		// Step 11: click next button for question 2 steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.isAlert(),
				"Error message didn't show - no error message when answer is not in characters alone");
	}

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" })
	public void changeAnswerInSetup() {
		GameSteps steps = new GameSteps(driver);

		Reporter.log("Change selection of answer in setup stage", true);

		// Step 2: open the game site steps.openPage(); sleep(3000);
		Assert.assertEquals(steps.getPageTitle(), SiteTitle, "Error: url is wrong");

		// Step 3: click on start button steps.startGame();
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q1Text), "Wrong screen - not question screen");

		// Step 4: write question 1 Reporter.log("enter question 1 with A", true);
		steps.enterqQuestion("A");
		Assert.assertTrue(steps.confirmQInput("A"), "The question is not A");

		sleep(2000);

		// Step 5: click next button to fill answers steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a1Text),
				"This is not Answers screen for question 1");

		// Step 6: fill answer A1 -- first line steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0), "Answer in line 1 is not A");

		// Step 7: fill answer A2 -- second line steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 8: fill answer A3 -- third line steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 9: fill answer A4 -- fourth line steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not D");

		// Step 10: check second line as correct answer steps.checkAnswer(1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(1), "Correct answer is not at line 2");

		sleep(2000);

		// Step 11: check third line as correct answer steps.checkAnswer(2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(2), "Correct answer is not at line 3");

		sleep(2000);

		// Step 12: check fourth line as correct answer steps.checkAnswer(3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(3), "Correct answer is not at line 4");

		sleep(2000);

		// Step 13: click next button for question 2 steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q2Text),
				"Wrong screen - not question 2 screen");

	}

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" })
	public void skipFillingAQuestion() {
		GameSteps steps = new GameSteps(driver);

		Reporter.log("Skip filling a question", true);

		// Step 2: open the game site steps.openPage(); sleep(3000);
		Assert.assertEquals(steps.getPageTitle(), SiteTitle, "Error: url is wrong");

		// Step 3: click on start button steps.startGame();
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q1Text), "Wrong screen - not question screen");

		// Step 4: write question 1 Reporter.log("enter question 1 with A", true);
		steps.enterqQuestion("A");
		Assert.assertTrue(steps.confirmQInput("A"), "The question is not A");

		sleep(2000);

		// Step 5: click next button to fill answers steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a1Text),
				"This is not Answers screen for question 1");

		// Step 6: fill answer A1 -- first line steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0), "Answer in line 1 is not A");

		// Step 7: fill answer A2 -- second line steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 8: fill answer A3 -- third line steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 9: fill answer A4 -- fourth line steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not D");

		// Step 10: check first line as correct answer steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1");

		sleep(2000);

		// Step 11: click next button for question 2 steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q2Text),
				"Wrong screen - not question 2 screen");

		// Step 12: click next button without filling question steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.isAlert(), "Error message didn't show - A question wasn't inserted");

	}

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" })
	public void QinAnotherLang() {

		GameSteps steps = new GameSteps(driver);

		Reporter.log("input question in another language at setup", true);

		// Step 2: open the game site steps.openPage(); sleep(3000);
		Assert.assertEquals(steps.getPageTitle(), SiteTitle, "Error: url is wrong");

		// Step 3: click on start button steps.startGame();
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q1Text), "Wrong screen - not question screen");

		// Step 4: write question 1 Reporter.log("enter question 1 with א", true);

		steps.enterqQuestion("א");
		Assert.assertTrue(steps.confirmQInput("א"), "The question is not א");

		sleep(2000);

		// Step 5: click next button to fill answers steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.isAlert(),
				"Error message didn't show - no error message when question is not in english");

	}

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" })
	public void overMzxCharA() {

		GameSteps steps = new GameSteps(driver);

		Reporter.log("Input more than 30 characters in an answer", true);

		// Step 2: open the game site steps.openPage(); sleep(3000);
		Assert.assertEquals(steps.getPageTitle(), SiteTitle, "Error: url is wrong");

		// Step 3: click on start button steps.startGame();
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q1Text), "Wrong screen - not question screen");

		// Step 4: write question 1 Reporter.log("enter question 1 with A", true);
		steps.enterqQuestion("A");
		Assert.assertTrue(steps.confirmQInput("A"), "The question is not A");

		sleep(2000);

		// Step 5: click next button to fill answers steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a1Text),
				"This is not Answers screen for question 1");

		// Step 6: fill answer A1 -- first line
		steps.fillAnswer("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", 0),
				"Answer in line 1 is not AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

		// Step 7: fill answer A2 -- second line steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 8: fill answer A3 -- third line steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 9: fill answer A4 -- fourth line steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not D");

		// Step 10: check first line as correct answer steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1");

		sleep(2000);

		// Step 11: click next button for question 2 steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.isAlert(),
				"Error message didn't show - no error message when answer is more than 30 characters");

	}

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" })
	public void lastQback42Q() {
		GameSteps steps = new GameSteps(driver);

		Reporter.log("Go back from final question to previous question", true);

		// Step 2: open the game site steps.openPage(); sleep(3000);
		Assert.assertEquals(steps.getPageTitle(), SiteTitle, "Error: url is wrong");

		// Step 3: click on start button steps.startGame();
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q1Text), "Wrong screen - not question screen");

		// Step 4: write question 1 Reporter.log("enter question 1 with A", true);
		steps.enterqQuestion("A");
		Assert.assertTrue(steps.confirmQInput("A"), "The question is not A");

		sleep(2000);

		// Step 5: click next button to fill answers steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a1Text),
				"This is not Answers screen for question 1");

		// Step 6: fill answer A1 -- first line steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0), "Answer in line 1 is not A");

		// Step 7: fill answer A2 -- second line steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 8: fill answer A3 -- third line steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 9: fill answer A4 -- fourth line steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not D");

		// Step 10: check first line as correct answer steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1");

		sleep(2000);

		// Step 11: click next button for question 2 steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q2Text),
				"Wrong screen - not question 2 screen");

		// Step 12: write question 1 Reporter.log("enter question 2 with B", true);
		steps.enterqQuestion("B");
		Assert.assertTrue(steps.confirmQInput("B"), "The question is not B");

		sleep(2000);

		// Step 13: click next button to fill answers steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a2Text),
				"This is not Answers screen for question 2");

		// Step 14: fill answer A1 -- first line steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0), "Answer in line 1 is not A");

		// Step 15: fill answer A2 -- second line steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 16: fill answer A3 -- third line steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 17: fill answer A4 -- fourth line steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not A");

		// Step 18: check first line as correct answer steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1");

		// Step 19: click next button for question 2 steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q3Text),
				"Wrong screen - not question 3 screen");

		// Step 20: write question 1 Reporter.log("enter question 3 with C", true);
		steps.enterqQuestion("C");
		Assert.assertTrue(steps.confirmQInput("C"), "The question is not C");

		sleep(2000);

		// Step 21: click next button to fill answers steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a3Text),
				"This is not Answers screen for question 3");

		// Step 22: fill answer A1 -- first line steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0), "Answer in line 1 is not A");

		// Step 23: fill answer A2 -- second line steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 24: fill answer A3 -- third line steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 25: fill answer A4 -- fourth line steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not A");

		// Step 26: check first line as correct answer steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1");

		sleep(2000);

		// Step 27: click back for question 2 steps.clicBackQ(); sleep(1000);
		SoftAssert softAssert = new SoftAssert(); // soft assert for many fails

		softAssert.assertTrue(steps.confirmText(qText) && steps.confirmText(q2Text), "This is not question 2 screen");

		// Step 28: click back again to try to get to question 2 steps.clicBackQ();
		sleep(1000);
		softAssert.assertTrue(steps.confirmText(qText) && steps.confirmText(q2Text), "This is not question 2 screen");

		softAssert.assertAll();

	}

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" })
	public void answersAfterLastQBack() {

		GameSteps steps = new GameSteps(driver);

		Reporter.log("Check answers of last question after back button press", true);

		// Step 2: open the game site steps.openPage(); sleep(3000);
		Assert.assertEquals(steps.getPageTitle(), SiteTitle, "Error: url is wrong");

		// Step 3: click on start button steps.startGame();
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q1Text), "Wrong screen - not question screen");

		// Step 4: write question 1 Reporter.log("enter question 1 with A", true);
		steps.enterqQuestion("A");
		Assert.assertTrue(steps.confirmQInput("A"), "The question is not A");

		sleep(2000);

		// Step 5: click next button to fill answers steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a1Text),
				"This is not Answers screen for question 1");

		// Step 6: fill answer A1 -- first line steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0), "Answer in line 1 is not A");

		// Step 7: fill answer A2 -- second line steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 8: fill answer A3 -- third line steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 9: fill answer A4 -- fourth line steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not D");

		// Step 10: check first line as correct answer steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1");

		sleep(2000);

		// Step 11: click next button for question 2 steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q2Text),
				"Wrong screen - not question 2 screen");

		// Step 12: write question 1 Reporter.log("enter question 2 with B", true);
		steps.enterqQuestion("B");
		Assert.assertTrue(steps.confirmQInput("B"), "The question is not B");

		sleep(2000);

		// Step 13: click next button to fill answers steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a2Text),
				"This is not Answers screen for question 2");

		// Step 14: fill answer A1 -- first line steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0), "Answer in line 1 is not A");

		// Step 15: fill answer A2 -- second line steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 16: fill answer A3 -- third line steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 17: fill answer A4 -- fourth line steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not A");

		// Step 18: check first line as correct answer steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1");

		// Step 19: click next button for question 2 steps.clickQNext(); sleep(1000);
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q3Text),
				"Wrong screen - not question 3 screen");

		// Step 20: write question 1 Reporter.log("enter question 3 with C", true);
		steps.enterqQuestion("C");
		Assert.assertTrue(steps.confirmQInput("C"), "The question is not C");

		sleep(2000);

		// Step 21: click next button to fill answers steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a3Text),
				"This is not Answers screen for question 3");

		// Step 22: fill answer A1 -- first line steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0), "Answer in line 1 is not A");

		// Step 23: fill answer A2 -- second line steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 24: fill answer A3 -- third line steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 25: fill answer A4 -- fourth line steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not A");

		// Step 26: check first line as correct answer steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1");

		sleep(2000);

		// Step 27: click back for question 2 steps.clicBackQ(); sleep(1000);
		SoftAssert softAssert = new SoftAssert(); // soft assert for many fails

		softAssert.assertTrue(steps.confirmText(qText) && steps.confirmText(q2Text), "This is not question 2 screen");

		if (steps.confirmText(aText) && steps.confirmText(a3Text)) {
			// we are in answers screen for question3
			// Step 28: check answers
			softAssert.assertTrue(steps.confirmAInput("A", 0), "Answer in line 1 for Q3 is not A");
			softAssert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 for Q3 is not B");
			softAssert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 for Q3 is not C");
			softAssert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 for Q3 is not D");
		}

		softAssert.assertAll();

	}

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" })
	public void randomQ() {
		GameSteps steps = new GameSteps(driver);

		Reporter.log("Are the game questions random?", true);

		Reporter.log("to be sure we will check 3 times", true);

		// Step 2: open the game site
		steps.openPage();
		sleep(3000);
		Assert.assertEquals(steps.getPageTitle(), SiteTitle, "Error: url is wrong");

		// Step 3: click on start button
		steps.startGame();
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q1Text), "Wrong screen - not question screen");

		// Step 4: write question 1
		Reporter.log("enter question 1 with A", true);
		steps.enterqQuestion("A");
		Assert.assertTrue(steps.confirmQInput("A"), "The question is not A");
		steps.add2stackQ("A");
		sleep(2000);

		// Step 5: click next button to fill answers
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a1Text),
				"This is not Answers screen for question 1");

		// Step 6: fill answer A1 -- first line
		steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0), "Answer in line 1 is not A");

		// Step 7: fill answer A2 -- second line
		steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 8: fill answer A3 -- third line
		steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 9: fill answer A4 -- fourth line
		steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not D");

		// Step 10: check first line as correct answer
		steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1");

		sleep(2000);

		// Step 11: click next button for question 2
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q2Text),
				"Wrong screen - not question 2 screen");

		// Step 12: write question 1
		Reporter.log("enter question 2 with B", true);
		steps.enterqQuestion("B");
		Assert.assertTrue(steps.confirmQInput("B"), "The question is not B");
		steps.add2stackQ("B");
		sleep(2000);

		// Step 13: click next button to fill answers
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a2Text),
				"This is not Answers screen for question 2");

		// Step 14: fill answer A1 -- first line
		steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0), "Answer in line 1 is not A");

		// Step 15: fill answer A2 -- second line
		steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 16: fill answer A3 -- third line
		steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 17: fill answer A4 -- fourth line
		steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not A");

		// Step 18: check first line as correct answer
		steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1");

		// Step 19: click next button for question 2
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q3Text),
				"Wrong screen - not question 3 screen");

		// Step 20: write question 1
		Reporter.log("enter question 3 with C", true);
		steps.enterqQuestion("C");
		Assert.assertTrue(steps.confirmQInput("C"), "The question is not C");
		steps.add2stackQ("C");
		sleep(2000);

		// Step 21: click next button to fill answers
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a3Text),
				"This is not Answers screen for question 3");

		// Step 22: fill answer A1 -- first line
		steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0), "Answer in line 1 is not A");

		// Step 23: fill answer A2 -- second line
		steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1), "Answer in line 2 is not B");

		// Step 24: fill answer A3 -- third line
		steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2), "Answer in line 3 is not C");

		// Step 25: fill answer A4 -- fourth line
		steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3), "Answer in line 4 is not A");

		// Step 26: check first line as correct answer
		steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1");

		sleep(2000);

		// Step 27: click next button to start playing
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(playText), "Wrong screen - not Let's play screen");

		/********************************************************************************************/
		/*****************************************
		 * Let's Play
		 ***************************************/
		/******************************************************************************************/

		// int i = 3;
		boolean r = false;
		String Q = "", stackCurrQ = "";

		// Click Play button
		steps.clickPlay();
		sleep(2000);
		Reporter.log("Let us play!!", true);
		Assert.assertTrue(steps.confirmText(playStartText), "Play game did not start");

		for (int j = 0; j < 3; j++) { // 3 tries
			Reporter.log("Try number: " + (j + 1), true);
			Reporter.log("Stack is => " + steps.stackQ, true);
			for (int i = 3; i >= 1; i--) { // 3 questions

				// confirm question to be random
				Q = steps.getQuestion(i - 1).toString().trim().toUpperCase();
				stackCurrQ = steps.getFromStackQ().toString().trim().toUpperCase();
				Reporter.log("Q = " + Q + "\nstackCurrQ = " + stackCurrQ, true);

				if (!Q.matches(stackCurrQ)) {
					r = true;
					Reporter.log("r = " + r, true);
					break;
				}

				// check right answer to be the first
				steps.checkAnswerPlay(0);
				sleep(1000);
				Assert.assertTrue(steps.confirmAPlayChecked(0), "Correct answer is not at line 1");
				Reporter.log("Answer checked on line: 1", true);
				sleep(2000);

				// Step 30: click next button
				steps.clickANext();
				sleep(2000);
				Assert.assertTrue(steps.confirmText(playStartText), "You are not in play mode");

				Reporter.log("i is " + i, true);

			}

			if (r) {// if found random don't try again
				break;
			}
			if (j < 2) {
				// add the setup question to stack
				steps.add2stackQ("A");
				steps.add2stackQ("B");
				steps.add2stackQ("C");
				steps.setqNum(2);

				// try to play again
				steps.clickTryAgain();
				sleep(2000);
				Reporter.log("Let us play!!", true);
				Assert.assertTrue(steps.confirmText(playStartText), "Play game did not start");
			}

		}

		Assert.assertTrue(r, "Questions are not random");

	}

}
