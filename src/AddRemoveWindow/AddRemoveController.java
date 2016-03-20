package AddRemoveWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 * AddRemove window's controller.
 * 
 * @author Sam Silvestro
 */
public class AddRemoveController implements ActionListener, ItemListener {
   /**
    * The Add/Remove window's model component.
    */
   private AddRemoveModel model;
   
   /**
    * Constructor.
    * 
    * @param model
    *           the Add/Remove window's model component.
    */
   public AddRemoveController(AddRemoveModel model) {
      this.model = model;
   }

   @Override
   public void actionPerformed(ActionEvent event) {
      Object objSource = event.getSource();
      
      if(objSource instanceof JButton) {
         JButton btnSource = (JButton) objSource;
         if(btnSource.getName().equals("btnAdd")) {
            model.addSelectedCity();
         } else if(btnSource.getName().equals("btnRemove")) {
            model.removeSelectedCity();
         } else if(btnSource.getName().equals("btnAddConnection")) {
            model.addSelectedConnection();
         } else if(btnSource.getName().equals("btnRemoveConnection")) {
            model.removeSelectedConnection();
         }
      }
   }

   @Override
   public void itemStateChanged(ItemEvent event) {
      Object objSource = event.getSource();
      
      if(objSource instanceof JComboBox) {
         JComboBox<String> cmbSource = (JComboBox<String>) objSource;
      
         if(cmbSource.getName().equals("cmbRemoveCityList")) {
            // Differentiate between "select" and "deselect" item events. Only
            // populate the Remove City details if the user has made a new
            // selection.
            if(event.getStateChange() == ItemEvent.SELECTED) {
               model.populateRemoveCityDetails();
            }
         } else if(cmbSource.getName().equals("cmbCityConnectionsList")) {
            // Differentiate between "select" and "deselect" item events. Only
            // populate the City Connections lists if the user has made a new
            // selection.
            if(event.getStateChange() == ItemEvent.SELECTED) {
               model.populateConnectionsLists();
            }
         }
      }
   }
   
}
