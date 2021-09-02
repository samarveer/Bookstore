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
public class Owner extends User{
  
    private String username; 
    private String password;
    private int points; 
    private String status; 
    File file;
    FileReader output; 
    FileWriter input;
    BufferedWriter write;
    BufferedReader read; 
    
    private String data1 = "Name Of The Wind,50\n"
            + "The Way Of Kings,45";    
    
    private String data2 = "John,pass,Gold,10000";
    
    /**
     *
     * @param userName
     * @param password
     */
    public Owner(String userName, String password){
        this.username = userName;
        this.password = password; 
    }

    /**
     *
     * @return
     */
    public String getUsername(){
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
    public String getPassword(){
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
    public int getPoints(){
        return points;
    }

    /**
     *
     * @param points
     */
    public void setPoints(int points){
        this.points = points;
    }

    /**
     *
     * @param name
     * @param price
     * @throws IOException
     */
    public void addBook(String name, double price) throws IOException{       
        output = new FileReader("Book.txt");
        input = new FileWriter("Book.txt", true);
        write = new BufferedWriter(input);
        
        Book b = new Book(name, price);   
        write.write(""+b.getName()+","+b.getPrice());  
        write.newLine();
        
        write.close();
    }
    
    /**
     *
     * @param name
     * @param price
     * @throws IOException
     */
    public void deleteBook(String name, double price) throws IOException{        
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
     * @param username
     * @param password
     * @param status
     * @param points
     * @throws IOException
     */
    public void addCustomer(String username, String password, String status, double points) throws IOException{ 
        output = new FileReader("Customer.txt");
        input = new FileWriter("Customer.txt", true);
        write = new BufferedWriter(input);
        
        Customer c = new Customer(username, password, status, points); 
        write.write(""+c.getUsername()+","+c.getPassword()+","+c.getStatus()+","+c.getPoints());  
        write.newLine();
        
        write.close();
    }
    
    /**
     *
     * @param username
     * @param password
     * @param status
     * @param points
     * @throws IOException
     */
    public void deleteCustomer(String username, String password, String status, double points) throws IOException{

        output = new FileReader("Customer.txt");
        read = new BufferedReader(output);
        
        String curr, next;
        String s = "";
        while((curr = read.readLine()) != null){
            s += curr + "\r\n";
        }
        read.close();
        next = s.replaceAll(username, "DELETED");
        input = new FileWriter("Customer.txt");
        input.write(next);
        input.close();
    }
    
    /**
     *
     * @param points
     * @return
     */
    public String changeStatus(int points){
        if(points < 1000){
            status = "Silver";
        }
        if(points >= 1000){
            status = "Gold";
        } 
        return status; 
    }
    
    /**
     *
     * @throws IOException
     */
    public void createBookStore() throws IOException{
        file = new File("Book.txt");
        input = new FileWriter("Book.txt", true);
        write = new BufferedWriter(input);
        String [] data;
        String name;
        int lines = 0;
        int del = 0;

        try{
            output = new FileReader("Book.txt");
            BufferedReader read  = new BufferedReader(output);

            String line;

            while((line  = read.readLine()) != null){
                data = null;
                data = line.split(",");
                name = data[0];
                lines++;
                if(name.equals("DELETED")){
                    del++;
                }
            }
        } catch (FileNotFoundException ex) {         
        } catch (IOException ex) {                    
        }
        if(file.length() == 0 || lines == del){
            write.write(data1);
            write.newLine();
            write.close();
        }
    }
    public void createCustomer() throws IOException{
        file = new File("Customer.txt");
        input = new FileWriter("Customer.txt", true);
        write = new BufferedWriter(input);
       
        String [] data;
        String name;
        int lines = 0;
        int del = 0;
       
        try{
            output = new FileReader("Book.txt");
            BufferedReader read  = new BufferedReader(output);
            
            String line;
            
            while((line  = read.readLine()) != null){
                data = null;
                data = line.split(",");
                name = data[0];
                lines++;
                if(name.equals("DELETED")){
                    del++;
                }
            }
        } catch (FileNotFoundException ex) {         
        } catch (IOException ex) {                    
        }
        System.out.println(lines+" "+del);
    }

    @Override
    public void changeState(Stage primaryStage) {
        LoginFX l = new LoginFX();
        l.start(primaryStage);
    }
    
    public static void main(String[] args) throws IOException {
        Owner o = new Owner("ads", "ad");
        o.createCustomer();
    }

    @Override
    public String toString() {
        return "" +username+ ", " +password+ "";
    }
    
}
