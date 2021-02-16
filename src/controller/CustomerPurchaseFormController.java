/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Inventory;
import model.InventoryDB;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dynam
 */
// COntroller Class to Handle the Customer Purchase Form
public class CustomerPurchaseFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    // Bind the Elements of the Customer Purchase Form with the Controller
    @FXML
    private TextField codeTextField;

    @FXML
    private TextField productNameTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private JFXButton submitBtn;

    @FXML
    private Label codeValidLabel;

    @FXML
    private Button cartBtn;

    @FXML
    private JFXButton checkOutBtn;

    @FXML
    private TextArea textArreaTotalPrice;

    @FXML
    private JFXButton productListBtn;

    private Alert alert;

    //declare Variable to compare the Values in the product List
    public String tempProductCode;
    public String getCode;
    public String tempItem;
    public double tempPrice;
    public int quantity;

    // Initialize the method to handle all the buttons events.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // This Handle the Product Code Validation 
        checkOutBtn.setVisible(false);
        cartBtn.setVisible(false);
        productListBtn.setVisible(false);
        List<Inventory> inventory = new ArrayList<>();

        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Invalid Alert Box");
                alert.setHeaderText("Product Code Validation");

                try {

                    // Specify the location of the Product File and Read the Product List
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Projects\\NetBeans\\AssignmentOne_BEDI\\src\\resources\\Product.txt"));

                    for (String fileRead = bufferedReader.readLine(); fileRead != null; fileRead = bufferedReader.readLine()) {

                        if (codeTextField.getText().trim().isEmpty()) {

                            codeValidLabel.setText("Please Enter Product Code");
                        }

                        if (quantityTextField.getText().trim().isEmpty()) {

                            codeValidLabel.setText("Quantity is Required");
                        }

                        String[] tokStrings = fileRead.split(",");

                        // Comparing the Values with the product
                        tempProductCode = (tokStrings[0]);

                        getCode = codeTextField.getText();

                        tempItem = tokStrings[1];

                        tempPrice = Double.parseDouble(tokStrings[2]);
                        quantity = Integer.parseInt(quantityTextField.getText());

                        Inventory tempInventory = new Inventory(tempProductCode, tempItem, quantity, tempPrice);

                        inventory.add(tempInventory);
                        
                       

                        if (getCode.equals(tempProductCode)) {
                            productNameTextField.setText(tempItem);

                            priceTextField.setText(Double.toString(tempPrice));
                            codeValidLabel.setStyle("-fx-border-color:green;color:green");
                            codeValidLabel.setText("Valid Code");
                            cartBtn.setVisible(true);
                            break;

                        } else {
                            codeValidLabel.setStyle("-fx-border-color:red;color:red");
                            codeValidLabel.setText("Not Valid Code");
                            cartBtn.setVisible(false);
                            productListBtn.setVisible(true);

                        }

                    }

                    bufferedReader.close();

                    Inventory inventory1 = new Inventory();
                    try {
                        inventory1.setQuantity(quantity);
                    } catch (Exception ex) {
                        alert.setContentText(ex.getMessage());
                        alert.show();
                    }

                } catch (FileNotFoundException ex) {

                    alert.setContentText("File Not Found");
                    alert.show();

                } catch (IOException ex) {

                    alert.setContentText("Check Input Validation");
                    alert.show();

                }

            }
        });

        // Add to Cart Function
        cartBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                checkOutBtn.setVisible(true);
                InventoryDB inventoryDB = new InventoryDB();

                Inventory inventory1 = new Inventory();

                String productName = productNameTextField.getText();

                double productPrice = Double.parseDouble(priceTextField.getText());

                try {

                    inventory1.setProductCode(getCode);

                    inventory1.setProductName(productName);
                    inventory1.setPrice(productPrice);
                    inventory1.setQuantity(quantity);

                    inventoryDB.addToCartList(inventory1);
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Product is Added to the cart");
                    a.showAndWait();
                    clearFields();

                } catch (IOException ex) {

                    alert.setContentText("Quantity should be minium 1");
                    alert.show();

                } catch (Exception ex) {

                    alert.setContentText("Error Entering into the file");
                    alert.show();

                }

            }
        });

        // show the product List Form
        productListBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    Parent root = FXMLLoader.load(getClass().getResource("/views/ProductList.fxml"));
                    Scene home_page_scene = new Scene(root);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    app_stage.setScene(home_page_scene);
                    app_stage.show();
                } catch (IOException ex) {
                    alert.setContentText("Location is Not Found");
                    alert.show();
                }
            }
        });

// Show The CheckOut Form
        checkOutBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    Parent root = FXMLLoader.load(getClass().getResource("/views/CheckOutForm.fxml"));
                    Scene home_page_scene = new Scene(root);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    app_stage.setScene(home_page_scene);
                    app_stage.show();
                } catch (IOException ex) {
                    alert.setContentText("Location is Not Found");
                    alert.show();
                }

            }
        });

    }

    // method to clear fields after purchase
    public void clearFields() {

        codeTextField.clear();
        priceTextField.clear();
        productNameTextField.clear();
        quantityTextField.clear();
        cartBtn.setVisible(false);
        codeValidLabel.setVisible(false);

    }

}
