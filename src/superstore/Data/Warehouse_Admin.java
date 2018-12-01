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
public class Warehouse_Admin implements Serializable{
        
    private String loginid;
    private String password;
    private Warehouse warehouse;

    public Warehouse_Admin(String loginid,String password,Warehouse warehouse) {
        this.loginid = loginid;
        this.warehouse = warehouse;
        this.password = password;
    }

    public String getLoginid() {
        return loginid;
    }

    public String getPassword() {
        return password;
    }
    
    public void handleOrders(){
        
    }
    
    public Warehouse getWarehouse() {
        return warehouse;
    }
    
}
