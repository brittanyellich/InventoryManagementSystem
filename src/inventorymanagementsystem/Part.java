/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;
import javafx.beans.property.*;

/**
 *
 * @author Britt
 */
public abstract class Part {   
    private SimpleIntegerProperty PartID = new SimpleIntegerProperty(0);
    private SimpleStringProperty PartName = new SimpleStringProperty("");
    private SimpleDoubleProperty PartPrice = new SimpleDoubleProperty(0.0);
    private SimpleIntegerProperty PartInStock = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty PartMin = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty PartMax = new SimpleIntegerProperty(0);
    
    public Part(){
        
    }
    
    public Part(int partID, String partName, int partInStock,  
            double partPrice, int partMax, int partMin) {
        setPartID(partID);
        setPartName(partName);
        setPartInStock(partInStock);
        setPartPrice(partPrice);
        setPartMax(partMax);
        setPartMin(partMin);
    }
   
    public void setPartID(int partID) {
        PartID.set(partID);
    }
    
    public int getPartID() {
        return PartID.get();
    }
    
    public IntegerProperty PartIDProperty(){
        return PartID;
    }
    
    public void setPartName(String name) {
        PartName.set(name);
    }
    
    public String getPartName() {
        return PartName.get();
    }
    
    public StringProperty PartNameProperty(){
        return PartName;
    }
    
    public void setPartPrice(double price) {
        PartPrice.set(price);
    }
    
    public double getPartPrice() {
        return PartPrice.get();
    }
    
    public DoubleProperty PartPriceProperty(){
        return PartPrice;
    }
    
    public void setPartInStock(int inStock) {
        PartInStock .set(inStock);
    }
    
    public int getPartInStock() {
        return PartInStock.get();
    }
    
    public IntegerProperty PartInStockProperty(){
        return PartInStock;
    }
    
    public void setPartMax(int max) {
        PartMax.set(max);
    }
    
    public int getPartMax() {
        return PartMax.get();
    }
    
    public void setPartMin(int min) {
        PartMin.set(min);
    }
    
    public int getPartMin() {
        return PartMin.get();
    }
}
