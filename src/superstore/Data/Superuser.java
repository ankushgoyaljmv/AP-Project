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
public class Superuser implements Serializable {
    
   AllWarehouses warehouses;
   AllStores stores;

    /**
     *
     * @param warehouses
     * @param stores
     */
    public Superuser(AllWarehouses warehouses, AllStores stores) {
        this.stores = stores;
        this.warehouses = warehouses;
    }
    
    /**
     *
     * @param warehouse
     */
    public void display(Warehouse warehouse){
        
    }
   
    /**
     *
     * @param store
     */
    public void display(Store store){
        
    }
    
    /**
     *
     */
    public void displayAll(){
        
    }
    
    /**
     *
     */
    public void createWarehouse(){
        warehouses.getAllwarehouses().add(new Warehouse(100,"TEST"));
    }
    
    /**
     *
     */
    public void createStore(){
        
    }
    
    /**
     *
     */
    public void assignWarehouse(){
        
    }
    
    /**
     *
     */
    public void createWarehouseAdmin(){
        
    }
    
    /**
     *
     */
    public void createStoreAdmin(){
        
    }
    
    
}
