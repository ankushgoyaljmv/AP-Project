/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore.Data;

import java.io.Serializable;

/**
 *
 * @author PD
 */
public class End_Users implements Serializable{
    
    private String name;
    private double balance;
    private Cart cart;

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public double getBalance() {
        return balance;
    }

    /**
     *
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance += balance;
    }

    /**
     *
     * @return
     */
    public Cart getCart() {
        return cart;
    }

    /**
     *
     */
    public void addItem(){
        
    }
    
    /**
     *
     */
    public void deleteItem(){
        
    }
    
    /**
     *
     */
    public void checkout(){
        
    }
    
}
