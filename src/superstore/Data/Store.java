/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author PD
 */
public class Store implements Functionalities,Serializable{
    
    private int ID;
    private double D;
    private String name;
    private Warehouse upwarehouse;
    private ArrayList<Item> items;
    private ArrayList<Category> categories;
    private Store_Admin admin;
    private int UID;

    /**
     *
     * @param ID
     * @param name
     */
    public Store(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.categories = new ArrayList<>();
        this.items = new ArrayList<>();
        this.UID = 1;
    }

    /**
     *
     * @return
     */
    public Store_Admin getAdmin() {
        return admin;
    }
    
    /**
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     *
     * @param admin
     */
    public void setAdmin(Store_Admin admin) {
        this.admin = admin;
    }

    /**
     *
     * @return
     */
    public boolean isLinked(){
        return !(upwarehouse == null);
    }
    
    /**
     *
     * @return
     */
    public int getUID() {
        return UID;
    }

    /**
     *
     */
    public void increamentUID() {
        this.UID++;
    }
    
    /**
     *
     * @return
     */
    public double getD() {
        return D;
    }

    /**
     *
     * @param D
     */
    public void setD(double D) {
        this.D = D;
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
     * @return
     */
    public Warehouse getUpwarehouse() {
        return upwarehouse;
    }

    /**
     *
     * @return
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     *
     * @return
     */
    public ArrayList<Category> getCategories() {
        return categories;
    }

    /**
     *
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
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
     * @param upwarehouse
     */
    public void setUpwarehouse(Warehouse upwarehouse) {
        this.upwarehouse = upwarehouse;
    }

    /**
     *
     * @param name
     */
    @Override
    public void addCategory(String name) {
       Category c = new Category(name);
        this.categories.add(c);
    }

    /**
     *
     * @param name
     * @param categoryName
     */
    @Override
    public void addSub_Category(String name,Category categoryName) {
        categoryName.addSubCategory(name);
    }
  
    /**
     *
     * @param categoryName
     * @param subCategoryName
     */
    @Override
    public void addItem(Category categoryName,Sub_Category subCategoryName) {
        Item temp = new Item();
        this.items.add(temp);
        categoryName.getSubcategories().get(categoryName.getSubcategories().indexOf(subCategoryName)).getItems().add(temp);
        System.out.println("ITEM ADDED TO > " + categoryName.getName() + " > " + subCategoryName.getName());
    }
    
    /**
     *
     * @param categoryName
     * @param subCategoryName
     * @param temp
     */
    public void addItem(Category categoryName,Sub_Category subCategoryName,Item temp) {
        this.items.add(temp);
        categoryName.getSubcategories().get( categoryName.getSubcategories().indexOf(subCategoryName) ).getItems().add(temp);
        System.out.println("ITEM ADDED TO > " + categoryName.getName() + " > " + subCategoryName.getName());
    }
    
    /**
     *
     */
    @Override
    public void updateCategory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void updateSub_Category() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void updateItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void deleteCategory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param index
     */
    public void deleteCategory(int index) {
        Sub_Category temp;
        Item itemTemp = new Item();
        
        for (int i = 0; i < this.categories.get(index).getSubcategories().size(); i++) {
            this.deleteSub_Category(index, i, false);
        }
        
        System.out.println("AFTER DELETING ITEMS");
        this.categories.get(index).getSubcategories().clear();//clearing all sub categories
        this.categories.remove(index);//removing the category
        System.out.println("CATEGORY REMOVED");
    }
    
    /**
     *
     */
    @Override
    public void deleteSub_Category() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *
     * @param index
     * @param index1
     * @param deletesub
     */
    public void deleteSub_Category(int index,int index1,boolean deletesub) {
        Item temp;
        Item itemTemp = new Item();
        
        for (int i = 0; i < this.categories.get(index).getSubcategories().get(index1).getItems().size(); i++) {
            temp = this.categories.get(index).getSubcategories().get(index1).getItems().get(i);
            
            itemTemp.setName(temp.getName().trim());
            int index2 = this.items.indexOf(itemTemp);
            int index3 = this.categories.get(index).getSubcategories().get(index1).getItems().indexOf(itemTemp);
            //deleteing that item
            deleteItem(index,index1,index2,index3);
        }
        
        System.out.println("AFTER DELTING ITEMS");
        if(deletesub)
            this.categories.get(index).getSubcategories().remove(index1);
        System.out.println("SUBCATEGORY REMOVED");
    }
    
    /**
     *
     * @param index
     * @param index1
     */
    public void deleteSub_Category(int index,int index1) {
        Item temp;
        Item itemTemp = new Item();
        
        for (int i = 0; i < this.categories.get(index).getSubcategories().get(index1).getItems().size(); i++) {
            temp = this.categories.get(index).getSubcategories().get(index1).getItems().get(i);
            
            itemTemp.setName(temp.getName().trim());
            int index2 = this.items.indexOf(itemTemp);
            int index3 = this.categories.get(index).getSubcategories().get(index1).getItems().indexOf(itemTemp);
            //deleteing that item
            deleteItem(index,index1,index2,index3);
        }
        
        System.out.println("AFTER DELTING ITEMS");
        this.categories.get(index).getSubcategories().remove(index1);
        System.out.println("SUBCATEGORY REMOVED");
    }

    /**
     *
     * @param index
     * @param index1
     * @param index2
     * @param index3
     */
    @Override
    public void deleteItem(int index,int index1,int index2,int index3) {
        // cat | sub | items Index | cat>sub>items Index
        if(index2>this.items.size() || index2==-1)
            System.out.println("ERROR CANT DELETE AND ITEM WHICH IS NOT PRESENT");
        else{
            this.items.remove(index2);
            this.categories.get(index).getSubcategories().get(index1).getItems().remove(index3);
        }
    }

    /**
     *
     */
    @Override
    public void display() {
        
        System.out.println("\n\ncategories-->");
        for (int i = 0; i < this.categories.size(); i++) {
            System.out.println("category name:- " + this.categories.get(i).getName());
            for (int j = 0; j < this.categories.get(i).getSubcategories().size(); j++) {
                System.out.println("subcategory:- " + this.categories.get(i).getSubcategories().get(j).getName());
                System.out.println("ITEMS--");
                for (int k = 0; k < this.categories.get(i).getSubcategories().get(j).getItems().size(); k++) {
                    System.out.println("Item no-" + k + "      :- " + this.categories.get(i).getSubcategories().get(j).getItems().get(k).getName() );
                }
                System.out.println("");
            }
            System.out.println("\n");
        }
        
    }

    /**
     *
     * @return
     */
    @Override
    public Item findItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void sort() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void order() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Store other = (Store) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param item
     */
    public void order(Item item) {
        this.upwarehouse.order(item);
    }
    
}
