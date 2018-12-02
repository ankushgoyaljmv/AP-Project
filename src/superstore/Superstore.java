/*
 * Name    : - PORVIL
 * Roll No : - 2017304
 */
package superstore;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import superstore.FXML.StartupController;

/**
 *
 * @author PD
 */
public class Superstore extends Application {
   
    private User_Login_Database loginDatabase ;

    @Override
    public void init() throws Exception {
        super.init(); 
        this.readData();
    }

    @Override
    public void stop() throws Exception {
        super.stop(); 
        this.writeData();
    }
    
    private void readData(){
        loginDatabase = SaveData.readUserDatabase(loginDatabase);
        if(loginDatabase == null){
            loginDatabase = new User_Login_Database();
        }
    }
    
    private void writeData(){
        loginDatabase = SaveData.writeUserDatabase(loginDatabase);
    }
    
    public User_Login_Database getLoginDatabase() {
        return loginDatabase;
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
            
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Startup.fxml"));
        Parent root = (Pane)loader.load();
        loader.<StartupController>getController().initialize(loginDatabase,this.loginDatabase.getWarehouses(),this.loginDatabase.getStores(),this.loginDatabase.getSuperuser());
        Scene scene = new Scene(root, 600,600);
        primaryStage.setTitle("SuperStore Management");
        primaryStage.setScene(scene);
        primaryStage.show();
        
//        primaryStage.setOnCloseRequest(e -> {
//            try {
//                stop();
//            } catch (Exception ex) {
//                Logger.getLogger(Superstore.class.getName()).log(Level.SEVERE, null, ex);
//                System.out.println("COULDN'T STOP AND SAVE DATA CORRECTLY");
//            }
//        });
//        
//        primaryStage.setResizable(false);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
