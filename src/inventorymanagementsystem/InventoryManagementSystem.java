/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import inventorymanagementsystem.Part;
import inventorymanagementsystem.Inventory;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Britt
 */
public class InventoryManagementSystem extends Application {
    public static Scene mainScene;
    static Stage stage1;
    static Stage stage2;
    @Override
    public void start(Stage stage) throws Exception {
        stage1 = stage;
        
        Parent main = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        mainScene = new Scene(main);
        
        stage.setScene(mainScene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //Part List Additions
        
        
    }
    
}
