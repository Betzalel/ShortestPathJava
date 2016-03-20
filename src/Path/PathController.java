package Path;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import dijkstra.Dijkstra;
import Classes.CityInformation;
import Classes.Connection;

public class PathController {
	protected PathModel myModel = new PathModel();
	
	public PathController() {//ArrayList<CityInformation> cityList){
		//myModel.cityList = cityList;
		myModel.draw();
	}
	public PathModel getModel() {
		return myModel;
	}
	
	public void setXProportion(int proportion){
		myModel.XPROPORTION = proportion;
		myModel.draw();
	}
	public void clearConnections(){
		myModel.clearConnections();
	}
	public void setYProportion(int proportion){
		myModel.YPROPORTION = proportion;
		myModel.draw();
	}
	public void setDotSize(int size){
		myModel.DOTSIZE = size;
		myModel.draw();
	}
	public void setConnections(ArrayList<Connection> connections){
		myModel.setConnections(connections);
		myModel.draw();
	}
	/*public void setConnections(ArrayList<Connection> connections) throws InterruptedException
	{
		ArrayList<ArrayList<Connection>> list = new ArrayList<ArrayList<Connection>>();
		for(int x = 0; x < connections.size(); x++)
		{
			list.add(new ArrayList<Connection>());
			for(int y = 0; y <= x ; y++)
			{
				list.get(x).add(connections.get(y));
			}
		}
		
		for(int x = 0; x < list.size(); x++)
		{
			System.out.println("Draw Incremented List"+list.get(x));
			
			myModel.setConnections(list.get(x));
			myModel.draw();
		}
	}*/
	public void setPanelXSize(int size){
		myModel.PANELXSIZE = size;
		myModel.draw();
	}
	public void setPanelYSize(int size){
		myModel.PANELYSIZE = size;
		myModel.draw();
	}
	public void setNameXOffset(int size){
		myModel.NAMEXOFFSET = size;
		myModel.draw();
	}
	public void setNameYOffset(int size){
		myModel.NAMEYOFFSET = size;
		myModel.draw();
	}
	public void setFontSize(int size){
		myModel.FONTSIZE = size;
		myModel.draw();
	}
	public PathView getPanel(){
		myModel.draw();
		return myModel.getPanel();
	}
	// Used to get start Dijkstras Algrithm
	public void handleDijkstras(HashMap<String, CityInformation> graph, CityInformation city1, CityInformation city2) throws InterruptedException {
		myModel.clearCurrentShortestPath();
		Dijkstra djk = new Dijkstra(graph, city1, city2);
		
		
		ArrayList<CityInformation> temp = djk.getShortestPath();
		
		ArrayList<ArrayList<CityInformation>> list = new ArrayList<ArrayList<CityInformation>>();
		for(int x = 0; x < temp.size(); x++)
		{
			list.add(new ArrayList<CityInformation>());
			for(int y = 0; y <= x ; y++)
			{
				list.get(x).add(temp.get(y));
			}
		}
		myModel.myColor = Color.GREEN;
		for(int x = 0; x < list.size(); x++)
		{
			System.out.println("Draw Incremented List"+list.get(x));
			Thread.sleep(1000);
			myModel.setCurrentShortestPath(list.get(x));
			myModel.draw();
		}
		Thread.sleep(1000);
		myModel.myColor = Color.BLUE;
		myModel.setCurrentShortestPath(temp);
		myModel.draw();
	}
}
