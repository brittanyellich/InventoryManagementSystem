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
public class Outsourced extends Part {
    private SimpleStringProperty CompanyName = new SimpleStringProperty("");

    public Outsourced(int partID, String partName, int partInStock,  
            double partPrice, int partMax, int partMin, String companyName) {
        super(partID, partName, partInStock, partPrice, partMin, partMax);
        setCompanyName(companyName);
    }
    
    public String getCompanyName(){
        return CompanyName.get();   
    }
    
    public void setCompanyName(String companyName) {
        CompanyName.set(companyName);
    }
}
