/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.util.ArrayList;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Britt
 */
public class Product {
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private SimpleIntegerProperty ProductID = new SimpleIntegerProperty(0);
    private SimpleStringProperty ProductName = new SimpleStringProperty("");
    private SimpleDoubleProperty ProductPrice = new SimpleDoubleProperty(0.0);
    private SimpleIntegerProperty ProductInStock = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty ProductMax = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty ProductMin = new SimpleIntegerProperty(0);
    
    
    private Part part;

    public Product(){
        
    }
    
    public Product(int productID, String name, int inv,  
            double price, int max, int min, ObservableList currentProductParts) {
        setProductName(name);
        setProductID(productID);
        setProductInStock(inv);
        setProductPrice(price);
        setProductMax(max);
        setProductMin(min);
        setAssociatedParts(currentProductParts);
    }
    
    public void setAssociatedParts(ObservableList parts) {
        //Set list of parts to the associated parts for a product
        this.associatedParts = parts;
    }
    
    public void setProductID(int productID) {
        ProductID.set(productID);
    }
    
    public int getProductID() {
        return ProductID.get();
    }
    
     public IntegerProperty ProductIDProperty(){
        return ProductID;
    }
    
    public void setProductName(String name) {
        ProductName.set(name);
    }
    
    public String getProductName() {
        return ProductName.get();
    }
    
     public StringProperty ProductNameProperty(){
        return ProductName;
    }
    
    public void setProductPrice(double price) {
        ProductPrice.set(price);
    }
    
    public double getProductPrice() {
        return ProductPrice.get();
    }
    
     public DoubleProperty ProductPriceProperty(){
        return ProductPrice;
    }
    
    public void setProductInStock(int inv) {
        ProductInStock.set(inv);
    }
    
    public int getProductInStock() {
        return ProductInStock.get();
    }
    
     public IntegerProperty ProductInStockProperty(){
        return ProductInStock;
    }
    
    public void setProductMax(int max) {
        ProductMax.set(max);
    }
    
    public int getProductMax() {
        return ProductMax.get();
    }
    
    public void setProductMin(int min) {
        ProductMin.set(min);
    }
    
    public int getProductMin(){
        return ProductMin.get();
    }
    
    public void addAssociatedPart(Part newPart) {
        //add a part to the arraylist for the product
        associatedParts.add(newPart);
    }
    
    public boolean removeAssociatedPart(int partID) {
        //remove a part based on the partID from the product array list
        for(Part p:associatedParts){
            if(p.getPartID()== partID){
                associatedParts.remove(p);
            }
        }
        return true;
    }
    
    public Part lookupAssociatedPart(int partID) {
        //lookup an associated part in the part array list based on the partID
        for(Part p : associatedParts) {
            if(p.getPartID()== partID){
                return p;
            } else {
            }
        } throw new IllegalStateException("ID: " + partID + " is not in the list");
    }
    
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }
    
}
