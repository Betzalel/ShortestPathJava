/**
 * 
 */
package dijkstra;


import java.util.*;

import Classes.CityInformation;

/**
 * @author richardhampton
 *
 */
public class Vertex implements Comparable<Vertex> {
	/**
	 * Current observed city information, so as to provide
	 * ease with possible future classes.
	 */
	public final CityInformation cityInfo;
	/**
	 * Current observed city name
	 */
	public final String cityName;
	/**
	 * A structure to hold the list of connections and associated weights.
	 */
	public ArrayList<Edge> adjancies;
	/**
	 * Setting initial minimum distance to object to infinity.
	 */
	public double minDistance = Double.POSITIVE_INFINITY;
	/**
	 * This will be used to walk backwards to obtain shortest path's connected cities.
	 */
	public Vertex previous;

	/**
	 * Constructor initializes all instance variables.
	 * @param city The current observed city object.
	 */
	public Vertex(CityInformation city){ 
		cityInfo = city;
		cityName = city.getCityName();
		adjancies = new ArrayList<Edge>();
	}
	
	/**
	 * Prints representation of object to standard out.
	 */
	public String toString(){ 
		return "CityName: " + cityInfo + "\nAdjancies: " + adjancies.toString(); 
	}
	
	/**
	 * Interface implementation for comparing Comparable.
	 */
	public int compareTo(Vertex other){
		return Double.compare(minDistance, other.minDistance);
	}

}
