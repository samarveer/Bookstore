/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

/**
 *
 * @author Samarveer Sandhu
 */
public class Login {
    
    private String username;
    private String password;
    private User user; 

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
    public void setUsername(String username) {
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
     * @param user
     */
    public void setState(User user){
        this.user = user;
    }
}
