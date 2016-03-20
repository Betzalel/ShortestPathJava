package StatusSideBar;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class renders the Status Bar which will update information as 
 * Dijkstra's algorithm travels from city to city. 
 * @author Shelley Klinzing
 */
public class StatusBarView extends JPanel {
	private StatusBarModel model;

	/**
	 * Constructor for the Status Bar Viewer
	 */
	public StatusBarView() {
	   final Color WINDOW_COLOR = new Color(196, 187, 201);
	   
		/* NORTH:
		 * Status Bar
		 */
		this.setLayout(new GridLayout(2, 2)); /* create the layout */
		this.setBackground(WINDOW_COLOR); /* set the background color */
		JLabel label = new JLabel("Please select a starting " +
				"city and a destination."); /* create the label */
		this.add(label); /* add the label */
		
		/* create the model */
		model = new StatusBarModel(label);
	}
	
	/**
	 * Getter for the StatusBarModel
	 * @return the StatusBarModel corresponding to the StatusBarView
	 */
	public StatusBarModel getModel() {
		return model;
	}
}