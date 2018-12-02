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
public class Sub_Category implements Serializable {
    
    private String name;
    private ArrayList<Item> items;

    /**
     *
     * @param name
     */
    public Sub_Category(String name) {
        this.name = name;
        this.items = new ArrayList<>();
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
    public ArrayList<Item> getItems() {
        return items;
    }
    
}
