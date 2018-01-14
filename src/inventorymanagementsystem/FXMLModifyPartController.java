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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class FXMLModifyPartController implements Initializable {

    
    @FXML
    private AnchorPane modifyPartWindow;
    @FXML
    private RadioButton modifyPartOutsourcedRadioButton;
    @FXML
    private ToggleGroup modifyPart;
    @FXML
    private RadioButton modifyPartInHouseRadioButton;
    @FXML
    private TextField modifyPartIDEntry;
    @FXML
    private TextField modifyPartNameEntry;
    @FXML
    private TextField modifyPartInvEntry;
    @FXML
    private TextField modifyPartPriceEntry;
    @FXML
    private TextField modifyPartMaxEntry;
    @FXML
    private TextField modifyPartMinEntry;
    @FXML
    private TextField modifyPartCompNameEntry;
    @FXML
    private Button modifyPartCancelButton;
    @FXML
    private Button modifyPartSaveButton;
    @FXML
    private Label modifyCompanyNameLabel;

    @FXML
    private TextField modifyPartMachIDEntry;

    @FXML
    private Label modifyMachineIDLabel;
    
    private Inhouse Inhouse;
    private Outsourced Outsourced;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Add part info for selected part
        int selectedPartID = FXMLDocumentController.selectedPartID;
        modifyPartIDEntry.setText(new Integer(selectedPartID).toString());
        System.out.println(FXMLDocumentController.selectedPartID);
        //Lookup part in the parts observable array
        Part partToModify = Inventory.lookupPart(selectedPartID);
        System.out.println(partToModify);
        //Add data to the entries for modifying
        modifyPartNameEntry.setText(partToModify.getPartName().toString());
        modifyPartInvEntry.setText(new Integer(partToModify.getPartInStock()).toString());
        modifyPartPriceEntry.setText(new Double(partToModify.getPartPrice()).toString());
        modifyPartMaxEntry.setText(new Integer(partToModify.getPartMax()).toString());
        modifyPartMinEntry.setText(new Integer(partToModify.getPartMin()).toString());
    }    
    
    @FXML
    private void modifyPartOutsourcedRadioButtonSelected(ActionEvent event) {
        System.out.println("Outsourced Radio button Selected");
        RadioButton selectedRadioButton = (RadioButton) modifyPart.getSelectedToggle();
        String toggle = selectedRadioButton.getText();
        if(toggle.equals("Outsourced")){
            modifyMachineIDLabel.setVisible(false);
            modifyPartMachIDEntry.setVisible(false);
            modifyCompanyNameLabel.setVisible(true);
            modifyPartCompNameEntry.setVisible(true);
        } else {
        }
    }

    @FXML
    private void modifyPartInHouseRadioButtonSelected(ActionEvent event) {
        System.out.println("In House Radio button Selected");
        RadioButton selectedRadioButton = (RadioButton) modifyPart.getSelectedToggle();
        String toggle = selectedRadioButton.getText();
        if(toggle.equals("In-House")){
            modifyMachineIDLabel.setVisible(true);
            modifyPartMachIDEntry.setVisible(true);
            modifyCompanyNameLabel.setVisible(false);
            modifyPartCompNameEntry.setVisible(false);
        } else {
        }
    }

    @FXML
    private void modifyPartCancelButtonClicked(ActionEvent event) throws IOException {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Part will not be saved");
        confirm.setContentText("Are you sure you wish to exit without saving?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) modifyPartCancelButton.getScene().getWindow();
            stage.close();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void modifyPartSaveButtonClicked(ActionEvent event) {
        System.out.println("Modify part save button ");
        //Populate data with the part information
        int partID = new Integer(modifyPartIDEntry.getText());
        String partName = modifyPartNameEntry.getText();
        int partInStock = new Integer(modifyPartInvEntry.getText());
        double partPrice = new Double(modifyPartPriceEntry.getText());
        int partMax = new Integer(modifyPartMaxEntry.getText());
        int partMin = new Integer(modifyPartMinEntry.getText());
        String partCompName = modifyPartCompNameEntry.getText();
        String partMachID = modifyPartMachIDEntry.getText();
        if(partInStock < partMin || partInStock > partMax){
           Alert inventoryAlert = new Alert(Alert.AlertType.WARNING);
           inventoryAlert.setTitle("Inventory Warning");
           inventoryAlert.setHeaderText("There was a problem");
           inventoryAlert.setContentText("Inventory must be greater than the minimum and less than the maximum for the part!");
           
           inventoryAlert.showAndWait();
        } else if(partMin > partMax) {
           Alert valueAlert = new Alert(Alert.AlertType.WARNING);
           valueAlert.setTitle("Value Warning");
           valueAlert.setHeaderText("There was a problem");
           valueAlert.setContentText("Part Maximum must be larger than Part Minimum!");
           
           valueAlert.showAndWait();
        } else {
        //Determine which radio button is selected
        RadioButton selectedRadioButton = (RadioButton) modifyPart.getSelectedToggle();
        String toggle = selectedRadioButton.getText();
        //Modify either an inhouse or outsourced part depending on which radio button is selected
        if(toggle.equals("In-House")){
            System.out.println("Modifying In-House Part!");
            Inhouse modifiedInHousePart = new Inhouse(partID, partName, 
                partInStock, partPrice, 
                partMax,partMin, 
                Integer.parseInt(partMachID));
            Inventory.updatePart(partID, modifiedInHousePart);
        }else if (toggle.equals("Outsourced")){
            System.out.println("Modifying Outsourced Part!");
            Outsourced modifiedOutsourcedPart = new Outsourced(partID, partName, 
                partInStock, partPrice, 
                partMax,partMin, 
                partCompName);
            Inventory.updatePart(partID, modifiedOutsourcedPart);
        }
        Stage stage = (Stage) modifyPartSaveButton.getScene().getWindow();
        stage.close();
        }
    }
}
