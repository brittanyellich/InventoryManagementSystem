/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Britt
 */
public class Inhouse extends Part {
    private SimpleIntegerProperty MachineID = new SimpleIntegerProperty(0);

    public Inhouse(int partID, String partName, int partInStock,  
            double partPrice, int partMax, int partMin, int machineID) {
        super(partID, partName, partInStock, partPrice, partMin, partMax);
        setMachineID(machineID);
    }
    
    public int getMachineID(){
        return MachineID.get();
    }
    
    public void setMachineID(int machineID) {
        MachineID.set(machineID);
    }
}
