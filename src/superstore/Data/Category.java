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
public class Category implements Serializable{
    
    private String name;
    private ArrayList<Sub_Category> subcategories;

    /**
     *
     * @param name
     */
    public Category(String name){
        this.name = name;
        this.subcategories = new ArrayList<>();
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
    public ArrayList<Sub_Category> getSubcategories() {
        return subcategories;
    }    
    
    /**
     *
     * @param name
     */
    public void addSubCategory(String name){
        subcategories.add(new Sub_Category(name));
    }
    
}
