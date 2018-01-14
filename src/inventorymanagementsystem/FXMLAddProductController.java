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
public class FXMLAddProductController implements Initializable {

    @FXML
    private TextField addProductIDEntry;

    @FXML
    private TextField addProductNameEntry;

    @FXML
    private TextField addProductInvEntry;

    @FXML
    private TextField addProductPriceEntry;

    @FXML
    private TextField addProductMaxEntry;

    @FXML
    private TextField addProductMinEntry;

    @FXML
    private Button addProductSearchButton;

    @FXML
    private TextField addProductSearchEntry;

    @FXML
    private TableView<Part> productPartTable;

    @FXML
    private TableColumn<Part, Integer> productPartIDCol;

    @FXML
    private TableColumn<Part, String> productPartNameCol;

    @FXML
    private TableColumn<Part, Integer> productPartInvCol;

    @FXML
    private TableColumn<Part, Double> productPartPriceCol;

    @FXML
    private Button addProductAddButton;

    @FXML
    private TableView<Part> addedPartProductTable;

    @FXML
    private TableColumn<Part, Integer> addedPartIDCol;

    @FXML
    private TableColumn<Part, String> addedPartNameCol;

    @FXML
    private TableColumn<Part, Integer> addedPartInvCol;

    @FXML
    private TableColumn<Part, Double> addedPartPriceCol;

    @FXML
    private Button addProductDeleteButton;

    @FXML
    private Button addProductCancelButton;

    @FXML
    private Button addProductSaveButton;
    
    public ObservableList<Part> searchParts =FXCollections.observableArrayList();
    public ObservableList<Part> addingParts =FXCollections.observableArrayList();
    public Product product;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Product Part Table Initialization
        productPartIDCol.setCellValueFactory(cellData -> cellData.getValue().PartIDProperty().asObject());
        productPartNameCol.setCellValueFactory(cellData -> cellData.getValue().PartNameProperty());
        productPartInvCol.setCellValueFactory(cellData -> cellData.getValue().PartInStockProperty().asObject());
        productPartPriceCol.setCellValueFactory(cellData -> cellData.getValue().PartPriceProperty().asObject());
        productPartTable.setItems(Inventory.getAllParts()); 
        
        //Added Part Table Initialization
        addedPartIDCol.setCellValueFactory(cellData -> cellData.getValue().PartIDProperty().asObject());
        addedPartNameCol.setCellValueFactory(cellData -> cellData.getValue().PartNameProperty());
        addedPartInvCol.setCellValueFactory(cellData -> cellData.getValue().PartInStockProperty().asObject());
        addedPartPriceCol.setCellValueFactory(cellData -> cellData.getValue().PartPriceProperty().asObject());
        addedPartProductTable.setItems(addingParts); 
        
        addProductInvEntry.setText("0");
    }    

    @FXML
    private void addProductSearchButtonClicked(ActionEvent event) {
        System.out.println("Add Product Search Button Clicked!");
        //Search through productPartTable
        searchParts.removeAll();
        System.out.println("Search parts button clicked!");
        String searchItem=addProductSearchEntry.getText(); 
          if (searchItem.equals("")){
            productPartTable.getItems().clear();
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
                productPartTable.setItems(searchParts);
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
                productPartTable.setItems(searchParts);
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
    if(addProductSearchEntry.getText().isEmpty()){
        productPartTable.setItems(Inventory.getAllParts());
    }    
    }

    @FXML
    private void addProductAddButtonClicked(ActionEvent event) {
        System.out.println("Add Product Add Button Clicked!");
        //Add selected part from productPartTable to ObservableArray for a product/to the addedPartProductTable
        int selectedPartID = productPartTable.getSelectionModel().getSelectedItem().getPartID();
        Part partToAdd = Inventory.lookupPart(selectedPartID);
        addingParts.add(partToAdd);
    }

    @FXML
    private void addProductDeleteButtonClicked(ActionEvent event) {
        System.out.println("Add Product Delete Button Clicked!");
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Please confirm");
        confirm.setContentText("Are you sure you want to remove this part?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK){
            //Delete selected part from addedPartProductTable from the ObservableArray for the selected product
            int selectedPartID = productPartTable.getSelectionModel().getSelectedItem().getPartID();
            addingParts.remove(Inventory.lookupPart(selectedPartID));
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        
    }

    @FXML
    private void addProductCancelButtonClicked(ActionEvent event) throws IOException {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Product will not be saved");
        confirm.setContentText("Are you sure you wish to exit without saving?");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) addProductCancelButton.getScene().getWindow();
            stage.close();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void addProductSaveButtonClicked(ActionEvent event) {
        System.out.println("Add Product Save Button Clicked");
//        Save product to the mainProductTable on the DocumentController screen
//        Get details from text entries
//        Set Product ID to auto-increment
        ObservableList<Part> partsToAssociate =FXCollections.observableArrayList();
        int productID = 1;
        int i = Inventory.getAllProducts().size();
        productID+= i;
        String productIDText = Integer.toString(productID);
        addProductIDEntry.setText(productIDText);
        //Get data from add part entries
        String productName = addProductNameEntry.getText();
        int productInStock = new Integer(addProductInvEntry.getText());
        double productPrice = new Double(addProductPriceEntry.getText());
        String productMax = addProductMaxEntry.getText();
        String productMin = addProductMinEntry.getText();
        //Add product to the products ObservableList
        partsToAssociate = addingParts;
        double sumPartsPrice = 0;
        for(Part p : partsToAssociate) {
            sumPartsPrice += p.getPartPrice();
        }
        if(productPrice < sumPartsPrice) {
           Alert priceAlert = new Alert(Alert.AlertType.WARNING);
           priceAlert.setTitle("Price Warning");
           priceAlert.setHeaderText("There was a problem");
           priceAlert.setContentText("Product price must be greater than or equal to sum of individual parts");
           
           priceAlert.showAndWait();
        } else if(partsToAssociate.isEmpty()) {
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
        } else if(productPrice <= 0 || addProductInvEntry.getText().isEmpty()) {
           Alert inventoryAlert = new Alert(Alert.AlertType.WARNING);
           inventoryAlert.setTitle("Product Warning");
           inventoryAlert.setHeaderText("There was a problem");
           inventoryAlert.setContentText("Product must have a price");
           
           inventoryAlert.showAndWait();
        } else if(!(productInStock >=0)) {
           Alert inventoryAlert = new Alert(Alert.AlertType.WARNING);
           inventoryAlert.setTitle("Product Warning");
           inventoryAlert.setHeaderText("There was a problem");
           inventoryAlert.setContentText("Product must have an inventory level");
           
           inventoryAlert.showAndWait();
        } else {
//        addedPartProductTable.setItems(partsToAssociate);
        System.out.println(partsToAssociate);
        Product productToAdd = new Product(productID, productName, productInStock, 
                productPrice, Integer.parseInt(productMax), 
                Integer.parseInt(productMin), partsToAssociate);
            Inventory.addProduct(productToAdd);
        //Replace the text with blanks in each text entry
        addProductIDEntry.setText("");
        addProductNameEntry.setText("");
        addProductInvEntry.setText("");
        addProductPriceEntry.setText("");
        addProductMaxEntry.setText("");
        addProductMinEntry.setText("");
        //Close add product
        Stage stage = (Stage) addProductSaveButton.getScene().getWindow();
        stage.close();
        }
    }
    
}
