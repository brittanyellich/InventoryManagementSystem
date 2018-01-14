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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class FXMLModifyProductController implements Initializable {

    @FXML
    private TextField modifyProductIDEntry;
    @FXML
    private TextField modifyProductNameEntry;
    @FXML
    private TextField modifyProductInvEntry;
    @FXML
    private TextField modifyProductPriceEntry;
    @FXML
    private TextField modifyProductMaxEntry;
    @FXML
    private TextField modifyProductMinEntry;
    @FXML
    private Button modifyProductSearchButton;
    @FXML
    private TextField modifyProductSearchEntry;
    @FXML
    private Button modifyProductAddButton;
    @FXML
    private Button modifyProductDeleteButton;
    @FXML
    private Button modifyProductCancelButton;
    @FXML
    private Button modifyProductSaveButton;
    @FXML
    private TableView<Part> allPartTable;
    @FXML
    private TableColumn<Part, Integer> allPartIDCol;
    @FXML
    private TableColumn<Part, String> allPartNameCol;
    @FXML
    private TableColumn<Part, Integer> allPartInvCol;
    @FXML
    private TableColumn<Part, Double> allPartPriceCol;
    @FXML
    private TableView<Part> addedPartModifyProductTable;
    @FXML
    private TableColumn<Part, Integer> addedProductPartIDCol;
    @FXML
    private TableColumn<Part, String> addedProductPartNameCol;
    @FXML
    private TableColumn<Part, Integer> addedProductPartInvCol;
    @FXML
    private TableColumn<Part, Double> addedProductPartPriceCol;
    
    public ObservableList<Part> searchParts =FXCollections.observableArrayList();
    public ObservableList<Part> productParts =FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         //Add part info for selected part
        int selectedProductID = FXMLDocumentController.selectedProductID;
        modifyProductIDEntry.setText(new Integer(selectedProductID).toString());
        System.out.println(FXMLDocumentController.selectedProductID);
        //Lookup part in the parts observable array
        Product productToModify = Inventory.lookupProduct(selectedProductID);
        System.out.println(productToModify);
        //Add data to the entries for modifying
        modifyProductNameEntry.setText(productToModify.getProductName().toString());
        modifyProductInvEntry.setText(new Integer(productToModify.getProductInStock()).toString());
        modifyProductPriceEntry.setText(new Double(productToModify.getProductPrice()).toString());
        modifyProductMaxEntry.setText(new Integer(productToModify.getProductMax()).toString());
        modifyProductMinEntry.setText(new Integer(productToModify.getProductMin()).toString());
        
        //Product Part Table Initialization
        allPartIDCol.setCellValueFactory(cellData -> cellData.getValue().PartIDProperty().asObject());
        allPartNameCol.setCellValueFactory(cellData -> cellData.getValue().PartNameProperty());
        allPartInvCol.setCellValueFactory(cellData -> cellData.getValue().PartInStockProperty().asObject());
        allPartPriceCol.setCellValueFactory(cellData -> cellData.getValue().PartPriceProperty().asObject());
        allPartTable.setItems(Inventory.getAllParts()); 
        
        productParts = Inventory.lookupProductParts(selectedProductID);
        System.out.println(productParts);
        //Added Part Table Initialization
        addedProductPartIDCol.setCellValueFactory(cellData -> cellData.getValue().PartIDProperty().asObject());
        addedProductPartNameCol.setCellValueFactory(cellData -> cellData.getValue().PartNameProperty());
        addedProductPartInvCol.setCellValueFactory(cellData -> cellData.getValue().PartInStockProperty().asObject());
        addedProductPartPriceCol.setCellValueFactory(cellData -> cellData.getValue().PartPriceProperty().asObject());
        addedPartModifyProductTable.setItems(productParts); 
    }    

    @FXML
    private void modifyProductSearchButtonClicked(ActionEvent event) {
        //Search parts table in modify product screen
        System.out.println("Modify product search button clicked!");
        //Search through allPartTable
        searchParts.removeAll();
        System.out.println("Search parts button clicked!");
        String searchItem=modifyProductSearchEntry.getText(); 
          if (searchItem.equals("")){
            allPartTable.getItems().clear();
        }
          else{
    boolean found=false;
    try{
        int partID=Integer.parseInt(searchItem);
        for(Part p: Inventory.getAllParts()){
            if(Inventory.lookupPart(partID) == p){
                System.out.println("This is part "+ partID);
                found=true;
                searchParts.add(p);
                allPartTable.setItems(searchParts);
            } else {
            }
            
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Part not found");

            alert.showAndWait();
        }
    }
    catch(NumberFormatException e){
        for(Part p: Inventory.getAllParts()){
            if(p.getPartName().equals(searchItem)){
                System.out.println("This is part "+p.getPartID());
                found=true;
                searchParts.add(p);
                allPartTable.setItems(searchParts);
            }        
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error");
            alert.setContentText("Part not found");

            alert.showAndWait();
        }
    }
    }
    if(modifyProductSearchEntry.getText().isEmpty()){
        allPartTable.setItems(Inventory.getAllParts());
    }    
    }

    @FXML
    private void modifyProductAddButtonClicked(ActionEvent event) {
        System.out.println("Modify product add button clicked!");
        //Add selected part from productPartTable to ObservableArray for a product/to the addedPartProductTable
        int selectedPartID = allPartTable.getSelectionModel().getSelectedItem().getPartID();
        Part partToAdd = Inventory.lookupPart(selectedPartID);
        productParts.add(partToAdd);
    }

    @FXML
    private void modifyProductDeleteButtonClicked(ActionEvent event) {
        System.out.println("Modify product delete button clicked!");
        //Delete part from the product table
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Please confirm");
            confirm.setHeaderText("Please confirm");
            confirm.setContentText("Are you sure you want to remove this part?");

            Optional<ButtonType> result = confirm.showAndWait();
            if (result.get() == ButtonType.OK){
                //Delete selected part from addedPartProductTable from the ObservableArray for the selected product
                int selectedPartID = addedPartModifyProductTable.getSelectionModel().getSelectedItem().getPartID();
                productParts.remove(Inventory.lookupPart(selectedPartID));
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        
    }

    @FXML
    private void modifyProductCancelButtonClicked(ActionEvent event) throws IOException {
        //Cancel modifying product and return to main screen
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Part will not be saved");
        confirm.setContentText("Are you sure you wish to exit without saving?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) modifyProductCancelButton.getScene().getWindow();
            stage.close();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void modifyProductSaveButtonClicked(ActionEvent event) {
        //Save the modified product
        System.out.println("Modify product save button clicked!");
        int productID = new Integer(modifyProductIDEntry.getText());
        String productName = modifyProductNameEntry.getText();
        int productInStock = new Integer(modifyProductInvEntry.getText());
        double productPrice = new Double(modifyProductPriceEntry.getText());
        String productMax = modifyProductMaxEntry.getText();
        String productMin = modifyProductMinEntry.getText();
        //Check that entered information is valid
        double sumPartsPrice = 0;
        for(Part p : Inventory.lookupProductParts(productID)) {
            sumPartsPrice += p.getPartPrice();
        }
        if(productPrice < sumPartsPrice) {
           Alert priceAlert = new Alert(Alert.AlertType.WARNING);
           priceAlert.setTitle("Price Warning");
           priceAlert.setHeaderText("There was a problem");
           priceAlert.setContentText("Product price must be greater than or equal to sum of individual parts");
           
           priceAlert.showAndWait();
        }else if(Inventory.lookupProductParts(productID).isEmpty()) {
           Alert partAlert = new Alert(Alert.AlertType.WARNING);
           partAlert.setTitle("Part Warning");
           partAlert.setHeaderText("There was a problem");
           partAlert.setContentText("Each product must have at least one part!");
           
           partAlert.showAndWait();
        } else if(productName.isEmpty()) {
           Alert inventoryAlert = new Alert(Alert.AlertType.WARNING);
           inventoryAlert.setTitle("Product Warning");
           inventoryAlert.setHeaderText("There was a problem");
           inventoryAlert.setContentText("Product must have a name");
           
           inventoryAlert.showAndWait();
        } else if(productPrice <= 0) {
           Alert inventoryAlert = new Alert(Alert.AlertType.WARNING);
           inventoryAlert.setTitle("Product Warning");
           inventoryAlert.setHeaderText("There was a problem");
           inventoryAlert.setContentText("Product must have a price");
           
           inventoryAlert.showAndWait();
        } else if(!(productInStock >=0 || modifyProductInvEntry.getText().isEmpty())) {
           Alert inventoryAlert = new Alert(Alert.AlertType.WARNING);
           inventoryAlert.setTitle("Product Warning");
           inventoryAlert.setHeaderText("There was a problem");
           inventoryAlert.setContentText("Product must have an inventory level");
           
           inventoryAlert.showAndWait();
        } else {
        //Modify either an inhouse or outsourced part depending on which radio button is selected
        Product modifiedProduct = new Product(productID, productName, productInStock, 
                productPrice, Integer.parseInt(productMax), 
                Integer.parseInt(productMin), Inventory.lookupProductParts(productID));
            Inventory.updateProduct(productID, modifiedProduct);
        //Replace the text with blanks in each text entry
        modifyProductIDEntry.setText("");
        modifyProductNameEntry.setText("");
        modifyProductInvEntry.setText("");
        modifyProductPriceEntry.setText("");
        modifyProductMaxEntry.setText("");
        modifyProductMinEntry.setText("");
        Stage stage = (Stage) modifyProductSaveButton.getScene().getWindow();
        stage.close();
        }
    }
    
}
