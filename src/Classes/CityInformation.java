package Classes;
import java.util.ArrayList;

import Utility.CoordinateConversion;


public class CityInformation {
	private String cityName;
	private double lattitude;
	private double longitude;
	private ArrayList<Connection> connections;
	
	public CityInformation(String name, String lat, String lon) {
		cityName = name;
		lattitude = CoordinateConversion.convertLattitude(lat);
		longitude = CoordinateConversion.convertLongitude(lon);
		connections = new ArrayList<Connection>();
	}
	
	public String toString() {
		String answer = "City: " + cityName + "\n\tLat: " + lattitude + "\n\tLon: " + longitude + "\n\tConnections:\n";
		if (connections.size() == 0) {
			answer += "\t\tNo connections to this city.\n";
		} else {
		    for (Connection connect: connections) {
		    	if (connect.getCity1().getCityName().compareTo(cityName) != 0)
		    		answer += "\t\t" + connect.getCity1().getCityName() + "\n";
		    	else
		    		answer += "\t\t" + connect.getCity2().getCityName() + "\n";
		    }
		}
		
		return answer;
	}

	public String getCityName() {
		return cityName;
	}

	public double getLattitude() {
		return lattitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public ArrayList<Connection> getConnections() {
		return connections;
	}
	
	public void addConnection(Connection connection) {
		connections.add(connection);
		
	}
}
