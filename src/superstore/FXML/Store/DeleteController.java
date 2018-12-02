/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore.FXML.Store;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import superstore.Data.Item;
import superstore.Data.Store;

/**
 * FXML Controller class
 *
 * @author PD
 */
public class DeleteController implements Initializable {

    
    @FXML
    ChoiceBox catCB;
    @FXML
    ChoiceBox subCB;
    @FXML
    ChoiceBox itemCB;
    @FXML
    Button deleteB;
    @FXML
    Button logoutB;
    @FXML
    Button clearB;
    
    
    private Store store;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     *
     * @param store
     */
    public void initialize(Store store) {
        this.store = store;
                
        this.catCB.setTooltip(new Tooltip("Select Category"));
        this.subCB.setTooltip(new Tooltip("Select Sub-Category"));
        this.itemCB.setTooltip(new Tooltip("Select Item"));
        
        //category populated
        this.catCB.getItems().clear();
        for (int i = 0; i < this.store.getCategories().size(); i++) {
            this.catCB.getItems().add(this.store.getCategories().get(i).getName());
        }
        
        this.catCB.getSelectionModel().selectedItemProperty().addListener(  (e,oldvalue,newvalue) -> {
            
            System.out.println(newvalue);
          
            int index = this.catCB.getSelectionModel().getSelectedIndex();
            
            if(index >= 0){
                this.subCB.getItems().clear();
                for (int i = 0; i < this.store.getCategories().get(index).getSubcategories().size(); i++) {
                    this.subCB.getItems().add(this.store.getCategories().get(index).getSubcategories().get(i).getName());
                }
            }
            
        });
        
        
        this.subCB.getSelectionModel().selectedItemProperty().addListener(  (e,oldvalue,newvalue) -> {
            
            System.out.println(newvalue);
          
            int index = this.catCB.getSelectionModel().getSelectedIndex();
            int index1 = this.subCB.getSelectionModel().getSelectedIndex();
            
            if(index >= 0 && index1>=0){
                this.itemCB.getItems().clear();
                for (int i = 0; i < this.store.getCategories().get(index).getSubcategories().get(index1).getItems().size(); i++) {
                    this.itemCB.getItems().add(this.store.getCategories().get(index).getSubcategories().get(index1).getItems().get(i).getName());
                }
            }
            
        });
        
        
    }    
    
    /**
     *
     */
    public void delete(){
        int index = this.catCB.getSelectionModel().getSelectedIndex();
        int index1 = this.subCB.getSelectionModel().getSelectedIndex();
        int index2 = this.itemCB.getSelectionModel().getSelectedIndex();
        
        if(index >=0){
            if(index1>=0){
                if(index2>=0){
                    //DELETE ITEM
                    delItem();
                }
                else{
                    //DELETE SUB-CATEGORY
                    delSub();
                }
            }
            else{
                //DELETE CATEGORY
                delCat();
            }
        }
        else{
            System.out.println("CHOOSE A CATEGORY TO DELETE");
        }
        
        //
        reset();
    }
    
    /**
     *
     */
    public void delItem(){
        System.out.println("deleteing items");

        int index = this.catCB.getSelectionModel().getSelectedIndex();
        int index1 = this.subCB.getSelectionModel().getSelectedIndex();
        
        int indexT = this.itemCB.getSelectionModel().getSelectedIndex();//items index
        
        Item itemTemp = new Item();
        itemTemp.setName(this.store.getCategories().get(index).getSubcategories().get(index1).getItems().get(indexT).getName());
        
        int index2 = this.store.getItems().indexOf(itemTemp);
        int index3 = this.store.getCategories().get(index).getSubcategories().get(index1).getItems().indexOf(itemTemp);
        
//        System.out.println("index of item:- " + this.nameTF.getText().trim() + "-- " + index2);
        
        this.store.deleteItem(index,index1,index2,index3);//THIS LINE REMOVED,CAREFULL
        //NOT WORKING   CHECK ITEMS REFERENCES
        System.out.println("ITEM DELETED");
        
        
    }
    
    /**
     *
     */
    public void delSub(){
        System.out.println("delteing subcategories");
        
        int index = this.catCB.getSelectionModel().getSelectedIndex();
        int index1 = this.subCB.getSelectionModel().getSelectedIndex();
        
        this.store.deleteSub_Category(index, index1);
        System.out.println("AFTER DELSUB METHOD");
        
        //CHECK THIS METHOD,might we wrong,TILL-151118-0158AM
    }
    
    /**
     *
     */
    public void delCat(){
        System.out.println("delteing categories");
        
        int index = this.catCB.getSelectionModel().getSelectedIndex();
        
        this.store.deleteCategory(index);
        System.out.println("AFTER DELCAT METHOD");
        
        //CHECK THIS METHOD,might we wrong,TILL-201118-0549PM
    }
        
    /**
     *
     */
    public void reset(){
        this.catCB.getItems().clear();
        this.subCB.getItems().clear();
        this.itemCB.getItems().clear();
        

        for (int i = 0; i < this.store.getCategories().size(); i++) {
            this.catCB.getItems().add(this.store.getCategories().get(i).getName());
        }

    }
    
    /**
     *
     */
    public void logout(){
        System.out.println("LOGGING OUT , ADD CODE HERE");
    }
    
}
