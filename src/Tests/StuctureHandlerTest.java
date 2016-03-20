package Tests;

import java.util.HashMap;

import Classes.CityInformation;
import Utility.StructureHandler;

public class StuctureHandlerTest {

	public static void main(String[] args) {
		StructureHandler strucHandl = StructureHandler.getStructureHandler();

		
		System.out.println("\n******\n");
		
		HashMap<String, CityInformation> hm = strucHandl.getCityList();
		
		CityInformation cairo = hm.get("Cairo");
		CityInformation lagos = hm.get("Lagos");
		CityInformation capTwn = hm.get("Cape Town");
		CityInformation birmin = hm.get("Birmingham");
		
		System.out.println("Remove Connection Test:");
		System.out.println(cairo);
		
		strucHandl.removeConnection("Cairo", "Lagos");
		
		System.out.println(cairo);
		System.out.println(lagos);
		
		System.out.println("Removing City Test:");
		System.out.println(lagos);
		System.out.println(capTwn);
		System.out.println(birmin);
		
		System.out.println();
		System.out.println("Removing Lagos");
		
		strucHandl.removeCity("Lagos");
		
		/*
		lagos = hm.get("Lagos");
		System.out.println(lagos);
		System.out.println(capTwn);
		System.out.println(birmin);
		*/
		
		System.out.println(hm);
		System.out.println();
		System.out.println("***************");
		System.out.println();
		
		System.out.println("Removing Houston");
		
		strucHandl.removeCity("Houston");
		
		System.out.println(hm);
		System.out.println();
		System.out.println("***************");
		System.out.println();
		
		System.out.println("Size of hm: " + hm.size());
		System.out.println("Number of Keys: " + hm.keySet().size());
		
		
	}

}
