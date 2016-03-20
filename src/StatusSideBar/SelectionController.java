package StatusSideBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import AddRemoveWindow.AddRemoveDriver;
import Classes.CityInformation;
import Path.PathController;
import Utility.StructureHandler;
import Utility.UpdateData;

/**
 * The controller translates the user's interactions with the view 
 * into actions that the model will perform.
 * This class is listening for the user to click the "OK" button in the 
 * selection view in order to start the Dijkstra GUI.
 * @author Shelley Klinzing
 *
 */
public class SelectionController implements ActionListener, UpdateData {
	private SelectionModel model;
	private SelectionView viewWest = null;
	private StatusBarView viewNorth = null;
	private PathController pathView = null;
	private JFrame frmMainWindow;

	/**
	 * Constructor for the Selection Controller
	 * @param model the model to use.
	 * @param view the view to use.
	 */
	public SelectionController(SelectionModel model, SelectionView viewWest, 
			StatusBarView viewNorth, PathController pathView, JFrame frame) {
		this.model = model;
		this.viewWest = viewWest;
		this.viewNorth = viewNorth;
		this.pathView = pathView;
		this.frmMainWindow = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		/*
		 * After selecting the Start destination and End destination, clicking 
		 * the okay button will then start running the Dijkstra GUI. I need the 
		 * name of the method to call for this.
		 */
		
		/* initialize the JComboBox and Button */
		JComboBox combobox = null;
		JButton button = null;
		
		/* determine if e is a JComboBox or JButton */
		if(event.getSource() instanceof JComboBox) {
			combobox = (JComboBox) event.getSource();
			if (combobox == viewWest.cityDropBox1) {
				viewWest.fixCityTwoSelection();
			}
		} else {
			button = (JButton) event.getSource();
			
			StructureHandler s = viewWest.getHandler(); /* get StructureHandler */
			
			// If the Add/Remove button was pressed then open its window...
			if(button == viewWest.cmdAddRemove) {
			   AddRemoveDriver.OpenWindow(s, frmMainWindow);
			} else { // otherwise, they clicked the OK button...
	   			/* get the name of the start and ending city and set them */
	   			String start = (String) viewWest.cityDropBox1.getSelectedItem();
	   			model.setStartCity(start);
	   			String end = (String) viewWest.cityDropBox2.getSelectedItem();
	   			model.setEndCity(end);
	   			
	   			CityInformation startCity = model.getStartCity();
	   			CityInformation endCity = model.getEndCity();
	   			
	   			// Do not proceed to dijkstra code unless both end points are
	   			// valid.
	   			if(startCity == null || endCity == null) {
	   			   return;
	   			}
	   			
	   			// Debug output
	   			System.out.println("Start: " + start);
	   			System.out.println("End: " + end);
	   			System.out.println(startCity);
	   			System.out.println(endCity);
	   			
	   			/* handle Dijkstra */
	   			try {
                  pathView.handleDijkstras(s.getCityList(), startCity, endCity);
               } catch(InterruptedException e) {
                  e.printStackTrace();
               }
	   			
	   			/* update the status bar */
	   			StatusBarModel statusModel = viewNorth.getModel();
	   			statusModel.update(start, end);
			}
		}
	} /* end actionPerformed() */

   @Override
   public void updateData() {
      // Rebuilt the west view's city lists.
      viewWest.buildCityLists();
      
      // Update the model's HashMap of cities.
      model.hm = viewWest.getHandler().getCityList();
   }
} /* end class */