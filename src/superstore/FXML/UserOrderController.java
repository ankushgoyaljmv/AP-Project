/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstore.FXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import superstore.Data.Item;
import superstore.Data.Store;

/**
 * FXML Controller class
 *
 * @author KRISHNA
 */
public class UserOrderController implements Initializable {

    @FXML
    Button dispB;
    @FXML
    Button cancelB;
    @FXML
    Button orderB;
    @FXML
    Button selectB;
    @FXML
    ChoiceBox cCB;
    @FXML
    ChoiceBox sCB;
    @FXML
    TextField qTF;
    @FXML
    Label itemL;
    @FXML
    Label msg;
    @FXML
    TableView dataTV;
    
    private Store store;
    private Item item ;
    
    TableColumn<Item,String> nameColumn = new TableColumn<>("Name");
    TableColumn<Item,Double> priceColumn = new TableColumn<>("Price");
    TableColumn<Item,String> IDColumn = new TableColumn<>("UID");
    TableColumn<Item,Integer> quantityColumn = new TableColumn<>("Quantity");
        
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void initialize(Store store) {
        this.store = store;
        this.orderB.setDisable(true);
        
        this.cCB.setTooltip(new Tooltip("Select Category"));
        this.sCB.setTooltip(new Tooltip("Select Sub-Category"));
        
        this.cCB.getItems().clear();
        for (int i = 0; i < this.store.getCategories().size(); i++) {
            String s = this.store.getCategories().get(i).getName();
            this.cCB.getItems().add(s);
        }
        
        this.cCB.getSelectionModel().selectedItemProperty().addListener(  (e,oldvalue,newvalue) -> {
            
            System.out.println(newvalue);
//            reset();
            
            int index = this.cCB.getSelectionModel().getSelectedIndex();
            
            if(index >= 0){
                this.sCB.getItems().clear();
                for (int i = 0; i < this.store.getCategories().get(index).getSubcategories().size(); i++) {
                    this.sCB.getItems().add(this.store.getCategories().get(index).getSubcategories().get(i).getName());
                }
            }
            
        });
        
        this.setup();
    }    
 
    public void select(){
        if(dataTV.getSelectionModel().getSelectedIndex()>=0){
            item = (Item) dataTV.getSelectionModel().getSelectedItem();
            this.itemL.setText("Selected Item :- " + item.getName());
            this.orderB.setDisable(false);
        }
        else{
            item = null;
            this.itemL.setText("No Item Selected");
            this.orderB.setDisable(true);
        }
    }
    
    public void order(){
        String q = this.qTF.getText().trim();
        if(!q.isEmpty() && item!=null){
            this.orderB.setDisable(false);
            int qq = Integer.parseInt(q);
            //process order
            
            int i = this.store.getItems().indexOf(item);
            int avail = this.store.getItems().get(i).getQuantity();
            if(qq > avail){
                System.out.println("CANT ORDER MORE THAN WHATS AVAILABLE");
                this.msg.setText("CANT ORDER MORE THAN WHATS AVAILABLE");
            }
            else if(qq == avail){
                this.store.order(item);
                this.msg.setText("Item Ordered, THANK YOU");
                this.dataTV.getItems().clear();
                this.display();
            }
            else{
                this.store.getItems().get(i).setQuantity(avail-qq);                
                this.msg.setText("Item Ordered, THANK YOU");
                this.dataTV.getItems().clear();
                this.display();
            }
            //
            this.qTF.setText("");
            this.itemL.setText("No Item Selected");
            this.orderB.setDisable(true);
            
        }
        
    }
    
    public void clear(){
        this.sCB.getItems().clear();
        this.cCB.getItems().clear();
        this.dataTV.getItems().clear();
        this.itemL.setText("No Item Selected");
                    
        for (int i = 0; i < this.store.getCategories().size(); i++) {
            String s = this.store.getCategories().get(i).getName();
            this.cCB.getItems().add(s);
        }
    }
    
     public void back(ActionEvent event){
//        if(stage != null)
//            stage.close();
//        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    private void setup(){
        
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        IDColumn.setMinWidth(50);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("UID"));
        
        quantityColumn.setMinWidth(80);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
     
        dataTV.getColumns().addAll(IDColumn,nameColumn,quantityColumn,priceColumn);
        priceColumn.setSortType(TableColumn.SortType.ASCENDING);//check sorting stuff
        nameColumn.setSortType(TableColumn.SortType.ASCENDING);//check sorting stuff
        dataTV.getSortOrder().setAll(nameColumn,priceColumn,quantityColumn);
    }
     
    public void display(){
        
        int cindex = this.cCB.getSelectionModel().getSelectedIndex();
        int sindex = this.sCB.getSelectionModel().getSelectedIndex();
        int type = 0; //1-cat,2-cat>sub,3-all
        if(cindex>=0){
            if(sindex>=0){
                type = 2;
            }
            else{
                type = 1;
            }
        }
        else{
            type = 3;
        }
        
        dataTV.setItems(getItems(type));
        
    }
    
    public ObservableList<Item> getItems(int type){
        
        ObservableList<Item>  items = FXCollections.observableArrayList();
        int cindex = this.cCB.getSelectionModel().getSelectedIndex();
        int sindex = this.sCB.getSelectionModel().getSelectedIndex();
                
        switch(type){
            case 1:
                for (int i = 0; i < store.getCategories().get(cindex).getSubcategories().size(); i++) {
                    for (int j = 0; j < store.getCategories().get(cindex).getSubcategories().get(i).getItems().size(); j++) {
                        items.add(store.getCategories().get(cindex).getSubcategories().get(i).getItems().get(j));
                        System.out.println(store.getCategories().get(cindex).getSubcategories().get(i).getItems().get(j).getName());
                    }
                }
                break;
            case 2:
                for (int i = 0; i < store.getCategories().get(cindex).getSubcategories().get(sindex).getItems().size(); i++) {
                    items.add(store.getCategories().get(cindex).getSubcategories().get(sindex).getItems().get(i));
                    System.out.println(store.getCategories().get(cindex).getSubcategories().get(sindex).getItems().get(i).getName());
                }
                break;
            case 3:
                for (int i = 0; i < store.getItems().size(); i++) {
                    items.add(store.getItems().get(i));
                    System.out.println(store.getItems().get(i).getName());
                }        
                break;
            default:
                System.out.println("CHECK HERE PLZ INSIDE USERORDERCONTROLLER");
        }
        
        return items;
    }
}
