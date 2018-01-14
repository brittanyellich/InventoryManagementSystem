/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Britt
 */
public class Inventory extends Observable {
//    public ArrayList<Part> allParts = new ArrayList<Part>();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
//These need to be ArrayLists...
    //Somehow need to reference
    private static ObservableList<Product> products = FXCollections.observableArrayList();
    private Product Product;
    private Part Part;
    private Outsourced Outsourced;
    private Inhouse Inhouse;

    
    
    public Inventory(){
//        this.products = FXCollections.observableArrayList();
//        this.allParts = FXCollections.observableArrayList();
    }
    
    
    public static void addProduct(Product newProduct) {
        //add product to the products array list
        products.add(newProduct);
    }
    
    public static boolean removeProduct(int productID) {
        //remove product from the product array list by the productID
        products.remove(productID);
            return true;
    }
    
    public static Product lookupProduct(int productID) {
        for(Product p : products) {
            if(p.getProductID() == productID){
                return p;
            }
        } throw new IllegalStateException("ID: " + productID + " is not in the list");
    }
    
    public static void updateProduct(int productID, Product product) {
        //update product by productID
        Product productToUpdate = lookupProduct(productID);
        System.out.println(productToUpdate);
        int productToUpdateIndex = products.indexOf(productToUpdate);
        if(productToUpdate.getProductID() == productID) {
            products.set(productToUpdateIndex, product);
        }
        
    }
    
    public static void addPart(Part newPart) {
        //add part to the part ArrayList
        allParts.add(newPart);
    }
   
    public static boolean deletePart(int partID) {
        //delete part from the part ArrayList
        for(Part p:allParts){
            if(p.getPartID()== partID){
                allParts.remove(p);
            }
        }
        return true;
    }
    
    public static Part lookupPart(int partID) {
        //lookup the part in the part arraylist
        for(Part p : allParts) {
            if(p.getPartID() == partID){
                return p;
            }
        } throw new IllegalStateException("ID: " + partID + " is not in the list");
    }
    
    
    public static void updatePart(int partID, Part part) {
        //update part by part ID
//        lookupPart(partID);
        Part partToUpdate = lookupPart(partID);
        System.out.println(partToUpdate);
        int partToUpdateIndex = allParts.indexOf(partToUpdate);
        if(partToUpdate.getPartID() == partID) {
            allParts.set(partToUpdateIndex, part);
        }
    }
    
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts() {
        return products;
    }
}
