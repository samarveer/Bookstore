/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Samarveer Sandhu
 */
public class CustomerFX extends Application {
    
    private String username, password, status;  
    private double points;
      
    private String name;
    private double price;
    private double totCost = 0;
    private double totPoints = 0;
    Customer c;
    
    private ObservableList<Book> books = FXCollections.observableArrayList();
    
    FileReader output;

    CustomerFX(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    @Override
    public void start(Stage window) {   
        c = new Customer(username, password, status, points);
        Pane p = new Pane();
      
        Button bt1 = new Button ("Buy");    
        Button bt2 = new Button ("Redeem Points and Buy");
        Button bt3 = new Button ("Logout");
        
        Text msg = new Text(30, 50, "Welcome " + c.getUsername() + ". You have " + c.getPoints() + " Points. Your Status is " + c.getStatus() +".");
        msg.setFont(new Font(12));
        msg.setY(20);
        
        try {
            c.createBookStore();
        } catch (IOException ex) {
            Logger.getLogger(OwnerFX.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        TableView<Book> table = new TableView();
        TableColumn<Book, String> c1 = new TableColumn<>("Book Name");
        TableColumn<Book, String> c2 = new TableColumn<>("Book Price");
        TableColumn c3 = new TableColumn<>("Select");
        
        c1.setCellValueFactory(new PropertyValueFactory<>("name"));
        c2.setCellValueFactory(new PropertyValueFactory<>("price"));
        c3.setCellValueFactory(new PropertyValueFactory<>("select"));
        
        table.getColumns().add(c1);
        table.getColumns().add(c2);
        table.getColumns().add(c3);
        
        table.setLayoutY(30);
        table.setLayoutX(40);
        table.setPrefHeight(150);
        
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        String [] data;
                
        try{
            output = new FileReader("Book.txt");
            BufferedReader read  = new BufferedReader(output);
            
            String line;
            while((line  = read.readLine()) != null){
                data = null;
                data = line.split(",");
                name = data[0];
                price = Double.parseDouble(data[1]);
                if(!name.equals("DELETED")){
                    table.getItems().add(new Book(name, price));
                    books.add(new Book(name, price));
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {                    
        }
    
        bt1.setLayoutY(200);
        bt1.setLayoutX(0);
        bt1.setPrefWidth(75); 

        bt1.setOnAction((ActionEvent event) -> {
            for (Book b : books){
                totCost = b.getTotal();
            }
            totPoints = totCost*10;
            Book b1 = new Book(name, price);
            
            for (int i = 0; i < b1.getCheckedB().size(); i++) {
                try {
                    c.buyBook(""+b1.getCheckedB().get(i));
                } catch (IOException ex) {
                    Logger.getLogger(CustomerFX.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
            p.getChildren().clear();            
            try {
                cost(window);
            } catch (IOException ex) {
                Logger.getLogger(OwnerFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        bt2.setOnAction((ActionEvent event) -> {
            for (Book b : books){
                totCost = b.getTotal();
            }
            totPoints = totCost * 100;            
            p.getChildren().clear();
            try {
                costP(window);
            } catch (IOException ex) {
                Logger.getLogger(OwnerFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        bt2.setLayoutY(200);
        bt2.setLayoutX(85);
        bt2.setPrefWidth(160);  
        
        bt3.setOnAction((ActionEvent event) -> {
            p.getChildren().clear();
            c.changeState(window);
        });

        bt3.setLayoutY(200);
        bt3.setLayoutX(255);
        bt3.setPrefWidth(75);  
        
        p.getChildren().add(bt1);
        p.getChildren().add(bt2);
        p.getChildren().add(bt3);
        p.getChildren().add(msg);
        p.getChildren().add(table);
        
        p.setStyle("-fx-base: rgba(195, 176, 145, 255);");
        
        window.setScene(new Scene(p, 375, 250));
        window.show();
    }
    
    /**
     *
     * @param window
     * @param username
     * @param password
     */
    public void createCustomer(Stage window, String username, String password){
        String [] data;
                
        try{
            output = new FileReader("Customer.txt");
            BufferedReader read  = new BufferedReader(output);
            
            String line;
            
            while((line  = read.readLine()) != null){
                data = null;
                data = line.split(",");
                username = data[0];
                password = data[1];
                
                if(username.equals(username) && password.equals(password)){
                    status = data[2];
                    points = Double.parseDouble(data[3]);
                }
            }
        } catch (FileNotFoundException ex) {         
        } catch (IOException ex) {                    
        }
        start(window);
    }

    
    private void cost(Stage window) throws IOException{
        Pane p = new Pane();
        
        Button bt1 = new Button("Logout");
        c.addPoint(username, totPoints);
        Text costMsg = new Text(30, 50, "Total Cost: " + totCost + ".");
        costMsg.setFont(new Font(12));
        costMsg.setY(20);
        
        Text psMsg = new Text(30, 50, "Points: " + c.getPoints() + ", Status: " + c.getStatus());
        psMsg.setFont(new Font(12));
        psMsg.setY(40);
        
        bt1.setLayoutY(50);
        bt1.setLayoutX(65);
        bt1.setPrefWidth(75);  
        
        p.getChildren().add(costMsg);
        p.getChildren().add(psMsg);
        p.getChildren().add(bt1);

        window.setScene(new Scene(p, 330, 250));
        window.show();
        
        p.setStyle("-fx-base: rgba(60, 60, 60, 255);");
        
        bt1.setOnAction((ActionEvent event) -> {
            p.getChildren().clear();
            c.changeState(window);
        });       
    }
    
    private void costP(Stage window) throws IOException{
        Pane p = new Pane();
        
        Button bt1 = new Button ("Logout");
        c.redeemPoints(username, totPoints);
        Text costMsg = new Text(30, 50, "Total points redeemed: " + totPoints + ".");
        
        costMsg.setFont(new Font(12));
        costMsg.setY(20);
        
        Text psMsg = new Text(30, 50, "Points: " + c.getPoints() + ", Status: " + c.getStatus());
        psMsg.setFont(new Font(12));
        psMsg.setY(40);
        
        bt1.setLayoutY(50);
        bt1.setLayoutX(65);
        bt1.setPrefWidth(75);  
        
        p.getChildren().add(costMsg);
        p.getChildren().add(psMsg);
        p.getChildren().add(bt1);

        window.setScene(new Scene(p, 330, 250));
        window.show();
        
        p.setStyle("-fx-base: rgba(60, 60, 60, 255);");
        
        bt1.setOnAction((ActionEvent event) -> {
            p.getChildren().clear();
            c.changeState(window);
        });       
    }
    /**
     * 
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
