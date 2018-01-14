/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
        int partInStock = new Integer(addPartInvEntry.getText());
        double partPrice = new Double(addPartPriceEntry.getText());
        int partMax = new Integer(addPartMaxEntry.getText());
        int partMin = new Integer(addPartMinEntry.getText());
        String partCompName = addPartCompNameEntry.getText();
        String partMachID = addPartMachIDEntry.getText();
        //Check exception controls
        if(partInStock < partMin || partInStock > partMax){
           Alert inventoryAlert = new Alert(AlertType.WARNING);
           inventoryAlert.setTitle("Inventory Warning");
           inventoryAlert.setHeaderText("There was a problem");
           inventoryAlert.setContentText("Inventory must be greater than the minimum and less than the maximum for the part!");
           
           inventoryAlert.showAndWait();
        } else if(partMin > partMax) {
           Alert valueAlert = new Alert(AlertType.WARNING);
           valueAlert.setTitle("Value Warning");
           valueAlert.setHeaderText("There was a problem");
           valueAlert.setContentText("Part Maximum must be larger than Part Minimum!");
           
           valueAlert.showAndWait();
        } else {
        //Determine which radio button is selected
        RadioButton selectedRadioButton = (RadioButton) addPart.getSelectedToggle();
        String toggle = selectedRadioButton.getText();
        //Add either an inhouse or outsourced part depending on which radio button is selected
        if(toggle.equals("In-House")){
            System.out.println("Adding In-House Part!");
            Inventory.addPart(new Inhouse(partID, partName, 
                partInStock, partPrice, 
                partMax,partMin, 
                Integer.parseInt(partMachID)));
        }else if (toggle.equals("Outsourced")){
            System.out.println("Adding Outsourced Part!");
            Inventory.addPart(new Outsourced(partID, partName, 
                partInStock, partPrice, 
                partMax,partMin, 
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
        Stage stage = (Stage) addPartSaveButton.getScene().getWindow();
        stage.close();
        }
    }

    @FXML
    private void addPartCancelButtonClicked(ActionEvent event) throws IOException {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Part will not be saved");
        confirm.setContentText("Are you sure you wish to exit without saving?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) addPartCancelButton.getScene().getWindow();
            stage.close();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
    
}
