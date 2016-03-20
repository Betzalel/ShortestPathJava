package Utility;

/**
 * Provides needed methods to conduct spherical geometry on latitude and longitude.
 * @author houckrj
 *
 */
public class SphericalGeometry {
	/**
	 * The radius of earth.
	 * NOTE: This assumes a perfect circle, which the earth is not.
	 */
	private static int RADIUS = 6371; // earth radius in km
	
	/**
	 * Calculates the distance between two cities.
	 * @param city1Lat
	 * @param city2Lat
	 * @param city1Lon
	 * @param city2Lon
	 * @return
	 */
	public static double cityDistance(double city1Lat, double city2Lat, double city1Lon, double city2Lon) {
		double x, y, distance, deltaLat, deltaLong;
		
		// Convert to Radians
		city1Lat = toRadians(city1Lat);
		city2Lat = toRadians(city2Lat);
		city1Lon = toRadians(city1Lon);
		city2Lon = toRadians(city2Lon);
		
		// Get change in cities
		deltaLat = city2Lat - city1Lat;
		deltaLong = city2Lon - city1Lon;
		
		x = Math.sin(deltaLat/2) * Math.sin(deltaLat/2) + Math.sin(deltaLong/2) * Math.sin(deltaLong/2) * Math.cos(city1Lat) * Math.cos(city2Lat);
		y = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1-x));
		distance = RADIUS * y;
		
		return distance;
	}
	
	/**
	 * Calculates the bearing, from North, from city1 to city2.
	 * @param city1Lat
	 * @param city2Lat
	 * @param city1Lon
	 * @param city2Lon
	 * @return
	 */
	public static double cityBearing(double city1Lat, double city2Lat, double city1Lon, double city2Lon) {
		
		double x, y, bearing, deltaLong;
		
		// Convert to Radians
		city1Lat = toRadians(city1Lat);
		city2Lat = toRadians(city2Lat);
		city1Lon = toRadians(city1Lon);
		city2Lon = toRadians(city2Lon);
		
		// Get change in cities
		deltaLong = city2Lon - city1Lon;
		
		// Find Bearing
		y = Math.sin(deltaLong) * Math.cos(city2Lat);
		x = Math.cos(city1Lat) * Math.sin(city2Lat) - Math.sin(city1Lat) * Math.cos(city2Lat) * Math.cos(deltaLong);
		bearing = Math.atan2(y, x);
		
		bearing = toDegrees(bearing);
		
		if (bearing < 0) {
			bearing = 360 + bearing;
		}
		
		return bearing;
	}
	
	/**
	 * Converts from degrees to radians.
	 * @param degrees
	 * @return
	 */
	private static double toRadians(double degrees) {
		return (degrees * (Math.PI / 180));
	}
	
	/**
	 * Converts from radians to degrees.
	 * @param radians
	 * @return
	 */
	private static double toDegrees(double radians) {
		return (radians * (180 / Math.PI));
	}
}
