/*
 * Name    : - PORVIL
 * Roll No : - 2017304
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import superstore.Data.AllWarehouses;
import superstore.Data.Store;
import superstore.FXML.Store.AddController;
import superstore.FXML.Store.DeleteController;
import superstore.FXML.Store.ModifyController;

/**
 * FXML Controller class
 *
 * @author PD
 */
public class StorePageController implements Initializable {

    @FXML
    Button addB;
    @FXML
    Button delB;
    @FXML
    Button modB;
    @FXML
    Button dispB;
    @FXML
    Button logoutB;
    
    Store store;
    AllWarehouses warehouses;
    
    Scene scene;
    Stage stage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initialize(Store store,AllWarehouses warehouses) {
        this.store = store;
        this.warehouses = warehouses;
    }     
    
    public void add() throws IOException{
        System.out.println("inside gotoadd");
        String s="Store/add.fxml";
        
        System.out.println("S:- " + s);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(s));
        Parent root = (Pane)loader1.load();
        
        loader1.<AddController>getController().initialize(store);
        scene = new Scene(root, 600,600);
        stage = new Stage();
        
        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    public void delete() throws IOException{
        System.out.println("inside gotoadd");
        String s="Store/delete.fxml";
        
        System.out.println("S:- " + s);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(s));
        Parent root = (Pane)loader1.load();
        
        loader1.<DeleteController>getController().initialize(store);
        scene = new Scene(root, 600,600);
        stage = new Stage();
        
        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    public void modify() throws IOException{
        System.out.println("inside gotomodify");
        String s="Store/modify.fxml";
        
        System.out.println("S:- " + s);
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(s));
        Parent root = (Pane)loader1.load();
        
        loader1.<ModifyController>getController().initialize(store);
        scene = new Scene(root, 600,600);
        stage = new Stage();
        
        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    
    public void display() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WarehouseDataDisplay.fxml"));
        Parent root = (Pane) loader.load();
        
        loader.<WarehouseDataDisplayController>getController().initialize(this.store, 2);//FIX this using choichbox and complete it
        scene = new Scene(root, 600, 600);
        stage = new Stage();

        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    
    public void logout(ActionEvent event){
        if(stage != null)
            stage.close();
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    
}
