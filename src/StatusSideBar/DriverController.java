package StatusSideBar;
import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Classes.CityInformation;
import Path.PathController;
import Utility.StructureHandler;
import Utility.UpdateData;

/**
 * Tester class for the status bar and selection bar.
 * @author Shelley Klinzing
 *
 */
public class DriverController {

	/**
	 * Test the window
	 */
	public static void main(String[] args) {
	   final String WINDOW_TITLE = "Flappy Dijkstra's";
	   
		/*
		 * Generate Model
		 */
		SelectionModel model = new SelectionModel();
		
		/* set the layout for the status and side bar */
		JFrame frmMainWindow = new JFrame();
		frmMainWindow.setLayout(new BorderLayout());
		
		/* side bar view */
		SelectionView viewWest = new SelectionView();
		frmMainWindow.add(viewWest, BorderLayout.WEST);
		
		/* status bar view */
		StatusBarView viewNorth = new StatusBarView();
		frmMainWindow.add(viewNorth, BorderLayout.NORTH);
		
		/* Dijkstra path view */
		PathView pathView = new PathView();
		frmMainWindow.add(pathView.getPathView(), BorderLayout.EAST);
		
		/* 
		 * generate the Controller
		 */
		SelectionController controller = new SelectionController(model, 
				viewWest, viewNorth, pathView.getPathController(), frmMainWindow);
		
		/*
		 * Register Listeners
		 */
		viewWest.registerListener(controller);
		viewWest.s.addListener(controller);
		
		/*
		 * Set up the view
		 */
		frmMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainWindow.setTitle(WINDOW_TITLE);
		frmMainWindow.pack();
		frmMainWindow.setVisible(true);
	} /* end main method */

}