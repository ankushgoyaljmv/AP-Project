/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superstore.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import superstore.Data.AllWarehouses;
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
    Button logoutB;
    @FXML
    Button otherwarehousedata;
    @FXML
    ChoiceBox otherwarehouse;
    @FXML
    Label warehouseNameLabel;

    private Warehouse warehouse;
    private AllWarehouses warehouses;
    
    Scene scene;
    Stage stage;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initialize(Warehouse warehouse,AllWarehouses warehouses) {
        this.warehouse = warehouse;
        this.warehouses = warehouses;
        this.warehouseNameLabel.setText("Warehouse Name :- " + this.warehouse.getName());
        
        this.otherwarehouse.setTooltip(new Tooltip("Select Warehouse"));
        
        this.otherwarehouse.getItems().clear();
        for (int i = 0; i < this.warehouses.getAllwarehouses().size(); i++) {
            this.otherwarehouse.getItems().add(this.warehouses.getAllwarehouses().get(i).getName());
        }
        
    }    
       
    public void display(){
        System.out.println("\nDISPLAYING");
        this.warehouse.display();
        System.out.println("END OF DISPLAY\n\n");
    }
    
    public void guiDisplay() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WarehouseDataDisplay.fxml"));
        Parent root = (Pane) loader.load();
        loader.<WarehouseDataDisplayController>getController().initialize(this.warehouse, 1);//FIX this using choichbox and complete it
        scene = new Scene(root, 600, 600);
        stage = new Stage();

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
        scene = new Scene(root, 600,600);
        stage = new Stage();
        
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
        scene = new Scene(root, 600,600);
        stage = new Stage();
        
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
        scene = new Scene(root, 600,600);
        stage = new Stage();
        
        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    
    public void showotherWD() throws IOException{
        
        int index = this.otherwarehouse.getSelectionModel().getSelectedIndex();
        if(index>=0){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WarehouseDataDisplay.fxml"));
            Parent root = (Pane) loader.load();
            loader.<WarehouseDataDisplayController>getController().initialize(this.warehouses.getAllwarehouses().get(index), 1);//FIX this using choichbox and complete it
            scene = new Scene(root, 600, 600);
            stage = new Stage();

            stage.setTitle("SuperStore Management");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        else{
            System.out.println("SELECT SOME OTHER WAREHOUSE FIRST");
        }
    }
    
    public void logout(ActionEvent event){
        if(stage != null)
            stage.close();
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
}
