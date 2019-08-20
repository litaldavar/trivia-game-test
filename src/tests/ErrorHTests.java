package tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

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

		// Step 10: don't check answer but click next button
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.isAlert(), "Error message didn't show - no answer was checked");
	}

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" } )
	public void overMaxCharsQ() {
		GameSteps steps = new GameSteps(driver);

		Reporter.log("Over max characters in a question", true);

		steps.openPage();
		sleep(3000);
		Assert.assertEquals(steps.getPageTitle(), SiteTitle, "Error: url is wrong");

		// Step 3: click on start button
		steps.startGame();
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q1Text), "Wrong screen - not question screen");

		Reporter.log("entered question: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" , true );
		// Step 4: write question 1
		Reporter.log("enter question 1 with A", true);
		steps.enterqQuestion("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // 51 Characters
		sleep(1000);
		Assert.assertTrue(steps.confirmQInput("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"), "The question is not AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		

		// Step 5: click next button to fill answers
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmQInput("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA") , "Error message didn't show - no error message when max characters in question is over 50");
		/*
		 * Assert.assertTrue(steps.isAlert(),
		 * "Error message didn't show - no error message when max characters in question is over 50"
		 * );
		 */
	}

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" }  )
	public void numberAsAnswer() {

		GameSteps steps = new GameSteps(driver);

		Reporter.log("number as an answer in setup", true);

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

		sleep(2000);

		// Step 5: click next button to fill answers
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a1Text),
				"This is not Answers screen for question 1");

		Reporter.log("fill answer as 1" , true);
		// Step 6: fill answer A1 -- first line
		steps.fillAnswer("1", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("1", 0), "Answer in line 1 is not 1");

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
		Assert.assertTrue(steps.isAlert(),
				"Error message didn't show - no error message when answer in not in characters alone");
	}

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" }  )
	public void changeAnswerInSetup() {
		GameSteps steps = new GameSteps(driver);

		Reporter.log("Change selection of answer in setup stage", true);

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

		// Step 10: check second line as correct answer
		steps.checkAnswer(1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(1), "Correct answer is not at line 2");

		sleep(2000);

		// Step 11: check third line as correct answer
		steps.checkAnswer(2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(2), "Correct answer is not at line 3");

		sleep(2000);

		// Step 12: check fourth line as correct answer
		steps.checkAnswer(3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(3), "Correct answer is not at line 4");

		sleep(2000);

		// Step 13: click next button for question 2
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q2Text),
				"Wrong screen - not question 2 screen");

	}

	@Test(groups = { "error-handling" }, dependsOnGroups = { "sanity" } )
	public void skipFillingAQuestion() {
		GameSteps steps = new GameSteps(driver);

		Reporter.log("Skip filling a question", true);

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

		// Step 12: click next button without filling question
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.isAlert(), "Error message didn't show - A question wasn't inserted");

	}

}
