package Path;

import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Color;

import Utility.StructureHandler;
import Utility.UpdateData;
import Classes.Connection;
import Classes.CityInformation;


public class PathModel implements UpdateData {
	
	StructureHandler strcHndl = StructureHandler.getStructureHandler();
	ArrayList<CityInformation> cityList;
	ArrayList<Connection> connections = new ArrayList<Connection>();
	
	// Holds the most recent shortest path.
	ArrayList<CityInformation> shortestPath = null;
	
	PathView myView;
	Color myColor = Color.BLACK;
	double XPROPORTION = 2;
	double YPROPORTION = 2;
	int DOTSIZE = 5;
	int PANELXSIZE = 800;
	int PANELYSIZE = 360;
	int NAMEXOFFSET = -15;
	int NAMEYOFFSET = 20;
	int FONTSIZE = 10;
	
	
	public PathModel(){
		myView = new PathView(this);
        myView.setPreferredSize(new Dimension(PANELXSIZE, PANELYSIZE));
        cityList = strcHndl.cityListAsArrayList();
        strcHndl.addListener(this);
        /*
         * Commenting this out because the border looks strange when rendered
         * with the status bar and selection bar. -Shelley
         * myView.setBorder(BorderFactory.createLineBorder(Color.black));
         */
	}
	public void clearConnections(){
		connections.clear();
		draw();
	}
	public void setConnections(ArrayList<Connection> connections){
		this.connections = connections;
		draw();
	}
	public void draw(){
		myView.repaint();
	}
	public PathView getPanel(){
		return myView;
	}
	
	// Sets the Shortest Path so it can be displayed.
	protected void setCurrentShortestPath(ArrayList<CityInformation> shrtstPth) {
		shortestPath = shrtstPth;
	}
	
	// Removes the Shortest Path
	// Used after it has been displayed to reset.
	protected void clearCurrentShortestPath() {
		shortestPath = null;
	}
	
	// Makes sure that there is a shortest path set for the View.
	protected boolean hasShortestPath() {
		if (shortestPath != null)
			return true;
		else
			return false;
	}
	@Override
	public void updateData() {
		cityList = strcHndl.cityListAsArrayList();
		draw();
	}
	
	
}
