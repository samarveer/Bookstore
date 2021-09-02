/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

import javafx.stage.Stage;

/**
 *
 * @author Samarveer Sandhu
 */
abstract class User {
    
    public abstract void changeState(Stage window);
    
    @Override
    public abstract String toString();
}
