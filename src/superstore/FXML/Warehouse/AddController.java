/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore.FXML.Warehouse;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import superstore.Data.Category;
import superstore.Data.Item;
import superstore.Data.Sub_Category;
import superstore.Data.Warehouse;

/**
 * FXML Controller class
 *
 * @author PD
 */
public class AddController implements Initializable {
    
    @FXML
    ChoiceBox mainCB;
    @FXML
    ChoiceBox catCB;
    @FXML
    ChoiceBox subCB;
    @FXML
    TextField TF;
    @FXML
    Button addItem;
    @FXML
    Button addcommon;
    @FXML
    AnchorPane ap2;
    
    private Warehouse warehouse;
    private int type;//0-cat , 1-sub , 2-sub
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initialize(Warehouse warehouse) {
        
        this.warehouse = warehouse;
        
        //HIDE by default
        reset();
        this.addcommon.setDisable(true);
        
        
        this.mainCB.setTooltip(new Tooltip("Select Main Option"));
        this.catCB.setTooltip(new Tooltip("Select Category"));
        this.subCB.setTooltip(new Tooltip("Select Sub-Category"));
        
        //CHEAT
        
        this.warehouse.addCategory("test@category1");
        this.warehouse.addCategory("test@category2");
        this.warehouse.addCategory("test@category3");
           
        Category c = this.warehouse.getCategories().get(0);
        this.warehouse.addSub_Category("test@subcategory1",c);
        this.warehouse.addSub_Category("test@subcategory2",c);
        this.warehouse.addSub_Category("test@subcategory3",c);
        
        this.warehouse.addItem(this.warehouse.getCategories().get(0) , this.warehouse.getCategories().get(0).getSubcategories().get(0));
        this.warehouse.getItems().get(0).setName("Test@Item1");
        this.warehouse.addItem(this.warehouse.getCategories().get(0) , this.warehouse.getCategories().get(0).getSubcategories().get(0));
        this.warehouse.getItems().get(1).setName("Test@Item2");
        //
        
        
        //ADDING DEFAULT OPTIONS - ADD - cat,sub,item
        this.mainCB.getItems().addAll("Add Category","Add Sub-Category","Add Item");
        
        this.mainCB.getSelectionModel().selectedItemProperty().addListener(  (e,oldvalue,newvalue) -> {
            
            System.out.println(newvalue);
            reset();
            
            if(this.mainCB.getSelectionModel().getSelectedIndex() >= 0){
                this.type = this.mainCB.getSelectionModel().getSelectedIndex() ;
                
                switch(this.type){
                    case 0:
                        //add cat
                        this.TF.setPromptText("Enter Category Name");
                        break;
                    case 1:
                        //add sub
                        //populate categories
                        for (int i = 0; i < this.warehouse.getCategories().size(); i++) {
                            this.catCB.getItems().add(this.warehouse.getCategories().get(i).getName());
                        }
                        
                        this.TF.setPromptText("Enter Sub-Category Name");
                        this.catCB.setVisible(true);
                        break;
                    case 2:
                        //add item
                        
                        this.TF.setPromptText("Enter Item Name");
                        this.catCB.setVisible(true);
                        this.subCB.setVisible(true);
                        break;
                    default:
                        System.out.println("DEFAULT");
                        break;
                }
            }
            
        });
        
        this.catCB.getSelectionModel().selectedItemProperty().addListener(  (e,oldvalue,newvalue) -> {
            
            System.out.println(newvalue);
//            reset();
            
            int index = this.catCB.getSelectionModel().getSelectedIndex();
            
            if(index >= 0){
                for (int i = 0; i < this.warehouse.getCategories().get(index).getSubcategories().size(); i++) {
                    this.subCB.getItems().add(this.warehouse.getCategories().get(index).getSubcategories().get(i).getName());
                }
            }
            
        });
        
        
        
        
    }    
    
    public void add1(){
        
        String s = "";
        Category c = null;
        switch(this.type){
            case 0:
                s = this.TF.getText().trim();
                c = new Category(s);
                this.warehouse.getCategories().add(c);
                System.out.println("category added -> " + s);

                this.addcommon.setDisable(true);
                this.TF.setText("");
                reset();
                
                break;
            
            case 1:
                int index = this.catCB.getSelectionModel().getSelectedIndex();
                s = this.TF.getText().trim();
                c = this.warehouse.getCategories().get(index);
                this.warehouse.addSub_Category(s, c);
                System.out.println("subcategory added -> " + s + " at --> " + c.getName());

                this.addcommon.setDisable(true);
                this.TF.setText("");
                reset();
                this.catCB.setVisible(true);
                
                break;
            default:
                System.out.println("WTH noob");
                break;
        }
        
        
    }
    
    public void reset(){
        this.ap2.setVisible(false);
        this.catCB.setVisible(false);
        this.subCB.setVisible(false);
        this.TF.setText("");
        this.TF.setPromptText("");
    }
    
    public void check(){
        System.out.println("INSIDE CHECK");
        System.out.println("type;- " + type);
        
        switch(this.type){
                    case 0:
                        //add cat
                        cat();
                        break;
                    case 1:
                        //add sub
                        sub();
                        break;
                    case 2:
                        //add item
                        item();
                        break;
                    default:
                        System.out.println("DEFAULT");
                        break;
                }
        
    }
    
    public void cat(){
        String name = TF.getText().trim();
        if (TF.getText() != null && (!TF.getText().isEmpty() || !TF.getText().trim().isEmpty())) {
            if(checkIfCategoryExists(name)){
                //category exists
                this.addcommon.setDisable(true);
            }
            else{
                this.addcommon.setDisable(false);
            }
        }
        else{
            this.addcommon.setDisable(true);
        }
    }
    
    public void sub(){
        
        String name = TF.getText().trim();
        if (TF.getText() != null && (!TF.getText().isEmpty() || !TF.getText().trim().isEmpty())) {
            if(checkIfSubCategoryExists(name)){
                //subcategory exists
                this.addcommon.setDisable(true);
            }
            else{
                this.addcommon.setDisable(false);
            }
        }
        else{
            this.addcommon.setDisable(true);
        }
        
    }
    
    public void item(){
        
    }
    
    public void clickOnAdd(){
        System.out.println("inside click on add");
        
    }
    
    private boolean checkIfItemExists(String name){
        //returns true if item exists,else false
        
        ArrayList<Item> items = null;
        int index = this.catCB.getSelectionModel().getSelectedIndex();
        int index1 = this.subCB.getSelectionModel().getSelectedIndex();
        
        System.out.println("index:- " + index1 + "       INSIDE checkIfItemExists Method.");
        if(index1 >=0 && index >=0){
            items = this.warehouse.getCategories().get(index).getSubcategories().get(index1).getItems();
        }
        
        if(items == null){
            return false;
        }
        else{
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getName().equals(name))
                    return true;
            }
        }
        return false;        
    }
    
    private boolean checkIfCategoryExists(String name){
        //returns true if category exists,else false
        
        ArrayList<Category> cat = null;

        cat = this.warehouse.getCategories();
        
        
        if(cat == null){
            return false;
        }
        
        else{
            for (int i = 0; i < cat.size(); i++) {
                if(cat.get(i).getName().equals(name))
                    return true;
            }
        }
        return false;        
    }
    
    private boolean checkIfSubCategoryExists(String name){
        //returns true if subcategory exists,else false
        
        ArrayList<Sub_Category> sub = null;
        int index = this.subCB.getSelectionModel().getSelectedIndex();
        
        if(index >=0){
            sub = this.warehouse.getCategories().get(index).getSubcategories();
        }
        
        if(sub == null){
            return false;
        }
        else{
            for (int i = 0; i < sub.size(); i++) {
                if(sub.get(i).getName().equals(name))
                    return true;
            }
        }
        return false;        
    }
    
}
