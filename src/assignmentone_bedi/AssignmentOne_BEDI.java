/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentone_bedi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dynam_BEDI
 */

// Main Class Execute JavaFX Application
public class AssignmentOne_BEDI extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        // Loading the Default View Form
        FXMLLoader fXMLLoader= new FXMLLoader();
   
        
       Parent root = fXMLLoader.load(getClass().getResource("/views/WelcomeForm.fxml"));
        
     
        // Creating Scene and Passing View
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
