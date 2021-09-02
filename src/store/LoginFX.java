/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Samarveer Sandhu
 */
public class LoginFX extends Application {
    
    String username;
    String password;
    
   @Override
    public void start(Stage window) {
        GridPane gp = new GridPane();
        Scene s = new Scene(gp, 260, 200);
        
        TextField user = new TextField();
        PasswordField pass = new PasswordField();
        
        Button bt1 = new Button();
        bt1.setText("Login");
        Label l1 = new Label("Username: ");
        Label l2 = new Label("Password: ");
        l1.setTranslateY(50);
        l1.setTranslateX(16);
        user.setTranslateX(20);
        user.setTranslateY(50);
        l2.setTranslateY(60);
        l2.setTranslateX(16);
        pass.setTranslateX(20);
        pass.setTranslateY(60);
        bt1.setTranslateX(110);
        bt1.setTranslateY(80);
        
        
        bt1.setOnAction((ActionEvent event) -> {
            username = user.getText();
            password = pass.getText();
            
            gp.getChildren().clear();
            
            if(username.equalsIgnoreCase("Admin") && password.equalsIgnoreCase("Admin")){
                OwnerFX o = new OwnerFX(username, password);
                o.start(window);
            }
            if(!username.equalsIgnoreCase("admin")){
                Customer cust = new Customer(username, password, "Null", 0);
                if(cust.check(username, password) == true){
                    CustomerFX c = new CustomerFX(username, password);
                    c.createCustomer(window, username, password);
                }
            }
        });
        
        gp.addRow(0, l1, user);
        gp.addRow(1, l2, pass);
        gp.addRow(2, bt1);
        gp.setStyle("-fx-base: rgba(195, 176, 145, 255);");
        bt1.setStyle("-fx-base: rgba(60, 60, 60, 255);");
        window.setTitle("Login");
        window.setScene(s);
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
