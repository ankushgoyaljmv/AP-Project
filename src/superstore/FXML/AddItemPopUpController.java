/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstore.FXML;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import superstore.Data.Category;
import superstore.Data.Item;
import superstore.Data.Warehouse;

/**
 * FXML Controller class
 *
 * @author arsheya
 */
public class AddItemPopUpController implements Initializable {

    @FXML
    AnchorPane ap1;
    @FXML
    AnchorPane ap2;
    @FXML
    TextField nameTF;
    @FXML
    TextField quantityTF;
    @FXML
    TextField priceTF;
    @FXML
    TextField hTF;
    @FXML
    TextField kTF;
    @FXML
    ChoiceBox categoryCB;
    @FXML
    ChoiceBox subcategoryCB;
    @FXML
    ChoiceBox mainMenuCB;
    @FXML
    Button addItem;
    @FXML
    Button deleteItem;
    @FXML
    Button updateItem;
    @FXML    
    private Warehouse warehouse;
//    ArrayList<Item> items;
    
    private int currentChoice = 1000; //0-add 1-delete 2-modify
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initialize(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.ap1.setVisible(false);
        this.ap2.setVisible(false);
        
        reset();
        
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
        
        
        //ADDING DEFAULT OPTIONS - ADD,DELETE,MODIFY
        this.mainMenuCB.getItems().addAll("ADD","DELETE","MODIFY");
        //
        
        this.mainMenuCB.getSelectionModel().selectedItemProperty().addListener(  (e,oldvalue,newvalue) -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1000), ap1);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            
            reset();
            
            System.out.println(newvalue);
            
            
            if(this.mainMenuCB.getSelectionModel().getSelectedIndex() >= 0){
                this.currentChoice = this.mainMenuCB.getSelectionModel().getSelectedIndex() ;
                
                switch(this.currentChoice){
                case 0:
                    addScreen();
                    break;
                case 1:
                    deleteScreen();
                    break;
                case 2:
                    modifyScreen();
                    break;
                default:
                    reset();
                    break;
            }
                
                System.out.println("Checking if isVisible" + ap1.isVisible());
                if(!ap1.isVisible()){
                    ap1.setVisible(true);
                    ft.play();
                }
                check();
                
            }else {
                    ap1.setVisible(false);
                }
        });
        
        
        
        this.mainMenuCB.setTooltip(new Tooltip("Select Main Option"));
        this.categoryCB.setTooltip(new Tooltip("Select Category"));
        this.subcategoryCB.setTooltip(new Tooltip("Select Sub-Category"));
            
        this.categoryCB.getSelectionModel().selectedItemProperty().addListener((e,oldvalue,newvalue) -> {
            System.out.println(newvalue);
            this.subcategoryCB.getItems().clear();
            String temp1 = "";
            int index = this.categoryCB.getSelectionModel().getSelectedIndex();
            System.out.println("index:- " + index);
            if(index >=0){
                for (int i = 0; i < warehouse.getCategories().get(index).getSubcategories().size();i++) {
                    temp1 = warehouse.getCategories().get(index).getSubcategories().get(i).getName();
                    System.out.println(temp1);
                    this.subcategoryCB.getItems().add(temp1);
                }
            }
            check();
            
        });
        
        
        
        this.subcategoryCB.getSelectionModel().selectedItemProperty().addListener( (e,oldvalue,newvalue) -> {
            System.out.println(newvalue);
            String temp1 = "";
            int index = this.categoryCB.getSelectionModel().getSelectedIndex();
            int index1 = this.subcategoryCB.getSelectionModel().getSelectedIndex();
            System.out.println("index:- " + index1);
            ArrayList<Item> items;
            if(index1 >=0){
                items = this.warehouse.getCategories().get(index).getSubcategories().get(index1).getItems();
            }
            else{
                items = null;//may be wrong
                nameTF.setText("");
                ap2.setVisible(false);
            }
            
            if(items.size()> 0)
                System.out.println(items.get(0).getName());
            else
                System.out.println("EMPTY");
            
            check();
        });
        
        
        String temp = "";
        for (int i = 0; i < warehouse.getCategories().size();i++) {
            temp = warehouse.getCategories().get(i).getName();
            System.out.println(temp);
            this.categoryCB.getItems().add(temp);
        }
        
        
       
    }
 

    public void check() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), ap2);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        
        
        if (nameTF.getText() != null && (!nameTF.getText().isEmpty() || !nameTF.getText().trim().isEmpty())) {
            
            switch(this.currentChoice){
                case 0:
                    //add
                    if (checkIfItemExists(nameTF.getText().trim())) {
                        ap2.setVisible(false);
                    } else if (!ap2.isVisible()) {
                        ap2.setVisible(true);
                        ft.play();
                    }
                    break;
                case 1:
                    //delete
                    if (checkIfItemExists(nameTF.getText().trim())) {
                        if(!ap2.isVisible()){
                            ap2.setVisible(true);
                            ft.play();
                        }
                    } else {
                        ap2.setVisible(false);
                    }
                    break;
                case 2:
                    //modify
                    if (checkIfItemExists(nameTF.getText().trim())) {
                        if(!ap2.isVisible()){
                            ap2.setVisible(true);
                            ft.play();
                            //show older details of item
                        }
                    } else {
                        ap2.setVisible(false);
                    }
                    break;
                    
            }
            
            
        } else {
           ap2.setVisible(false);
        }
    }
    
    private boolean checkIfItemExists(String name){
        ArrayList<Item> items = null;
        int index = this.categoryCB.getSelectionModel().getSelectedIndex();
        int index1 = this.subcategoryCB.getSelectionModel().getSelectedIndex();
        System.out.println("index:- " + index1 + "       INSIDE checkIfItemExists Method.");
        if(index1 >=0){
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
    
    public void clickOnAdd(){
        System.out.println("ADDING ITEM");
        
        Item itemTemp = new Item();
        String name = this.nameTF.getText().trim();
        double price = Double.parseDouble(this.priceTF.getText().trim());
        int quantity = Integer.parseInt(this.quantityTF.getText().trim());
        int h = Integer.parseInt(this.hTF.getText().trim());
        int k = Integer.parseInt(this.kTF.getText().trim());
        
        itemTemp.setName(name);
        itemTemp.setH(h);
        itemTemp.setK(k);
        itemTemp.setPrice(price);
        itemTemp.setQuantity(quantity);
        
        int index = this.categoryCB.getSelectionModel().getSelectedIndex();
        int index1 = this.subcategoryCB.getSelectionModel().getSelectedIndex();
        
        if(index>=0 && index1>=0){
            String category = this.warehouse.getCategories().get(index).getName();
            String subcategory = this.warehouse.getCategories().get(index).getSubcategories().get(index1).getName();

            itemTemp.setPath(category + ">" + subcategory);
            this.warehouse.addItem(this.warehouse.getCategories().get(index), this.warehouse.getCategories().get(index).getSubcategories().get(index1),itemTemp);
            System.out.println(itemTemp);
            System.out.println("ITEM ADDED");
        }
        else{
            System.out.println("EITHER CATEGORY OR SUBCATEGORY ISNT CHOOSEN");
        }
        this.printA();
        
        check();
//        System.out.println(itemTemp);
        
    }
    
    public void clickOnDelete(){
        System.out.println("DELETING ITEM");
        int index = this.categoryCB.getSelectionModel().getSelectedIndex();
        int index1 = this.subcategoryCB.getSelectionModel().getSelectedIndex();
        
        Item itemTemp = new Item();
        itemTemp.setName(this.nameTF.getText().trim());
        int index2 = this.warehouse.getItems().indexOf(itemTemp);
        int index3 = this.warehouse.getCategories().get(index).getSubcategories().get(index1).getItems().indexOf(itemTemp);
        
        System.out.println("index of item:- " + this.nameTF.getText().trim() + "-- " + index2);
        this.warehouse.deleteItem(index,index1,index2,index3);//THIS LINE REMOVED,CAREFULL
        //NOT WORKING   CHECK ITEMS REFERENCES
        System.out.println("ITEM DELETED");
        
        check();
    }
    
    public void clickOnUpdate(){
        System.out.println("UPDATING ITEM");
        
        int index = this.categoryCB.getSelectionModel().getSelectedIndex();
        int index1 = this.subcategoryCB.getSelectionModel().getSelectedIndex();
        
        Item itemTemp = new Item();
        itemTemp.setName(this.nameTF.getText().trim());
        int index2 = this.warehouse.getItems().indexOf(itemTemp);
        
        double price = Double.parseDouble(this.priceTF.getText().trim());
        int quantity = Integer.parseInt(this.quantityTF.getText().trim());
        int h = Integer.parseInt(this.hTF.getText().trim());
        int k = Integer.parseInt(this.kTF.getText().trim());
        
        System.out.println(this.warehouse.getItems().get(index2));
        
        this.warehouse.getItems().get(index2).setH(h);
        this.warehouse.getItems().get(index2).setK(k);
        this.warehouse.getItems().get(index2).setPrice(price);
        this.warehouse.getItems().get(index2).setQuantity(quantity);
        
        System.out.println(this.warehouse.getItems().get(index2));
        
        System.out.println("ITEM UPDATED");
        
        
        //DELETE ALL CODE BELOW THIS
        System.out.println("ITEMS");
        for (int i = 0; i < this.warehouse.getItems().size(); i++) {
            System.out.println(this.warehouse.getItems().get(i).getName());
        }
        System.out.println("");

        //
    
        check();
        
        System.out.println("IKIKIK");
        this.printI();
        System.out.println("");
        this.printA();
        
    }
    
    private void addScreen(){
        this.ap2.setVisible(true);
        this.hTF.setVisible(true);
        this.kTF.setVisible(true);
        this.priceTF.setVisible(true);
        this.quantityTF.setVisible(true);
        this.addItem.setVisible(true);
    }
    
    private void modifyScreen(){
        this.ap2.setVisible(true);
        this.hTF.setVisible(true);
        this.kTF.setVisible(true);
        this.priceTF.setVisible(true);
        this.quantityTF.setVisible(true);
        this.updateItem.setVisible(true);
        
        
        int index = this.categoryCB.getSelectionModel().getSelectedIndex();
        int index1 = this.subcategoryCB.getSelectionModel().getSelectedIndex();
        
        String name = nameTF.getText().trim();
        Item check = new Item();
        check.setName(name);
        
        int index2 = -1;
        Item tempItem = null;
        if(index>=0 && index1>=0) 
            index2 = this.warehouse.getCategories().get(index).getSubcategories().get(index1).getItems().indexOf(check);
        if(index2>=0){
            tempItem = this.warehouse.getCategories().get(index).getSubcategories().get(index1).getItems().get(index2);

        this.hTF.setText(tempItem.getH() + "");
        this.kTF.setText(tempItem.getK() + "");
        this.priceTF.setText(tempItem.getPrice() + "");
        this.quantityTF.setText(tempItem.getQuantity() + "");
        
        }
    }
    
    private void deleteScreen(){
        this.deleteItem.setVisible(true);
    }
    
    private void reset(){
        this.ap2.setVisible(false);
        this.hTF.setVisible(false);
        this.kTF.setVisible(false);
        this.priceTF.setVisible(false);
        this.quantityTF.setVisible(false);
        this.addItem.setVisible(false);
        this.deleteItem.setVisible(false);
        this.updateItem.setVisible(false);
    }

    public void printI(){
        System.out.println("Items all");
      
        for (int i = 0; i < this.warehouse.getItems().size(); i++) {
            System.out.println(this.warehouse.getItems().get(i).getName());
            System.out.println(this.warehouse.getItems().get(i));
        }
        System.out.println("all iems done\n");

    }
    
    public void printA(){
        System.out.println("Items by warehouse");
      
        for (int i = 0; i < this.warehouse.getCategories().size(); i++) {
            System.out.println("Category-> " + this.warehouse.getCategories().get(i).getName());
            for (int j = 0; j < this.warehouse.getCategories().get(i).getSubcategories().size(); j++) {
                System.out.println("Sub-Category-> " + this.warehouse.getCategories().get(i).getSubcategories().get(j).getName());
                for (int k = 0; k < this.warehouse.getCategories().get(i).getSubcategories().get(j).getItems().size(); k++) {
                    System.out.println(this.warehouse.getCategories().get(i).getSubcategories().get(j).getItems().get(k).getName());
                    System.out.println(this.warehouse.getCategories().get(i).getSubcategories().get(j).getItems().get(k));
                }
                System.out.println("");
            }
            System.out.println("\n");
        }
        System.out.println("all iems done\n");

    }
    
}
