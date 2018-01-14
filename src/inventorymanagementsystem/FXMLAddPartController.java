/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class FXMLAddPartController implements Initializable {

    @FXML
    private RadioButton addPartInHouseRadioButton;
    @FXML
    private ToggleGroup addPart;
    @FXML
    private RadioButton addPartOutsourcedRadioButton;
    @FXML
    private TextField addPartIDEntry;
    @FXML
    private TextField addPartNameEntry;
    @FXML
    private TextField addPartInvEntry;
    @FXML
    private TextField addPartPriceEntry;
    @FXML
    private TextField addPartMaxEntry;
    @FXML
    private TextField addPartMinEntry;
    @FXML
    private TextField addPartCompNameEntry;
    @FXML
    private Button addPartSaveButton;
    @FXML
    private Button addPartCancelButton;
    @FXML
    private Label companyNameLabel;
    @FXML
    private TextField addPartMachIDEntry;
    @FXML
    private Label machineIDLabel;
//    @FXML
//    private TableView<Part> mainPartTable;
//    @FXML
//    public ObservableList<Part> partData =FXCollections.observableArrayList();

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addPartInHouseRadioButtonSelected(ActionEvent event) {
        System.out.println("In House Radio button Selected");
        RadioButton selectedRadioButton = (RadioButton) addPart.getSelectedToggle();
        String toggle = selectedRadioButton.getText();
        if(toggle.equals("In-House")){
            machineIDLabel.setVisible(true);
            addPartMachIDEntry.setVisible(true);
            companyNameLabel.setVisible(false);
            addPartCompNameEntry.setVisible(false);
        } else {
        }
    }

    @FXML
    private void addPartOutsourcedRadioButtonSelected(ActionEvent event) {
        System.out.println("Outsourced Radio button Selected");
        RadioButton selectedRadioButton = (RadioButton) addPart.getSelectedToggle();
        String toggle = selectedRadioButton.getText();
        if(toggle.equals("Outsourced")){
            machineIDLabel.setVisible(false);
            addPartMachIDEntry.setVisible(false);
            companyNameLabel.setVisible(true);
            addPartCompNameEntry.setVisible(true);
        } else {
        }
    }

    @FXML
    private void addPartSaveButtonClicked(ActionEvent event) {
        //Get details from text entries
        //Set Part ID to auto-increment
        int partID = 1;
        int i = Inventory.getAllParts().size();
        partID+= i;
        String partIDText = Integer.toString(partID);
        addPartIDEntry.setText(partIDText);
        //Get data from add part entries
        String partName = addPartNameEntry.getText();
        String partInStock = addPartInvEntry.getText();
        String partPrice = addPartPriceEntry.getText();
        String partMax = addPartMaxEntry.getText();
        String partMin = addPartMinEntry.getText();
        String partCompName = addPartCompNameEntry.getText();
        String partMachID = addPartMachIDEntry.getText();
        //Determine which radio button is selected
        RadioButton selectedRadioButton = (RadioButton) addPart.getSelectedToggle();
        String toggle = selectedRadioButton.getText();
        //Add either an inhouse or outsourced part depending on which radio button is selected
        if(toggle.equals("In-House")){
            System.out.println("Adding In-House Part!");
            Inventory.addPart(new Inhouse(partID, partName, 
                Integer.parseInt(partInStock), Double.parseDouble(partPrice), 
                Integer.parseInt(partMax),Integer.parseInt(partMin), 
                Integer.parseInt(partMachID)));
        }else if (toggle.equals("Outsourced")){
            System.out.println("Adding Outsourced Part!");
            Inventory.addPart(new Outsourced(partID, partName, 
                Integer.parseInt(partInStock), Double.parseDouble(partPrice), 
                Integer.parseInt(partMax),Integer.parseInt(partMin), 
                partCompName));
        }
        //Replace the text with blanks in each text entry
        addPartIDEntry.setText("");
        addPartNameEntry.setText("");
        addPartInvEntry.setText("");
        addPartPriceEntry.setText("");
        addPartMaxEntry.setText("");
        addPartMinEntry.setText("");
        addPartCompNameEntry.setText(""); 
        addPartMachIDEntry.setText("");
    }

    @FXML
    private void addPartCancelButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) addPartCancelButton.getScene().getWindow();
        stage.close();
    }
    
}
