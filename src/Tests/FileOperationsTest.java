package Tests;

import Utility.FileOperations;

public class FileOperationsTest {

	public static void main(String[] args) {
		String buff;
		
		System.out.println("readInFile(cityInfo)");
		
		FileOperations.setReadFile(0);
		
		System.out.println("while loop");
		
		while((buff = FileOperations.getNextLine()) != null) {
			System.out.println(buff);
		}
		
		System.out.println("readInFile(connInfo)");
		FileOperations.setReadFile(1);
		
		while((buff = FileOperations.getNextLine()) != null) {
			System.out.println(buff);
		}
		
		System.out.println("writeOutFile(cityInfo)");
		FileOperations.setWriteFile(0);
		
		FileOperations.addCityToFile("Test", "11:11:11", "22:22:22");
		
		System.out.println("writeOutFile(connInfo)");
		FileOperations.setWriteFile(1);
		
		FileOperations.addConnection("City1", "City2");
	}

}
