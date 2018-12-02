/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore.FXML.Store;

import superstore.FXML.Store.*;
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
import superstore.Data.Store;

/**
 * FXML Controller class
 *
 * @author PD
 */
public class ModifyController implements Initializable {
@FXML
    ChoiceBox mainCB;
    @FXML
    ChoiceBox catCB;
    @FXML
    ChoiceBox subCB;
    @FXML
    ChoiceBox itemCB;
    @FXML
    TextField p;
    @FXML
    TextField q;
    @FXML
    TextField h;
    @FXML
    TextField k;
    @FXML
    TextField TF;
    @FXML
    TextField quantityTF;
    @FXML
    TextField priceTF;
    @FXML
    TextField hTF;
    @FXML
    TextField kTF;
    @FXML
    Button addItem;
    @FXML
    Button addcommon;
    @FXML
    AnchorPane ap2;
    
    private Store store;
    private int type;//0-cat , 1-sub , 2-sub
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initialize(Store store) {
        
        this.store = store;
        
        //HIDE by default
        reset();
        this.addcommon.setDisable(true);
        
        
        this.mainCB.setTooltip(new Tooltip("Select Main Option"));
        this.catCB.setTooltip(new Tooltip("Select Category"));
        this.subCB.setTooltip(new Tooltip("Select Sub-Category"));
        this.itemCB.setTooltip(new Tooltip("Select Item"));
        
        //CHEAT
//        this.store.addCategory("test@category1");
//        this.store.addCategory("test@category2");
//        this.store.addCategory("test@category3");
//           
//        Category c = this.store.getCategories().get(0);
//        this.store.addSub_Category("test@subcategory1",c);
//        this.store.addSub_Category("test@subcategory2",c);
//        this.store.addSub_Category("test@subcategory3",c);
//        
//        this.store.addItem(this.store.getCategories().get(0) , this.store.getCategories().get(0).getSubcategories().get(0));
//        this.store.getItems().get(0).setName("Test@Item1");
//        this.store.addItem(this.store.getCategories().get(0) , this.store.getCategories().get(0).getSubcategories().get(0));
//        this.store.getItems().get(1).setName("Test@Item2");
        //
        
        
        //ADDING DEFAULT OPTIONS - ADD - cat,sub,item
        this.mainCB.getItems().addAll("Modify Category","Modify Sub-Category","Modify Item");
        
        this.mainCB.getSelectionModel().selectedItemProperty().addListener(  (e,oldvalue,newvalue) -> {
            
            System.out.println(newvalue);
            reset();
            
            if(this.mainCB.getSelectionModel().getSelectedIndex() >= 0){
                this.type = this.mainCB.getSelectionModel().getSelectedIndex() ;
                
                switch(this.type){
                    case 0:
                        //modify cat
                        this.subCB.setVisible(false);
                        this.ap2.setVisible(false);
                        this.itemCB.setVisible(false);
                        this.catCB.setVisible(true);
                        this.catCB.getItems().clear();
                        
                        //populate categories
                        for (int i = 0; i < this.store.getCategories().size(); i++) {
                            this.catCB.getItems().add(this.store.getCategories().get(i).getName());
                        }
                        //till here
//                        this.addcommon.setDisable(false);
                        
                        
                        this.TF.setPromptText("Enter Category Name");
                        break;
                    case 1:
                        //modify sub
                        
                        this.ap2.setVisible(false);
                        this.itemCB.setVisible(false);
                        
                        //populate categories
                        this.catCB.getItems().clear();
                        for (int i = 0; i < this.store.getCategories().size(); i++) {
                            this.catCB.getItems().add(this.store.getCategories().get(i).getName());
                        }
                        
                        this.TF.setPromptText("Enter Sub-Category Name");
                        this.catCB.setVisible(true);
                        break;
                    case 2:
                        //modify item
                        
                        this.ap2.setVisible(true);
                        this.itemCB.setVisible(true);
                        this.priceTF.setText("");
                        this.quantityTF.setText("");
                        this.hTF.setText("");
                        this.kTF.setText("");
                        this.addcommon.setDisable(true);
                        
                        
                        //populate categories
                        this.catCB.getItems().clear();
                        for (int i = 0; i < this.store.getCategories().size(); i++) {
                            this.catCB.getItems().add(this.store.getCategories().get(i).getName());
                        }
                                                
                        this.TF.setPromptText("Enter Item Name");
                        this.catCB.setVisible(true);
//                        this.subCB.setVisible(true);
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
            int index;
            switch(this.type){
                case 0:
                    index = this.catCB.getSelectionModel().getSelectedIndex();
                    if(index>=0){
                        String s = this.store.getCategories().get(index).getName();
                        this.TF.setText(s);
                        this.addcommon.setDisable(false);
                    }
                    break;
                case 1:
                    index = this.catCB.getSelectionModel().getSelectedIndex();
                    if(index>=0){
                        //populate subcategories
                        this.subCB.getItems().clear();
                        for (int i = 0; i < this.store.getCategories().get(index).getSubcategories().size(); i++) {
                            this.subCB.getItems().add(this.store.getCategories().get(index).getSubcategories().get(i).getName());
                        }
                        this.subCB.setVisible(true);
                    }
                    
                    break;
                case 2:
                    index = this.catCB.getSelectionModel().getSelectedIndex();
                    if(index>=0){
                        //populate subcategories
                        this.subCB.getItems().clear();
                        for (int i = 0; i < this.store.getCategories().get(index).getSubcategories().size(); i++) {
                            this.subCB.getItems().add(this.store.getCategories().get(index).getSubcategories().get(i).getName());
                        }
                        this.subCB.setVisible(true);
                    }
                    break;
            }


//            int index = this.catCB.getSelectionModel().getSelectedIndex();
//            
//            if(index >= 0){
//                this.subCB.getItems().clear();
//                for (int i = 0; i < this.store.getCategories().get(index).getSubcategories().size(); i++) {
//                    this.subCB.getItems().add(this.store.getCategories().get(index).getSubcategories().get(i).getName());
//                }
//            }
            
        });
        
        this.subCB.getSelectionModel().selectedItemProperty().addListener(  (e,oldvalue,newvalue) -> {
            
            System.out.println(newvalue);
//            reset();
            int index,in;
            switch(this.type){
                case 0:
                    
                    break;
                case 1:
                    in = this.catCB.getSelectionModel().getSelectedIndex();
                    index = this.subCB.getSelectionModel().getSelectedIndex();
                    if(index>=0){
                        String s = this.store.getCategories().get(in).getSubcategories().get(index).getName();
                        this.TF.setText(s);
                        this.addcommon.setDisable(false);
                    }
                    
                    break;
                case 2:
                    in = this.catCB.getSelectionModel().getSelectedIndex();
                    index = this.subCB.getSelectionModel().getSelectedIndex();
                    if(index>=0){
                        
                        //populate items
                        this.itemCB.getItems().clear();
                        for (int i = 0; i < this.store.getCategories().get(in).getSubcategories().get(index).getItems().size(); i++) {
                            this.itemCB.getItems().add(this.store.getCategories().get(in).getSubcategories().get(index).getItems().get(i).getName());                                    
                        }
                        
                    }
                    break;
            }


        });
        
        
        
        this.itemCB.getSelectionModel().selectedItemProperty().addListener(  (e,oldvalue,newvalue) -> {
            
            System.out.println(newvalue);
//            reset();
            int index,in;
            switch(this.type){
                case 0:
                    
                    break;
                case 1:
                    
                    break;
                case 2:
                    in = this.catCB.getSelectionModel().getSelectedIndex();
                    index = this.subCB.getSelectionModel().getSelectedIndex();
                    int index1 = this.itemCB.getSelectionModel().getSelectedIndex();
                    if(index1>=0){
                        String s = this.store.getCategories().get(in).getSubcategories().get(index).getItems().get(index1).getName();
                        this.TF.setText(s);
                        this.addcommon.setDisable(false);
                        
                        
                        Item temp = this.store.getCategories().get(in).getSubcategories().get(index).getItems().get(index1);
                        this.priceTF.setText(temp.getPrice() + "");
                        this.quantityTF.setText(temp.getQuantity() + "");
                        this.hTF.setText(temp.getH() + "");
                        this.kTF.setText(temp.getK() + "");
                    }
                    break;
            }


        });
        
        
        
    }    
    
    public void add1(){
        System.out.println("type--> "+ type);
        String s = "";
        Category c = null;
        int ind,ind1;
        String old;
        switch(this.type){
            case 0:
                s = this.TF.getText().trim();
                ind = this.catCB.getSelectionModel().getSelectedIndex();
                old = this.store.getCategories().get(ind).getName();
                
                if(s.equalsIgnoreCase(old)){
                    System.out.println("SAME THING, NO CHANGE");
                }
                else{
                    for (int i = 0; i < this.store.getCategories().get(ind).getSubcategories().size(); i++) {
                        for (int j = 0; j < this.store.getCategories().get(ind).getSubcategories().get(i).getItems().size(); j++) {
                            String oldpath = this.store.getCategories().get(ind).getSubcategories().get(i).getItems().get(j).getPath();
                            String[] t = oldpath.split(">");
                            String newpath = s+">"+t[1];
                            Item temp = new Item();
                            temp.setName(this.store.getCategories().get(ind).getSubcategories().get(i).getItems().get(j).getName());
                            int in = this.store.getItems().indexOf(temp);
                            this.store.getCategories().get(ind).getSubcategories().get(i).getItems().get(j).setPath(newpath);
                            this.store.getItems().get(in).setPath(newpath);
                        }
                    }
                }
                //check here if updating cat,but not showing in items path
                //edit in all items
                //edit in main items
                
                this.store.getCategories().get(ind).setName(s);
                
                System.out.println("category name old -> " + old + "\nNew Name :- " + s);

                this.addcommon.setDisable(true);
                this.TF.setText("");
                reset();
                this.mainCB.getItems().clear();
                this.mainCB.getItems().addAll("Modify Category","Modify Sub-Category","Modify Item");
                this.catCB.getItems().clear();

                break;
            
            case 1:
                s = this.TF.getText().trim();
                ind = this.catCB.getSelectionModel().getSelectedIndex();
                ind1 = this.subCB.getSelectionModel().getSelectedIndex();
                old = this.store.getCategories().get(ind).getSubcategories().get(ind1).getName();
                
                if(s.equalsIgnoreCase(old)){
                    System.out.println("SAME THING, NO CHANGE");
                }
                else{
                    for (int j = 0; j < this.store.getCategories().get(ind).getSubcategories().get(ind1).getItems().size(); j++) {
                            String oldpath = this.store.getCategories().get(ind).getSubcategories().get(ind1).getItems().get(j).getPath();
                            String[] t = oldpath.split(">");
                            String newpath = s+">"+t[1];
                            Item temp = new Item();
                            temp.setName(this.store.getCategories().get(ind).getSubcategories().get(ind1).getItems().get(j).getName());
                            int in = this.store.getItems().indexOf(temp);
                            this.store.getCategories().get(ind).getSubcategories().get(ind1).getItems().get(j).setPath(newpath);
                            this.store.getItems().get(in).setPath(newpath);
                    }
                    
                }
                //check here if updating sub,but not showing in items path
                //edit in all items
                //edit in main items
                
                this.store.getCategories().get(ind).getSubcategories().get(ind1).setName(s);
                
                System.out.println("subcategory name old -> " + old + "\nNew Name :- " + s);

                this.addcommon.setDisable(true);
                this.TF.setText("");
                reset();
                
                this.mainCB.getItems().clear();
                this.mainCB.getItems().addAll("Modify Category","Modify Sub-Category","Modify Item");
                this.catCB.getItems().clear();
                this.subCB.getItems().clear();
                
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
        this.itemCB.setVisible(false);
        this.TF.setText("");
        this.TF.setPromptText("");
        
    }
    
    
    public void clickOnAdd(){
        System.out.println("inside click on add");
        
        int index = this.catCB.getSelectionModel().getSelectedIndex();
        int index1 = this.subCB.getSelectionModel().getSelectedIndex();
        int index2 = this.itemCB.getSelectionModel().getSelectedIndex();
        
                
        if(index2>=0){
            Item itemTemp = new Item();
            itemTemp.setName(this.store.getCategories().get(index).getSubcategories().get(index1).getItems().get(index2).getName());
            int in = this.store.getItems().indexOf(itemTemp);

            String name = this.TF.getText().trim();
            double price = Double.parseDouble(this.priceTF.getText().trim());
            int quantity = Integer.parseInt(this.quantityTF.getText().trim());
            int h = Integer.parseInt(this.hTF.getText().trim());
            int k = Integer.parseInt(this.kTF.getText().trim());

            //in cat>sub>item
            this.store.getCategories().get(index).getSubcategories().get(index1).getItems().get(index2).setName(name);
            this.store.getCategories().get(index).getSubcategories().get(index1).getItems().get(index2).setPrice(price);
            this.store.getCategories().get(index).getSubcategories().get(index1).getItems().get(index2).setQuantity(quantity);
            this.store.getCategories().get(index).getSubcategories().get(index1).getItems().get(index2).setH(h);
            this.store.getCategories().get(index).getSubcategories().get(index1).getItems().get(index2).setK(k);

            //in item
            this.store.getItems().get(in).setName(name);
            this.store.getItems().get(in).setPrice(price);
            this.store.getItems().get(in).setQuantity(quantity);
            this.store.getItems().get(in).setH(h);
            this.store.getItems().get(in).setK(k);
            
            this.ap2.setVisible(false);
            
            this.mainCB.getItems().clear();
            this.mainCB.getItems().addAll("Modify Category", "Modify Sub-Category", "Modify Item");
            this.catCB.getItems().clear();
            this.subCB.getItems().clear();
            this.itemCB.getItems().clear();
                
           
        }
        else{
            System.out.println("EITHER CATEGORY OR SUBCATEGORY OR ITEM ISNT CHOOSEN");
        }
        
//        System.out.println(itemTemp);
    
        //TILL 211218-1236PM
    }
    
    
}
