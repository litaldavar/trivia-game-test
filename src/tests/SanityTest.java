package tests;



import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Steps.GameSteps;
import tests.TestUtilities;

public class SanityTest extends TestUtilities {

	private String qText = "Please type here your question";
	private String aText = "Please enter 4 possible answers and Mark the right one! ";
	private String playText = "You finished to build the test - lets play!!";
	private String playStartText = "Test";
	private String q1Text = " question number: 1";	
	private String a1Text = " question number: 1";
	private String q2Text = " question number: 2";
	private String a2Text = " question number: 2";
	private String q3Text = " question number: 3";
	private String a3Text = " question number: 3";
	
	private String SiteTitle = "SVCOLLEGE TEST";
	
	@Test
	public void sanityTest()  {
		GameSteps steps = new GameSteps(driver);
		
		
		//Step 2: open the game site
		steps.openPage();
		sleep(3000);
		Assert.assertEquals(steps.getPageTitle(),SiteTitle ,"Error: url is wrong");
		
		//Step 3: click on start button
		steps.startGame();
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q1Text) , "Wrong screen - not question screen");
		
		//Step 4: write question 1
		Reporter.log("enter question 1 with A" , true);
		steps.enterqQuestion("A");
		Assert.assertTrue(steps.confirmQInput("A") , "The question is not A");
		
		sleep(2000);
		
		//Step 5: click next button to fill answers
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a1Text), "This is not Answers screen for question 1");
			
		//Step 6: fill answer A1 -- first line 
		steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0) , "Answer in line 1 is not A");
		
		//Step 7: fill answer A2 -- second line
		steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1) , "Answer in line 2 is not B");
		
		// Step 8: fill answer A3 -- third line
		steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2) , "Answer in line 3 is not C");
		
		// Step 9: fill answer A4 -- fourth line
		steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3) , "Answer in line 4 is not D");
		
		//Step 10: check first line as correct answer
		steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1" );
		
		sleep(2000);
		
		//Step 11: click next button for question 2
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q2Text), "Wrong screen - not question 2 screen");
		
		//Step 12: write question 1
		Reporter.log("enter question 2 with B" , true);
		steps.enterqQuestion("B");
		Assert.assertTrue(steps.confirmQInput("B") , "The question is not B");
		
		sleep(2000);
		
		//Step 13: click next button to fill answers
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a2Text), "This is not Answers screen for question 2");
					
		//Step 14: fill answer A1 -- first line 
		steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0) , "Answer in line 1 is not A");
				
		//Step 15: fill answer A2 -- second line
		steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1) , "Answer in line 2 is not B");
				
		// Step 16: fill answer A3 -- third line
		steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2) , "Answer in line 3 is not C");
				
		// Step 17: fill answer A4 -- fourth line
		steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3) , "Answer in line 4 is not A");
				
		//Step 18: check first line as correct answer
		steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1" );
		
		//Step 19: click next button for question 2
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(qText) && steps.confirmText(q3Text), "Wrong screen - not question 3 screen");
				
		//Step 20: write question 1
		Reporter.log("enter question 3 with C" , true);
		steps.enterqQuestion("C");
		Assert.assertTrue(steps.confirmQInput("C") , "The question is not C");
						
		sleep(2000);
		
		//Step 21: click next button to fill answers
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(aText) && steps.confirmText(a3Text), "This is not Answers screen for question 3");
					
		//Step 22: fill answer A1 -- first line 
		steps.fillAnswer("A", 0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("A", 0) , "Answer in line 1 is not A");
				
		//Step 23: fill answer A2 -- second line
		steps.fillAnswer("B", 1);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("B", 1) , "Answer in line 2 is not B");
				
		// Step 24: fill answer A3 -- third line
		steps.fillAnswer("C", 2);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("C", 2) , "Answer in line 3 is not C");
				
		// Step 25: fill answer A4 -- fourth line
		steps.fillAnswer("D", 3);
		sleep(1000);
		Assert.assertTrue(steps.confirmAInput("D", 3) , "Answer in line 4 is not A");
				
		//Step 26: check first line as correct answer
		steps.checkAnswer(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAChecked(0), "Correct answer is not at line 1" );
		
		sleep(2000);
		
		//Step 27: click next button to start playing
		steps.clickQNext();
		sleep(1000);
		Assert.assertTrue(steps.confirmText(playText) , "Wrong screen - not Let's play screen");
		
		/********************************************************************************************/
		/***************************************** Let's Play***************************************/
		/******************************************************************************************/
		
		//Step 28: Click Play button
		steps.clickPlay();
		sleep(2000);
		Reporter.log("Let us play!!", true);
		Assert.assertTrue(steps.confirmText(playStartText), "Play game did not start");
		
		//Step 29: check right answer to be the first
		steps.checkAnswerPlay(0);
		sleep(1000);
		Assert.assertTrue(steps.confirmAPlayChecked(0), "Correct answer is not at line 1" );
		Reporter.log("Answer checked on line: 1", true);
		sleep(2000);
		
		//Step 30: click next button
		steps.clickANext();
		sleep(2000);
		Assert.assertTrue(steps.confirmText(playStartText), "You are not in play mode");
		
		//Step 31: check right answer to be the first
		steps.checkAnswerPlay(0);
		//answertest1
		sleep(1000);
		Assert.assertTrue(steps.confirmAPlayChecked(0), "Correct answer is not at line 1" );
		Reporter.log("Answer checked on line: 1", true);		
		sleep(2000);
				
		//Step 32: click next button
		steps.clickANext();
		sleep(2000);
		Assert.assertTrue(steps.confirmText(playStartText), "You are not in play mode");
		
		//Step 33: check right answer to be the first
		steps.checkAnswerPlay(0);
		//answertest0
		sleep(1000);
		Assert.assertTrue(steps.confirmAPlayChecked(0), "Correct answer is not at line 1" );
		Reporter.log("Answer checked on line: 1", true);
		
		sleep(2000);
				
		//Step 34: click next button
		steps.clickANext();
		sleep(2000);
		Assert.assertTrue(steps.confirmText("Try again"), "Game not finished");
		Reporter.log("Sanity test finished!!",true);		
				
		
	}
	
	
	

}
