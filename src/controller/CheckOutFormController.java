/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextArea;
import java.io.BufferedReader;
import java.io.File;
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
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 *
 * @author dynam_BEDI
 */
// JavaFX Controller For Checkout Form
public class CheckOutFormController implements Initializable {

    // Declare Elements and Bind to Checkout Form
    @FXML
    private JFXTextArea checkOutDetailsTextArea;

    @FXML
    private Button invoiceBtn;

    @FXML
    private Button printInvoiceBtn;

    // Method For Initialize the Elements and Make it as Working
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Method to work with the Invoice Button 
        // This Method read the invoice from the cart LIst File and display the invoice
        invoiceBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // Specify the Location of the File 
                File file = new File("D:\\Projects\\NetBeans\\AssignmentOne_BEDI\\src\\resources\\CartList");
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                    String line;
                    StringBuffer stringBuffer = new StringBuffer();

                    // Read the Content from the file and Display in the Text Area
                    while ((line = bufferedReader.readLine()) != null) {

                        stringBuffer.append(line);

                        stringBuffer.append("\n");

                    }

                    
                    checkOutDetailsTextArea.setText(stringBuffer.toString());

                    checkOutDetailsTextArea.setWrapText(true);
                    bufferedReader.close();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CheckOutFormController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CheckOutFormController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        printInvoiceBtn.setOnAction(e -> print(checkOutDetailsTextArea));

    }

    // Method to Print the Invoice
    private void print(Node node) {

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            System.out.println(job.jobStatusProperty().asString());

            boolean printed = job.printPage(node);
            if (printed) {
                job.endJob();
            } else {
                System.out.println("Printing failed.");
            }
        } else {
            System.out.println("Could not create a printer job.");
        }
    }

}
