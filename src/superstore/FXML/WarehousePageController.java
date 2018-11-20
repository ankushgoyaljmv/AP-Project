/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstore.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import superstore.Data.Warehouse;
import superstore.FXML.Warehouse.AddController;
import superstore.FXML.Warehouse.DeleteController;
import superstore.FXML.Warehouse.ModifyController;

/**
 * FXML Controller class
 *
 * @author arsheya
 */
public class WarehousePageController implements Initializable {

    @FXML
    Button itemButton;
    @FXML
    Button addB;
    @FXML
    Button deleteB;
    @FXML
    Button modifyB;
    
    @FXML
    ChoiceBox otherwarehouse;
    
    @FXML
    Label warehouseNameLabel;

    private Warehouse warehouse;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initialize(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.warehouseNameLabel.setText("Warehouse Name :- " + this.warehouse.getName());
        
        
        
    }    
    
    public void itemButtonOnClick() throws IOException{
        System.out.println("item button clicked");
        nextScreen(1);
        
    }
    
    public void itemButtonOnClick1() throws IOException{
        System.out.println("item button1 clicked");
        nextScreen(2);
    }
    public void itemButtonOnClick2() throws IOException{
        System.out.println("item button2 clicked");
        nextScreen(3);
    }
    
    public void display(){
        System.out.println("\nDISPLAYING");
        this.warehouse.display();
        System.out.println("END OF DISPLAY\n\n");
    }
 
    public void nextScreen(int n) throws IOException{//n--> 1-item  2-subcategory  3-category
        String s="";
        switch(n){
            case 1:
                s = "AddItemPopUp.fxml";
                break;
            case 2:
                s = "AddSubCategoryPopUp.fxml";
                break;
//            case 3:
//                s = "AddCategoryPopUp.fxml";
//                break;
        }
        System.out.println("S:- " + s);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(s));
        Parent root = (Pane)loader1.load();
        switch(n){
            case 1:
                loader1.<AddItemPopUpController>getController().initialize(warehouse);
                break;
            case 2:
                loader1.<AddSubCategoryPopUpController>getController().initialize(warehouse);
                break;
//            case 3:
//                loader1.<AddCategoryPopUpController>getController().initialize(warehouse);
//                break;
        }
//        loader1.<AddItemPopUpController>getController().initialize(warehouse);
        Scene scene = new Scene(root, 600,600);
        Stage stage = new Stage();
        
        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    public void goToAdd() throws IOException{
        System.out.println("inside gotoadd");
        String s="Warehouse/add.fxml";
        
        System.out.println("S:- " + s);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(s));
        Parent root = (Pane)loader1.load();
        
        loader1.<AddController>getController().initialize(warehouse);
        Scene scene = new Scene(root, 600,600);
        Stage stage = new Stage();
        
        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    public void goToDelete() throws IOException{
        System.out.println("inside gotoadd");
        String s="Warehouse/delete.fxml";
        
        System.out.println("S:- " + s);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(s));
        Parent root = (Pane)loader1.load();
        
        loader1.<DeleteController>getController().initialize(warehouse);
        Scene scene = new Scene(root, 600,600);
        Stage stage = new Stage();
        
        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    
    public void goToModify() throws IOException{
        System.out.println("inside gotomodify");
        String s="Warehouse/modify.fxml";
        
        System.out.println("S:- " + s);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(s));
        Parent root = (Pane)loader1.load();
        
        loader1.<ModifyController>getController().initialize(warehouse);
        Scene scene = new Scene(root, 600,600);
        Stage stage = new Stage();
        
        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
}
