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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
//        //Add data to the entries for modifying
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
        
        //Added Part Table Initialization
        addedProductPartIDCol.setCellValueFactory(cellData -> cellData.getValue().PartIDProperty().asObject());
        addedProductPartNameCol.setCellValueFactory(cellData -> cellData.getValue().PartNameProperty());
        addedProductPartInvCol.setCellValueFactory(cellData -> cellData.getValue().PartInStockProperty().asObject());
        addedProductPartPriceCol.setCellValueFactory(cellData -> cellData.getValue().PartPriceProperty().asObject());
        addedPartModifyProductTable.setItems(Product.getAllAssociatedParts()); 
    }    

    @FXML
    private void modifyProductSearchButtonClicked(ActionEvent event) {
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
        Product.addAssociatedPart(partToAdd);
    }

    @FXML
    private void modifyProductDeleteButtonClicked(ActionEvent event) {
        System.out.println("Modify product delete button clicked!");
        //Delete selected part from addedPartProductTable from the ObservableArray for the selected product
        int selectedPartID = addedPartModifyProductTable.getSelectionModel().getSelectedItem().getPartID();
        Product.removeAssociatedPart(selectedPartID);
    }

    @FXML
    private void modifyProductCancelButtonClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) modifyProductCancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void modifyProductSaveButtonClicked(ActionEvent event) {
        System.out.println("Modify product save button clicked!");
        int productID = new Integer(modifyProductIDEntry.getText());
        String productName = modifyProductNameEntry.getText();
        String productInStock = modifyProductInvEntry.getText();
        String productPrice = modifyProductPriceEntry.getText();
        String productMax = modifyProductMaxEntry.getText();
        String productMin = modifyProductMinEntry.getText();
        //Modify either an inhouse or outsourced part depending on which radio button is selected
        Product modifiedProduct = new Product(productID, productName, Integer.parseInt(productInStock), 
                Double.parseDouble(productPrice), Integer.parseInt(productMax), 
                Integer.parseInt(productMin), Product.getAssociatedParts());
            Inventory.updateProduct(productID, modifiedProduct);
        //Replace the text with blanks in each text entry
        modifyProductIDEntry.setText("");
        modifyProductNameEntry.setText("");
        modifyProductInvEntry.setText("");
        modifyProductPriceEntry.setText("");
        modifyProductMaxEntry.setText("");
        modifyProductMinEntry.setText("");
    }
    
}
