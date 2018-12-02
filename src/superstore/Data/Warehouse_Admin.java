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
public class Warehouse_Admin implements Serializable{
        
    private String loginid;
    private String password;
    private Warehouse warehouse;
    private ArrayList<Item> itemlist;

    /**
     *
     * @param loginid
     * @param password
     * @param warehouse
     */
    public Warehouse_Admin(String loginid,String password,Warehouse warehouse) {
        this.loginid = loginid;
        this.warehouse = warehouse;
        this.password = password;
        itemlist = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public String getLoginid() {
        return loginid;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }
    
    /**
     *
     * @return
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     *
     * @param warehouses
     */
    public void handleOrders(AllWarehouses warehouses) {
        
        ArrayList<Item> items = this.warehouse.getItems();
        for (int i1 = 0; i1 < items.size(); i1++) {
            Item item = items.get(i1);
            int amt = (int)item.getEOQ();
            int i = items.indexOf(item);

            if(items.get(i).getQuantity() >= amt){
                //availabele in cur warehouse
                items.get(i).setQuantity(items.get(i).getQuantity() - amt);
            }
            else{
                //see other warehouses
                int best = -1;
                int inside = -1;
                int req = amt;
                ArrayList<Item> temp = warehouses.getAllwarehouses().get(i).getItems();
                for (int j = 0; j < warehouses.getAllwarehouses().size(); j++) {
                    int ind = temp.indexOf(item);
                    if( ind >= 0){
                        if(temp.get(ind).getQuantity() > req ){
                            best = j;
                            inside = ind;
                            req = temp.get(ind).getQuantity();
                        }
                        
                    }
                }
                
                if(req >= amt){
                    temp.get(inside).setQuantity( req - amt);
                }

            }
        }
        
        
    }
    
    /**
     *
     * @param item
     */
    public void notification(Item item){
        itemlist.add(item);
    }
    
}
