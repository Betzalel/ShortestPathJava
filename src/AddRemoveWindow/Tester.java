package AddRemoveWindow;

/**
 * Tester class used to instantiate the Add/Remove window in isolation.
 * 
 * @author Sam Silvestro
 */
public class Tester {

   public static void main(String[] args) {
      // We do not have access to a StructureHandler object, therefore, the
      // Add/Remove window will have empty lists and combo boxes. This way of
      // running the window is only useful to inspect the UI, as functionality
      // will be lacking without data.
      AddRemoveDriver.OpenWindow(null, null);
   }
}