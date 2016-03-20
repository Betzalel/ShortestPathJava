package Utility;

import Classes.InvalidCoordinateException;

public class CoordinateConversion {
	public static double convertLongitude(String lonString) {
		String[] parts = lonString.split("[: ]");
		int deg = Integer.parseInt(parts[0]);
		int min = Integer.parseInt(parts[1]);
		int sec = Integer.parseInt(parts[2]);
		
		Double longitude = toDecDeg(deg, min, sec);
		
		if(longitude.compareTo(180.0) > 0) {
		   throw new InvalidCoordinateException("longitude cannot be greater than 180");
		}
		
		// If western hemisphere, make it negative
		if(parts[3].contains("W") || parts[3].contains("w")) {
			longitude *= -1;
		}
		
		return longitude;
	}
	
	public static double convertLattitude(String latString) throws InvalidCoordinateException {
		String[] parts = latString.split("[: ]");
		int deg = Integer.parseInt(parts[0]);
		int min = Integer.parseInt(parts[1]);
		int sec = Integer.parseInt(parts[2]);
		
		Double lattitude = toDecDeg(deg, min, sec);
		
		if (lattitude.compareTo(90.0) > 0) {
		   throw new InvalidCoordinateException("latitude cannot be greater than 90");
		}
		
		// If southern hemisphere, make it negative
		if(parts[3].contains("S") || parts[3].contains("s")) {
			lattitude *= -1;
		}
		
		return lattitude;
	}
	
	private static double toDecDeg(int deg, int min, int sec) {
		return (deg + ((double)((min * 60) + sec)/3600));
	}
}
