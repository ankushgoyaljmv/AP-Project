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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    TextField partial;
    @FXML
    Label itemL;
    @FXML
    Label msg;
    @FXML
    TableView dataTV;
    
    private Store store;
    private Item item ;
    ObservableList<Item> data = FXCollections.observableArrayList();

    
    TableColumn<Item,String> nameColumn = new TableColumn<>("Name");
    TableColumn<Item,Double> priceColumn = new TableColumn<>("Price");
    TableColumn<Item,String> IDColumn = new TableColumn<>("UID");
    TableColumn<Item,Integer> quantityColumn = new TableColumn<>("Quantity");
    
    FilteredList<Item> filteredData ;//= new FilteredList<>(data, p -> true);

        
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
        
        filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        partial.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Item -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(Item.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.

                } else if (String.valueOf(Item.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } 

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Item> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(dataTV.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        dataTV.setItems(sortedData);
        
        
        
    }    
 
    /**
     *
     */
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
    
    /**
     *
     */
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
                this.data.clear();
                this.display();
            }
            else{
                this.store.getItems().get(i).setQuantity(avail-qq);                
                this.msg.setText("Item Ordered, THANK YOU");
                this.data.clear();
                this.display();
            }
            //
            this.qTF.setText("");
            this.itemL.setText("No Item Selected");
            this.orderB.setDisable(true);
            
        }
        
    }
    
    /**
     *
     */
    public void clear(){
        this.sCB.getItems().clear();
        this.cCB.getItems().clear();
        this.data.clear();
        this.itemL.setText("No Item Selected");
                    
        for (int i = 0; i < this.store.getCategories().size(); i++) {
            String s = this.store.getCategories().get(i).getName();
            this.cCB.getItems().add(s);
        }
    }
    
    /**
     *
     * @param event
     */
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
     
    /**
     *
     */
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
        
        filteredData = new FilteredList<>(data, p -> true);
        SortedList<Item> sortedData = new SortedList<>(filteredData);
        
        dataTV.setItems(sortedData);
        
        
        
    }
    
    /**
     *
     * @param type
     * @return
     */
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
        
        this.data = items;
        return items;
    }
}
