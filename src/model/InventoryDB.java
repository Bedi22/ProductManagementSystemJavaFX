/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author dynam
 */
public class InventoryDB {

   

    // Method to Write The generated Bill to the File Using Buffered Writer
    
    public void addToCartList(Inventory inventory) throws IOException {

        File file = new File("D:\\Projects\\NetBeans\\AssignmentOne_BEDI\\src\\resources\\CartList");

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));

        bufferedWriter.write("Bill # " + Integer.toString(inventory.getBillNo()) + "\n");
        bufferedWriter.write("DATE " + inventory.getBillDate() + "\n");
        bufferedWriter.newLine();
        bufferedWriter.write("Quantity"+"\t" +"Code"+"\t"+"Product Name"+"\t\t"+"Price");
        bufferedWriter.newLine();
        bufferedWriter.write(Integer.toString(inventory.getQuantity()) + "\t\t");
        bufferedWriter.write((inventory.getProductCode()) + "\t\t");
        bufferedWriter.write(inventory.getProductName() + "\t\t");
        bufferedWriter.write(Double.toString(inventory.getPrice()) + "\n");

        bufferedWriter.newLine();
        bufferedWriter.write("SubTotal " + (inventory.price()) + "\n");

        bufferedWriter.write("Taxes @ 13% " + (inventory.taxAmount()) + "\n");

        bufferedWriter.write("Total " + (inventory.totalPrice()) + "\n");
         bufferedWriter.newLine();

        bufferedWriter.flush();

        bufferedWriter.close();

    }

}
