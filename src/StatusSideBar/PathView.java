package StatusSideBar;

import java.util.ArrayList;

import javax.swing.JPanel;

import Classes.CityInformation;
import Path.PathController;
import Utility.StructureHandler;

/**
 * This class will render the JPanel and PathController used in 
 * Dijkstra's algorithm.
 * @author Shelley Klinzing
 */
public class PathView {
	
	private PathController pathView;
	private ArrayList<CityInformation> cities;
	
	/**
	 * Create the StructureHandler
	 */
	StructureHandler s = StructureHandler.getStructureHandler();
	
	public PathView() {
		/* store the cities in an ArrayList<CityInformation> */
		cities = s.cityListAsArrayList();
		
		/* generate pathView to display Dijikstra */
		pathView = new PathController();//cities);
	}
	
	/**
	 * Return the JPanel so I can render the map in the driver.
	 * @return the JPanel containing the Dijkstra map
	 */
	public JPanel getPathView() {
		return pathView.getPanel(); 
	}
	
	/**
	 * Return the PathController so I can send it to the SelectionController.
	 * @return the PathController
	 */
	public PathController getPathController() {
		return pathView;
	}

}
