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
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Samarveer Sandhu
 */
public class OwnerFX extends Application {
    String username;
    String password;
    double points = 0;
    String status = null;
    
    String name;
    double price;
    FileReader output;

    Owner o = new Owner(username, password);
    
    /**
     *
     * @param username
     * @param password
     */
    public OwnerFX(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    @Override
    public void start(Stage window) {
        Pane p = new Pane();
        
        Button btnb = new Button("Books");
        Button btnc = new Button("Customers");
        Button btnl = new Button("Logout");
        
        btnb.setLayoutY(50);
        btnb.setLayoutX(115);
        btnb.setPrefWidth(75);
        
        btnc.setLayoutY(100);
        btnc.setLayoutX(115);
        btnc.setPrefWidth(75);
        
        btnl.setLayoutY(150);
        btnl.setLayoutX(115);
        btnl.setPrefWidth(75);
        
        btnb.setOnAction((ActionEvent event) -> {
            p.getChildren().clear();
            try {
                o.createBookStore();
                BooksFX(window);
            } catch (IOException ex) {
                Logger.getLogger(OwnerFX.class.getName()).log(Level.SEVERE, null, ex);
            } 
        });
        
        btnc.setOnAction((ActionEvent event) -> {
            p.getChildren().clear();
            try {
                o.createCustomer();
                CusFX(window);
            } catch (IOException ex) {
                Logger.getLogger(OwnerFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        btnl.setOnAction((ActionEvent event) -> {
            p.getChildren().clear();
            o.changeState(window);
        });
        
        p.getChildren().add(btnb);
        p.getChildren().add(btnc);
        p.getChildren().add(btnl);
        p.setStyle("-fx-base: rgba(195, 176, 145, 255);");
        
        window.setScene(new Scene(p, 300, 250));
        window.show();
    }
    
    private void BooksFX(Stage window) throws IOException{
        Button bt1 = new Button("Add");
        Button bt2 = new Button("Delete");
        Button bt3 = new Button("Back");
        
        TextField bName = new TextField();
        TextField bPrice = new TextField();
        TableView table = new TableView();
        
        bName.setPromptText("Name of Book");
        bPrice.setPromptText("Price of Book");
         
        TableColumn<Book, String> c1 = new TableColumn<>("Book Name");
        TableColumn<Book, String> c2 = new TableColumn<>("Book Price");
        c1.setCellValueFactory(new PropertyValueFactory<>("name"));
        c2.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        table.getColumns().add(c1);
        table.getColumns().add(c2);
        
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        bt1.setOnAction((ActionEvent event) -> {
            name = bName.getText();
            price = Double.parseDouble(bPrice.getText());
            try {
                o.addBook(name, price);
            } catch (IOException ex) {
                
            }
            table.getItems().add(new Book(name, price));
        });
        
        bt2.setOnAction((ActionEvent event) -> {
            Book b1;
            b1 = (Book)table.getSelectionModel().getSelectedItem();
            table.getItems().removeAll(b1);
            try {
                o.deleteBook(b1.getName(), b1.getPrice());
            } catch (IOException ex) {
                Logger.getLogger(OwnerFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        bt3.setOnAction((ActionEvent event) -> {
            start(window);
        });
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
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
                    
        }
        
        HBox h = new HBox();
        h.getChildren().addAll(bName, bPrice, bt1, bt2, bt3);
        
        VBox v = new VBox(table);
        v.getChildren().add(h);
        
        Scene scene = new Scene(v);
        window.setScene(scene);
    }
    
    private void CusFX(Stage window) throws IOException{
        Button bt1 = new Button ("Add");    
        Button bt2 = new Button ("Delete");
        Button bt3 = new Button ("Back");
        
        TextField user = new TextField();
        TextField pass = new TextField();
        TableView <Customer> table = new TableView();
        
        user.setPromptText("Username");
        pass.setPromptText("Password");
      
        TableColumn<Customer, String> c1 = new TableColumn<>("Username");
        TableColumn<Customer, String> c2 = new TableColumn<>("Password");
        TableColumn<Customer, Integer> c3 = new TableColumn<>("Points");
        TableColumn<Customer, String> c4 = new TableColumn<>("Status");
        
        c1.setCellValueFactory(new PropertyValueFactory<>("username"));
        c2.setCellValueFactory(new PropertyValueFactory<>("password"));
        c3.setCellValueFactory(new PropertyValueFactory<>("points"));
        c4.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        table.getColumns().add(c1);
        table.getColumns().add(c2);
        table.getColumns().add(c3);
        table.getColumns().add(c4);
        
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        bt1.setOnAction((ActionEvent event) -> {
            username = user.getText();
            password = pass.getText();
            points = 0;
            status = "S";
            try {
                o.addCustomer(username, password, status, points);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            table.getItems().add(new Customer(username, password, status, points));
            user.setText("");
            pass.setText("");
        });
        
        bt2.setOnAction((ActionEvent event) -> {
            Customer c5;
            c5 = (Customer)table.getSelectionModel().getSelectedItem();
            table.getItems().removeAll(c5);
            try {
                o.deleteCustomer(c5.getUsername(), c5.getPassword(), c5.getStatus(), c5.getPoints());
            }catch (IOException ex) {
                Logger.getLogger(OwnerFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        bt3.setOnAction((ActionEvent event) -> {
            start(window);
        });
        
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
                status=data[2];
                points=Double.parseDouble(data[3]);
                
                if(!username.equals("DELETED")){
                    table.getItems().add(new Customer(username, password, status, points));
                }
            }
        } catch (FileNotFoundException ex) {
                    
        } catch (IOException ex) {
                    
        }
        
        HBox h = new HBox();
        h.getChildren().addAll(user, pass, bt1, bt2, bt3);
        
        VBox v = new VBox(table);
        v.getChildren().add(h);
        Scene scene = new Scene(v);
        window.setScene(scene);
        window.show();    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
