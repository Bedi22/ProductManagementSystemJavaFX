/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.stage.Stage;

/**
 *
 * @author dynam
 */

// Controller Class Handled the Welcome Controller
public class WelcomeController implements  Initializable{
    
    
      @FXML
    private JFXButton enterBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        // Method to Display The Customer Purchase Form
        
        enterBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/views/CustomerPurchaseForm.fxml"));
                    
                    Scene scene = new Scene(root);
                    
                     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
                
            
        });
       
        
    }
    
}
