/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.io.IOException;
import java.net.URL;
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
    
//    private int selectedPart = FXMLDocumentController.selectedPartID;
    

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
        //Determine if the part is in-house or outsourced
//        if(partToModify.getMachineID() == null)
        //Add data to the entries for modifying
        modifyPartNameEntry.setText(partToModify.getPartName().toString());
        modifyPartInvEntry.setText(new Integer(partToModify.getPartInStock()).toString());
        modifyPartPriceEntry.setText(new Double(partToModify.getPartPrice()).toString());
        modifyPartMaxEntry.setText(new Integer(partToModify.getPartMax()).toString());
        modifyPartMinEntry.setText(new Integer(partToModify.getPartMin()).toString());
//        modifyPartCompNameEntry.setText(partToModify.getCompanyName().toString());
//        modifyPartMachIDEntry.setText(new Integer(partToModify.getMachineID()).toString());
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
        Stage stage = (Stage) modifyPartCancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void modifyPartSaveButtonClicked(ActionEvent event) {
        System.out.println("Modify part save button ");
        //Populate data with the part information
        int partID = new Integer(modifyPartIDEntry.getText());
        String partName = modifyPartNameEntry.getText();
        String partInStock = modifyPartInvEntry.getText();
        String partPrice = modifyPartPriceEntry.getText();
        String partMax = modifyPartMaxEntry.getText();
        String partMin = modifyPartMinEntry.getText();
        String partCompName = modifyPartCompNameEntry.getText();
        String partMachID = modifyPartMachIDEntry.getText();
        //Determine which radio button is selected
        RadioButton selectedRadioButton = (RadioButton) modifyPart.getSelectedToggle();
        String toggle = selectedRadioButton.getText();
        //Modify either an inhouse or outsourced part depending on which radio button is selected
        if(toggle.equals("In-House")){
            System.out.println("Modifying In-House Part!");
            Inhouse modifiedInHousePart = new Inhouse(partID, partName, 
                Integer.parseInt(partInStock), Double.parseDouble(partPrice), 
                Integer.parseInt(partMax),Integer.parseInt(partMin), 
                Integer.parseInt(partMachID));
            Inventory.updatePart(partID, modifiedInHousePart);
        }else if (toggle.equals("Outsourced")){
            System.out.println("Modifying Outsourced Part!");
            Outsourced modifiedOutsourcedPart = new Outsourced(partID, partName, 
                Integer.parseInt(partInStock), Double.parseDouble(partPrice), 
                Integer.parseInt(partMax),Integer.parseInt(partMin), 
                partCompName);
            Inventory.updatePart(partID, modifiedOutsourcedPart);
        }
    }
}
