/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.stage.Stage;

/**
 *
 * @author Samarveer Sandhu
 */
public class Customer extends User{
    
    private String username;
    private String password;
    private String status;
    private double points;
    
    private String data1 = "Name Of The Wind,50\n"
            + "The Way Of Kings,45";   
    
    File file;
    FileReader output; 
    FileWriter input; 
    BufferedReader read; 
    BufferedWriter write;
    
    /**
     *
     * @param userName
     * @param password
     * @param status
     * @param points
     */
    public Customer(String userName, String password, String status, double points){
        this.username = userName;
        this.password = password; 
        this.status = status;
        this.points = points;
    }
    
    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }
    
    /**
     *
     * @param username
     */
    public void setUsername(String username){
        this.username = username;
    }
    
    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }
    
    /**
     *
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
    }
    
    /**
     *
     * @return
     */
    public double getPoints() {
        return points;
    }
    
    /**
     *
     * @param points
     */
    public void setPoints(double points){
        this.points = points;
    }

    /**
     *
     * @return
     */
    public String getStatus () {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     *
     * @param name
     * @throws IOException
     */
    public void buyBook(String name) throws IOException{ 
        output = new FileReader("Book.txt");
        read = new BufferedReader(output);
        
        String curr, next;
        String s = "";
        while((curr = read.readLine()) != null){
            s += curr + "\r\n";
        }
        read.close();
        next = s.replaceAll(name, "DELETED");
        input = new FileWriter("Book.txt");
        input.write(next);
        input.close();
    }

    /**
     *
     * @param name
     * @param points
     * @throws IOException
     */
    public void redeemPoints(String name, double points) throws IOException {    
        output = new FileReader("Customer.txt");
        read  = new BufferedReader(output);
        this.points = this.points - points; 
        String data[];
        String line;
        String s = "";
            
        while((line  = read.readLine()) != null){
            data = null;
            data = line.split(",");
            username = data[0];
            password = data[1];
            status = data[2];
            if(name.equals(username)){
                s+=username+","+password+","+status+","+this.points+"\r\n";
                System.out.println(s);
            }
            else{
                s+=line+"\r\n";
            }
        }
        read.close();
        input = new FileWriter("Customer.txt");
        input.write(s);
        input.close();
    }
    
    /**
     *
     * @param name
     * @param points
     * @throws IOException
     */
    public void addPoint(String name, double points) throws IOException {     
        output = new FileReader("Customer.txt");
        read  = new BufferedReader(output);
        this.points = this.points + points; 
        String data[];
        String line;
        String s = "";
            
        while((line  = read.readLine()) != null){
            data = null;
            data = line.split(",");
            username = data[0];
            password = data[1];
            status = data[2];
            if(name.equals(username)){
                s+=username+","+password+","+status+","+this.points+"\r\n";
                System.out.println(s);
            }
            else{
                s+=line+"\r\n";
            }
        }
        read.close();
        input = new FileWriter("Customer.txt");
        input.write(s);
        input.close();
    }
    
    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean check(String username, String password){
        String [] data;
                
        try{
            output = new FileReader("Customer.txt");
            read  = new BufferedReader(output);
            
            String line;
            
            while((line  = read.readLine()) != null){
                data = null;
                data = line.split(",");
                username = data[0];
                password = data[1];
                
                if(username.equals(username) && password.equals(password)){
                    return true; 
                }
            }
        } catch (FileNotFoundException ex) {
                    
        } catch (IOException ex) {                    
        }  
        return false; 
    }

    /**
     *
     * @throws IOException
     */
    public void createBookStore() throws IOException{
        file = new File("Book.txt");
        input = new FileWriter("Book.txt", true);
        write = new BufferedWriter(input);
       
        if(file.length() == 0){
            write.write(data1);
            write.newLine();
            write.close();
        }
    }

    @Override
    public void changeState(Stage window) {
        LoginFX l = new LoginFX();
        l.start(window);
    }

    @Override
    public String toString() {
       return "" + username + ", " + password + ", " + status + ", " + points + "\n";
    }
}
