package Tests;
import Classes.*;

/*
 * Basic tests conducted successfully 11 March 2014 by Robert Houck for both
 * CityInformation and Connection classes.
 * 
 */
public class CityInformationTest {

	public static void main(String[] args) {
		// City 1
		String c1Name = "San Antonio";
		String c1Lat = "33:21:48 S";
		String c1Lon = "55:53:11 E";
		
		// City 2
		String c2Name = "South Padre";
		String c2Lat = "44:32:53 N";
		String c2Lon = "00:34:11 W";
		
		CityInformation city1 = new CityInformation(c1Name, c1Lat, c1Lon);
		CityInformation city2 = new CityInformation(c2Name, c2Lat, c2Lon);
		
		System.out.println(city1);
		System.out.println(city2);
		
		Connection connection1 = new Connection(city1, city2);
		
		System.out.println(connection1);
		
		System.out.println(city1);
		System.out.println(city2);
		
		// City 3
		String c3Name = "Moscow";
		String c3Lat = "71:00:23 N";
		String c3Lon = "50:34:41 E";
		
		CityInformation city3 = new CityInformation(c3Name, c3Lat, c3Lon);
		
		new Connection(city1, city3);
		
		System.out.println(city1);
		System.out.println(city3);
	}

}
