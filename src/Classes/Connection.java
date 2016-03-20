package Classes;
import Utility.SphericalGeometry;


public class Connection {
	private CityInformation city1, city2;
	private double distance, bearing1to2, bearing2to1;
	
	public Connection(CityInformation city1, CityInformation city2) {
		this.city1 = city1;
		this.city2 = city2;
		
		distance = SphericalGeometry.cityDistance(city1.getLattitude(), city2.getLattitude(), city1.getLongitude(), city2.getLongitude());
		bearing1to2 = SphericalGeometry.cityBearing(city1.getLattitude(), city2.getLattitude(), city1.getLongitude(), city2.getLongitude());
		bearing2to1 = SphericalGeometry.cityBearing(city2.getLattitude(), city1.getLattitude(), city2.getLongitude(), city1.getLongitude());
		
		addConnection();
	}
	
	public String toString() {
		String c1N = city1.getCityName();
		String c2N = city2.getCityName();
		String answer = "City 1: " + c1N + "\tCity 2: " + c2N + "\n";
		answer += "Bearing from " + c1N + " to " + c2N + ": " + bearing1to2 + "\n";
		answer += "Bearing from " + c2N + " to " + c1N + ": " + bearing2to1 + "\n";
		answer += "Distance: " + distance + "\n";
		
		return answer;
		
	}

	public CityInformation getCity1() {
		return city1;
	}

	public CityInformation getCity2() {
		return city2;
	}

	public double getDistance() {
		return distance;
	}

	public double getBearing1to2() {
		return bearing1to2;
	}

	public double getBearing2to1() {
		return bearing2to1;
	}
	
	private void addConnection() {
		city1.addConnection(this);
		city2.addConnection(this);
	}
	
	
}
