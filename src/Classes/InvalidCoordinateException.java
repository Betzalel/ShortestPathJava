package Classes;

/**
 * Invalid coordinate exception, thrown whenever an invalid longitude or
 * latitude is encountered.
 * 
 * @author Sam Silvestro
 */
public class InvalidCoordinateException extends RuntimeException {

   /**
    * Constructor.
    * 
    * @param message
    *           an error message describing the exception
    */
   public InvalidCoordinateException(String message) {
      // Pass the incoming message string to our parent's constructor.
      super(message);
   }

   /**
    * Default serial version ID -- makes Eclipse happy.
    */
   private static final long serialVersionUID = 1L;

}
