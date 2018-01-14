/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class FXMLDocumentController implements Initializable {

    private Part part;
    private Product product;
    
    @FXML
    private AnchorPane parts;
    @FXML
    private Button partsModifyButton;
    @FXML
    private Button partsAddButton;
    @FXML
    private Button partsSearchButton;
    @FXML
    private TextField partsSearchBox;
    @FXML
    private Text partsLabel;
    @FXML
    public TableView<Part> mainPartTable;
    @FXML
    private TableColumn<Part, Integer> PartIDCol;
    @FXML
    private TableColumn<Part, String> PartNameCol;
    @FXML
    private TableColumn<Part, Integer> PartInStockCol;
    @FXML
    private TableColumn<Part, Double> PartPriceCol;
    @FXML
    private TableColumn<Product, Integer> ProductIDCol;
    @FXML
    private TableColumn<Product, String> ProductNameCol;
    @FXML
    private TableColumn<Product, Integer> ProductInStockCol;
    @FXML
    private TableColumn<Product, Double> ProductPriceCol;
    @FXML
    private Button partsDeleteButton;
    @FXML
    private AnchorPane products;
    @FXML
    private Button productsModifyButton;
    @FXML
    private Button productsDeleteButton;
    @FXML
    private TableView<Product> productsMainTable;
    @FXML
    private Text productsLabel;
    @FXML
    private Button productsSearchButton;
    @FXML
    private TextField productsSearchBox;
    @FXML
    private Button productsAddButton;
    @FXML
    private Button exitButton;
    
    @FXML
    public ObservableList<Part> searchPartData =FXCollections.observableArrayList();
    public ObservableList<Product> searchProductData = FXCollections.observableArrayList();
    static int selectedPartID = 1;
    static int selectedProductID = 1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Part Table Initialization
        PartIDCol.setCellValueFactory(cellData -> cellData.getValue().PartIDProperty().asObject());
        PartNameCol.setCellValueFactory(cellData -> cellData.getValue().PartNameProperty());
        PartInStockCol.setCellValueFactory(cellData -> cellData.getValue().PartInStockProperty().asObject());
        PartPriceCol.setCellValueFactory(cellData -> cellData.getValue().PartPriceProperty().asObject());
        mainPartTable.setItems(Inventory.getAllParts()); 
        
        //Product Table Initialization
        ProductIDCol.setCellValueFactory(cellData -> cellData.getValue().ProductIDProperty().asObject());
        ProductNameCol.setCellValueFactory(cellData -> cellData.getValue().ProductNameProperty());
        ProductInStockCol.setCellValueFactory(cellData -> cellData.getValue().ProductInStockProperty().asObject());
        ProductPriceCol.setCellValueFactory(cellData -> cellData.getValue().ProductPriceProperty().asObject());
        productsMainTable.setItems(Inventory.getAllProducts()); 
    }    

    @FXML
    private void modifySelectedPart(ActionEvent event) throws IOException {
        //Open Modify Part screen in new window
        selectedPartID = mainPartTable.getSelectionModel().getSelectedItem().getPartID();
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modify Part");
        window.setMinWidth(250);
        Parent modifyNewPart = FXMLLoader.load(getClass().getResource("FXMLModifyPart.fxml"));
        Scene modifyPartScene = new Scene (modifyNewPart);
        window.setScene(modifyPartScene);
        window.showAndWait();
    }

    
    //Go to the add new part screen
    @FXML
    private void addNewPart(ActionEvent event) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New Part");
        window.setMinWidth(250);
        Parent addNewPart = FXMLLoader.load(getClass().getResource("FXMLAddPart.fxml"));
        Scene addPartScene = new Scene (addNewPart);
        window.setScene(addPartScene);
        window.showAndWait();
    }

    
    //Search for parts in the part table
    @FXML
    private void searchParts(ActionEvent event) {
        searchPartData.removeAll();
        System.out.println("Search parts button clicked!");
        String searchItem=partsSearchBox.getText(); 
          if (searchItem.equals("")){
            mainPartTable.getItems().clear();
        }
          else{
    boolean found=false;
    try{
        int partID=Integer.parseInt(searchItem);
        for(Part p: Inventory.getAllParts()){
            if(Inventory.lookupPart(partID) == p){
                System.out.println("This is part "+ partID);
                found=true;
                searchPartData.add(p);
                mainPartTable.setItems(searchPartData);
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
                searchPartData.add(p);
                mainPartTable.setItems(searchPartData);
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
    if(partsSearchBox.getText().isEmpty()){
        mainPartTable.setItems(Inventory.getAllParts());
    }
}
 

    @FXML
    private void deleteSelectedPart(ActionEvent event) {
        System.out.println("delete selected part button clicked!");
        Part selectedPart = mainPartTable.getSelectionModel().getSelectedItem();
        Inventory.deletePart(selectedPart.getPartID());
    }

    @FXML
    private void modifySelectedProduct(ActionEvent event) throws IOException {
        selectedProductID = productsMainTable.getSelectionModel().getSelectedItem().getProductID();
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Modify Part");
        window.setMinWidth(250);
        Parent modifyProduct = FXMLLoader.load(getClass().getResource("FXMLModifyProduct.fxml"));
        Scene modifyProductScene = new Scene (modifyProduct);
        window.setScene(modifyProductScene);
        window.showAndWait();

    }

    @FXML
    private void deleteSelectedProduct(ActionEvent event) {
        System.out.println("Delete selected product button clicked!");
        Product selectedProduct = productsMainTable.getSelectionModel().getSelectedItem();
        Inventory.removeProduct(selectedProduct.getProductID());
    }

    @FXML
    private void searchProducts(ActionEvent event) {
        System.out.println("Search products button clicked!");
    }

    @FXML
    private void addNewProduct(ActionEvent event) throws IOException {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New Product");
        window.setMinWidth(250);
        Parent addNewProduct = FXMLLoader.load(getClass().getResource("FXMLAddProduct.fxml"));
        Scene addProductScene = new Scene (addNewProduct);
        window.setScene(addProductScene);
        window.showAndWait();
    }

    @FXML
    private void exitIMS(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    
}
