package AddRemoveWindow;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import Classes.CityInformation;
import Classes.InvalidCoordinateException;
import Utility.StructureHandler;
import Classes.Connection;

/**
 * AddRemove window's model.
 * 
 * @author Sam Silvestro
 */
public class AddRemoveModel {
   private static final String EMPTY_STRING = "";
   
   /**
    * StructureHandler object that will be used to read and write information
    * from the AddRemove window to the rest of the application.
    */
   private StructureHandler structure;

   /**
    * The view component of the Add/Remove window.
    */
   private AddRemoveView view;

   /**
    * Constructor: initializes and populates window components in the view.
    * 
    * @param view
    *           the view component of the Add/Remove window
    * @param structure
    *           the StructureHandler object containing application data
    */
   public AddRemoveModel(AddRemoveView view, StructureHandler structure) {
      this.view = view;
      this.structure = structure;
      
      // Populate all on-screen lists.
      refreshView();
   }

   /**
    * Populates a city combo box.
    * 
    * @param box
    *           the combo box to populate
    */
   private void populateCityComboBox(JComboBox<String> box) {
      String selectedCityName = null;
      
      // Fetch the list index of the currently selected item.
      int selectedIndex = box.getSelectedIndex();
      
      // Avoid NullPointerException by returning now if the combo box is empty.
      if(selectedIndex != -1) {
         selectedCityName = (String) box.getSelectedItem();
      }
      
      // Clear the combo box of all entries first.
      box.removeAllItems();
      
      ArrayList<CityInformation> cityList = structure.cityListAsArrayList();
      ArrayList<String> cityNames = new ArrayList<String>();
      for(CityInformation city : cityList) {
         cityNames.add(city.getCityName());
      }
      
      Collections.sort(cityNames);
      
      box.setModel(new DefaultComboBoxModel<String>(
               cityNames.toArray(new String[1])));
      
      if(selectedIndex != -1) {
         box.setSelectedItem(selectedCityName);
      }
   }
   
   /**
    * Populates the latitude and longitude of the city currently selected in
    * the "Remove City" panel.
    */
   protected void populateRemoveCityDetails() {
      // Fetch the list index of the currently selected item.
      int selectedIndex = view.cmbRemoveCityList.getSelectedIndex();
      
      // Avoid NullPointerException by returning now if the combo box is empty.
      if(selectedIndex == -1) {
         view.setDisplayLatitude(EMPTY_STRING);
         view.setDisplayLongitude(EMPTY_STRING);
         return;
      }
      
      // Fetch the name of the currently selected city in the "Remove City"
      // panel.
      String selectedCityName = (String) view.cmbRemoveCityList.getSelectedItem();
      
      CityInformation city = structure.getCityList().get(selectedCityName);
      view.setDisplayLatitude(Double.toString(city.getLattitude()));
      view.setDisplayLongitude(Double.toString(city.getLongitude()));
   }
   
   /**
    * Repopulates all lists on the screen, taking note of what was currently
    * selected and ensuring these selections remain the same afterward.
    */
   private void refreshView() {
      populateCityComboBox(view.cmbCityConnectionsList);
      populateCityComboBox(view.cmbRemoveCityList);
      populateRemoveCityDetails();
      populateConnectionsLists();
   }
   
   /**
    * Builds the two city connections lists in the "City Connections" panel.
    */
   protected void populateConnectionsLists() {
      int selectedCityIndex;
      int hasConnectionSelectedIndex;
      int notConnectionSelectedIndex;
      ArrayList<CityInformation> allCities;
      ArrayList<Connection> allConnections;
      ArrayList<String> connectedCityNames = new ArrayList<String>();
      ArrayList<String> remainingCityNames = new ArrayList<String>();
      CityInformation selectedCity;
      String hasConnectionSelectionName = null;
      String notConnectionSelectionName = null;
      String selectedCityName;
      
      // Fetch the list index of the currently selected item.
      selectedCityIndex = view.cmbCityConnectionsList.getSelectedIndex();
      
      // Avoid NullPointerException by returning now if the City combo box is
      // empty.
      if(selectedCityIndex == -1)
         return;
      
      // Fetch the name of the currently selected city in the "City Connections"
      // panel.
      selectedCityName = (String) view.cmbCityConnectionsList.getSelectedItem();
      
      // Get the CityInformation object for this same city.
      selectedCity = structure.getCityList().get(selectedCityName);
      
      // Consolidate all connected cities into the local array list.
      allConnections = selectedCity.getConnections();
      for(Connection connection : allConnections) {
         String otherCityName;
         
         // Each Connection has two end points. Either of these end points could
         // be the selected city. Check them both to determine which is the
         // selected city and which is the "other city".
         if(connection.getCity1().getCityName().equals(selectedCityName))
            otherCityName = connection.getCity2().getCityName();
         else
            otherCityName = connection.getCity1().getCityName();
         
         connectedCityNames.add(otherCityName);
      }
      
      // Record the user's selections for the two City Connections list boxes.
      // These will be used to reselect these list items after the lists have
      // been rebuilt.
      hasConnectionSelectedIndex = view.lstHasConnections.getSelectedIndex();
      if(hasConnectionSelectedIndex != -1) {
         hasConnectionSelectionName = view.lstHasConnections.getSelectedValue();
      }
      notConnectionSelectedIndex = view.lstNotConnections.getSelectedIndex();
      if(notConnectionSelectedIndex != -1) {
         notConnectionSelectionName = view.lstNotConnections.getSelectedValue();
      }
      
      // Clear the City Connections lists of all entries first.
      view.lstHasConnectionsModel.removeAllElements();
      view.lstNotConnectionsModel.removeAllElements();
      
      Collections.sort(connectedCityNames);
      
      // Populate the lstHasConnections list with the names of connected cities.
      for(String connectedCityName : connectedCityNames) {
         view.lstHasConnectionsModel.addElement(connectedCityName);
      }
      
      // Consolidate all of the remaining cities into a local array list.
      allCities = structure.cityListAsArrayList();
      for(CityInformation someCity : allCities) {
         String someCityName = someCity.getCityName();
         
         // Only add the city name to the eligible cities list if it is neither
         // already in the connected cities list and it is not the selected
         // city itself.
         if(!connectedCityNames.contains(someCityName) &&
               !someCityName.equals(selectedCityName)) {
            remainingCityNames.add(someCityName);
         }
      }
      
      Collections.sort(remainingCityNames);
      
      // Populate the lstNotConnections list with the names of the remaining
      // cities.
      for(String remainingCity : remainingCityNames) {
         view.lstNotConnectionsModel.addElement(remainingCity);
      }
      
      // Restore the user's prior City Connections list selections after having
      // rebuilt the lists.
      if(hasConnectionSelectedIndex != -1) {
         view.lstHasConnections.setSelectedValue(hasConnectionSelectionName,
                  false);
      }
      if(notConnectionSelectedIndex != -1) {
         view.lstNotConnections.setSelectedValue(notConnectionSelectionName,
                  false);
      }
   }
   
   /**
    * Removes the currently selected city in the "Remove City" panel.
    */
   protected void removeSelectedCity() {
      // Fetch the list index of the currently selected item.
      int selectedIndex = view.cmbRemoveCityList.getSelectedIndex();
      
      // Avoid NullPointerException by returning now if the combo box is empty.
      if(selectedIndex == -1)
         return;
      
      // Fetch the name of the currently selected city in the "Remove City"
      // panel.
      String selectedCityName =
               (String) view.cmbRemoveCityList.getSelectedItem();
      
      // Debug output
      // System.err.println("Attempting to remove city: " + selectedCityName);
      
      structure.removeCity(selectedCityName);
      
      refreshView();
   }
   
   /**
    * Adds a new city from the "Add City" panel.
    */
   protected void addSelectedCity() {
      // Fetch the name, longitude, and latitude of the proposed city from the
      // "Add City" panel. Trim these strings at the same time in order to allow
      // for some slight user input error.
      String newCityName = view.getAddCityName().trim();
      String newCityLatitude = view.getAddLatitude().trim();
      String newCityLongitude = view.getAddLongitude().trim();

      // Validate user input.
      boolean isInputValid = validateUserInput(newCityName, newCityLatitude,
               newCityLongitude);
      if(!isInputValid)
         return;
      
      // Create a new CityInformation object using the validated user input.
      CityInformation newCity;
      try {
         newCity = new CityInformation(newCityName, newCityLatitude,
                  newCityLongitude);
      } catch(InvalidCoordinateException e) {
         JOptionPane.showMessageDialog(view,"Invalid coordinates.\n\n" +
                  "Latitude must be in the range [0, 90], and\n" +
                  "Longitude must be in the range [0, 180].",
                  "Error", JOptionPane.ERROR_MESSAGE);
         return;
      }
      
      // Debug output
      // System.err.println("Attempting to add city: " + newCity);
      
      int addStatus = structure.addCity(newCity);
      
      refreshView();
      
      // structure.addCity() will return 0 if we successfully added a new city.
      // If this is the case, automatically select it in the "City Connections"
      // panel.
      if(addStatus == 0) {
         view.cmbCityConnectionsList.setSelectedItem(newCityName);
      }
      
      // Clear the "Add City" text boxes following this add.
      view.resetAddCityForm();
   }
   
   /**
    * Validates the proposed name, longitude, and latitude of a new city. Each
    * of these parameters should be supplied as Strings.
    * 
    * @param cityName the proposed name of the new city
    * @param latitude the proposed latitude of the new city
    * @param longitude the proposed longitude of the new city
    * @return is the user input valid
    */
   private boolean validateUserInput(String cityName, String latitude,
            String longitude) {
      // Build pattern matching strings for each input field.
      // City name must begin with at least one word character.
      // Longitude and latitude must be in the form:
      //         <integer> <integer> <integer> <[N|n|S|s]|[E|e|W|w]>
      String cityNamePattern = "^\\w+.*";
      String latitudePattern = "^\\d+[ :]\\d+[ :]\\d+[ :][NnSs]$";
      String longitudePattern = "^\\d+[ :]\\d+[ :]\\d+[ :][EeWw]$";

      // Test the user's input with the above patterns.
      boolean hasValidCityName = cityName.matches(cityNamePattern);
      boolean hasValidLatitude = latitude.matches(latitudePattern);
      boolean hasValidLongitude = longitude.matches(longitudePattern);
      
      // If any field fails to supply a pattern match, report the error to the
      // user with a dialog box.
      if(!hasValidCityName) {
         JOptionPane.showMessageDialog(view,"Invalid city name.\n\n" +
                  "City names must begin with at least one word character.",
                  "Error", JOptionPane.ERROR_MESSAGE);
      } else if(!hasValidLatitude) {
         JOptionPane.showMessageDialog(view,"Invalid latitude.\n\n" +
                  "Latitude must be of the form: <int> <int> <int> <N|n|S|s>" +
                  "\nEx: 1 2 3 N", "Error", JOptionPane.ERROR_MESSAGE);
      } else if(!hasValidLongitude) {
         JOptionPane.showMessageDialog(view,"Invalid longitude.\n\n" +
                  "Longitude must be of the form: <int> <int> <int> <E|e|W|w>" +
                  "\nEx: 1 2 3 E", "Error", JOptionPane.ERROR_MESSAGE);
      }
      
      return (hasValidCityName && hasValidLatitude && hasValidLongitude);
   }
   
   /**
    * Adds the selected connection to the currently selected city in the "City
    * Connections" panel.
    */
   protected void addSelectedConnection() {
      // Fetch the list index of the currently selected item in the City list.
      int citySelectedIndex = view.cmbCityConnectionsList.getSelectedIndex();
      
      // Avoid NullPointerException by returning now if the City combo box is
      // empty.
      if(citySelectedIndex == -1)
         return;
      
      // Fetch the name of the currently selected city in the "Remove City"
      // panel.
      String selectedCityName =
               (String) view.cmbCityConnectionsList.getSelectedItem();
      
      // Fetch the list index of the currently selected item in the
      // lstNotConnections list.
      int connectionSelectedIndex = view.lstNotConnections.getSelectedIndex();
      
      // Avoid NullPointerException by returning now if no connection is
      // selected.
      if(connectionSelectedIndex == -1)
         return;
      
      String newConnection = view.lstNotConnections.getSelectedValue();
      
      // Debug output
      System.err.println("Attempting to add connection: " +
               selectedCityName + " - " + newConnection);
      
      structure.addConnection(selectedCityName, newConnection);
      
      refreshView();
   }
   
   /**
    * Removes the selected connection from the currently selected city in the
    * "City Connections" panel.
    */
   protected void removeSelectedConnection() {
      // Fetch the list index of the currently selected item in the City list.
      int citySelectedIndex = view.cmbCityConnectionsList.getSelectedIndex();
      
      // Avoid NullPointerException by returning now if the combo box is empty.
      if(citySelectedIndex == -1)
         return;
      
      // Fetch the name of the currently selected city in the "Remove City"
      // panel.
      String selectedCityName =
               (String) view.cmbCityConnectionsList.getSelectedItem();
      
      // Fetch the list index of the currently selected item in the
      // lstHasConnections list.
      int connectionSelectedIndex = view.lstHasConnections.getSelectedIndex();
      
      // Avoid NullPointerException by returning now if no connection is
      // selected.
      if(connectionSelectedIndex == -1)
         return;
      
      String oldConnection = view.lstHasConnections.getSelectedValue();
      
      // Debug output
      System.err.println("Attempting to remove connection: " +
               selectedCityName + " - " + oldConnection);
      
      structure.removeConnection(selectedCityName, oldConnection);
      
      refreshView();
   }
}
