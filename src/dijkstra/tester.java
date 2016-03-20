package dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Classes.CityInformation;
import Classes.Connection;
import Utility.StructureHandler;

public class tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		StructureHandler strucHandl = StructureHandler.getStructureHandler();
		HashMap<String, CityInformation> hm = strucHandl.getCityList();
		System.out.println("\n******\n");
		String startCity = "San Antonio";
		String endCity = "Birmingham";
		Dijkstra dij = new Dijkstra(hm, hm.get(startCity), hm.get(endCity));
		
		Vertex start = dij.getVerts().get(dij.getStartCity().getCityName());
		Vertex end = dij.getVerts().get(dij.getEndCity().getCityName());
		
		System.out.println("Distance From " + start.cityName + " to " + end.cityName +": " + end.minDistance);
		System.out.print("\tPath: ");
		for (CityInformation city : dij.getShortestPath()){
			System.out.print(city.getCityName() + ", ");	
		}
		System.out.println();
		
		startCity = "Lagos";
		endCity = "Buenos Aires";

		dij = new Dijkstra(hm, hm.get(startCity), hm.get(endCity));
		
		start = dij.getVerts().get(dij.getStartCity().getCityName());
		end = dij.getVerts().get(dij.getEndCity().getCityName());
		
		System.out.println("Distance From " + start.cityName + " to " + end.cityName +": " + end.minDistance);
		System.out.print("\tPath: ");
		for (CityInformation city : dij.getShortestPath()){
			System.out.print(city.getCityName() + ", ");	
			System.out.println("\nChoices Made:");
			for (Connection c : city.getConnections()){
				if (c.getCity1().getCityName().equals(city.getCityName())){					
					System.out.println("\t" + c.getCity2().getCityName());
				}
				else{
					System.out.println("\t" + c.getCity1().getCityName());
				
				}
			}
		}
		System.out.println();
		
		Iterator it = dij.getTraversal().entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pairs = (Map.Entry)it.next();
			CityInformation from = (CityInformation) pairs.getKey();
			System.out.println(from.getCityName() + "--->");
			ArrayList<CityInformation> toCities = (ArrayList<CityInformation>) pairs.getValue();
			for (CityInformation c : toCities){
				System.out.println("\t" + c.getCityName());
			}
			it.remove();
		}
	}

}
