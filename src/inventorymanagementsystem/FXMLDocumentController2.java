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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import inventorymanagementsystem.*;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import inventorymanagementsystem.*;
import java.text.Format;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

/**
 *
 * @author Britt
 */


public class FXMLDocumentController2 implements Initializable {
    
    private Part part;
    private Product product;
    private static Inventory inventory = new Inventory();
    private Inhouse inhouse;
    private Outsourced outsourced;
    
//        @FXML private TableView<Part> mainPartTable  = new TableView(inventory.getAllParts());
    @FXML private TableView<Part> mainPartTable;
//    @FXML private ObservableList<Part> partData = FXCollections.observableArrayList(inventory.getAllParts());
//    @FXML private TableColumn<Part, Integer> PartIDCol = new TableColumn<>("Part ID"); //int
//    @FXML private TableColumn<Part, String> PartNameCol  = new TableColumn<>("Part Name"); //string
//    @FXML private TableColumn<Part, Integer> PartInStockCol  = new TableColumn<>("Inventory Level"); //int
//    @FXML private TableColumn<Part, Double> PartPriceCol  = new TableColumn<>("Price/Cost per Unit"); //double
//    
    @FXML private TableColumn<Part, Integer> PartIDCol; //int
    @FXML private TableColumn<Part, String> PartNameCol; //string
    @FXML private TableColumn<Part, Integer> PartInStockCol; //int
    @FXML private TableColumn<Part, Double> PartPriceCol; //double
    
    
    
    
    //Handle moving between different scenes
    //Move from main IMS scene to Add Part Scene
    @FXML
    private void addNewPart(ActionEvent event) throws IOException {
        Parent addNewPart = FXMLLoader.load(getClass().getResource("FXMLAddPart.fxml"));
        Scene newPartScene = new Scene(addNewPart);
        Stage imsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        imsStage.setScene(newPartScene);
        imsStage.show();

    }
    
    //Press cancel button on add part scene and move back to main IMS screen
    @FXML
    private void addPartCancelButtonClicked(ActionEvent event) throws IOException {
        Parent imsScene = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage imsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        imsStage.setScene(InventoryManagementSystem.mainScene);
        imsStage.show();
        PartIDCol.setCellValueFactory(cellData -> cellData.getValue().PartIDProperty().asObject());
        PartNameCol.setCellValueFactory(cellData -> cellData.getValue().PartNameProperty());
        PartInStockCol.setCellValueFactory(cellData -> cellData.getValue().PartInStockProperty().asObject());
        PartPriceCol.setCellValueFactory(cellData -> cellData.getValue().PartPriceProperty().asObject());
        System.out.println(inventory.getAllParts());
        mainPartTable.setItems(inventory.getAllParts());
        
    }
    
    //Move from main IMS screen to modify part screen
    @FXML
    private void modifySelectedPart(ActionEvent event) throws IOException {
        Parent modifyNewPart = FXMLLoader.load(getClass().getResource("FXMLModifyPart.fxml"));
        Scene modifyPartScene = new Scene(modifyNewPart);
        Stage imsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        imsStage.setScene(modifyPartScene);
        imsStage.show();
    }
    
    //Press cancel button on add part scene and move back to main IMS screen
    @FXML
    private void modifyPartCancelButtonClicked(ActionEvent event) throws IOException {
        Parent imsScene = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene mainScene = new Scene(imsScene);
        Stage imsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        imsStage.setScene(mainScene);
        imsStage.show();
    }
    
    //Move from main IMS screen to add product screen
    @FXML
    private void addNewProduct(ActionEvent event) throws IOException {
        Parent addNewProduct = FXMLLoader.load(getClass().getResource("FXMLAddProduct.fxml"));
        Scene addProductScene = new Scene(addNewProduct);
        Stage imsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        imsStage.setScene(addProductScene);
        imsStage.show();
    }
    
    //Press cancel button on add part scene and move back to main IMS screen
    @FXML
    private void addProductCancelButtonClicked(ActionEvent event) throws IOException {
        Parent imsScene = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene mainScene = new Scene(imsScene);
        Stage imsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        imsStage.setScene(mainScene);
        imsStage.show();
    }
    
    //Move from main IMS screen to modify product screen
    @FXML
    private void modifySelectedProduct(ActionEvent event) throws IOException {
        Parent modifyProduct = FXMLLoader.load(getClass().getResource("FXMLModifyProduct.fxml"));
        Scene modifyProductScene = new Scene(modifyProduct);
        Stage imsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        imsStage.setScene(modifyProductScene);
        imsStage.show();
    }
    
    //Press cancel button on add part scene and move back to main IMS screen
    @FXML
    private void modifyProductCancelButtonClicked(ActionEvent event) throws IOException {
        Parent imsScene = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene mainScene = new Scene(imsScene);
        Stage imsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        imsStage.setScene(mainScene);
        imsStage.show();
    }
    
    //Main Page Part Table View

    
    @FXML 
    void updatePartsTable(ActionEvent event) {
        
    }
    
//    //From example
//    public class FormattedTableCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
//
//    private TextAlignment alignment;
//    private Format format;
//
//    public TextAlignment getAlignment() {
//        return alignment;
//    }
//
//    public void setAlignment(TextAlignment alignment) {
//        this.alignment = alignment;
//    }
//
//    public Format getFormat() {
//        return format;
//    }
//
//    public void setFormat(Format format) {
//        this.format = format;
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public TableCell<S, T> call(TableColumn<S, T> p) {
//        TableCell<S, T> cell = new TableCell<S, T>() {
//
//            @Override
//            public void updateItem(Object item, boolean empty) {
//                if (item == getItem()) {
//                    return;
//                }
//                super.updateItem((T) item, empty);
//                if (item == null) {
//                    super.setText(null);
//                    super.setGraphic(null);
//                } else if (format != null) {
//                    super.setText(format.format(item));
//                } else if (item instanceof Node) {
//                    super.setText(null);
//                    super.setGraphic((Node) item);
//                } else {
//                    super.setText(item.toString());
//                    super.setGraphic(null);
//                }
//            }
//        };
//        cell.setTextAlignment(alignment);
//        switch (alignment) {
//            case CENTER:
//                cell.setAlignment(Pos.CENTER);
//                break;
//            case RIGHT:
//                cell.setAlignment(Pos.CENTER_RIGHT);
//                break;
//            default:
//                cell.setAlignment(Pos.CENTER_LEFT);
//                break;
//        }
//        return cell;
//    }
//}
    
//    TableColumn<Part, Integer> pID = new TableColumn<>("Part ID");
//        pID.setCellValueFactory(new PropertyValueFactory<>("partID"));
//        partsList.getColumns().add(pID);
//        
//        TableColumn<Part, String> partName = new TableColumn<>("Part Name");
//        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
//        partsList.getColumns().add(partName);
//        
//        TableColumn<Part, Integer> partInStock = new TableColumn<>("Inventory Level");
//        partInStock.setCellValueFactory(new PropertyValueFactory<>("inStock"));
//        partsList.getColumns().add(partInStock);
//        
//        TableColumn<Part, Double> partPrice = new TableColumn<>("Price/Cost per Unit");
//        partPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        partsList.getColumns().add(partPrice);

//    @FXML public ObservableList<Part> partData=FXCollections.observableArrayList(Inventory.allParts);
    
//    @FXML private final ObservableList<Part> partData = FXCollections.observableList(Inventory.getAllParts());
    
    //Add Part Functionality
    //Declare Text Fields
    @FXML private TextField addPartIDEntry; //int, will eventually generate on its own
    @FXML private TextField addPartNameEntry; //string
    @FXML private TextField addPartInvEntry; //int
    @FXML private TextField addPartPriceEntry; //double
    @FXML private TextField addPartMaxEntry; //int
    @FXML private TextField addPartMinEntry; //int
    @FXML private TextField addPartCompNameEntry; //string
    @FXML private TextField addPartMachIDEntry;
    @FXML private ToggleGroup addPart;

    
    @FXML
    void addPartSaveButtonClicked(ActionEvent event)  {
//        try{
           System.out.println("Add Part Save Button Clicked");
//        partData = mainPartTable.getItems();
        String partID = addPartIDEntry.getText();
        String partName = addPartNameEntry.getText();
        String partInStock = addPartInvEntry.getText();
        String partPrice = addPartPriceEntry.getText();
        String partMax = addPartMaxEntry.getText();
        String partMin = addPartMinEntry.getText();
        String partCompName = addPartCompNameEntry.getText();
        String partMachID = addPartMachIDEntry.getText();
        RadioButton selectedRadioButton = (RadioButton) addPart.getSelectedToggle();
        String toggle = selectedRadioButton.getText();
        if(toggle.equals("In-House")){
            System.out.println("Adding In-House Part!");
            inventory.addPart(new Inhouse((Integer.parseInt(partID)), partName, 
                Integer.parseInt(partInStock), Double.parseDouble(partPrice), 
                Integer.parseInt(partMax),Integer.parseInt(partMin), 
                Integer.parseInt(partMachID)));
        }else if (toggle.equals("Outsourced")){
            System.out.println("Adding Outsourced Part!");
            inventory.addPart(new Outsourced((Integer.parseInt(partID)), partName, 
                Integer.parseInt(partInStock), Double.parseDouble(partPrice), 
                Integer.parseInt(partMax),Integer.parseInt(partMin), 
                partCompName));
        }
        System.out.println("At 291");
//        mainPartTable.getColumns();
//        mainPartTable.getColumns().addAll(PartIDCol, PartNameCol, PartInStockCol, PartPriceCol);
        System.out.println(mainPartTable.toString()+ "at line 294");
        addPartIDEntry.setText("");
        addPartNameEntry.setText("");
        addPartInvEntry.setText("");
        addPartPriceEntry.setText("");
        addPartMaxEntry.setText("");
        addPartMinEntry.setText("");
        addPartCompNameEntry.setText(""); 
        addPartMachIDEntry.setText("");
//        } catch(NullPointerException e) {
//            e.getMessage();
//        }
        
    }
    //Set up toggle for radio buttons
    @FXML Label machineIDLabel = new Label();
    @FXML Label companyNameLabel = new Label();

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
    
    
    //Modify Part Functionality
    @FXML
    private void modifyPartSaveButtonClicked(ActionEvent event) {
        System.out.println("Modify Part Save Button Clicked");
    }
    
    @FXML
    private void modifyPartInHouseRadioButtonSelected(ActionEvent event) {
        System.out.println("In House Radio button Selected");
    }
    
    @FXML
    private void modifyPartOutsourcedRadioButtonSelected(ActionEvent event) {
        System.out.println("Outsourced Radio button Selected");
    }
    
    //Add Product Functionality
    @FXML
    private void addProductSearchButtonClicked(ActionEvent event) {
        System.out.println("Add Product Search Button Clicked");
    }
   
    @FXML
    private void addProductAddButtonClicked(ActionEvent event) {
        System.out.println("Add Product Add Button Clicked");
    }
    
    @FXML
    private void addProductDeleteButtonClicked(ActionEvent event) {
        System.out.println("Add Product Delete Button Clicked");
    }
    
    @FXML
    private void addProductSaveButtonClicked(ActionEvent event) {
        System.out.println("Add Product Save Button Clicked");
    }
    
    //Modify Product Functionality
    @FXML
    private void modifyProductSearchButtonClicked(ActionEvent event) {
        System.out.println("Modify Product Search Button Clicked");
    }
   
    @FXML
    private void modifyProductAddButtonClicked(ActionEvent event) {
        System.out.println("Modify Product Add Button Clicked");
    }
    
    @FXML
    private void modifyProductDeleteButtonClicked(ActionEvent event) {
        System.out.println("Modify Product Delete Button Clicked");
    }
    
    @FXML
    private void modifyProductSaveButtonClicked(ActionEvent event) {
        System.out.println("Modify Product Save Button Clicked");
    }
    
    //Main Page Functionality
    @FXML
    private void deleteSelectedPart(ActionEvent event) {
        System.out.println("Delete Selected Part Button Clicked");
    }
    
    @FXML
    private void searchParts(ActionEvent event) {
        System.out.println("Search Parts Button Clicked");
    }
    
    
    @FXML
    private void deleteSelectedProduct(ActionEvent event) {
        System.out.println("Delete Selected Product Button Clicked");
    }
    
    @FXML
    private void searchProducts(ActionEvent event) {
        System.out.println("Search Products Button Clicked");
    }
        
    @FXML
    private void exitIMS(ActionEvent event) {
        System.out.println("Exit Inventory Management System Button Clicked");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        

    }    
    
}
