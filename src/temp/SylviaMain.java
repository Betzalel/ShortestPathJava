package temp;
import Classes.CityInformation;
import Path.PathController;
import Utility.StructureHandler;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;


public class SylviaMain {
	public static void main(String[] args) throws IOException, InterruptedException{
		//Scanner s = null;
		//String[] result;
		StructureHandler sh = StructureHandler.getStructureHandler();
		ArrayList<CityInformation> ctyLst = sh.cityListAsArrayList();
		System.out.println(ctyLst.size());
		// ArrayList<Connection> connections = new ArrayList<Connection>();
		/*
        try {
            s = new Scanner(new BufferedReader(new FileReader("CityInformation.txt")));

            while (s.hasNextLine()) {
            	result = s.nextLine().split(",");
            	cityList.add(new CityInformation(result[0],result[1],result[2]));
            }
        }
        finally {
            if (s != null) {
                s.close();
            }
        }
        */
		//cityList.add(new CityInformation("Cairo","30:2:39 N","31:14:8 E"));
		JFrame window = new JFrame("Test frame");
		PathController testPanel= new PathController();
		window.add(testPanel.getPanel());
		window.pack();
		window.setVisible(true);
		//Thread.sleep(5000);
		System.out.println("Handle Dijkstras\t " + ctyLst.get(2).getCityName() + "\t" + ctyLst.get(3).getCityName());
		
		testPanel.handleDijkstras(sh.getCityList(), ctyLst.get(2), ctyLst.get(3));
		//connections.add(new Connection(cityList.get(1),cityList.get(4)));
		//connections.add(new Connection(cityList.get(5),cityList.get(9)));
		//testPanel.setConnections(connections);
		//Thread.sleep(5000);
		//connections.clear();
		//connections.add(new Connection(cityList.get(3),cityList.get(15)));
		//connections.add(new Connection(cityList.get(15),cityList.get(4)));
		//testPanel.setConnections(connections);
		//Thread.sleep(5000);
		//testPanel.clearConnections();
		//Thread.sleep(5000);
		//testPanel.setXProportion(50);;
		
	}
}
