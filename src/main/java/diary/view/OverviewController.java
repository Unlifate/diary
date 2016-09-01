package diary.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import diary.MainApp;
import diary.model.Entry;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class OverviewController {
    @FXML
    private TableView<Entry> entryTable;
    @FXML
    private TableColumn<Entry, String> entryColumn;
    
    @FXML
    private TextField titleTextfield;
    @FXML
    private TextArea contentTextarea;

    // Reference to the main application.
    private MainApp mainApp;
    
    
    private boolean okClicked = false;
    private Entry entry;
    private Stage dialogStage;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public OverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the entry table with the entry column.
        entryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        entryColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        // Clear person details.
        showEntryDetails(null);

        // Listen for selection changes and show the person details when changed.
        entryTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showEntryDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        entryTable.setItems(mainApp.getEntryData());
    }
    
    /**
    * Fills all text fields to show details about the diary entry.
    * If the specified entry is null, all text fields are cleared.
    * 
    * @param entry the entry or null
    */
   private void showEntryDetails(Entry entry) {
       if (entry != null) {
           // Fill the text fields with info from the entry object.
           titleTextfield.setText(entry.getTitle());
           contentTextarea.setText(entry.getContent());

       } else {
           // Entry is null, remove all the text.
           titleTextfield.setText("");
           contentTextarea.setText("");
       }
   }
   
   /**
    * Called when the user clicks on the delete button.
    */
    @FXML
    private void handleDeleteEntry() {
        int selectedIndex = entryTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            entryTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No entry Selected");
            alert.setContentText("Please select a diary entry in the left table.");
            alert.showAndWait();
        }
    }
    
     /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
        
        // At least one text field must be filled
        if ((contentTextarea.getText() == null || contentTextarea.getText().length() == 0) &&
            (titleTextfield.getText() == null || titleTextfield.getText().length() == 0)) {
            errorMessage += "Please fill at least one field\n"; 
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct your input.");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    
}