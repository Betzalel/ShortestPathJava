/**
 * 
 */
package dijkstra;

/**
 * @author richardhampton
 *
 */
public class Edge {
	/**
	 * Connected city to the current observed city.
	 */
	public final Vertex target;
	/**
	 * The respective weight to the connected destination city.
	 */
	public final double weight;
	
	/**
	 * Constructor  initializes instance variables.
	 * @param argTarget The respective connected city from current observed city.
	 * @param argWeight The respective weight of connection.
	 */
	public Edge(Vertex argTarget, double argWeight){
		target = argTarget; 
		weight = argWeight; 
	}
}
