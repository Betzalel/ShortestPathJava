package StatusSideBar;

import java.util.HashMap;

import Utility.StructureHandler;
import Classes.*;

/**
 * This class is the Model class for the Side Bar. 
 * @author Shelley Klinzing
 */
public class SelectionModel {
	/**
	 * For handling the CityInformation objects.
	 */
	StructureHandler s;
	
	/**
	 * Get the cities and put them in a HashMap.
	 */
	HashMap<String, CityInformation> hm;
	
	/**
	 * The name of the starting city.
	 */
	CityInformation start;
	
	/**
	 * The name of the ending city.
	 */
	CityInformation end;
	
	/**
	 * This takes the information passed from the Controller and passes it 
	 * to the Dijkstra GUI.
	 */
	public SelectionModel() {
		s = StructureHandler.getStructureHandler();
		hm = s.getCityList();
	}
	
	/**
	 * setter for the starting city
	 * @param begin the String name of the starting city.
	 */
	public void setStartCity(String begin) {
		start = hm.get(begin);
	}
	
	/**
	 * setter for the destination city.
	 * @param dest the String name of the destination city.
	 */
	public void setEndCity(String dest) {
		end = hm.get(dest);
	}
	
	/**
	 * getter for the starting city
	 * @return the CityInformation object of the starting city.
	 */
	public CityInformation getStartCity() {
		return start;
	}
	
	/**
	 * getter for the destination city
	 * @return the CityInformation object of the destination city.
	 */
	public CityInformation getEndCity() {
		return end;
	}
}