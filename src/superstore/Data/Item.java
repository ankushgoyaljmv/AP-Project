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
public class Item implements Serializable{
    private static int D;
    
    private String path;
    private String name;
    private double price;
    private int quantity;
    private int H;
    private int K;
    private double EOQ;
    private long UID;

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        return this.name.equals(((Item)o).name); 
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Item{" + "path=" + path + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", H=" + H + ", K=" + K + ", EOQ=" + EOQ + ", UID=" + UID + '}';
    }
    
    /**
     *
     * @return
     */
    public static int getD() {
        return D;
    }

    /**
     *
     * @param D
     */
    public static void setD(int D) {
        Item.D = D;
    }

    /**
     *
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

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
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public int getH() {
        return H;
    }

    /**
     *
     * @param H
     */
    public void setH(int H) {
        this.H = H;
    }

    /**
     *
     * @return
     */
    public int getK() {
        return K;
    }

    /**
     *
     * @param K
     */
    public void setK(int K) {
        this.K = K;
    }

    /**
     *
     * @return
     */
    public double getEOQ() {
        return EOQ;
    }
    
    /**
     *
     * @param D
     */
    public void setEOQ(double D) {
        this.EOQ = Math.ceil(Math.sqrt( (2*D*this.K)/this.H ));
    }

    /**
     *
     * @return
     */
    public long getUID() {
        return UID;
    }

    /**
     *
     * @param UID
     */
    public void setUID(long UID) {
        this.UID = UID;
    }   
    
}
