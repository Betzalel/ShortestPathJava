package Path;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

import Classes.CityInformation;

@SuppressWarnings("serial")
public class PathView extends JPanel{	
	PathModel myModel;
	
	public PathView(PathModel controller){
		myModel = controller;
	}
	public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	//g.drawImage(image, 0, 0, null); //For drawing a image on the background
    	Graphics2D g2 = (Graphics2D)g;
    	g2.setFont(new Font("TimesRoman", Font.PLAIN, myModel.FONTSIZE)); 
    	int xcorr,ycorr,x2corr,y2corr;
    	String cityName;
    	
    	for(int x = 0; x< myModel.cityList.size();x++){
    		xcorr = (int) (myModel.XPROPORTION * (((CityInformation) myModel.cityList.get(x)).getLongitude()+180));
    		ycorr = (int) (myModel.YPROPORTION * (180-(((CityInformation) myModel.cityList.get(x)).getLattitude()+90)));
    		cityName = ((CityInformation) myModel.cityList.get(x)).getCityName();
    		g2.draw(new Ellipse2D.Double(xcorr - (myModel.DOTSIZE/2),ycorr - (myModel.DOTSIZE/2),myModel.DOTSIZE,myModel.DOTSIZE));
    		g2.drawString(cityName,xcorr+myModel.NAMEXOFFSET,ycorr+myModel.NAMEYOFFSET);
    		System.out.println("City:\t"+cityName+"\t"+xcorr+" "+ycorr);
    	}
    	
    	/*
    	for(int x = 0; x < myModel.connections.size();x++){
    		g2.setColor(Color.BLACK);
    		//System.out.println("Draw line:"+ connections.get(x).getCity1()+" "+connections.get(x).getCity2());
    		xcorr = (int) (myModel.XPROPORTION * (myModel.connections.get(x).getCity1().getLongitude()+180));
    		ycorr = (int) (myModel.YPROPORTION * (180-(myModel.connections.get(x).getCity1()).getLattitude()-90));
    		x2corr = (int) (myModel.XPROPORTION * (myModel.connections.get(x).getCity2().getLongitude()+180));
    		y2corr = (int) (myModel.YPROPORTION * (180-(myModel.connections.get(x).getCity2()).getLattitude()-90));
    		System.out.println("City 1:\t"+xcorr+" "+ycorr);
    		System.out.println("City 2:\t"+x2corr+" "+y2corr);
    		g2.draw(new Line2D.Double(xcorr,ycorr,x2corr,y2corr));
    	}
    	*/
    	
    	if (myModel.hasShortestPath()) {
    		// Draw out the shortest path
    		Ellipse2D.Double point;
    		Line2D.Double line;
    		
    		// change colors
    		g2.setColor(myModel.myColor);
    		// Draw out the points
    		for (int x = 0; x < myModel.shortestPath.size(); x++) {
    			xcorr = (int) (myModel.XPROPORTION * (((CityInformation) myModel.shortestPath.get(x)).getLongitude()+180));
        		ycorr = (int) (myModel.YPROPORTION * (180-(((CityInformation) myModel.shortestPath.get(x)).getLattitude()+90)));
        		cityName = ((CityInformation) myModel.shortestPath.get(x)).getCityName();
        		point = new Ellipse2D.Double(xcorr - (myModel.DOTSIZE/2),ycorr - (myModel.DOTSIZE/2),myModel.DOTSIZE,myModel.DOTSIZE);
        		g2.draw(point);
        		g2.fill(point);
        		g2.drawString(cityName,xcorr+myModel.NAMEXOFFSET,ycorr+myModel.NAMEYOFFSET);
    		}
    		
    		// change colors
    		g2.setColor(myModel.myColor);
    		// draw out connections
    		for (int x = 1; x < myModel.shortestPath.size(); x++) {
    			// coords for one city
    			xcorr = (int) (myModel.XPROPORTION * (myModel.shortestPath.get(x).getLongitude()+180));
        		ycorr = (int) (myModel.YPROPORTION * (180-(myModel.shortestPath.get(x).getLattitude()-90) - 180));
        		// coords for last city
        		x2corr = (int) (myModel.XPROPORTION * (myModel.shortestPath.get(x-1).getLongitude()+180));
        		y2corr = (int) (myModel.YPROPORTION * (180-(myModel.shortestPath.get(x-1).getLattitude()-90)- 180));
        		System.out.println("1 x " + xcorr + "\t1 y " + ycorr);
        		System.out.println("2 x " + x2corr + "\t2 y " + y2corr);
        		line = new Line2D.Double(xcorr,ycorr,x2corr,y2corr);
        		g2.draw(line);
        	
    		}    		
    	}
    }
}
