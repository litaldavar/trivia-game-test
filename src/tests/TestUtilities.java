package tests;



public class TestUtilities extends BaseTests{
	
	// STATIC SLEEP 
		protected void sleep(long millis) {
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

}
