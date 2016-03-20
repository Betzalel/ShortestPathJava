package Tests;

import Utility.*;

/* Basic tests conducted successfully 11 March 2014 by Robert Houck for both
 * CoordinateConversion and SephericalGeometry.
 * 
 */

public class CoordinateConverterTest {

	public static void main(String[] args) {
		String test1Lat = "81:22:31 S";
		String test1Lon = "100:23:33 W";
		
		String test2Lat = "0:23:59 N";
		String test2Lon = "90:23:33 E";
		
		double one = CoordinateConversion.convertLattitude(test1Lat);
		double two = CoordinateConversion.convertLongitude(test1Lon);
		double three = CoordinateConversion.convertLattitude(test2Lat);
		double four = CoordinateConversion.convertLongitude(test2Lon);
		
		System.out.print("One: " + one + "\nTwo: " + two + "\n");
		System.out.print("Three: " + three + "\nFour: " + four + "\n");
		
		double bearing = SphericalGeometry.cityBearing(one, three, two, four);
		double distance = SphericalGeometry.cityDistance(one, three, two, four);
		
		System.out.print("Bearing: " + bearing + "\nDistance: " + distance + "\n");
	}

}
