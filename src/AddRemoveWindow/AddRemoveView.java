package AddRemoveWindow;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * AddRemove window's view component.
 * 
 * @author Sam Silvestro
 */
public class AddRemoveView extends JFrame {
   private static final String EMPTY_STRING = "";
   
   /**
    * Default serial version ID.
    */
   private static final long serialVersionUID = 1L;

   /**
    * The Add/Remove window's width.
    */
   private static int WINDOW_WIDTH = 500;

   /**
    * The Add/Remove window's height.
    */
   private static int WINDOW_HEIGHT = 500;

   /**
    * The Add/Remove window's title.
    */
   private static String WINDOW_TITLE = "Modify City Data";

   // Some window components that we'll need access to throughout this class.
   private JButton btnAdd;
   private JButton btnAddConnection;
   private JButton btnRemove;
   private JButton btnRemoveConnection;
   private JLabel lblLatitudeOut;
   private JLabel lblLongitudeOut;
   private JTextField txtAddCityName;
   private JTextField txtAddLatitude;
   private JTextField txtAddLongitude;
   
   // The following window components (and their models) have package-private
   // visibility. They must be directly accessible to the Add/Remove model.
   DefaultListModel<String> lstHasConnectionsModel;
   DefaultListModel<String> lstNotConnectionsModel;
   JComboBox<String> cmbCityConnectionsList;
   JComboBox<String> cmbRemoveCityList;
   JList<String> lstHasConnections;
   JList<String> lstNotConnections;
   
   /**
    * Constructor: accepts a Component object which this window will be
    * positioned on top of.
    * 
    * @param alignOn
    *           the component on which to place the Add/Remove window
    */
   public AddRemoveView(Component alignOn) {
      // Constants defining the height and width of the command buttons on this
      // window.
      final int BUTTON_WIDTH = 90;
      final int BUTTON_HEIGHT = 25;
      
      // Create a padding insets that will be used by the various screen
      // components.
      Insets allPadding = new Insets(5, 5, 5, 5);
      Insets noPadding = new Insets(0, 0, 0, 0);
      
      // Use a GridBagLayout for the frame's content pane, enforcing a single
      // vertical column of titled borders that will contain panels filled with
      // other components.
      getContentPane().setLayout(new GridBagLayout());

      // Create a panel to contain the "Remove City" components.
      JPanel removeCityPanel = createSubPanel("Remove City");
      JPanel addCityPanel = createSubPanel("Add City");
      JPanel cityConnectionsPanel = createSubPanel("City Connections");

      // Create GridBag constraints for the three panels.
      GridBagConstraints pnlRemoveCityConstraints =
               new GridBagConstraints(0, 0, 1, 1, 1, 0.1,
                        GridBagConstraints.FIRST_LINE_START,
                        GridBagConstraints.BOTH, noPadding, 0, 0);
      GridBagConstraints pnlAddCityConstraints =
               new GridBagConstraints(0, 1, 1, 1, 0, 0.1,
                        GridBagConstraints.FIRST_LINE_START,
                        GridBagConstraints.BOTH, noPadding, 0, 0);
      GridBagConstraints pnlCityConnectionsConstraints =
               new GridBagConstraints(0, 2, 1, 1, 0, 0.8,
                        GridBagConstraints.FIRST_LINE_START,
                        GridBagConstraints.BOTH, noPadding, 0, 0);
      
      // Add the three panels the this JFrame's content pane.
      add(removeCityPanel, pnlRemoveCityConstraints);
      add(addCityPanel, pnlAddCityConstraints);
      add(cityConnectionsPanel, pnlCityConnectionsConstraints);

      // Begin "Remove City" panel component restrictions
      // Restrictions for first row
      GridBagConstraints lblCityNameConstraints = new GridBagConstraints();
      lblCityNameConstraints.gridx = 0;
      lblCityNameConstraints.gridy = 0;
      lblCityNameConstraints.weightx = 0.27;
      lblCityNameConstraints.weighty = 0.5;
      lblCityNameConstraints.insets = allPadding;
      lblCityNameConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

      GridBagConstraints lblLatitudeConstraints = new GridBagConstraints();
      lblLatitudeConstraints.gridx = 1;
      lblLatitudeConstraints.gridy = 0;
      lblLatitudeConstraints.weightx = 0.27;
      lblLatitudeConstraints.insets = allPadding;
      lblLatitudeConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

      GridBagConstraints lblLongitudeConstraints = new GridBagConstraints();
      lblLongitudeConstraints.gridx = 2;
      lblLongitudeConstraints.gridy = 0;
      lblLongitudeConstraints.weightx = 0.27;
      lblLongitudeConstraints.insets = allPadding;
      lblLongitudeConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
      
      // Restrictions for second row
      GridBagConstraints cmbRemoveCityListConstraints =
               new GridBagConstraints();
      cmbRemoveCityListConstraints.gridx = 0;
      cmbRemoveCityListConstraints.gridy = 1;
      cmbRemoveCityListConstraints.weighty = 0.5;
      cmbRemoveCityListConstraints.fill = GridBagConstraints.HORIZONTAL;
      cmbRemoveCityListConstraints.insets = allPadding;
      cmbRemoveCityListConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

      GridBagConstraints lblLatitudeOutConstraints = new GridBagConstraints();
      lblLatitudeOutConstraints.gridx = 1;
      lblLatitudeOutConstraints.gridy = 1;
      lblLatitudeOutConstraints.fill = GridBagConstraints.HORIZONTAL;
      lblLatitudeOutConstraints.insets = allPadding;
      lblLatitudeOutConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
      
      GridBagConstraints lblLongitudeOutConstraints = new GridBagConstraints();
      lblLongitudeOutConstraints.gridx = 2;
      lblLongitudeOutConstraints.gridy = 1;
      lblLongitudeOutConstraints.fill = GridBagConstraints.HORIZONTAL;
      lblLongitudeOutConstraints.insets = allPadding;
      lblLongitudeOutConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

      GridBagConstraints btnRemoveConstraints = new GridBagConstraints();
      btnRemoveConstraints.gridx = 3;
      btnRemoveConstraints.gridy = 1;
      btnRemoveConstraints.weightx = 0.19;
      btnRemoveConstraints.fill = GridBagConstraints.NONE;
      btnRemoveConstraints.insets = allPadding;
      btnRemoveConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
      // End "Remove City" panel component restrictions

      // Begin "Add City" panel component restrictions
      // Restrictions for first row
      GridBagConstraints lblAddCityNameConstraints = new GridBagConstraints();
      lblAddCityNameConstraints.gridx = 0;
      lblAddCityNameConstraints.gridy = 0;
      lblAddCityNameConstraints.weightx = 0.27;
      lblAddCityNameConstraints.weighty = 0.9;
      lblAddCityNameConstraints.insets = allPadding;
      lblAddCityNameConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

      GridBagConstraints lblAddLatitudeConstraints = new GridBagConstraints();
      lblAddLatitudeConstraints.gridx = 1;
      lblAddLatitudeConstraints.gridy = 0;
      lblAddLatitudeConstraints.weightx = 0.27;
      lblAddLatitudeConstraints.insets = allPadding;
      lblAddLatitudeConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

      GridBagConstraints lblAddLongitudeConstraints = new GridBagConstraints();
      lblAddLongitudeConstraints.gridx = 2;
      lblAddLongitudeConstraints.gridy = 0;
      lblAddLongitudeConstraints.weightx = 0.27;
      lblAddLongitudeConstraints.insets = allPadding;
      lblAddLongitudeConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

      // Restrictions for second row of "Add City" panel
      GridBagConstraints txtAddCityNameConstraints = new GridBagConstraints();
      txtAddCityNameConstraints.gridx = 0;
      txtAddCityNameConstraints.gridy = 1;
      txtAddCityNameConstraints.weighty = 0.9;
      txtAddCityNameConstraints.insets = allPadding;
      txtAddCityNameConstraints.fill = GridBagConstraints.HORIZONTAL;
      txtAddCityNameConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
      
      GridBagConstraints txtAddLatitudeConstraints = new GridBagConstraints();
      txtAddLatitudeConstraints.gridx = 1;
      txtAddLatitudeConstraints.gridy = 1;
      txtAddLatitudeConstraints.insets = allPadding;
      txtAddLatitudeConstraints.fill = GridBagConstraints.HORIZONTAL;
      txtAddLatitudeConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

      GridBagConstraints txtAddLongitudeConstraints = new GridBagConstraints();
      txtAddLongitudeConstraints.gridx = 2;
      txtAddLongitudeConstraints.gridy = 1;
      txtAddLongitudeConstraints.insets = allPadding;
      txtAddLongitudeConstraints.fill = GridBagConstraints.HORIZONTAL;
      txtAddLongitudeConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

      GridBagConstraints btnAddConstraints = new GridBagConstraints();
      btnAddConstraints.gridx = 3;
      btnAddConstraints.gridy = 1;
      btnAddConstraints.weightx = 0.19;
      btnAddConstraints.fill = GridBagConstraints.NONE;
      btnAddConstraints.insets = allPadding;
      btnAddConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
      
      // Restrictions for third row of "Add City" panel.
      GridBagConstraints lblAddLatitudeExConstraints = new GridBagConstraints();
      lblAddLatitudeExConstraints.gridx = 1;
      lblAddLatitudeExConstraints.gridy = 2;
      lblAddLatitudeExConstraints.weighty = 0.1;
      lblAddLatitudeExConstraints.insets = allPadding;
      lblAddLatitudeExConstraints.anchor = GridBagConstraints.FIRST_LINE_START;

      GridBagConstraints lblAddLongitudeExConstraints = new GridBagConstraints();
      lblAddLongitudeExConstraints.gridx = 2;
      lblAddLongitudeExConstraints.gridy = 2;
      lblAddLongitudeExConstraints.insets = allPadding;
      lblAddLongitudeExConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
      // End "Add City" panel component restrictions
      
      // Begin "City Connections" panel component restrictions
      // Restrictions for first row
      GridBagConstraints cmbCityConnectionsListConstraints =
               new GridBagConstraints();
      cmbCityConnectionsListConstraints.gridx = 0;
      cmbCityConnectionsListConstraints.gridy = 0;
      cmbCityConnectionsListConstraints.weightx = 1;
      cmbCityConnectionsListConstraints.weighty = 0.1;
      cmbCityConnectionsListConstraints.gridwidth = 3;
      cmbCityConnectionsListConstraints.fill = GridBagConstraints.HORIZONTAL;
      cmbCityConnectionsListConstraints.insets = allPadding;
      cmbCityConnectionsListConstraints.anchor = GridBagConstraints.PAGE_START;
      
      // Restrictions for second row
      GridBagConstraints lblHasConnectionsConstraints =
               new GridBagConstraints();
      lblHasConnectionsConstraints.gridx = 0;
      lblHasConnectionsConstraints.gridy = 1;
      lblHasConnectionsConstraints.insets = allPadding;
      lblHasConnectionsConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
      
      GridBagConstraints lblNotConnectionsConstraints =
               new GridBagConstraints();
      lblNotConnectionsConstraints.gridx = 2;
      lblNotConnectionsConstraints.gridy = 1;
      lblNotConnectionsConstraints.insets = allPadding;
      lblNotConnectionsConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
      
      // Restrictions for third row
      GridBagConstraints lstSourceConnectionsConstraints =
               new GridBagConstraints();
      lstSourceConnectionsConstraints.gridx = 0;
      lstSourceConnectionsConstraints.gridy = 2;
      lstSourceConnectionsConstraints.gridheight = 2;
      lstSourceConnectionsConstraints.weightx = 0.45;
      lstSourceConnectionsConstraints.weighty = 0.9;
      lstSourceConnectionsConstraints.fill = GridBagConstraints.BOTH;
      lstSourceConnectionsConstraints.insets = allPadding;
      lstSourceConnectionsConstraints.anchor =
               GridBagConstraints.FIRST_LINE_START;
      
      GridBagConstraints btnAddConnectionConstraints = new GridBagConstraints();
      btnAddConnectionConstraints.gridx = 1;
      btnAddConnectionConstraints.gridy = 2;
      btnAddConnectionConstraints.weightx = 0.1;
      btnAddConnectionConstraints.weighty = 0.9;
      btnAddConnectionConstraints.fill = GridBagConstraints.NONE;
      btnAddConnectionConstraints.insets = allPadding;
      btnAddConnectionConstraints.anchor = GridBagConstraints.CENTER;
      
      GridBagConstraints btnRemoveConnectionConstraints =
               new GridBagConstraints();
      btnRemoveConnectionConstraints.gridx = 1;
      btnRemoveConnectionConstraints.gridy = 3;
      btnRemoveConnectionConstraints.weightx = 0.1;
      btnRemoveConnectionConstraints.weighty = 0.9;
      btnRemoveConnectionConstraints.fill = GridBagConstraints.NONE;
      btnRemoveConnectionConstraints.insets = allPadding;
      btnRemoveConnectionConstraints.anchor = GridBagConstraints.CENTER;
      
      GridBagConstraints lstDestinationConnectionsConstraints =
               new GridBagConstraints();
      lstDestinationConnectionsConstraints.gridx = 2;
      lstDestinationConnectionsConstraints.gridy = 2;
      lstDestinationConnectionsConstraints.gridheight = 2;
      lstDestinationConnectionsConstraints.weightx = 0.45;
      lstDestinationConnectionsConstraints.weighty = 0.9;
      lstDestinationConnectionsConstraints.fill = GridBagConstraints.BOTH;
      lstDestinationConnectionsConstraints.insets = allPadding;
      lstDestinationConnectionsConstraints.anchor =
               GridBagConstraints.FIRST_LINE_START;
      // End "City Connections" panel component restrictions

      // Create and setup window components.
      JLabel lblCityName = new JLabel("City name:");
      JLabel lblLongitude = new JLabel("Longitude:");
      JLabel lblLatitude = new JLabel("Latitude:");
      cmbRemoveCityList = new JComboBox<String>();
      cmbRemoveCityList.setName("cmbRemoveCityList");
      lblLatitudeOut = new JLabel();
      lblLongitudeOut = new JLabel();
      lblLatitudeOut.setMinimumSize(new Dimension(115, 25));
      lblLatitudeOut.setPreferredSize(new Dimension(115, 25));
      lblLongitudeOut.setMinimumSize(new Dimension(115, 25));
      lblLongitudeOut.setPreferredSize(new Dimension(115, 25));
      btnRemove = new JButton("Remove");
      btnRemove.setName("btnRemove");
      btnRemove.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
      btnRemove.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
      JLabel lblAddCityName = new JLabel("City name:");
      JLabel lblAddLatitude = new JLabel("Latitude:");
      JLabel lblAddLongitude = new JLabel("Longitude:");
      JLabel lblAddLongitudeEx = new JLabel("Ex: 4 5 6 E");
      lblAddLongitudeEx.setForeground(Color.LIGHT_GRAY);
      JLabel lblAddLatitudeEx = new JLabel("Ex: 1 2 3 N");
      lblAddLatitudeEx.setForeground(Color.LIGHT_GRAY);
      txtAddCityName = new JTextField();
      txtAddLongitude = new JTextField();
      txtAddLongitude.setToolTipText("Ex: 4 5 6 E");
      txtAddLatitude = new JTextField();
      txtAddLatitude.setToolTipText("Ex: 1 2 3 N");
      btnAdd = new JButton("Add");
      btnAdd.setName("btnAdd");
      btnAdd.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
      btnAdd.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
      cmbCityConnectionsList = new JComboBox<String>();
      cmbCityConnectionsList.setName("cmbCityConnectionsList");
      JLabel lblHasConnections = new JLabel("Connected to");
      JLabel lblNotConnections = new JLabel("Available");
      lstHasConnections = new JList<String>();
      lstHasConnectionsModel = new DefaultListModel<String>();
      lstHasConnections.setModel(lstHasConnectionsModel);
      lstHasConnections.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      JScrollPane lstHasConnectionsScrollPane = new JScrollPane(lstHasConnections);
      lstHasConnectionsScrollPane.setViewportView(lstHasConnections);
      lstNotConnections = new JList<String>();
      lstNotConnectionsModel = new DefaultListModel<String>();
      lstNotConnections.setModel(lstNotConnectionsModel);
      lstNotConnections.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      JScrollPane lstNotConnectionsScrollPane = new JScrollPane(lstNotConnections);
      lstNotConnectionsScrollPane.setViewportView(lstNotConnections);
      btnAddConnection = new JButton("< Add");
      btnAddConnection.setName("btnAddConnection");
      btnAddConnection.setMinimumSize(new Dimension(BUTTON_WIDTH,
               BUTTON_HEIGHT));
      btnAddConnection.setPreferredSize(new Dimension(BUTTON_WIDTH,
               BUTTON_HEIGHT));
      btnRemoveConnection = new JButton("Remove >");
      btnRemoveConnection.setName("btnRemoveConnection");
      btnRemoveConnection.setMinimumSize(new Dimension(BUTTON_WIDTH,
               BUTTON_HEIGHT));
      btnRemoveConnection.setPreferredSize(new Dimension(BUTTON_WIDTH,
               BUTTON_HEIGHT));

      // Add of the window's components to this JFrame's content pane.
      removeCityPanel.add(lblCityName, lblCityNameConstraints);
      removeCityPanel.add(lblLongitude, lblLongitudeConstraints);
      removeCityPanel.add(lblLatitude, lblLatitudeConstraints);
      removeCityPanel.add(cmbRemoveCityList, cmbRemoveCityListConstraints);
      removeCityPanel.add(lblLatitudeOut, lblLatitudeOutConstraints);
      removeCityPanel.add(lblLongitudeOut, lblLongitudeOutConstraints);
      removeCityPanel.add(btnRemove, btnRemoveConstraints);

      addCityPanel.add(lblAddCityName, lblAddCityNameConstraints);
      addCityPanel.add(lblAddLatitude, lblAddLatitudeConstraints);
      addCityPanel.add(lblAddLongitude, lblAddLongitudeConstraints);
      addCityPanel.add(txtAddCityName, txtAddCityNameConstraints);
      addCityPanel.add(txtAddLatitude, txtAddLatitudeConstraints);
      addCityPanel.add(txtAddLongitude, txtAddLongitudeConstraints);
      addCityPanel.add(btnAdd, btnAddConstraints);
      addCityPanel.add(lblAddLatitudeEx, lblAddLatitudeExConstraints);
      addCityPanel.add(lblAddLongitudeEx, lblAddLongitudeExConstraints);
      
      cityConnectionsPanel.add(cmbCityConnectionsList,
               cmbCityConnectionsListConstraints);
      cityConnectionsPanel.add(lblHasConnections,
               lblHasConnectionsConstraints);
      cityConnectionsPanel.add(lblNotConnections,
               lblNotConnectionsConstraints);
      cityConnectionsPanel.add(lstHasConnectionsScrollPane,
               lstSourceConnectionsConstraints);
      cityConnectionsPanel.add(btnAddConnection,
               btnAddConnectionConstraints);
      cityConnectionsPanel.add(btnRemoveConnection,
               btnRemoveConnectionConstraints);
      cityConnectionsPanel.add(lstNotConnectionsScrollPane,
               lstDestinationConnectionsConstraints);

      // Destroy the window on close.
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);

      // Position the window relative to the application's main window.
      setLocationRelativeTo(alignOn);

      setTitle(WINDOW_TITLE);
      setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
      setVisible(true);
   }
   
   /**
    * Uses the specified controller to add action listeners for the needed
    * window components.
    * 
    * @param controller
    *           the Add/Remove window's controller component
    */
   public void RegisterListener(AddRemoveController controller) {
      cmbRemoveCityList.addItemListener(controller);
      cmbCityConnectionsList.addItemListener(controller);
      btnAdd.addActionListener(controller);
      btnRemove.addActionListener(controller);
      btnAddConnection.addActionListener(controller);
      btnRemoveConnection.addActionListener(controller);
   }
   
   public void setDisplayLatitude(String latitude) {
      lblLatitudeOut.setText(latitude);
   }
   
   public void setDisplayLongitude(String longitude) {
      lblLongitudeOut.setText(longitude);
   }
   
   public String getAddCityName() {
      return txtAddCityName.getText();
   }
   
   public String getAddLatitude() {
      return txtAddLatitude.getText();
   }
   
   public String getAddLongitude() {
      return txtAddLongitude.getText();
   }
   
   private JPanel createSubPanel(String frameTitle) {
      JPanel newPanel = new JPanel();
      newPanel.setLayout(new GridBagLayout());
      TitledBorder newBorder = BorderFactory
            .createTitledBorder(BorderFactory.createEtchedBorder());
      newBorder.setTitle(frameTitle);
      // Apply the titled border to the panel.
      newPanel.setBorder(newBorder);
      
      return newPanel;
   }
   
   protected void resetAddCityForm() {
      txtAddCityName.setText(EMPTY_STRING);
      txtAddLatitude.setText(EMPTY_STRING);
      txtAddLongitude.setText(EMPTY_STRING);
   }
}
