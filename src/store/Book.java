/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import java.util.ArrayList;
import javafx.scene.control.CheckBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Samarveer Sandhu
 */

public class Book {
    
    //Initializing instance variables
    private SimpleStringProperty name; 
    private SimpleDoubleProperty price; 
    private CheckBox sel;
    private static boolean check;
    private static double total;
    private static ObservableList<Book> books = FXCollections.observableArrayList();
    private static ArrayList<String> checkedB = new ArrayList<String>();

    /**
     *
     * @param name
     * @param price
     */
    public Book(String name, double price){
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.sel = new CheckBox();
                   
        sel.selectedProperty().addListener(
        (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            check = sel.isSelected();
            if (check == true) {
               checkedB.add(name);
               total += price;
            } else {
               total -= price;
               checkedB.remove(name);
            }  
        });
    }
    
    /**
     *
     * @return
     */
    public String getName(){
        return name.get();
    }
    
    /**
     *
     * @param name
     */
    public void setName(String name){
        this.name.set(name);
    }
    
    /**
     *
     * @return
     */
    public double getPrice(){
        return price.get();
    }
    
    /**
     *
     * @param price
     */
    public void setPrice(double price){
        this.price.set(price);
    }
    
    /**
     *
     * @return
     */
    public CheckBox getSel() {
        return sel;
    }
    
    /**
     *
     * @param select
     */
    public void setSel(CheckBox select) {
        this.sel = select;
    }
    
    /**
     *
     * @return
     */
    public double getTotal() {
        return total;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Book> getBooks () {
        return books;
    }
    
    /**
     *
     * @return
     */
    public ArrayList getCheckedB() {
        return checkedB;
    }
       
    @Override
    public String toString(){
        return "" +name+ ", $" +price+ "\n";
    }
}
