/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diary;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author peisenschmidt
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private TextArea NewItemTextTextArea; 
    @FXML
    private TextField NewItemTitleTextField; 
    @FXML
    private Button SaveNewItemButton;
    
    @FXML
    private void handleSaveNewItem(ActionEvent event) {
        System.out.println(NewItemTextTextArea.getText());
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* Remove focus from title field */
        NewItemTitleTextField.setFocusTraversable(false);        
    }    
    
}
