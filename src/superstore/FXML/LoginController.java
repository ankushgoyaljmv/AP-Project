/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore.FXML;

import com.sun.javafx.scene.control.skin.TextFieldSkin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import superstore.Data.AllStores;
import superstore.Data.AllWarehouses;
import superstore.Data.Store;
import superstore.Data.Superuser;
import superstore.Data.Warehouse;
import superstore.User_Login_Database;

/**
 * FXML Controller class
 *
 * @author PD
 */
public class LoginController implements Initializable {

    @FXML
    private Label userType;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button backB;
    @FXML
    private ToggleButton showPassword;
    @FXML
    private Label isFine;

    /**
     *
     */
    public int type;//1-superuser 2-warehouseadmin 3-storeadmin 4-enduser
    private User_Login_Database loginDatabase ;
    private AllWarehouses warehouses ;
    private AllStores stores ;
    private Superuser superuser ;
    
    Scene scene;
    Stage stage;
        
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
     * @param loginDatabase
     * @param warehouses
     * @param stores
     * @param superuser
     * @param type
     */
    public void initialize(User_Login_Database loginDatabase, AllWarehouses warehouses, AllStores stores, Superuser superuser, int type) {
        this.loginDatabase = loginDatabase;
        this.warehouses = warehouses;
        this.stores = stores;
        this.superuser = superuser;
        this.type = type;

        //CHEATCODE
//        this.loginDatabase.getWarehouseDatabase().put("z", new Warehouse_Admin("z","z",new Warehouse(99,"test@cheatcode")));
        //
        
        
        switch (type) {
            case 1:
                userType.setText("SUPERUSER");
                break;
            case 2:
                userType.setText("WAREHOUSE ADMIN");
                break;
            case 3:
                userType.setText("STORE ADMIN");
                break;
            case 4:
                userType.setText("END USER");
                break;
        }

        //TO TOGGLE PASSWORD VIEW ON/OFF
        BooleanProperty showPasswordvalue = new SimpleBooleanProperty() {
            @Override
            protected void invalidated() {
                // force maskText to be called
                String txt = password.getText();
                password.setText(null);
                password.setText(txt);
            }
        };
        password.setSkin(new TextFieldSkin(password) {
            @Override
            protected String maskText(String txt) {
                if (showPasswordvalue.get()) {
                    return txt;
                }
                return super.maskText(txt);
            }
        });

        showPasswordvalue.bind(showPassword.selectedProperty());

    }

    /**
     *
     */
    public void reset() {
        System.out.println("RESETINg " + type);
        userName.setText("");
        password.setText("");
        isFine.setText("");
    }

    /**
     *
     * @return
     */
    public boolean check() {
        boolean isOk = false;
        String str = password.getText();
        String output = "";
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;

        for (char ch : str.toCharArray()) {
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }

        }

        if (numberFlag && capitalFlag && lowerCaseFlag && str.length() > 7) {
            output = "\u2713";
            isOk = true;
        } else {
            if (str.length() == 0) {
                output = "Password Can Not Be Empty";
            } else if (!numberFlag) {
                output = "Password Should Contain Atleast One Digit";
            } else if (!capitalFlag) {
                output = "Password Should Contain Atleast One Uppercase Character";
            } else if (!lowerCaseFlag) {
                output = "Password Should Contain Atleast One Lowercase Character";
            } else if (str.length() < 8) {
                output = "Password should be atleast 8 charcters long";
            }

        }

        isFine.setText(output);
        return isOk;
    }
    
    /**
     *
     */
    public void validateLogin() {
        
        boolean isOk = check();
        
        
        String output = "";
//        if(isOk){
            String userName = this.userName.getText();
            String password = this.password.getText();
            if(!isOk){
                if(userName.equals("zzz") && password.equals("zzzz")){
                    System.out.println("CHEATCODE APPLIED LOL");
                    
                }
            }
            
            switch(this.type){
                case 1:
                    if(userName.equals("root") && password.equals("Qazwsx12")){
//                    if(userName.equals("z") && password.equals("z")){
                        
                        output = "SUPERUSER LOGIN SuccessFull";
                        try {
                            goToSuperuser();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("UNABLE TO GO TO SUPERUSER PAGE");
                        }
                    }
                    else
                        output = "Incorrect Username/Password";
                    break;
                case 2:
                    if(loginDatabase.getWarehouseDatabase().containsKey(userName))
                        if(loginDatabase.getWarehouseDatabase().get(userName).getPassword().equals(password)){
                            output = "Warehouse Admin :- userName logged in.";
                            try {
                                goToWarehouse(loginDatabase.getWarehouseDatabase().get(userName).getWarehouse());
                            } catch (IOException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println("UNABLE TO GO TO WAREHOUSE PAGE");
                            }
                        }
                        else
                            output = "Incorrect Password";
                    else
                        output = "UserName Doesn't Exists";
                    break;
                case 3:
                    if(loginDatabase.getStoreDatabase().containsKey(userName))
                        if(loginDatabase.getStoreDatabase().get(userName).getPassword().equals(password))
                        {
                        output = "Store Admin :- userName logged in.";
                        try {
                                goToStore(loginDatabase.getStoreDatabase().get(userName).getStore());
                            } catch (IOException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println("UNABLE TO GO TO Store PAGE");
                            }
                        }
                        else
                            output = "Incorrect Password";
                    else
                        output = "UserName Doesn't Exists";
                    break;
            }
            
//        }
       
        System.out.println(output);
    }

    /**
     *
     * @throws IOException
     */
    public void goToSuperuser() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SuperUserPage.fxml"));
        Parent root = (Pane)loader.load();
        loader.<SuperUserPageController>getController().initialize(loginDatabase,warehouses,stores,superuser);
        scene = new Scene(root, 600,600);
        stage = new Stage();
        
        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    /**
     *
     * @param warehouse
     * @throws IOException
     */
    public void goToWarehouse(Warehouse warehouse) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WarehousePage.fxml"));
        Parent root = (Pane)loader.load();
//        Scene scene;
//        Stage stage;
        loader.<WarehousePageController>getController().initialize(warehouse,warehouses);
        scene = new Scene(root, 600,600);
        stage = new Stage();
        
        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    /**
     *
     * @param store
     * @throws IOException
     */
    public void goToStore(Store store) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StorePage.fxml"));
        Parent root = (Pane)loader.load();
//        Scene scene;
//        Stage stage;
        loader.<StorePageController>getController().initialize(store,warehouses);
        scene = new Scene(root, 600,600);
        stage = new Stage();
        
        stage.setTitle("SuperStore Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
 
    /**
     *
     * @param event
     */
    public void goBack(ActionEvent event){
        
        if(stage != null)
            stage.close();
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        //CHECK THIS IF DOESNT WORK
    }
    
    /**
     *
     * @param ke
     */
    public void enterpress(KeyEvent ke){
        if (ke.getCode().equals(KeyCode.ENTER))
            this.validateLogin();
    }
    
}
