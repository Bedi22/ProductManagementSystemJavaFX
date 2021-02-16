/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextArea;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dynam
 */

// Product Controller to Handle the Product List Form
public class ProductListController implements Initializable {
    
      @FXML
    private Button backToPurchaseBtn;
      
        @FXML
    private Button showProductsBtn;
        
        
    @FXML
    private JFXTextArea showProductArea;
               
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Method to get the Customer Purchase Form
        backToPurchaseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                 
                try {

                    Parent root = FXMLLoader.load(getClass().getResource("/views/CustomerPurchaseForm.fxml"));
                    Scene home_page_scene = new Scene(root);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    app_stage.setScene(home_page_scene);
                    app_stage.show();
                } catch (IOException ex) {
                    
                }
            }
        });
        
       
        // Method to show the product from  the product file
       showProductsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                try {
                    BufferedReader  bufferedReader = new BufferedReader(new FileReader("D:\\Projects\\NetBeans\\AssignmentOne_BEDI\\src\\resources\\Product.txt"));
                    
                    StringBuffer buffer = new StringBuffer();
                    
                    
                     for (String fileRead = bufferedReader.readLine(); fileRead != null; fileRead = bufferedReader.readLine()) {
                         
                         buffer.append(fileRead);
                         buffer.append("\n");
                         
                         showProductArea.setText(buffer.toString().trim().toUpperCase());
                         
                     }
                    
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ProductListController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ProductListController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               
            }
        });
        
    }    
    
}
