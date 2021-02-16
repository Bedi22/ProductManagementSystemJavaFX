/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;

/**
 *
 * @author dynam
 */

// Inventory Class 
public class Inventory {

    // Declare the variables
    
    private static int count = 1000;

    private String productCode;
    private String productName;
    private int quantity;
    private double price;

    private int billNo = count++;
    
    String datePattern = "EEEEE MMMMM yyyy";
    SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
    String billDate = dateFormat.format(new Date());

   // private Date billDate;
    
    // Constructor With the parameters
 
    public Inventory(String productCode, String productName, int quantity, double price) {

        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;

    }

    // Constructor with additional Parameters
    
    public Inventory(String productCode, String productName, int quantity, double price, int billNo, String billDate) {

        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.billDate = billDate;
        this.billNo = billNo;

    }

    
    // Empty Contructor
    public Inventory() {

    }

    
    
    // getter and setter method
    public int getBillNo() {
        return billNo;
    }

    public void setBillNo(int billNo) {
        this.billNo = billNo;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public int getQuantity() {

        return quantity;
    }

    // Validate the Quantity using the Exception
    public void setQuantity(int quantity) throws Exception {

        if (quantity == 0) {

            throw new InputMismatchException("Atleast Minimum Quanity is 1");
        } else {
            this.quantity = quantity;
        }

    }

    public String getProductCode() {
        return productCode;
    }

    // Validate the Product Code using the Exception
    public void setProductCode(String productCode) throws InputMismatchException {

        if (productCode.equals("")) {

            throw new InputMismatchException("Product Code is Required");
        } else {

            this.productCode = productCode;

        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // method to get the Price based on the quantities and Price of Perticular Product
    public String price() {
        
        double prices = quantity * price;
        return String.format("%.2f", prices) ;
    }

    // Method to get the total price including the VAT
    public String totalPrice() {
        
        double totalPrice = quantity * price * 1.13;

        return String.format("%.2f",totalPrice);
    }

    // Method to get the VAT Amount
    public String taxAmount() {
        
        double totalAmount = quantity * price * 0.13; 

        return String.format("%.2f",totalAmount);
    }

    // getting all the grand Total
    public void grandTotal() {

        ArrayList<Inventory> inventorys = new ArrayList<>();

        for (Inventory inventory : inventorys) {

            inventory.totalPrice();
        }

    }
    
    // To String Method
  
    @Override
    public String toString() {

        return "Product Code is  " + getProductCode() 
                + "Product Name " + getProductName() 
                + "\n" + "Product Price " + String.format("%2f", getPrice());

    }

}
