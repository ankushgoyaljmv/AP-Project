/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore.Data;

/**
 *
 * @author PD
 */
public interface Functionalities {
    
    /**
     *
     * @param name
     */
    void addCategory(String name);

    /**
     *
     * @param name
     * @param categoryName
     */
    void addSub_Category(String name,Category categoryName);

    /**
     *
     * @param categoryName
     * @param subCategoryName
     */
    void addItem(Category categoryName,Sub_Category subCategoryName);

    /**
     *
     */
    void updateCategory();

    /**
     *
     */
    void updateSub_Category();

    /**
     *
     */
    void updateItem();

    /**
     *
     */
    void deleteCategory();

    /**
     *
     */
    void deleteSub_Category();

    /**
     *
     * @param index
     * @param index1
     * @param index2
     * @param index3
     */
    void deleteItem(int index,int index1,int index2,int index3);

    /**
     *
     */
    void display();

    /**
     *
     * @return
     */
    Item findItem();

    /**
     *
     */
    void sort();

    /**
     *
     */
    void order();
    
}
