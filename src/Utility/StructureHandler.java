package Utility;

import Classes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Generates and holds the structure of cities and their connections.
 * @author houckrj
 *
 */
public class StructureHandler {
	
	/**
	 * StructureHandler to ensure each unit has the same StructureHandler
	 */
	private static StructureHandler instance = null;
	
	/**
	 * A HashMap<String, CityInformation> that uses the city name as the key.
	 */
	private HashMap<String, CityInformation> cityList;
	
	/**
	 * List of elements that want to be updated when changes are made to the structure.
	 */
	private ArrayList<UpdateData> updateList = null;
	
	/**
	 * Initializes cityList and generates the structure.
	 */
	private StructureHandler() {
		updateList = new ArrayList<UpdateData>();
		cityList = new HashMap<String, CityInformation>();
		generateCityListFromFile();		
		generateConnectionsFromFile();
	}

   public static StructureHandler getStructureHandler() {
      if(instance == null)
         instance = new StructureHandler();
      return instance;
   }
	
	/**
	 * Provides the toString of each CityInformation.
	 */
	public String toString() {
		String returnString = "";
		
		Set<String> keys = cityList.keySet();
		
		for (String key: keys) {
			returnString += cityList.get(key).toString();
		}
		
		return returnString;
	}
	
	/**
	 * Get the current HashMap for use.
	 * @return
	 */
	public HashMap<String, CityInformation> getCityList() {
		return cityList;
	}
	
	/**
	 * Adds a listener to be updated when the structure changes.
	 * @param element
	 */
	public void addListener(UpdateData element) {
		updateList.add(element);
	}
	
	/**
	 * Removes a listener.
	 * @param element
	 */
	public void removeListner(UpdateData element) {
		updateList.remove(element);
	}
	
	/**
	 * Adds a CityInformation to the structure.
	 * @param city
	 * @return
	 */
	public int addCity(CityInformation city) {
		if (cityList.containsKey(city.getCityName())) {
			System.out.println("City already in cityList");
			return 1;
		}
		cityList.put(city.getCityName(), city);
		updateListeners();
		return 0;
	}
	
	/**
	 * Adds a Connection to two cities.
	 * @param city1
	 * @param city2
	 * @return
	 */
	public int addConnection(String city1, String city2) {
		if (!cityList.containsKey(city1) || !cityList.containsKey(city2)) {
			System.out.println("One city is not in the cityList");
			return 1;
		}
		
		CityInformation info1 = cityList.get(city1);
		CityInformation info2 = cityList.get(city2);
		
		ArrayList<Connection> tempConn = info1.getConnections();
		
		for (Connection element : tempConn) {
			if (element.getCity1().getCityName().equals(city2) || element.getCity2().getCityName().equals(city2)) {
				System.out.println("Connection Already Exists");
				return 1;
			}
		}
		
		new Connection(info1, info2);
		
		updateListeners();
		return 0;
	}
	
	/**
	 * Remove a city from the HashMap by the city name.
	 * Also removes all connections related to the city.
	 * @param cityName
	 * @return
	 */
	public int removeCity(String cityName) {
		CityInformation temp = cityList.get(cityName);
		ArrayList<Connection> cons = temp.getConnections();
		ArrayList<String> list = new ArrayList<String>();
		
		
		for (Connection con : cons) {
			if (con.getCity1().getCityName().equals(cityName)) {
				list.add(con.getCity2().getCityName());
			} else {
				list.add(con.getCity1().getCityName());
			}
		}
		
		for (String city : list) {
			internalRemoveConnection(cityName, city);
		}
		
//		For Java 8
//		if(cityList.remove(cityName, temp)) {
//			System.out.println(cityName + " removed");
//			return 0;
//		} else {
//			return 1;
//		}
//		For Java 7 and before
		cityList.remove(cityName);
		updateListeners();
		return 0;
	}
	
	public ArrayList<CityInformation> cityListAsArrayList() {
		ArrayList<CityInformation> temp = new ArrayList<CityInformation>();
		Set<String> cityNames = cityList.keySet();
		
		for (String city : cityNames) {
			temp.add(cityList.get(city));
		}
		
		return temp;
	}
	
	/**
	 * Remove a connection by the names of the connecting cities.
	 * Does not update listeners.
	 * @param cityName1
	 * @param cityName2
	 * @return
	 */
	private int internalRemoveConnection(String cityName1, String cityName2) {
		CityInformation temp = cityList.get(cityName1);
		ArrayList<Connection> cons = temp.getConnections();
		
		int successes = 0;
	
		for (Connection con : cons) {
			if (con.getCity1().getCityName().equals(cityName2))  {
				cons.remove(con);
				temp = con.getCity1();
				successes++;
				break;
			} else if (con.getCity2().getCityName().equals(cityName2)) {
				cons.remove(con);
				temp = con.getCity2();
				successes++;
				break;
			}
		}
		
		cons = temp.getConnections();
		
		for (Connection con : cons) {
			if (con.getCity1().getCityName().equals(cityName1))  {
				cons.remove(con);
				temp = con.getCity1();
				successes++;
				break;
			} else if (con.getCity2().getCityName().equals(cityName1)) {
				cons.remove(con);
				temp = con.getCity2();
				successes++;
				break;
			}
		}
		
		if (successes != 2) {		
			System.err.println("Connection not found: " + cityName1 + " " + cityName2 + " Times Found: " + successes);
			return 1;
		} else {
			return 0;
		}
	}
		
	/**
	 * Remove a connection by the names of the connecting cities.
	 * @param cityName1
	 * @param cityName2
	 * @return
	 */
	public int removeConnection(String cityName1, String cityName2) {
		CityInformation temp = cityList.get(cityName1);
		ArrayList<Connection> cons = temp.getConnections();
		
		int successes = 0;
	
		for (Connection con : cons) {
			if (con.getCity1().getCityName().equals(cityName2))  {
				cons.remove(con);
				temp = con.getCity1();
				successes++;
				break;
			} else if (con.getCity2().getCityName().equals(cityName2)) {
				cons.remove(con);
				temp = con.getCity2();
				successes++;
				break;
			}
		}
		
		cons = temp.getConnections();
		
		for (Connection con : cons) {
			if (con.getCity1().getCityName().equals(cityName1))  {
				cons.remove(con);
				temp = con.getCity1();
				successes++;
				break;
			} else if (con.getCity2().getCityName().equals(cityName1)) {
				cons.remove(con);
				temp = con.getCity2();
				successes++;
				break;
			}
		}
	
			
		if (successes != 2) {		
			System.err.println("Connection not found: " + cityName1 + " " + cityName2 + " Times Found: " + successes);
			return 1;
		} else {
			updateListeners();
			return 0;
		}
	}
			

	@SuppressWarnings("unused")
	private void generateCityListFromFile() {
		// generate the cities
		FileOperations.setReadFile(0);
		String buff;
		while((buff = FileOperations.getNextLine()) != null) {
			String temp[] = buff.split(",");
			
			if (temp.length != 3) {
				System.err.println("temp not proper length: "+ temp.length + " " + buff);
			}
			CityInformation cityTemp = new CityInformation(temp[0], temp[1], temp[2]);
		
			if (cityTemp == null) {
				System.err.println("CityInformation creation failed: " + buff);
			}
			
			if (cityList.get(cityTemp.getCityName()) == null) {
				cityList.put(cityTemp.getCityName(), cityTemp);
			} else {
				System.err.println("Duplicate entry for " + cityTemp.getCityName() + ". Keeping first entry.");
			}
		}
	}
	
	/**
	 * Uses FileOperations to get information from file to build
	 * Connection and add them to the proper CityInformation.
	 */	
	private void generateConnectionsFromFile() {
		if (cityList.size() < 2) {
			System.err.println("No connctions can be made.");
			return;
		}
		
		FileOperations.setReadFile(1);
		String buff;
		while((buff = FileOperations.getNextLine()) != null) {
			String temp[] = buff.split(",");
			
			if (temp.length != 2) {
				System.err.println("temp not proper length: " + buff);
			}
			
			CityInformation city1 = cityList.get(temp[0]);
			CityInformation city2 = cityList.get(temp[1]);
			
			if (city1 == null) {
				System.err.println("City missing from HashMap: " + temp[0] + "\t(" + temp[1] + ")");
			}
			
			if (city2 == null) {
				System.err.println("City missing from HashMap: " + temp[1] + "\t(" + temp[0] + ")");
			}
			
			if (city1 == null || city2 == null) { continue;}
			
			new Connection(city1, city2);
			
		}
	}
	
	/**
	 * Updates added listeners that the structure has changed.
	 */
	private void updateListeners() {
		if (updateList == null) {
			return;
		}
		
		for (UpdateData element : updateList) {
			element.updateData();
		}
	}
	
}
