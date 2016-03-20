package StatusSideBar;

import javax.swing.JLabel;

import Utility.StructureHandler;

/**
 * This Model updates the information in the Status Bar.
 * @author Shelley Klinzing
 */
public class StatusBarModel {
	/**
	 * This is the string to display on the Status Bar
	 */
	private String display = "";
	
	/**
	 * The JLabel to update with the status.
	 */
	private JLabel label;
	
	/**
	 * For handling the CityInformation objects.
	 */
	StructureHandler s;

	/**
	 * StatusBarModel constructor
	 */
	public StatusBarModel(JLabel label) {
		this.label = label;
	}

	/**
	 * Return the String value of the status bar to display.
	 * @return the StatusBar String
	 */
	public String getLabel() {
		return display;
	}
	
	public void update(String start, String dest) {
		label.setText("Mapping the shortest distance between " +
				start + " and " + dest + ".");
		/*
		 * This method will update the status bar. I will need to call a 
		 * method to get the current city in Dijikstra's algorithm. Once this 
		 * method is called, I will update the display string and display it 
		 * on the status bar. 
		 * 
		 * Will probably be easier for Dijkstra to call this method and update.
		 */
	}
}