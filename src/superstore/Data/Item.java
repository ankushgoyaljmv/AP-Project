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

    @Override
    public boolean equals(Object o) {
        return this.name.equals(((Item)o).name); 
    }

    @Override
    public String toString() {
        return "Item{" + "path=" + path + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", H=" + H + ", K=" + K + ", EOQ=" + EOQ + ", UID=" + UID + '}';
    }
    
    public static int getD() {
        return D;
    }

    public static void setD(int D) {
        Item.D = D;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getH() {
        return H;
    }

    public void setH(int H) {
        this.H = H;
    }

    public int getK() {
        return K;
    }

    public void setK(int K) {
        this.K = K;
    }

    public double getEOQ() {
        return EOQ;
    }
    
    public void setEOQ(double D) {
        this.EOQ = Math.ceil(Math.sqrt( (2*D*this.K)/this.H ));
    }

    public long getUID() {
        return UID;
    }

    public void setUID(long UID) {
        this.UID = UID;
    }   
    
}
