package StatusSideBar;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Classes.CityInformation;
import Utility.StructureHandler;

/**
 * This class renders the two drop down boxes to select the start city 
 * and ending city, as well as a button to send the data.
 * @author Shelley Klinzing
 */
public class SelectionView extends JPanel {
	/**
	 * create the two drop boxes
	 */
	protected JComboBox<String> cityDropBox1;
	protected JComboBox<String> cityDropBox2;
	
	/**
	 * create the OKAY button
	 */
	protected JButton cmdOkButton;
	
	/**
	 * Create the Add/Remove button
	 */
	protected JButton cmdAddRemove;
	
	/**
	 * The StructureHandler object.
	 */
	StructureHandler s;
	
	/**
	 * Contains an alphabetized list of all city names.
	 */
	ArrayList<String> cityNames;
	
	/**
	 * Constructor for StatusSideBarView
	 */
	public SelectionView() {
	   final Color WINDOW_COLOR = new Color(196, 187, 201);
	   
	   // Create the StructureHandler.
	   s = StructureHandler.getStructureHandler();
	   
	   cityNames = new ArrayList<String>();
	   
		cityDropBox1 = new JComboBox<String>();
		cityDropBox2 = new JComboBox<String>();
		cityDropBox1.setMaximumRowCount(10); /* display 10 rows */
		cityDropBox2.setMaximumRowCount(10); /* display 10 rows */
		
		// Populate the city drop down lists.
		buildCityLists();
		
		/* WEST:
		 * Drop down menu for Starting City and Destination
		 */
      setLayout(new GridLayout(11, 0));
      setBackground(WINDOW_COLOR); /* set background color */

      JLabel start = new JLabel("Starting City:");
      JLabel destination = new JLabel("Destination:");

      add(start); /* add start label */
      add(cityDropBox1); /* add the box to the west panel */
      add(destination); /* add destination label */
      add(cityDropBox2); /* add the box to the west panel */

      // Add the Add/Remove button
      cmdAddRemove = new JButton("Add/Remove");
      add(cmdAddRemove);

      JLabel spacer = new JLabel();
      add(spacer);

      /* add the OK button */
      cmdOkButton = new JButton("OK");
      add(cmdOkButton);
	} /* end constructor */
	
	protected void buildCityLists() {
      // Fetch the list index of the currently selected item.
	   int selectedCity1Index = cityDropBox1.getSelectedIndex();
      int selectedCity2Index = cityDropBox2.getSelectedIndex();
      String selectedCity1Name = null;
      String selectedCity2Name = null;
      
      // If the user had made selections, remember the names.
      if(selectedCity1Index != -1) {
         selectedCity1Name = (String) cityDropBox1.getSelectedItem();
      }
      if(selectedCity2Index != -1) {
         selectedCity2Name = (String) cityDropBox2.getSelectedItem();
      }
	   
      /* get an ArrayList of all the city names */
      ArrayList<CityInformation> cities = s.cityListAsArrayList();

      // Dump our current list of city names and rebuild it.
      cityNames.clear();
      for(CityInformation city : cities) {
         cityNames.add(city.getCityName());
      }
      Collections.sort(cityNames);

      // Clear the contents of the city drop down boxes.
	   cityDropBox1.removeAllItems();
      cityDropBox2.removeAllItems();
	   
      // Populate the city drop down boxes with the new city names.
	   cityDropBox1.setModel(new DefaultComboBoxModel<String>(
	            cityNames.toArray(new String[0])));
      cityDropBox2.setModel(new DefaultComboBoxModel<String>(
               cityNames.toArray(new String[0])));

      // Restore the user's previous selections to both boxes, if they still
      // exist.
      if(selectedCity1Index != -1) {
         cityDropBox1.setSelectedItem(selectedCity1Name);
      }
      if(selectedCity2Index != -1) {
         cityDropBox2.setSelectedItem(selectedCity2Name);
      }
      
      // Now fix drop box 2 to ensure it is not possible to select the same
      // city as in drop box 1.
      fixCityTwoSelection();
	}
	
	/**
	 * Register the controller as the listener to the button.
	 * @param controller The event handler for the calculator
	 */
	public void registerListener(SelectionController controller) {
		cmdOkButton.addActionListener(controller);
		cityDropBox1.addActionListener(controller);
		cityDropBox2.addActionListener(controller);
		cmdAddRemove.addActionListener(controller);
	}
	
	/**
	 * Be able to get the corresponding StructureHandler
	 * @return the StructureHandler
	 */
	public StructureHandler getHandler() {
		return s;
	}
	
	protected void fixCityTwoSelection() {
	   // Record the selected city name in drop box 2.
	   String secondSelectedCityName = (String) cityDropBox2.getSelectedItem();
	   
      // Clear the contents of the drop box 2.
      cityDropBox2.removeAllItems();
      
      // Repopulate drop box 2 with all all city names.
      cityDropBox2.setModel(new DefaultComboBoxModel<String>(
               cityNames.toArray(new String[0])));

      // Record the selected city name in drop box 1.
      String firstSelectedCityName = (String) cityDropBox1.getSelectedItem();
      
      // Remove from drop box 2 the city name selected in drop box 1.
      cityDropBox2.removeItem(firstSelectedCityName);
      
      // Restore the user's previous selection to drop box 2 (if it still
      // exists).
      cityDropBox2.setSelectedItem(secondSelectedCityName);
	}
} /* end StatusBar */