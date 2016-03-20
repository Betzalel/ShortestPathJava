package AddRemoveWindow;

import java.awt.Component;
import Utility.StructureHandler;

/**
 * Intended to ease the creation and display of the Add/Remove window. Contains
 * a single public method that can be called when the "Add/Remove" button is
 * pressed. Enforces at most one Add/Remove window open at a time.
 * 
 * @author Sam Silvestro
 */
public class AddRemoveDriver {
   private static AddRemoveView view = null;
   private static AddRemoveModel model = null;
   private static AddRemoveController controller = null;
   
   /**
    * Instantiates the Add/Remove window's model, view, and controller
    * components with one easy-to-use method call. If the window already exists,
    * gives it focus.
    * 
    * @param structure
    *           the StructureHandler object used to read and write data to/from
    *           the rest of the application
    * @param component
    *           the windowing Component to place the Add/Remove window on top of
    */
   public static void OpenWindow(StructureHandler structure,
            Component component) {
      // If the window already exists and is open, give it focus and quit.
      if(view != null && view.isVisible()) {
         view.toFront();
         return;
      }
      
      // Create the view and pass it the Component it will be positioned atop
      // of (this should usually be the main window's frame).
      view = new AddRemoveView(component);

      // Create the model and pass in the view and the StructureHandler object.
      // The model will need the view in order to manipulate on-screen
      // components using program logic.
      model = new AddRemoveModel(view, structure);

      // Create the controller and pass it the model. The controller will need
      // to invoke methods on the model when certain events occur.
      controller = new AddRemoveController(model);

      // Register the controller with the components in the view.
      view.RegisterListener(controller);
   }
}
