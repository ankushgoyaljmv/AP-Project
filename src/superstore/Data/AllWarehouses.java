/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author PD
 */
public class AllWarehouses implements Serializable{
    
    private int warehouseID = 1;
    private ArrayList<Warehouse> allwarehouses = new ArrayList<>();

    /**
     *
     * @return
     */
    public int getWarehouseID() {
        return warehouseID;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Warehouse> getAllwarehouses() {
        return allwarehouses;
    }
    
    /**
     *
     */
    public void incrementWarehouseID(){
        this.warehouseID++;
    }
    
}
