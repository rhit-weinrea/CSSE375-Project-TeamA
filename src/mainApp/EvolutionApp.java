package mainApp;

public class EvolutionApp {

	public void runApp() {

		new EvolutionViewer();

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
