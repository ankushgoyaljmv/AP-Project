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
public class Store_Admin implements Serializable{
    
    private String loginid;
    private String password;
    private Store store;

    /**
     *
     * @param loginid
     * @param password
     * @param store
     */
    public Store_Admin(String loginid, String password, Store store) {
        this.loginid = loginid;
        this.password = password;
        this.store = store;
    }

    /**
     *
     * @return
     */
    public Store getStore() {
        return store;
    }

    /**
     *
     * @param store
     */
    public void setStore(Store store) {
        this.store = store;
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
    
}
