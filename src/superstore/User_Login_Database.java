/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import superstore.Data.AllStores;
import superstore.Data.AllWarehouses;
import superstore.Data.Store_Admin;
import superstore.Data.Superuser;
import superstore.Data.Warehouse_Admin;

/**
 *
 * @author PD
 */
public class User_Login_Database implements Serializable{
    
    private AllWarehouses warehouses ;//= new AllWarehouses();
    private AllStores stores ;//= new AllStores();
    private Superuser superuser ;//= new Superuser(warehouses,stores);
    
    private HashMap<String,Warehouse_Admin> warehouseDatabase ;
    private HashMap<String,Store_Admin> storeDatabase ;

    /**
     *
     */
    public User_Login_Database() {
        warehouseDatabase = new HashMap<>();
        storeDatabase = new HashMap<>();
        warehouses = new AllWarehouses();
        stores = new AllStores();
        superuser = new Superuser(warehouses,stores);
    
    }

    /**
     *
     * @return
     */
    public AllWarehouses getWarehouses() {
        return warehouses;
    }

    /**
     *
     * @return
     */
    public AllStores getStores() {
        return stores;
    }

    /**
     *
     * @return
     */
    public Superuser getSuperuser() {
        return superuser;
    }    

    /**
     *
     * @return
     */
    public HashMap<String, Warehouse_Admin> getWarehouseDatabase() {
        return warehouseDatabase;
    }

    /**
     *
     * @return
     */
    public HashMap<String, Store_Admin> getStoreDatabase() {
        return storeDatabase;
    }

}
