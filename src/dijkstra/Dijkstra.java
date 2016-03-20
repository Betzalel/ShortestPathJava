/**
 * 
 */
package dijkstra;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import Classes.*;

/**
 * Djikstra's Algorithm
 * This class wil be used to find the shortest distance between two cities, using 
 * the idea of cities being vertexes, and edges being the connections between vertexes
 * with respective weight values signifying distance between vertexes.
 * 
 * @author richardhampton
 * 
 */
public class Dijkstra {

	/**
	 * A HashMap with city name for key, and Vertex object for respective values.
	 */
	private HashMap<String, Vertex> verts;
	/**
	 * City start is the origin for shortest path.
	 */
	private CityInformation cityStart;
	/**
	 * City end is the destination city for shortest path.
	 */
	private CityInformation cityEnd;
	/**
	 * Shortest path between two cities, comprised of CityInformation objects.
	 */
	private ArrayList<CityInformation> shortestPath;
	/**
	 * Traversal path while searching for shortestPath
	 */
	private HashMap<CityInformation, ArrayList<CityInformation>> traversal;
	/**
	 * Constructor for Dijkstra class, initializes all instance variables.
	 * @param graph HashMap with key being city name, and value being repsective cityInformation
	 * @param cityStart the starting city for shortest Path
	 * @param cityEnd the ending city for shortest path
	 */
	public Dijkstra(HashMap<String, CityInformation> graph, CityInformation cityStart, CityInformation cityEnd){
		verts = new HashMap<String, Vertex>();
		shortestPath = new ArrayList<CityInformation>();
		traversal = new HashMap<CityInformation,ArrayList<CityInformation>>();
		this.cityStart = cityStart;
		this.cityEnd = cityEnd;
		setVerts(graph);
		setEdges();
		findShortestPath();
		setShortestPath(verts.get(cityEnd.getCityName()));
	}

	/**
	 * Sets up the verticies for a graph structure based on the Cities found in 
	 * param graph 
	 * @param graph HashMap with key being city name, and value being repsective cityInformation
	 */
	private void setVerts(HashMap<String, CityInformation> graph){
		for (Map.Entry<String, CityInformation> entry : graph.entrySet()){
			if (entry != null){
				verts.put(entry.getKey(), new Vertex(entry.getValue()));

			}
		}

	}

	/**
	 * This will set up the connections of each vertex. The respective Vertex objects 
	 * adjacencies are updated with the matching Vertex object from the verts instance 
	 * variable.  
	 */
	private void setEdges(){
		Edge edge = null;
		Vertex vert = null;	
		for (Map.Entry<String, Vertex> entry : verts.entrySet()){	
			for (Connection c : entry.getValue().cityInfo.getConnections()){				
				if (c.getCity1().getCityName().equals(entry.getKey())){					
					edge = new Edge(verts.get(c.getCity2().getCityName()), c.getDistance());
					verts.get(entry.getKey()).adjancies.add(edge);
				}
				else{
					edge = new Edge(verts.get(c.getCity1().getCityName()), c.getDistance());
					verts.get(entry.getKey()).adjancies.add(edge);				
				}	
			}
		}
	}

	/**
	 * Using priority queue, and traverses with no back track.
	 * The next Vertex's previous instance variable is updated with
	 * the previous Vertex that has the running shortest distance. 
	 */
	private void findShortestPath(){
		Vertex start = verts.get(cityStart.getCityName());
		Vertex end = verts.get(cityEnd.getCityName());
		start.minDistance = 0.;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(start);
		Vertex vertex = null;	
		Vertex v = null;
		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();
			// Visit each edge exiting u
			ArrayList<CityInformation> toCities = new ArrayList<CityInformation>();
			for (Edge e : u.adjancies){
				v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);
					v.minDistance = distanceThroughU ;
					v.previous = u;
					vertexQueue.add(v);
					// send v to Path View to update information
				}
				toCities.add(v.cityInfo);
				if (v.cityName.equals(cityEnd.getCityName())) break;
			}
			traversal.put(u.cityInfo, toCities);

			if (v.cityName.equals(cityEnd.getCityName())) break;
		}

	}

	/**
	 * Returns shortestPath
	 * @return shortesPath ArrayList<CityInformation>
	 */
	public ArrayList<CityInformation> getShortestPath(){
		return shortestPath;
	}

	/**
	 * This function traverses the graph, starting at the cityEnd and walking
	 * backwards, setting shortestPath.
	 * @param target Vertex cityEnd
	 */
	public void setShortestPath(Vertex target){
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);
		Collections.reverse(path);
		for (Vertex city : path){
			shortestPath.add(city.cityInfo);
		}
	}

	/**
	 * Returns returns the HashMap verts
	 * @return verts HashMap<String, Vertex>
	 */
	public HashMap<String, Vertex> getVerts(){
		return verts;
	}

	/**
	 * Returns the starting city
	 * @return CityInformation cityStart
	 */
	public CityInformation getStartCity(){
		return cityStart;
	}

	/**
	 * Returns the ending city
	 * @return CityInformation cityEnd
	 */
	public CityInformation getEndCity(){
		return cityEnd;
	}
	
	public HashMap<CityInformation, ArrayList<CityInformation>> getTraversal(){
		return traversal;
	}
}
