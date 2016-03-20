package Utility;

import java.io.*;
import Classes.*;

/**
 * Contains methods for file operations and identifying which file to use.
 * @author houckrj
 *
 */
public class FileOperations {
	/**
	 * The file containing the city information.
	 */
	private static String cityInfo = "CityInformation.txt";				// 0 when calling set...File()
	/**
	 * The file containing connection information.
	 */
	private static String connInfo = "ConnectionInformation.txt";		// 1 when calling set...File()
	
	/**
	 * The current BufferedReader active.
	 */
	private static BufferedReader readBuffer;
	/**
	 * The current BufferedWriter active.
	 */
	private static BufferedWriter writeBuffer;
	
	/**
	 * Used to set which file to be read from.
	 *      0: City Information file
	 *      1: Connection Information file
	 * @param type
	 * @return
	 */
	public static int setReadFile(int type) {
		if (type == 0) {
			return readInFile(cityInfo);
		} else if (type == 1) {
			return readInFile(connInfo);
		}
		
		return 1;
	}
	/**
	 * Used to set which file to be write to.
	 *      0: City Information file
	 *      1: Connection Information file
	 * @param type
	 * @return
	 */
	public static int setWriteFile(int type) {
		if (type == 0) {
			return writeOutFile(cityInfo);
		} else if (type == 1) {
			return writeOutFile(connInfo);
		}
		
		return 1;
	}
	
	/**
	 * Sets up the BufferedReader according to the set read file.
	 * @param fileName
	 * @return
	 */
	private static int readInFile(String fileName) {
		
		try {
			readBuffer = new BufferedReader(new FileReader(fileName));
			return 0;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 1;
	}
	
	/**
	 * Gets the next line from the current file being read from.
	 * @return
	 */
	public static String getNextLine() {
		try {
			return readBuffer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Sets up the BufferedWriter according to the set write file.
	 * @param fileName
	 * @return
	 */
	private static int writeOutFile(String fileName) {
		try {
			writeBuffer = new BufferedWriter(new FileWriter(fileName, true));
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	
	// Need to have this format for consistency issues. It is difficult to go from double to the DD:MM:SS format being used.
	/**
	 * Will write a city to file.
	 * @param name
	 * @param lat
	 * @param lon
	 * @return
	 */
	public static int addCityToFile(String name, String lat, String lon) {
		setWriteFile(0);
		String insert = name + "," + lat + "," + lon;
		PrintWriter pw = new PrintWriter(writeBuffer);
		pw.println(insert);
		pw.close();
		return 0;
	}
	
	/**
	 * Will write a connection to file with city names.
	 * @param city1
	 * @param city2
	 * @return
	 */
	public static int addConnection(String city1, String city2) {
		setWriteFile(1);
		String insert = city1 + "," + city2;
		PrintWriter pw = new PrintWriter(writeBuffer);
		pw.println(insert);
		pw.close();
		return 0;
	}
	
	/**
	 * Will write a connection to file with CityInformations.
	 * @param city1
	 * @param city2
	 * @return
	 */
	public static int addConnection(CityInformation city1, CityInformation city2) {
		String insert = city1.getCityName() + "," + city2.getCityName();
		PrintWriter pw = new PrintWriter(writeBuffer);
		pw.println(insert);
		pw.close();
		return 0;
	}
	
	// last to implement
	
	public static int removeCityFromFile() {
		
		return 0;
	}
	
	
}
