package mainApp;

/**
 * Class: MainApp
 * 
 * @author Wynesakia Akamah & Abby Weinreb <br>
 *         Purpose: Top level class for CSSE220 Project containing main method
 *         <br>
 *         Restrictions: None
 */
public class MainApp {

	public void runApp() {

		new OpeningScreenComp();

	} // runApp

	/**
	 * ensures: runs the application
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		MainApp mainApp = new MainApp();
		mainApp.runApp();
	} // main

}