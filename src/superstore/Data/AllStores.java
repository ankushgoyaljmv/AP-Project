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
public class AllStores implements Serializable{
    
    private ArrayList<Store> allstores = new ArrayList<>();
    private int storeID = 1;
    
    /**
     *
     * @return
     */
    public ArrayList<Store> getAllstores() {
        return allstores;
    }
    
    /**
     *
     * @return
     */
    public int getStoreID() {
        return storeID;
    }
    
    /**
     *
     */
    public void incrementStoreID(){
        this.storeID++;
    }
    
}
