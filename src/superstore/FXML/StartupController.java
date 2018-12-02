/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import superstore.Data.AllStores;
import superstore.Data.AllWarehouses;
import superstore.Data.Superuser;
import superstore.User_Login_Database;

/**
 * FXML Controller class
 *
 * @author PD
 */
public class StartupController implements Initializable {

    Scene scene;
    Stage stage;
        
    @FXML
    Button userName;
    @FXML
    Button warehouseAdmin;
    @FXML
    Button storeAdmin;
    @FXML
    Button endUser;
    @FXML
    Button exit;
    
    private User_Login_Database loginDatabase ;
    private AllWarehouses warehouses ;
    private AllStores stores ;
    private Superuser superuser ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initialize(User_Login_Database loginDatabase, AllWarehouses warehouses, AllStores stores, Superuser superuser) {
        this.loginDatabase = loginDatabase;
        this.warehouses = warehouses;
        this.stores = stores;
        this.superuser = superuser;
        System.out.println("STARTUP INTIZLIZED");
    }    
    
    public void superUserOnPress(){
        System.out.println("SUPERUSER");
        try {
            goToLoginPage(1);
        } catch (IOException ex) {
            Logger.getLogger(StartupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void warehouseAdminOnPress(){
        System.out.println("WAREHOUSEADMIN");
        try {
            goToLoginPage(2);
        } catch (IOException ex) {
            Logger.getLogger(StartupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void storeAdminOnPress(){
        System.out.println("STOREADMIN");
        try {
            goToLoginPage(3);
        } catch (IOException ex) {
            Logger.getLogger(StartupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void endUserOnPress(){
        System.out.println("ENDUSER");
//        try {
//            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("EndUser.fxml"));
//            Parent root = (Pane) loader1.load();
////        Parent root = FXMLLoader.load(Superstore.class.getResource("FXML/Login.fxml"));
//            loader1.<EndUserController>getController().initialize();
//            scene = new Scene(root, 600, 600);
//            stage = new Stage();
//
//            stage.setTitle("SuperStore Management");
//            stage.setScene(scene);
//            stage.setResizable(false);
//            stage.show();
//            
//        } catch (IOException ex) {
//            System.out.println("WTH");
//            Logger.getLogger(StartupController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        System.out.println("In startup:- complete this thing first" );

    }
    
    public void exit(){
        if(stage != null)
            stage.close();
        exit.getScene().getWindow().hide();
        System.out.println("STAGE CLOSED");
    }

    private void goToLoginPage(int type) throws IOException{
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = (Pane)loader1.load();
//        Parent root = FXMLLoader.load(Superstore.class.getResource("FXML/Login.fxml"));
        loader1.<LoginController>getController().initialize(loginDatabase,warehouses,stores,superuser,type);
        scene = new Scene(root, 600,600);
        stage = new Stage();
        
        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }
    
}
